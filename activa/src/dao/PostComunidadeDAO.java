package dao;

import interfaces.PostComunidadeI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.PostComunidade;
import modelo.Comunidade;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class PostComunidadeDAO implements PostComunidadeI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (PostComunidade postComunidade) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into postcomunidade (pk_postComunidade,ds_post,dt_post,fk_comunidade,fk_membro) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "postcomunidade", "pk_postComunidade");
				
				stmt.setLong(1,pk);
				stmt.setString(2,postComunidade.getDescricao());
				stmt.setString(3,postComunidade.getData());
				stmt.setLong(4, postComunidade.getComunidade().getPkServico());
				stmt.setLong(5, postComunidade.getMembro().getPkUsuario());		
				
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
	
	public int alterar(PostComunidade postComunidade) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update postcomunidade set ds_post=?,dt_post=?,fk_comunidade=?,fk_membro=? where pk_postComunidade=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,postComunidade.getDescricao());
			stmt.setString(2,postComunidade.getData());
			stmt.setLong(3, postComunidade.getComunidade().getPkServico());
			stmt.setLong(4, postComunidade.getMembro().getPkUsuario());	
			stmt.setLong(5, postComunidade.getPkPostComunidade());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (PostComunidade postComunidade) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from postcomunidade where pk_postComunidade = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, postComunidade.getPkPostComunidade());
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
	
	public Collection<PostComunidade> consultarTodos() throws CadastroException{
		Collection<PostComunidade> col = new ArrayList<PostComunidade>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e, postcomunidade f" +
				             " where c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=a.pk_servico " +
				             " and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade and a.pk_comunidade=f.fk_comunidade";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostComunidade postComunidade = new PostComunidade();
					carregar(rs, postComunidade);
					col.add(postComunidade);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<PostComunidade> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		Collection<PostComunidade> col = new ArrayList<PostComunidade>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e, postcomunidade f" +
	             			 " where a.pk_comunidade=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=a.pk_servico " +
	             			 " and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade and a.pk_comunidade=f.fk_comunidade";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, comunidade.getPkServico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostComunidade postComunidade = new PostComunidade();
					carregar(rs, postComunidade);
					col.add(postComunidade);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public PostComunidade consultar(PostComunidade postComunidade) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from comunidade a,ambiente b,servico c,usuario d, categoriacomunidade e, postcomunidade f" +
                         " where f.pk_postComunidade=? and c.fk_ambiente=b.pk_ambiente and a.pk_comunidade=a.pk_servico " +
                         " and c.fk_membro=d.pk_usuario and a.fk_catComunidade=e.pk_catComunidade and a.pk_comunidade=f.fk_comunidade";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, postComunidade.getPkPostComunidade());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, postComunidade);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return postComunidade;
	}
	
	private void carregar(ResultSet rs ,PostComunidade postComunidade) throws SQLException{
		//carrega ambiente
		postComunidade.getComunidade().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		postComunidade.getComunidade().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		postComunidade.getComunidade().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		postComunidade.getComunidade().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		postComunidade.getComunidade().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		postComunidade.getComunidade().setPkServico(rs.getLong("c.pk_servico"));
		postComunidade.getComunidade().setNome(rs.getString("c.no_servico"));
		postComunidade.getComunidade().setData(rs.getString("c.dt_servico"));
		postComunidade.getComunidade().setDescricao(rs.getString("c.ds_servico"));
		postComunidade.getComunidade().setImagem(rs.getString("c.no_imagem"));
		postComunidade.getComunidade().setStatus(rs.getString("c.st_servico"));
		postComunidade.getComunidade().setAutomatico(rs.getString("c.fl_automatico"));
		postComunidade.getComunidade().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		postComunidade.getComunidade().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		postComunidade.getComunidade().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		postComunidade.getComunidade().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		postComunidade.getComunidade().getMembro().setNome(rs.getString("d.no_usuario"));
		postComunidade.getComunidade().getMembro().setApelido(rs.getString("d.no_apelido"));
		postComunidade.getComunidade().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		postComunidade.getComunidade().getMembro().setEmail(rs.getString("d.no_email"));
		postComunidade.getComunidade().getMembro().setSenha(rs.getString("d.pw_senha"));
		postComunidade.getComunidade().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		postComunidade.getComunidade().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		postComunidade.getComunidade().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		postComunidade.getComunidade().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		postComunidade.getComunidade().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		postComunidade.getComunidade().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		postComunidade.getComunidade().getMembro().setCep(rs.getString("d.nu_cep"));
		postComunidade.getComunidade().getMembro().setStatus(rs.getString("d.st_usuario"));
		postComunidade.getComunidade().getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega categoriaComunidade
		postComunidade.getComunidade().getCategoriaComunidade().setPkCatComunidade(rs.getLong("e.pk_catComunidade"));
		postComunidade.getComunidade().getCategoriaComunidade().setNome(rs.getString("e.no_catComunidade"));
		postComunidade.getComunidade().getCategoriaComunidade().setDescricao(rs.getString("e.ds_catComunidade"));
		
		//carrega postComunidade
		postComunidade.setPkPostComunidade(rs.getLong("f.pk_postComunidade"));
		postComunidade.setDescricao(rs.getString("f.ds_postComunidade"));
		postComunidade.setData(rs.getString("f.dt_postComunidade"));
	}
}
