package nucleo.wadl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public abstract class NivelWADLPersist 
{
	abstract String getNomeTabela();
	abstract protected String getStringInclusao();
	abstract NivelWADL createObjNivel( ResultSet rs ) throws SQLException, ExcecaoBanco;
	
	public NivelWADL Busca( int id ) throws ExcecaoBanco
	{
		NivelWADL obj = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( "select * " +
														  "from " + getNomeTabela() + " " + 
														  "where ( IdBanco = ?)" );
				
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() ) 
	        	obj = createObjNivel( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException(" + getNomeTabela() + "): " + e.getMessage() );
		}
		
		return obj;
	}

	public boolean Inclui(NivelWADL novo) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			throw new ExcecaoBanco("Erro SQLException(" + getNomeTabela() + "): Sem Conexão!");
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( getStringInclusao() );
			
			novo.preparaStatetementInclusao(pst);
			
			linhas = pst.executeUpdate();
					
	        pst = cnx.prepareStatement( "select @@identity as IdResult" );

	        ResultSet rs = pst.executeQuery();

	        if ( rs.next() ) 
	        	novo.setIdBanco(rs.getInt("IdResult"));

			rs.close();
			pst.close();	        
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException(" + getNomeTabela() + "): " + e.getMessage());
		}

		if ( linhas == 0 )
			throw new ExcecaoBanco("Erro SQLException(" + getNomeTabela() + "): Nenhum Registro Gravado!");
		
		return linhas != 0;
	}
}
