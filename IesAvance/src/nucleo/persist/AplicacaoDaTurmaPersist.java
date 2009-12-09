package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.NivelAvance;
import nucleo.nivel.AplicacaoDaTurma;
import nucleo.lista.ListaAplicacaoDaTurma;
import nucleo.lista.ListaNivelAvance;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public class AplicacaoDaTurmaPersist extends NivelAvancePersist 
{

	public AplicacaoDaTurmaPersist() throws ExcecaoBanco 
	{
	}

	NivelAvance criaNivel(ResultSet rs) throws SQLException
	{
		return new AplicacaoDaTurma(rs);
	}
	
	protected String getSelectPorNomeComNomeResponsavel()
	{
		return (new AplicacaoDaTurma()).getSelectPorNomeComNomeResponsavel();
	}
	protected String getSelectPorIdComNomeResponsavel()
	{
		return (new AplicacaoDaTurma()).getSelectPorIdComNomeResponsavel();
	}
	protected String getSelectPorIdOrigemComNomeResponsavel()
	{
		return (new AplicacaoDaTurma()).getSelectPorIdOrigemComNomeResponsavel();
	}
	
	protected ListaNivelAvance criaLista()
	{
		return new ListaAplicacaoDaTurma();
	}

	protected String getStringInclusao()
	{
		return (new AplicacaoDaTurma()).getStringInclusao();
	}

	//	 retorna todas as aplicacoes habilitadas para uma determinada turma.
	public static String Listar(int idTurma) throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
			 							"select * from NPAplicacaoTurma AT " +
			 							"left join NPAplicacao A on AT.IdAplicacao = A.Id " +
			 							"where AT.IdTurma = ? " );

			pst.setInt(  1, idTurma);

			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("Id") + ", " + 
	        			 rs.getString("Nome") + "," + 
	        			 rs.getBoolean("externa") + "," +
	        			 rs.getBoolean("feed") + "," +
	        			 rs.getString("Url") + ";");
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
