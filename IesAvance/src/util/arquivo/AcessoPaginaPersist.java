package util.arquivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.*;

public class AcessoPaginaPersist {

	public AcessoPaginaPersist() {
	}
	public AcessoPagina Busca( String nome ) throws ExcecaoBanco
	{
		AcessoPagina novo = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement(
										"select * from NPAcessoPagina where Pagina = ?");
				
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
				
	        if (rs.next()) 
	        {
	        	novo = new AcessoPagina();
	        	novo.setPagina( nome );
	        	novo.setDiretorio( rs.getString("Diretorio") );
	        	novo.setNivelAcesso( rs.getInt("NivelAcesso") );
	        }
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return novo;
	}
}
