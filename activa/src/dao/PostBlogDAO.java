package dao;

import interfaces.PostBlogI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Blog;
import modelo.PostBlog;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class PostBlogDAO implements PostBlogI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (PostBlog postBlog) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into postblog (pk_postBlog,ds_post,dt_post,fk_blog,fk_membro) values (?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "postblog", "pk_postBlog");
				
				stmt.setLong(1,pk);
				stmt.setString(2,postBlog.getDescricao());
				stmt.setString(3,postBlog.getData());
				stmt.setLong(4, postBlog.getBlog().getPkServico());
				stmt.setLong(5, postBlog.getMembro().getPkUsuario());		
				
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
	
	public int alterar(PostBlog postBlog) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update postblog set ds_post=?,dt_post=?,fk_blog=?,fk_membro=? where pk_postBlog=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,postBlog.getDescricao());
			stmt.setString(2,postBlog.getData());
			stmt.setLong(3, postBlog.getBlog().getPkServico());
			stmt.setLong(4, postBlog.getMembro().getPkUsuario());	
			stmt.setLong(5, postBlog.getPkPostBlog());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (PostBlog postBlog) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from postblog where pk_postBlog = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, postBlog.getPkPostBlog());
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
	
	public Collection<PostBlog> consultarTodos() throws CadastroException{
		Collection<PostBlog> col = new ArrayList<PostBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d, postblog e" +
				             " where c.fk_ambiente=b.pk_ambiente and a.pk_blog=a.pk_servico " +
				             " and c.fk_membro=d.pk_usuario and a.pk_blog=e.fk_blog";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostBlog postBlog = new PostBlog();
					carregar(rs, postBlog);
					col.add(postBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<PostBlog> consultarPorBlog(Blog blog) throws CadastroException{
		Collection<PostBlog> col = new ArrayList<PostBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d, postblog e" +
	             			 " where a.pk_blog=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=a.pk_servico " +
	             			 " and c.fk_membro=d.pk_usuario and a.pk_blog=e.fk_blog";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, blog.getPkServico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					PostBlog postBlog = new PostBlog();
					carregar(rs, postBlog);
					col.add(postBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public PostBlog consultar(PostBlog postBlog) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from blog a,ambiente b,servico c,usuario d, postblog e" +
                         " where f.pk_postBlog=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=a.pk_servico " +
                         " and c.fk_membro=d.pk_usuario and a.pk_blog=e.fk_blog";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, postBlog.getPkPostBlog());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, postBlog);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return postBlog;
	}
	
	private void carregar(ResultSet rs ,PostBlog postBlog) throws SQLException{
		//carrega ambiente
		postBlog.getBlog().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		postBlog.getBlog().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		postBlog.getBlog().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		postBlog.getBlog().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		postBlog.getBlog().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		postBlog.getBlog().setPkServico(rs.getLong("c.pk_servico"));
		postBlog.getBlog().setNome(rs.getString("c.no_servico"));
		postBlog.getBlog().setData(rs.getString("c.dt_servico"));
		postBlog.getBlog().setDescricao(rs.getString("c.ds_servico"));
		postBlog.getBlog().setImagem(rs.getString("c.no_imagem"));
		postBlog.getBlog().setStatus(rs.getString("c.st_servico"));
		postBlog.getBlog().setAutomatico(rs.getString("c.fl_automatico"));
		postBlog.getBlog().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		postBlog.getBlog().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		postBlog.getBlog().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		postBlog.getBlog().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		postBlog.getBlog().getMembro().setNome(rs.getString("d.no_usuario"));
		postBlog.getBlog().getMembro().setApelido(rs.getString("d.no_apelido"));
		postBlog.getBlog().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		postBlog.getBlog().getMembro().setEmail(rs.getString("d.no_email"));
		postBlog.getBlog().getMembro().setSenha(rs.getString("d.pw_senha"));
		postBlog.getBlog().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		postBlog.getBlog().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		postBlog.getBlog().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		postBlog.getBlog().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		postBlog.getBlog().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		postBlog.getBlog().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		postBlog.getBlog().getMembro().setCep(rs.getString("d.nu_cep"));
		postBlog.getBlog().getMembro().setStatus(rs.getString("d.st_usuario"));
		postBlog.getBlog().getMembro().setImagem(rs.getString("d.img_usuario"));
		
		//carrega postBlog
		postBlog.setPkPostBlog(rs.getLong("e.pk_postBlog"));
		postBlog.setDescricao(rs.getString("e.ds_postBlog"));
		postBlog.setData(rs.getString("e.dt_postBlog"));
	}
}
