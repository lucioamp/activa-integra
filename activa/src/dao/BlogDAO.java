package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Ambiente;
import modelo.Blog;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.BlogI;

public class BlogDAO implements BlogI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Blog blog) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into blog (pk_blog) values (?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = (int) blog.getPkServico();
				
				stmt.setLong(1,pk);
								
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
	
	public int alterar(Blog blog) throws CadastroException{
		int r=0;
		/*try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update blog set fk_catBlog=? where pk_blog=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1,blog.getCategoriaBlog().getPkCatBlog());
			stmt.setLong(2, blog.getPkServico());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}*/
	
		return r;
	}
	
	public int excluir (Blog blog) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from blog where pk_blog = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, blog.getPkServico());
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
	
	public Collection<Blog> consultarTodosBlogs() throws CadastroException{
		Collection<Blog> col = new ArrayList<Blog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_blog=c.pk_servico and " +
						" c.fk_membro=d.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Blog blog = new Blog();
					carregar(rs, blog);
					col.add(blog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<Blog> consultarBlogsPorAmbiente(Ambiente ambiente) throws CadastroException{
		Collection<Blog> col = new ArrayList<Blog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d" +
						" where b.pk_ambiente=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=c.pk_servico " +
						" and c.fk_membro=d.pk_usuario";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, ambiente.getPkAmbiente());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Blog blog = new Blog();
					carregar(rs, blog);
					col.add(blog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public Blog consultar(Blog blog) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from blog a,ambiente b,servico c,usuario d" +
					" where a.pk_blog=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=c.pk_servico " +
					" and c.fk_membro=d.pk_usuario";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, blog.getPkServico());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, blog);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return blog;
	}
	
	private void carregar(ResultSet rs ,Blog blog) throws SQLException{
		//carrega ambiente
		blog.getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		blog.getAmbiente().setNome(rs.getString("b.no_ambiente"));
		blog.getAmbiente().setData(rs.getString("b.dt_ambiente"));
		blog.getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		blog.getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		blog.setPkServico(rs.getLong("c.pk_servico"));
		blog.setNome(rs.getString("c.no_servico"));
		blog.setData(rs.getString("c.dt_servico"));
		blog.setDescricao(rs.getString("c.ds_servico"));
		blog.setImagem(rs.getString("c.no_imagem"));
		blog.setStatus(rs.getString("c.st_servico"));
		blog.setAutomatico(rs.getString("c.fl_automatico"));
		blog.getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		blog.getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		blog.getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		blog.getMembro().setCpf(rs.getLong("d.nu_cpf"));
		blog.getMembro().setNome(rs.getString("d.no_usuario"));
		blog.getMembro().setApelido(rs.getString("d.no_apelido"));
		blog.getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		blog.getMembro().setEmail(rs.getString("d.no_email"));
		blog.getMembro().setSenha(rs.getString("d.pw_senha"));
		blog.getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		blog.getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		blog.getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		blog.getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		blog.getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		blog.getMembro().setComplemento(rs.getString("d.ds_complemento"));
		blog.getMembro().setCep(rs.getString("d.nu_cep"));
		blog.getMembro().setStatus(rs.getString("d.st_usuario"));
		blog.getMembro().setImagem(rs.getString("d.img_usuario"));
	
	}
}
