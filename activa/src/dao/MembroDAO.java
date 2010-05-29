package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import modelo.Membro;
import modelo.Usuario;
import util.CadastroException;
import util.ConnectionFactory;
import interfaces.MembroI;

public class MembroDAO implements MembroI{

	Connection conn = null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	
	public int incluir (Membro membro) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="insert into membro (pk_membro, fk_formacaoAcademica, fl_permissao) values (?,?,?)";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membro.getPkUsuario());
				stmt.setLong(2,membro.getFormacaoAcademica().getPkFormacaoAcademica());				
				stmt.setString(3,membro.getPermissao());	
				
				System.out.println(stmt);
				r=stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return r;
	}
	
	public int alterar (Membro membro) throws CadastroException{
		int r =0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
								
				String sql ="update membro set fk_formacaoAcademica=?, fl_permissao=? where pk_membro=?";
								
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1,membro.getFormacaoAcademica().getPkFormacaoAcademica());
				stmt.setString(2,membro.getPermissao());
				stmt.setLong(3,membro.getPkUsuario());
											
				System.out.println(stmt);
				r=stmt.executeUpdate();
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new CadastroException(e);
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return r;
	}
	
	public int excluir (Membro membro) throws CadastroException{
		int r = 0;
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql ="delete from membro where pk_membro = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, membro.getPkUsuario());
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
	
	public Collection<Membro> consultarTodosMembros() throws CadastroException{
		Collection<Membro> col = new ArrayList<Membro>();
			try{
				conn = ConnectionFactory.getInstance().getConnection();
				String sql = "select * from membro, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
						" pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
			
				stmt = conn.prepareStatement(sql);
				System.out.println("UfDAO Consultar consultarTodas :" +stmt );
				rs = stmt.executeQuery();
				
				while (rs.next()){
					Membro membro = new Membro();
					carregar(rs, membro);
					col.add(membro);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
			}
			
		return col;
	}
	
	public Membro consultar(Membro membro) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select * from membro, usuario, bairro, municipio, uf, formacaoacademica, instituicao where " +
					" pk_usuario=? and pk_membro=pk_usuario and pk_formacaoAcademica = fk_formacaoAcademica";
		
			stmt = conn.prepareStatement(sql);
			
			stmt.setLong(1, membro.getPkUsuario());
			
			System.out.println("UfDAO Consultar consultar:" +stmt );
			rs = stmt.executeQuery();
			
			if (rs.next()){
				carregar(rs, membro);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			ConnectionFactory.getInstance().closeConnection(rs, stmt, conn);
		}
		
		return membro;
	}
	
	public boolean isMembro(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_membro from membro where pk_membro=?";
		
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
	
	public boolean isAluno(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_membro from membro where pk_membro=? and fl_permissao='A'";
		
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
	
	public boolean isProfessor(Usuario usuario) throws CadastroException{
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = "select pk_membro from membro where pk_membro=? and fl_permissao='P'";
		
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
	
	private void carregar(ResultSet rs ,Membro membro) throws SQLException{
		
		//carrega bairro
		if(rs.getLong("fk_bairro") > 0)
		{
			membro.getBairro().setPkBairro(rs.getLong("fk_bairro"));
			try {
				membro.getBairro().consultar();				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//formacaoAcademica
		membro.getFormacaoAcademica().setPkFormacaoAcademica(rs.getLong("pk_formacaoAcademica"));
		membro.getFormacaoAcademica().setNome(rs.getString("no_formacaoAcademica"));
		
		//carrega instituicao
		if(rs.getLong("fk_instituicao") > 0)
		{
			membro.getInstituicao().setPkInstituicao(rs.getLong("fk_instituicao"));
			try {
				membro.getInstituicao().consultar();			
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		//carrega usuario
		membro.setPkUsuario(rs.getLong("pk_usuario"));
		membro.setCpf(rs.getLong("nu_cpf"));
		membro.setNome(rs.getString("no_usuario"));
		membro.setApelido(rs.getString("no_apelido"));
		membro.setDataNasc(rs.getString("dt_nasc"));
		membro.setEmail(rs.getString("no_email"));
		membro.setSenha(rs.getString("pw_senha"));
		membro.setPerguntaChave(rs.getString("ds_perguntaChave"));
		membro.setRespostaChave(rs.getString("ds_respostaChave"));
		membro.setTipoLogradouro(rs.getString("tp_logradouro"));
		membro.setLogradouro(rs.getString("no_logradouro"));
		membro.setNumero(rs.getLong("nu_logradouro"));
		membro.setComplemento(rs.getString("ds_complemento"));
		membro.setCep(rs.getString("nu_cep"));
		membro.setStatus(rs.getString("st_usuario"));
		membro.setImagem(rs.getString("img_usuario"));
		
		membro.setPermissao(rs.getString("fl_permissao"));
		
	}
}
