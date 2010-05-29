package dao;

import interfaces.UsuarioI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.MicroBlog;
import modelo.Usuario;
import util.CadastroException;
import util.Comuns;
import util.ConnectionFactory;

public class UsuarioDAO implements UsuarioI {

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Usuario usuario) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into usuario (" +
						"pk_usuario, nu_cpf, no_usuario, no_apelido, dt_nasc, no_email, pw_senha, ds_perguntaChave," +
						"ds_respostaChave, tp_logradouro, no_logradouro, nu_logradouro, ds_complemento, nu_cep, " +
						"st_usuario, img_usuario, fk_bairro, fk_instituicao) " +
						"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "usuario", "pk_usuario");
				
				stmt.setLong(1,pk);
				stmt.setLong(2,usuario.getCpf());
				stmt.setString(3,usuario.getNome());
				stmt.setString(4,usuario.getApelido());
				stmt.setString(5,usuario.getDataNasc());
				stmt.setString(6,usuario.getEmail());
				stmt.setString(7,usuario.getSenha());
				stmt.setString(8,usuario.getPerguntaChave());
				stmt.setString(9,usuario.getRespostaChave());
				stmt.setString(10,usuario.getTipoLogradouro());
				stmt.setString(11,usuario.getLogradouro());
				stmt.setLong(12,usuario.getNumero());
				stmt.setString(13,usuario.getComplemento());
				stmt.setString(14,usuario.getCep());
				stmt.setString(15,usuario.getStatus());
				stmt.setString(16,usuario.getImagem());
				stmt.setLong(17,usuario.getBairro().getPkBairro());
				stmt.setLong(18,usuario.getInstituicao().getPkInstituicao());
				
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
	
	public int cadastrar (Usuario usuario) throws CadastroException{
		int pk = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into usuario (pk_usuario, nu_cpf, no_usuario, no_email, pw_senha, st_usuario) values (?,?,?,?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				
				pk = Comuns.getMaxId(conn, "usuario", "pk_usuario");
				
				stmt.setLong(1,pk);
				stmt.setLong(2,usuario.getCpf());
				stmt.setString(3,usuario.getNome());
				stmt.setString(4,usuario.getEmail());
				stmt.setString(5,usuario.getSenha());
				stmt.setString(6,usuario.getStatus());
				
				System.out.println(stmt);
				stmt.executeUpdate();
				System.out.println(1234);
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return pk;
	}
	
	public int alterar(Usuario usuario) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update usuario set nu_cpf=?, no_usuario=?, no_apelido=?, dt_nasc=?, no_email=?, pw_senha=?, ds_perguntaChave=?," +
						"ds_respostaChave=?, tp_logradouro=?, no_logradouro=?, nu_logradouro=?, ds_complemento=?, nu_cep=?, " +
						"st_usuario=?, img_usuario=?, fk_bairro=?, fk_instituicao=? where pk_usuario=?";
			
			stmt = conn.prepareStatement(sql);
							
			stmt.setLong(1,usuario.getCpf());
			stmt.setString(2,usuario.getNome());
			stmt.setString(3,usuario.getApelido());
			stmt.setString(4,usuario.getDataNasc());
			stmt.setString(5,usuario.getEmail());
			stmt.setString(6,usuario.getSenha());
			stmt.setString(7,usuario.getPerguntaChave());
			stmt.setString(8,usuario.getRespostaChave());
			stmt.setString(9,usuario.getTipoLogradouro());
			stmt.setString(10,usuario.getLogradouro());
			stmt.setLong(11,usuario.getNumero());
			stmt.setString(12,usuario.getComplemento());
			stmt.setString(13,usuario.getCep());
			stmt.setString(14,usuario.getStatus());
			stmt.setString(15,usuario.getImagem());
			stmt.setLong(16,usuario.getBairro().getPkBairro());
			stmt.setLong(17,usuario.getInstituicao().getPkInstituicao());
			stmt.setLong(18,usuario.getPkUsuario());
			
			System.out.println("altera:"+stmt);
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int alterarImagem(Usuario usuario) throws CadastroException{
		int r=0;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql ="update usuario set img_usuario=? where pk_usuario=?";
			
			stmt = conn.prepareStatement(sql);
							

			stmt.setString(1,usuario.getImagem());
			stmt.setLong(2,usuario.getPkUsuario());
			
			System.out.println("altera:"+stmt);
			r=stmt.executeUpdate();
			
		}catch (Exception e){
			throw new CadastroException(e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
	
		return r;
	}
	
	public int excluir (Usuario usuario) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from usuario where pk_usuario = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, usuario.getPkUsuario());
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
	
	public Collection<Usuario> consultarTodos() throws CadastroException{
		Collection<Usuario> col = new ArrayList<Usuario>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from usuario ";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					System.out.println("pk_usuario: "+rs.getLong("pk_usuario"));
					Usuario usuario = new Usuario();
					carregar(rs, usuario);
					
					Membro membro = new Membro();
					membro.setPkUsuario(usuario.getPkUsuario());
					usuario.setMicroBlogs(MicroBlog.consultarPorMembro(membro));
					
					col.add(usuario);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Usuario consultar(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from usuario where pk_usuario=? ";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuario.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, usuario);
				Membro membro = new Membro();
				membro.setPkUsuario(usuario.getPkUsuario());
				usuario.setMicroBlogs(MicroBlog.consultarPorMembro(membro));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return usuario;
	}
	
	public Usuario consultarPorLogin(String login) throws CadastroException{
		Usuario usuario = null;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from usuario where no_email=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, login);
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				usuario = new Usuario();
				carregar(rs, usuario);
				
				Membro membro = new Membro();
				membro.setPkUsuario(usuario.getPkUsuario());
				usuario.setMicroBlogs(MicroBlog.consultarPorMembro(membro));
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return usuario;
	}
	
	public boolean validaLogin(String login, String senha) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select no_email, pw_senha from usuario where no_email=? and pw_senha=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, login);
			stmt.setString(2, senha);
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
				
		return false;
	}
	
	public boolean estaAtivo(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_usuario from usuario where st_usuario='A' and pk_usuario=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, usuario.getPkUsuario());
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
				
		return false;
	}
	
	public boolean existeEmail(String email) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_usuario from usuario where no_email=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
				
		return false;
	}
	
	public boolean existeCpf(Long cpf) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_usuario from usuario where nu_cpf=?";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, cpf);
						
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
				
		return false;
	}
	
	private void carregar(ResultSet rs ,Usuario usuario) throws SQLException{

		//carrega bairro
		usuario.getBairro().setPkBairro(rs.getLong("fk_bairro"));
		if(usuario.getBairro().getPkBairro() > 0)
		{
			try {
				usuario.getBairro().consultar();
			} catch (Exception e) {}
		}
		
		if(rs.getLong("fk_instituicao") > 0)
		{
			usuario.getInstituicao().setPkInstituicao(rs.getLong("fk_instituicao"));
			try {
				usuario.getInstituicao().consultar();			
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		//carrega usuario
		usuario.setPkUsuario(rs.getLong("pk_usuario"));
		usuario.setCpf(rs.getLong("nu_cpf"));
		usuario.setNome(rs.getString("no_usuario"));
		usuario.setApelido(rs.getString("no_apelido"));
		usuario.setDataNasc(rs.getString("dt_nasc"));
		usuario.setEmail(rs.getString("no_email"));
		usuario.setSenha(rs.getString("pw_senha"));
		usuario.setPerguntaChave(rs.getString("ds_perguntaChave"));
		usuario.setRespostaChave(rs.getString("ds_respostaChave"));
		usuario.setTipoLogradouro(rs.getString("tp_logradouro"));
		usuario.setLogradouro(rs.getString("no_logradouro"));
		usuario.setNumero(rs.getLong("nu_logradouro"));
		usuario.setComplemento(rs.getString("ds_complemento"));
		usuario.setCep(rs.getString("nu_cep"));
		usuario.setStatus(rs.getString("st_usuario"));
		usuario.setImagem(rs.getString("img_usuario"));
		
	}
}
