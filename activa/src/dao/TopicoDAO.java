package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Topico;
import modelo.Forum;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;
import interfaces.TopicoI;

public class TopicoDAO implements TopicoI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Topico topico) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into topico (pk_topico,ds_topico,dt_topico,fk_forum,fk_membro) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "topico", "pk_topico");
				
				stmt.setLong(1,pk);
				stmt.setString(2,topico.getDescricao());
				stmt.setString(3,topico.getData());
				stmt.setLong(4, topico.getForum().getPkServico());			
				stmt.setLong(5, topico.getMembro().getPkUsuario());	
				
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
	
	public int alterar(Topico topico) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update topico set ds_topico=?,dt_topico=?,fk_forum=?, fk_membro=? where pk_topico=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,topico.getDescricao());
			stmt.setString(2,topico.getData());
			stmt.setLong(3, topico.getForum().getPkServico());
			stmt.setLong(4, topico.getMembro().getPkUsuario());	
			stmt.setLong(5, topico.getPkTopico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Topico topico) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from topico where pk_topico = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, topico.getPkTopico());
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
	
	public Collection<Topico> consultarTodos() throws CadastroException{
		Collection<Topico> col = new ArrayList<Topico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, usuario i"+
							" where g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and a.pk_forum=h.fk_forum and h.fk_membro=i.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Topico topico = new Topico();
					carregar(rs, topico);
					col.add(topico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Topico> consultarPorForum(Forum forum) throws CadastroException{
		Collection<Topico> col = new ArrayList<Topico>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, usuario i"+
							" where a.pk_forum=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
							" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and a.pk_forum=h.fk_forum and h.fk_membro=i.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, forum.getPkServico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Topico topico = new Topico();
					carregar(rs, topico);
					col.add(topico);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Topico consultar(Topico topico) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from forum a,categoriaforum b,etapa c,meta d,ambiente e, tarefa f, servico g, topico h, usuario i"+
					" where h.pk_topico=? and g.fk_ambiente=e.pk_ambiente and c.fk_meta=d.pk_meta and b.fk_etapa=c.pk_etapa"+
					" and a.fk_catForum=b.pk_catForum and f.pk_tarefa=a.fk_tarefa and a.pk_forum=g.pk_servico and a.pk_forum=h.fk_forum and h.fk_membro=i.pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, topico.getPkTopico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, topico);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return topico;
	}
	
	private void carregar(ResultSet rs ,Topico topico) throws SQLException{
				
		//carrega meta
		topico.getForum().getCategoriaForum().getEtapa().getMeta().setPkMeta(rs.getLong("d.pk_meta"));
		topico.getForum().getCategoriaForum().getEtapa().getMeta().setDescricao(rs.getString("d.ds_meta"));
		topico.getForum().getCategoriaForum().getEtapa().getMeta().setDuracao(rs.getString("d.ds_duracao"));
		
		//carrega etapa
		topico.getForum().getCategoriaForum().getEtapa().setPkEtapa(rs.getLong("c.pk_etapa"));
		topico.getForum().getCategoriaForum().getEtapa().setNome(rs.getString("c.no_etapa"));
		topico.getForum().getCategoriaForum().getEtapa().setDescricao(rs.getString("c.ds_etapa"));
		topico.getForum().getCategoriaForum().getEtapa().setData(rs.getString("c.dt_etapa"));
		
		//carrega tarefa
		topico.getForum().getTarefa().setPkTarefa(rs.getLong("f.pk_tarefa"));
		topico.getForum().getTarefa().setNome(rs.getString("f.no_tarefa"));
		topico.getForum().getTarefa().setDescricao(rs.getString("f.ds_tarefa"));
		topico.getForum().getTarefa().setData(rs.getString("f.dt_tarefa"));
		
		//carrega categoriaForum		
		topico.getForum().getCategoriaForum().setPkCatForum(rs.getLong("b.pk_catForum"));
		topico.getForum().getCategoriaForum().setNome(rs.getString("b.no_catForum"));
		topico.getForum().getCategoriaForum().setDescricao(rs.getString("b.ds_catForum"));
		
		//carrega servico
		topico.getForum().setPkServico(rs.getLong("g.pk_servico"));
		topico.getForum().setNome(rs.getString("g.no_servico"));
		topico.getForum().setData(rs.getString("g.dt_servico"));
		topico.getForum().setDescricao(rs.getString("g.ds_servico"));
		topico.getForum().setImagem(rs.getString("g.no_imagem"));
		topico.getForum().setStatus(rs.getString("g.st_servico"));
		topico.getForum().setAutomatico(rs.getString("g.fl_automatico"));
		topico.getForum().getMembro().setPkUsuario(rs.getLong("g.fk_membro"));
		topico.getForum().getAmbiente().setPkAmbiente(rs.getLong("g.fk_ambiente"));
		
		//carrega membro
		topico.getMembro().setPkUsuario(rs.getLong("i.pk_usuario"));
		topico.getMembro().setCpf(rs.getLong("i.nu_cpf"));
		topico.getMembro().setNome(rs.getString("i.no_usuario"));
		topico.getMembro().setApelido(rs.getString("i.no_apelido"));
		topico.getMembro().setDataNasc(rs.getString("i.dt_nasc"));
		topico.getMembro().setEmail(rs.getString("i.no_email"));
		topico.getMembro().setSenha(rs.getString("i.pw_senha"));
		topico.getMembro().setPerguntaChave(rs.getString("i.ds_perguntaChave"));
		topico.getMembro().setRespostaChave(rs.getString("i.ds_respostaChave"));
		topico.getMembro().setTipoLogradouro(rs.getString("i.tp_logradouro"));
		topico.getMembro().setLogradouro(rs.getString("i.no_logradouro"));
		topico.getMembro().setNumero(rs.getLong("i.nu_logradouro"));
		topico.getMembro().setComplemento(rs.getString("i.ds_complemento"));
		topico.getMembro().setCep(rs.getString("i.nu_cep"));
		topico.getMembro().setStatus(rs.getString("i.st_usuario"));
		topico.getMembro().setImagem(rs.getString("i.img_usuario"));
		
		//carrega ambiente
		topico.getForum().getAmbiente().setPkAmbiente(rs.getLong("e.pk_ambiente"));
		topico.getForum().getAmbiente().setNome(rs.getString("e.no_ambiente"));
		topico.getForum().getAmbiente().setData(rs.getString("e.dt_ambiente"));
		topico.getForum().getAmbiente().setDescricao(rs.getString("e.ds_ambiente"));
		topico.getForum().getAmbiente().setImagem(rs.getString("e.no_imagem"));
		
		//carrega topico
		topico.setPkTopico(rs.getLong("h.pk_topico"));
		topico.setDescricao(rs.getString("h.ds_topico"));
		topico.setData(rs.getString("h.dt_topico"));
	}
}
