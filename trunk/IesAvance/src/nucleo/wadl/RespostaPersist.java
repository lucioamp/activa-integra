package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;
import java.util.Iterator;

public class RespostaPersist  extends NivelWADLPersist
{
	public RespostaPersist() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#getNomeTabela()
	 */
	String getNomeTabela()						{ return "NPRespostas";		}

	/*
	 * (non-Javadoc)
	 * @see nucleo.wadl.NivelWADLPersist#createObjNivel(java.sql.ResultSet)
	 */
	NivelWADL createObjNivel( ResultSet rs ) throws SQLException	
	{ 
		return new Resposta(rs);	
	}
	
	protected String getStringInclusao()
	{
		return "insert into " + getNomeTabela() + 
			   " (IdAplicacao, Id, MediaType, Element, Status, RespostaErro, IdMetodo)" +
			   " values (?, ?, ?, ?, ?, ?, ?)";
	}
	
	public int carregaListaRespostas( int idMetodo, List<Resposta> lisRes ) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection()) == null )
			return 0;
		
		try 
		{
	        Iterator it;
			Resposta res;
			NoArvoreResposta noRaiz;
			NoArvoreRespostaPersist noPersist;
			
			noPersist = new NoArvoreRespostaPersist();
			
			PreparedStatement pst = cnx.prepareStatement( "select * " +
										" from " + getNomeTabela() + 
										" where ( IdMetodo = ? ) " );
				
			pst.setInt(1, idMetodo);

			ResultSet rs = pst.executeQuery();
				
	        while ( rs.next() )
	        	lisRes.add( new Resposta( rs ) );
	        
	        rs.close();
	        pst.close();
	        
	        it = lisRes.iterator();
	        while ( it.hasNext() )
	        {
	        	res = (Resposta)it.next();
	        	
	        	noRaiz = noPersist.carregaNoRaiz(res.getIdBanco());
	        	
	        	res.setEstruturaXML(noRaiz);
	        }
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() );
		}
		catch (Exception eg)		
		{
			String saiErro = "";
			for ( int k = 0; k < eg.getStackTrace().length; k++ )
				saiErro += eg.getStackTrace()[k].toString() + " +/+ ";
			throw new ExcecaoBanco("AvaNCE(carregaListaRespostas): " + saiErro);
		}
		
		return lisRes.size();
	}
}
