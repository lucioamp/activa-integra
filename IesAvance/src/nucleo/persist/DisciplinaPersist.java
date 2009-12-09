package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.Disciplina;
import nucleo.nivel.NivelAvance;
import nucleo.lista.ListaDisciplina;
import nucleo.lista.ListaNivelAvance;

import util.banco.*;

public class DisciplinaPersist extends NivelAvancePersist
{
	public DisciplinaPersist() throws ExcecaoBanco
	{
	}

	NivelAvance criaNivel(ResultSet rs) throws SQLException
	{
		return new Disciplina(rs);
	}
	
	protected String getSelectPorNomeComNomeResponsavel()
	{
		return (new Disciplina()).getSelectPorNomeComNomeResponsavel();
	}
	protected String getSelectPorIdComNomeResponsavel()
	{
		return (new Disciplina()).getSelectPorIdComNomeResponsavel();
	}
	protected String getSelectPorIdOrigemComNomeResponsavel()
	{
		return (new Disciplina()).getSelectPorIdOrigemComNomeResponsavel();
	}
	
	protected ListaNivelAvance criaLista()
	{
		return new ListaDisciplina();
	}

	protected String getStringInclusao()
	{
		return (new Disciplina()).getStringInclusao();
	}
	
	public static String ListaDisciplina( ) throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPDisciplina" );
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("IdDisciplina") +  "," + rs.getString("NomeDisciplina") + ";");
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
