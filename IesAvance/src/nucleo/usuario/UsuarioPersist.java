package nucleo.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.*;

public class UsuarioPersist {
	
	public UsuarioPersist() throws ExcecaoBanco
	{
	}

	public Usuario Busca( int id ) throws ExcecaoBanco
	{
		Usuario novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPUsuario where " +
										"idUsuario = ?");
				
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        	novo = new Usuario( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}

	public Usuario Busca( String cpf, String senha ) throws ExcecaoBanco
	{
		Usuario novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPUsuario where " +
										"(CPF = ?) and (Senha = ?)");
				
			pst.setString(1, cpf);
			pst.setString(2, senha);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        {
	        	novo = new Usuario( rs );
	        }
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}

	public boolean Existe( String cpf ) throws ExcecaoBanco
	{
		boolean res = false;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPUsuario where " +
										"CPF = ?");
				
			pst.setString(1, cpf);
			
			ResultSet rs = pst.executeQuery();

			// Se tem next, então CPF foi encontrado
	        res = rs.next(); 
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return res;
	}

	public int BuscaOpenID( String identificador ) throws ExcecaoBanco
	{
		int id = -1;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return -1;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPUserOpenIDs where " +
										"openid_url = ?");
				
			pst.setString(1, identificador);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        {
	        	id = rs.getInt("idUsuario");
	        }
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return id;
	}

	public boolean IncluiOpenID( String identificador, int idUsuario ) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"insert into NPUserOpenIDs " +
					"(openid_url, IdUsuario)" +
					" values (?, ?)");
				
			pst.setString(  1, identificador);
			pst.setInt(2, idUsuario);
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return linhas != 0;
	}

	public static String ListaUsuario( String tipo ) throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPUsuario where " + tipo + " = 1");
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("IdUsuario") + "," + rs.getString("cpf") + "," + rs.getString("NomeUsuario") + ";");
	        }
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return str;
	}

	public boolean Inclui(Usuario novo) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"insert into NPUsuario " +
					"(Email, Senha, NomeUsuario, CPF, Telefone, Celular," +
					"EhAluno, EhAdmin, EhProfessor, EhTutor, DataCadastro)" +
					" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, curdate())");
				
			pst.setString(  1, novo.getEmail());
			pst.setString(  2, novo.getSenha());
			pst.setString(  3, novo.getNomeUsuario());
			pst.setString(  4, novo.getCPF());
			pst.setString(  5, novo.getTelefone());
			pst.setString(  6, novo.getCelular());
			pst.setBoolean( 7, novo.isPapelAluno());
			pst.setBoolean( 8, novo.isPapelAdmin());
			pst.setBoolean( 9, novo.isPapelProf());
			pst.setBoolean(10, novo.isPapelTutor());
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return linhas != 0;
	}
}
