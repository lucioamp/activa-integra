package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public class ParametroPersist extends NivelWADLPersist 
{
	public ParametroPersist() 
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#getNomeTabela()
	 */
	String getNomeTabela()			{ return "NPParametros";		}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#createObjNivel(java.sql.ResultSet)
	 */
	NivelWADL createObjNivel( ResultSet rs ) throws SQLException	
	{ 
		return new Parametro(rs);	
	}
	
	protected String getStringInclusao()
	{
		return "insert into " + getNomeTabela() + 
			   " ( IdAplicacao, Name, Style, Type, Path," + 
			   " Required, IdRecurso, IdMetodo )" +
		       " values (?, ?, ?, ?, ? , ?, ?, ?)";
	}
	
	public void carregaParametros( int idAplic, int idRec, int idMet,
								   List<Parametro> lis ) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection()) == null )
		{
			throw new ExcecaoBanco("Erro de conexao!" );
		}
		
		try 
		{
			String tRec = idRec != -1 ? "= ?" : "is null",
			       tMet = idMet != -1 ? "= ?" : "is null";
			
			PreparedStatement pst = cnx.prepareStatement( "select * " +
														  "from " + getNomeTabela() + " " + 
														  "where ( IdAplicacao = ? ) " +
														  "and ( IdRecurso " + tRec + " ) " +
														  "and ( IdMetodo " + tMet + " ) "  );
				
			pst.setInt(1, idAplic);
			if (idRec != -1) pst.setInt(2, idRec);
			if (idMet != -1) pst.setInt(2, idMet);

			ResultSet rs = pst.executeQuery();
				
	        while ( rs.next() ) 
	        	lis.add( new Parametro( rs ) );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() );
		}
	}
}
