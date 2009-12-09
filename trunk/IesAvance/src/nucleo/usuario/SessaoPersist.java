package nucleo.usuario;

import java.sql.*;
import java.text.SimpleDateFormat;

import util.banco.*;

public class SessaoPersist {

	protected static SimpleDateFormat bdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public SessaoPersist() throws ExcecaoBanco
	{
	}

	public Sessao Busca( String idSessao ) throws ExcecaoBanco
	{
		Sessao novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPSessao where " + 
										"(idSessao = ?) and "  + 
										"(logado <> 0)");
				
			pst.setString(1, idSessao);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        {
	        	novo = new Sessao( rs );
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

	public Sessao Busca( String idSessao, String endIP ) throws ExcecaoBanco
	{
		Sessao novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPSessao where " + 
										"(idSessao = ?) and " + 
										"(endIPUsuario = ?) and "  + 
										"(logado <> 0)");
				
			pst.setString(1, idSessao);
			pst.setString(2, endIP);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        {
	        	novo = new Sessao( rs );
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

	public Sessao Busca( String idSessao, int idUsuario, String endIP ) throws ExcecaoBanco
	{
		Sessao novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
											"select * from NPSessao where " + 
											"(idSessao = ?) and " + 
											"(idUsuario = ?) and " + 
											"(endIPUsuario = ?) and "  + 
											"(logado <> 0)");
				
			pst.setString(1, idSessao);
			pst.setInt(2, idUsuario);
			pst.setString(3, endIP);
			
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        	novo = new Sessao( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}

	public boolean Inclui(Sessao novo) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					//set dateformat dmy; 
					"insert into NPSessao " +
					"(idUsuario, idSessao, EndIPUsuario, idTurma, dataCriacao, dataUltimoAcesso, logado)" +
					" values (?, ?, ?, ?, ?, ?, ?)");

			pst.setInt(  1, novo.getIdUsuario());
			pst.setString( 2, novo.getIdSessao());
			pst.setString(  3, novo.getEndIPUsuario());
			pst.setInt(  4, novo.getIdTurma());
//			pst.setString( 5, bdf.format(novo.getDataCriacao()));
//			pst.setString( 6, bdf.format(novo.getDataUltimoAcesso()));
			pst.setDate(5, new java.sql.Date(novo.getDataCriacao().getTime()));
			pst.setDate(6, new java.sql.Date(novo.getDataUltimoAcesso().getTime()));
			pst.setInt(  7, novo.getLogado());
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException Inclui Sessao: " + e.getMessage());
		}
		
		return linhas != 0;
	}

	public boolean AtualizaTurma( String idSessao, int idTurma ) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;;

		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"update NPSessao set idTurma = ? " +
					"where (IdSessao = ?) and (logado <> 0)");

			pst.setInt( 1, idTurma);
			pst.setString( 2, idSessao);
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException AtualizaTurma Sessao: " + e.getMessage());
		}

		return linhas != 0;
	}	

	public boolean FazLogin( String idSessao ) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;;

		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"update NPSessao set logado = 1 " +
					"where IdSessao = ?");

			pst.setString( 1, idSessao);
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException FazLogout Sessao: " + e.getMessage());
		}

		return linhas != 0;
	}	

	public int FazLogout( String idSessao ) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return 0;

		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"update NPSessao set logado = 0 " +
					"where IdSessao = ?");

			pst.setString( 1, idSessao);
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException FazLogout Sessao: " + e.getMessage());
		}

		return linhas;
	}

	public int LogoutGeral() throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return 0;;

		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
					"update NPSessao set logado = 0 where logado <> 0" );
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException FazLogout Sessao: " + e.getMessage());
		}

		return linhas;
	}
}
