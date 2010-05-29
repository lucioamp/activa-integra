package dao;

import interfaces.ForumI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.CategoriaForum;
import modelo.Forum;
import util.CadastroException;
import util.ConnectionFactory;

public class ForumDAO implements ForumI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Forum forum) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into forum (pk_forum,fk_catForum,fk_tarefa) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = (int) forum.getPkServico();
				
				stmt.setLong(1, pk);
				stmt.setLong(2,forum.getCategoriaForum().getPkCatForum());
				stmt.setLong(3, forum.getTarefa().getPkTarefa());			
				
				System.out.println(stmt);
				stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return pk;
	}
	
	public int alterar(Forum forum) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update forum set fk_catForum=?, fk_tarefa=? where pk_forum=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1,forum.getCategoriaForum().getPkCatForum());
			stmt.setLong(2,forum.getTarefa().getPkTarefa());	
			stmt.setLong(3, forum.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Forum forum) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from forum where pk_forum = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, forum.getPkServico());
				r = stmt.executeUpdate();
			
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}
			finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
		
		return r;
	}
	
	public Collection<Forum> consultarTodosForuns() throws CadastroException{
		Collection<Forum> col = new ArrayList<Forum>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, usuario h"+
							" where g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and g.fk_membro=h.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Forum forum = new Forum();
					carregar(rs, forum);
					col.add(forum);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Forum> consultarPorCategoria(CategoriaForum categoriaForum) throws CadastroException{
		Collection<Forum> col = new ArrayList<Forum>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, usuario h"+
							" where b.pk_catForum=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and g.fk_membro=h.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, categoriaForum.getPkCatForum());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Forum forum = new Forum();
					carregar(rs, forum);
					col.add(forum);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Forum consultar(Forum forum) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, usuario h"+
					" where a.pk_forum=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
					" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and g.fk_membro=h.pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, forum.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, forum);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return forum;
	}
	
	private void carregar(ResultSet rs ,Forum forum) throws SQLException{
		//carrega meta
		forum.getCategoriaForum().getEtapa().getMeta().setPkMeta(rs.getLong("d.pk_meta"));
		forum.getCategoriaForum().getEtapa().getMeta().setDescricao(rs.getString("d.ds_meta"));
		forum.getCategoriaForum().getEtapa().getMeta().setDuracao(rs.getString("d.ds_duracao"));
		
		//carrega etapa
		forum.getCategoriaForum().getEtapa().setPkEtapa(rs.getLong("c.pk_etapa"));
		forum.getCategoriaForum().getEtapa().setNome(rs.getString("c.no_etapa"));
		forum.getCategoriaForum().getEtapa().setDescricao(rs.getString("c.ds_etapa"));
		forum.getCategoriaForum().getEtapa().setData(rs.getString("c.dt_etapa"));
		
		//carrega tarefa
		forum.getTarefa().setPkTarefa(rs.getLong("f.pk_tarefa"));
		forum.getTarefa().setNome(rs.getString("f.no_tarefa"));
		forum.getTarefa().setDescricao(rs.getString("f.ds_tarefa"));
		forum.getTarefa().setData(rs.getString("f.dt_tarefa"));
		
		//carrega categoriaForum		
		forum.getCategoriaForum().setPkCatForum(rs.getLong("b.pk_catForum"));
		forum.getCategoriaForum().setNome(rs.getString("b.no_catForum"));
		forum.getCategoriaForum().setDescricao(rs.getString("b.ds_catForum"));
		
		//carrega membro
		forum.getMembro().setPkUsuario(rs.getLong("h.pk_usuario"));
		forum.getMembro().setCpf(rs.getLong("h.nu_cpf"));
		forum.getMembro().setNome(rs.getString("h.no_usuario"));
		forum.getMembro().setApelido(rs.getString("h.no_apelido"));
		forum.getMembro().setDataNasc(rs.getString("h.dt_nasc"));
		forum.getMembro().setEmail(rs.getString("h.no_email"));
		forum.getMembro().setSenha(rs.getString("h.pw_senha"));
		forum.getMembro().setPerguntaChave(rs.getString("h.ds_perguntaChave"));
		forum.getMembro().setRespostaChave(rs.getString("h.ds_respostaChave"));
		forum.getMembro().setTipoLogradouro(rs.getString("h.tp_logradouro"));
		forum.getMembro().setLogradouro(rs.getString("h.no_logradouro"));
		forum.getMembro().setNumero(rs.getLong("h.nu_logradouro"));
		forum.getMembro().setComplemento(rs.getString("h.ds_complemento"));
		forum.getMembro().setCep(rs.getString("h.nu_cep"));
		forum.getMembro().setStatus(rs.getString("h.st_usuario"));
		forum.getMembro().setImagem(rs.getString("h.img_usuario"));
		
		//carrega ambiente
		forum.getAmbiente().setPkAmbiente(rs.getLong("e.pk_ambiente"));
		forum.getAmbiente().setNome(rs.getString("e.no_ambiente"));
		forum.getAmbiente().setData(rs.getString("e.dt_ambiente"));
		forum.getAmbiente().setDescricao(rs.getString("e.ds_ambiente"));
		forum.getAmbiente().setImagem(rs.getString("e.no_imagem"));
					
		//carrega servico
		forum.setPkServico(rs.getLong("g.pk_servico"));
		forum.setNome(rs.getString("g.no_servico"));
		forum.setData(rs.getString("g.dt_servico"));
		forum.setDescricao(rs.getString("g.ds_servico"));
		forum.setImagem(rs.getString("g.no_imagem"));
		forum.setStatus(rs.getString("g.st_servico"));
		forum.setAutomatico(rs.getString("g.fl_automatico"));
	}
}
