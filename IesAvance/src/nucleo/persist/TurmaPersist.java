package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.NivelAvance;
import nucleo.nivel.Turma;
import nucleo.lista.ListaTurma;
import nucleo.lista.ListaNivelAvance;


import util.banco.*;

public class TurmaPersist extends NivelAvancePersist 
{
	public TurmaPersist() throws ExcecaoBanco
	{
	}

	NivelAvance criaNivel(ResultSet rs) throws SQLException
	{
		return new Turma(rs);
	}
	
	protected String getSelectPorNomeComNomeResponsavel()
	{
		return (new Turma()).getSelectPorNomeComNomeResponsavel();
	}
	protected String getSelectPorIdComNomeResponsavel()
	{
		return (new Turma()).getSelectPorIdComNomeResponsavel();
	}
	protected String getSelectPorIdOrigemComNomeResponsavel()
	{
		return (new Turma()).getSelectPorIdOrigemComNomeResponsavel();
	}
	
	protected ListaNivelAvance criaLista()
	{
		return new ListaTurma();
	}

	protected String getStringInclusao()
	{
		return (new Turma()).getStringInclusao();
	}

	// Busca pela identificação da turma
	public Turma Busca( int idTurma ) throws ExcecaoBanco
	{
		Turma novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPTurma where Id = ?");
				
			pst.setInt(1, idTurma);
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        	novo = new Turma( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}

	// Busca pela identificação da turma
	public Turma Busca( int idTurma, int idUsuario ) throws ExcecaoBanco
	{
		Turma novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
						"select distinct T.*  from NPTurma T " +
						"left join NPTurmaAluno TA on T.Id = TA.IdTurma " +
						"where T.Id = ? "  +
						"and (Ta.IdAluno = ? "    + 
						" or T.IdProfessor = ? " +
						" or T.IdTutor = ?) " );

			pst.setInt(  1, idTurma);
			pst.setInt(  2, idUsuario);
			pst.setInt(  3, idUsuario);
			pst.setInt(  4, idUsuario);
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        	novo = new Turma( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}

	//	 retorna todas as turmas relacionadas com um usuário (professor, tutor ou aluno).
	public static String ListaTurma(int idUsuario) throws ExcecaoBanco
	{
		String str = "";
		String papel = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
			 							"select distinct T.Id, T.Nome, Ta.IdAluno, " +
			 							"T.IdProfessor, T.IdTutor  from NPTurma T " +
			 							"left join NPTurmaAluno TA on T.Id = TA.IdTurma " +
			 							"where Ta.IdAluno = ? " + 
			 							"or T.IdProfessor = ? " +
			 							"or T.IdTutor = ? " );

			pst.setInt(  1, idUsuario);
			pst.setInt(  2, idUsuario);
			pst.setInt(  3, idUsuario);
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	if ( rs.getInt("IdProfessor") == idUsuario )
	        	{
	        		papel = "professor";
	        	}
	        	else if ( rs.getInt("IdTutor") == idUsuario )
	        	{
	        		papel = "tutor";
	        	}
	        	else
	        	{
	        		papel = "aluno";
	        	}
	        		
	        	str += ( rs.getInt("Id") + "," + 
	        			 rs.getString("Nome") + "," + 
	        			 papel + ";");
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

	public static String ListaTurma() throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPTurma");
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("Id") + "," + rs.getString("Nome") + ";");
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
}
