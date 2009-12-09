package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public class NoArvoreRespostaPersist extends NivelWADLPersist {

	@Override
	NivelWADL createObjNivel(ResultSet rs) throws SQLException, ExcecaoBanco 
	{
		return new NoArvoreResposta(rs);	
	}

	@Override
	String getNomeTabela()			{ return "NPNosArvoreResposta";		}

	@Override
	protected String getStringInclusao() 
	{
		return "insert into  NPNosArvoreResposta" + 
			   " (IdAplicacao, NomeElem, NomeParam, Style, Type," +
			   " EhAtributo, Path, IdLinkMetodo, href, IdNoPai, IdResposta)" +
		       " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	public static boolean setIdLinkMetodo(NoArvoreResposta no) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst;
			pst = cnx.prepareStatement( "UPDATE NPNosArvoreResposta " +
									    " SET IdLinkMetodo = ? " +
									    " WHERE IdBanco = ? ");
			pst.setInt(1, no.getIdLinkMetodo());
			pst.setInt(2, no.getIdBanco());

			pst.executeUpdate();

			pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException:: " + e.getMessage());
		}
		
		return true;
	}
	
	public NoArvoreResposta carregaNoRaiz( int idResposta ) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    NoArvoreResposta noRaiz = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection()) == null )
			return noRaiz;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( "select *, R.Path + '/' + M.Id as PathLink " +
										" from " + getNomeTabela() + " N " +
										" left outer join NPMetodos M on M.IdBanco = N.IdLinkMetodo " +
										" left outer join NPRecursos R on M.IdRecurso = R.IdBanco " +
										" where ( IdResposta = ? ) and ( IdNoPai is null )" );
			
			pst.setInt(1, idResposta);

			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() )
	        	noRaiz = new NoArvoreResposta( rs );
	        
	        rs.close();
	        pst.close();
        	
        	if ( noRaiz != null )
        		carregaNosFilhos( idResposta, noRaiz.getIdBanco(), noRaiz.getNoArvore() );
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
			throw new ExcecaoBanco("AvaNCE(carregaNoRaiz): " + saiErro);
		}
		
		return noRaiz;
	}
	
	public int carregaNosFilhos( int idResposta, int idNoPai, 
								 List<NoArvoreResposta> lisNoFilho ) throws ExcecaoBanco
	{
		Iterator it;
	    Connection cnx = null;
	    NoArvoreResposta node;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection()) == null )
			return 0;
		
		if ( lisNoFilho == null )
			return 0;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( "select *, R.Path + '/' + M.Id as PathLink " +
														  " from " + getNomeTabela() + " N " +
														  " left outer join NPMetodos M on M.IdBanco = N.IdLinkMetodo " +
														  " left outer join NPRecursos R on M.IdRecurso = R.IdBanco " +
														  " where ( IdResposta = ? ) and ( IdNoPai = ? )" );
				
			pst.setInt(1, idResposta);
			pst.setInt(2, idNoPai);

			ResultSet rs = pst.executeQuery();
				
	        while ( rs.next() )
	        	lisNoFilho.add( new NoArvoreResposta( rs ) );
	        
	        rs.close();
	        pst.close();

	        it = lisNoFilho.iterator();
	        while ( it.hasNext() )
	        {
	        	node = (NoArvoreResposta)it.next();

	        	carregaNosFilhos( idResposta, node.getIdBanco(), node.getNoArvore() );
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
			throw new ExcecaoBanco("AvaNCE(carregaNosFilhos): " + saiErro);
		}
		
		return lisNoFilho.size();
	}
}
