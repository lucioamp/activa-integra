package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;
import java.util.List;

public class RecursoPersist extends NivelWADLPersist
{
	public RecursoPersist() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#getNomeTabela()
	 */
	String getNomeTabela()						{ return "NPRecursos";		}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#createObjNivel(java.sql.ResultSet)
	 */
	NivelWADL createObjNivel( ResultSet rs ) throws SQLException, ExcecaoBanco	
	{ 
		return new Recurso(rs);	
	}
	
	protected String getStringInclusao()
	{
		return "insert into " + getNomeTabela() + 
			   " (IdAplicacao, nome, base, path, IdRecursoPai, IdAutenticacao)" +
			   " values (?, ?, ?, ?, ?, ?)";
	}
	
	public Recurso Busca( int idAplic, String nome, int idPai ) throws ExcecaoBanco
	{
		Recurso obj = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			String teste = idPai == -1 ? "is null" : "= ?";
			
			PreparedStatement pst = cnx.prepareStatement( "select * " +
														  "from " + getNomeTabela() + " " + 
														  "where ( IdAplicacao = ? ) " +
														  "and ( Nome = ? ) " +
														  "and ( IdRecursoPai " + teste + " ) " );
				
			pst.setInt(1, idAplic);
			pst.setString(2, nome);
			if ( idPai != -1 )
				pst.setInt(3, idPai);

			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() ) 
	        	obj = new Recurso( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() );
		}
		
		return obj;
	}
	
	public int carregaListaRecursos( int idAplic, List<Recurso> lisRec ) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection()) == null )
			return 0;
		
		try 
		{
			Recurso rec;
			
			PreparedStatement pst = cnx.prepareStatement( "select r.*, m.Id " +
										"from " + getNomeTabela() + " r " +
										"join NPMetodos m on m.IdRecurso = r.IdBanco " + 
										"where ( r.IdAplicacao = ? ) " );
				
			pst.setInt(1, idAplic);

			ResultSet rs = pst.executeQuery();
				
	        while ( rs.next() )
	        {
	        	rec = new Recurso( rs );
	        	
	        	rec.setPath( rec.getPath() + "/" + rs.getString("Id"));
	        	lisRec.add( rec );
	        }
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() );
		}
		
		return lisRec.size();
	}
}
