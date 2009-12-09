package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

import nucleo.nivel.NivelAvance;
import nucleo.lista.ListaNivelAvance;

public abstract class NivelAvancePersist 
{
	public NivelAvancePersist() throws ExcecaoBanco
	{
	}
	
	abstract NivelAvance criaNivel(ResultSet rs) throws SQLException;
	abstract protected String getSelectPorNomeComNomeResponsavel();
	abstract protected String getSelectPorIdComNomeResponsavel();
	abstract protected String getSelectPorIdOrigemComNomeResponsavel();
	abstract protected ListaNivelAvance criaLista(); 
	abstract protected String getStringInclusao();

	public NivelAvance Busca( String nome ) throws ExcecaoBanco
	{
		NivelAvance umNivel = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( getSelectPorNomeComNomeResponsavel() );
				
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() ) 
	        	umNivel = criaNivel( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return umNivel;
	}

	public NivelAvance Busca( int id ) throws ExcecaoBanco
	{
		NivelAvance umNivel = null;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( getSelectPorIdComNomeResponsavel() );
				
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
				
	        if ( rs.next() ) 
	        	umNivel = criaNivel( rs );
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() + 
								   "(" + getSelectPorIdComNomeResponsavel() + ")");
		}
		
		return umNivel;
	}
	
	public ListaNivelAvance Lista( int id ) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    ListaNivelAvance lista = criaLista();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( getSelectPorIdOrigemComNomeResponsavel() );

			// Nos niveis que não tem um nível acima (por ex. Curso) o parâmetro
			// não é necessário, então é melhor não definí-lo.
			if ( id != -1 )	pst.setInt(1, id);
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        	lista.Inclui(rs);
	        
	        rs.close();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage() + 
					   			   "(" + getSelectPorIdOrigemComNomeResponsavel() + ")");
		}
		
		return lista;
	}

	public boolean Inclui(NivelAvance novo) throws ExcecaoBanco
	{
		int linhas = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement( getStringInclusao() );
			
			novo.preparaStatetementInclusao(pst);
			
			linhas = pst.executeUpdate();
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
		
		return linhas != 0;
	}
}
