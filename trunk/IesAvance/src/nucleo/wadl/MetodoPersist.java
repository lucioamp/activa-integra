package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public class MetodoPersist extends NivelWADLPersist 
{
	public MetodoPersist() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#getNomeTabela()
	 */
	String getNomeTabela()						{ return "NPMetodos";		}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#createObjNivel(java.sql.ResultSet)
	 */
	NivelWADL createObjNivel( ResultSet rs ) throws SQLException, ExcecaoBanco	
	{ 
		return new Metodo(rs);	
	}
	
	protected String getStringInclusao()
	{
		return "insert into " + getNomeTabela() + 
			   " (IdAplicacao, Id, Name, IdRecurso, IdAutenticacao)" +
			   " values (?, ?, ?, ?, ?)";
	}
	
	public Metodo Busca( int idAplic, String nome, int idPai ) throws ExcecaoBanco
	{
		Metodo obj = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			String teste = nome != null ? "and ( Id = ? ) " : "";
			
			PreparedStatement pst = cnx.prepareStatement( "select * " +
														  "from " + getNomeTabela() + " " + 
														  "where ( IdAplicacao = ? ) " +
														  "and ( IdRecurso = ? ) " +
														  teste  );
				
			pst.setInt(1, idAplic);
			pst.setInt(2, idPai);
			if ( nome != null )
				pst.setString(3, nome);

			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() ) 
	        	obj = new Metodo( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() );
		}
		
		return obj;
	}
}
