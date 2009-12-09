package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.NivelAvance;
import nucleo.nivel.Curso;
import nucleo.lista.ListaCurso;
import nucleo.lista.ListaNivelAvance;


import util.banco.*;

public class CursoPersist extends NivelAvancePersist {

	public CursoPersist() throws ExcecaoBanco
	{
	}

	NivelAvance criaNivel(ResultSet rs) throws SQLException
	{
		return new Curso(rs);
	}
	
	protected String getSelectPorNomeComNomeResponsavel()
	{
		return (new Curso()).getSelectPorNomeComNomeResponsavel();
	}
	protected String getSelectPorIdComNomeResponsavel()
	{
		return (new Curso()).getSelectPorIdComNomeResponsavel();
	}
	protected String getSelectPorIdOrigemComNomeResponsavel()
	{
		return (new Curso()).getSelectPorIdOrigemComNomeResponsavel();
	}
	
	protected ListaNivelAvance criaLista()
	{
		return new ListaCurso();
	}

	protected String getStringInclusao()
	{
		return (new Curso()).getStringInclusao();
	}
	
	public static String ListaCurso( ) throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPCurso" );
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("IdCurso") +  "," + rs.getString("NomeCurso") + ";");
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
