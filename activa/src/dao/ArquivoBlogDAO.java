package dao;

import interfaces.ArquivoBlogI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.ArquivoBlog;
import modelo.Blog;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class ArquivoBlogDAO implements ArquivoBlogI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (ArquivoBlog arquivoBlog) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into arquivoblog (pk_arquivoBlog,no_arquivoBlog,fk_blog) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "arquivoblog", "pk_arquivoBlog");
				
				stmt.setLong(1,pk);
				stmt.setString(2,arquivoBlog.getNome());
				stmt.setLong(3,arquivoBlog.getBlog().getPkServico());	
								
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
	
	public int alterar(ArquivoBlog arquivoBlog) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update arquivoblog set no_arquivoBlog=?,fk_blog=? where pk_arquivoBlog=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,arquivoBlog.getNome());
			stmt.setLong(2,arquivoBlog.getBlog().getPkServico());	
			stmt.setLong(3, arquivoBlog.getPkArquivoBlog());
						
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (ArquivoBlog arquivoBlog) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from arquivoblog where pk_arquivoBlog = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, arquivoBlog.getPkArquivoBlog());
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
	
	public Collection<ArquivoBlog> consultarTodos() throws CadastroException{
		Collection<ArquivoBlog> col = new ArrayList<ArquivoBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d, arquivoblog e" +
						" where c.fk_ambiente=b.pk_ambiente and a.pk_blog=c.pk_servico and " +
						" c.fk_membro=d.pk_usuario and e.fk_blog=a.pk_blog";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ArquivoBlog arquivoBlog = new ArquivoBlog();
					carregar(rs, arquivoBlog);
					col.add(arquivoBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Collection<ArquivoBlog> consultarPorBlog(Blog blog) throws CadastroException{
		Collection<ArquivoBlog> col = new ArrayList<ArquivoBlog>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from blog a,ambiente b,servico c,usuario d, arquivoblog e" +
						" where a.pk_blog=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=c.pk_servico " +
						" and c.fk_membro=d.pk_usuario and e.fk_blog=a.pk_blog";
			
				stmt = conn.prepareStatement(sql);
				
				stmt.setLong(1, blog.getPkServico());
				
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					ArquivoBlog arquivoBlog = new ArquivoBlog();
					carregar(rs, arquivoBlog);
					col.add(arquivoBlog);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}	
	
	public ArquivoBlog consultar(ArquivoBlog arquivoBlog) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from blog a,ambiente b,servico c,usuario d, arquivoblog e" +
					" where e.pk_arquivoBlog=? and c.fk_ambiente=b.pk_ambiente and a.pk_blog=a.pk_servico " +
					" and c.fk_membro=d.pk_usuario and e.fk_blog=a.pk_blog";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, arquivoBlog.getPkArquivoBlog());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, arquivoBlog);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return arquivoBlog;
	}
	
	private void carregar(ResultSet rs ,ArquivoBlog arquivoBlog) throws SQLException{
		//carrega ambiente
		arquivoBlog.getBlog().getAmbiente().setPkAmbiente(rs.getLong("b.pk_ambiente"));
		arquivoBlog.getBlog().getAmbiente().setNome(rs.getString("b.no_ambiente"));
		arquivoBlog.getBlog().getAmbiente().setData(rs.getString("b.dt_ambiente"));
		arquivoBlog.getBlog().getAmbiente().setDescricao(rs.getString("b.ds_ambiente"));
		arquivoBlog.getBlog().getAmbiente().setImagem(rs.getString("b.no_imagem"));
		
		//carrega servico
		arquivoBlog.getBlog().setPkServico(rs.getLong("c.pk_servico"));
		arquivoBlog.getBlog().setNome(rs.getString("c.no_servico"));
		arquivoBlog.getBlog().setData(rs.getString("c.dt_servico"));
		arquivoBlog.getBlog().setDescricao(rs.getString("c.ds_servico"));
		arquivoBlog.getBlog().setImagem(rs.getString("c.no_imagem"));
		arquivoBlog.getBlog().setStatus(rs.getString("c.st_servico"));
		arquivoBlog.getBlog().setAutomatico(rs.getString("c.fl_automatico"));
		arquivoBlog.getBlog().getMembro().setPkUsuario(rs.getLong("c.fk_membro"));
		arquivoBlog.getBlog().getAmbiente().setPkAmbiente(rs.getLong("c.fk_ambiente"));
		
		//carrega usuario
		arquivoBlog.getBlog().getMembro().setPkUsuario(rs.getLong("d.pk_usuario"));
		arquivoBlog.getBlog().getMembro().setCpf(rs.getLong("d.nu_cpf"));
		arquivoBlog.getBlog().getMembro().setNome(rs.getString("d.no_usuario"));
		arquivoBlog.getBlog().getMembro().setApelido(rs.getString("d.no_apelido"));
		arquivoBlog.getBlog().getMembro().setDataNasc(rs.getString("d.dt_nasc"));
		arquivoBlog.getBlog().getMembro().setEmail(rs.getString("d.no_email"));
		arquivoBlog.getBlog().getMembro().setSenha(rs.getString("d.pw_senha"));
		arquivoBlog.getBlog().getMembro().setPerguntaChave(rs.getString("d.ds_perguntaChave"));
		arquivoBlog.getBlog().getMembro().setRespostaChave(rs.getString("d.ds_respostaChave"));
		arquivoBlog.getBlog().getMembro().setTipoLogradouro(rs.getString("d.tp_logradouro"));
		arquivoBlog.getBlog().getMembro().setLogradouro(rs.getString("d.no_logradouro"));
		arquivoBlog.getBlog().getMembro().setNumero(rs.getLong("d.nu_logradouro"));
		arquivoBlog.getBlog().getMembro().setComplemento(rs.getString("d.ds_complemento"));
		arquivoBlog.getBlog().getMembro().setCep(rs.getString("d.nu_cep"));
		arquivoBlog.getBlog().getMembro().setStatus(rs.getString("d.st_usuario"));
		arquivoBlog.getBlog().getMembro().setImagem(rs.getString("d.img_usuario"));
		
				
		//carrega arquivoBlog
		arquivoBlog.setPkArquivoBlog(rs.getLong("e.pk_arquivoBlog"));
		arquivoBlog.setNome(rs.getString("e.no_arquivoBlog"));
		
	}
}
