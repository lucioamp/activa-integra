package nucleo.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.lista.ListaAplicacao;
import nucleo.lista.ListaNivelAvance;
import nucleo.nivel.Aplicacao;
import nucleo.nivel.NivelAvance;

import util.banco.ExcecaoBanco;
import util.banco.Persistencia;

public class AplicacaoPersist extends NivelAvancePersist 
{

	public AplicacaoPersist() throws ExcecaoBanco
	{
	}

	NivelAvance criaNivel(ResultSet rs) throws SQLException
	{
		return new Aplicacao(rs);
	}
	
	protected String getSelectPorNomeComNomeResponsavel()
	{
		return (new Aplicacao()).getSelectPorNomeComNomeResponsavel();
	}
	protected String getSelectPorIdComNomeResponsavel()
	{
		return (new Aplicacao()).getSelectPorIdComNomeResponsavel();
	}
	protected String getSelectPorIdOrigemComNomeResponsavel()
	{
		return (new Aplicacao()).getSelectPorIdOrigemComNomeResponsavel();
	}

	protected String getStringInclusao()
	{
		return (new Aplicacao()).getStringInclusao();
	}
	protected ListaNivelAvance criaLista()
	{
		return new ListaAplicacao();
	}

	public static String Lista() throws ExcecaoBanco
	{
		String str = "";
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return null;
		
		try 
		{
			PreparedStatement pst = cnx.prepareStatement("select * from NPAplicacao");
				
			ResultSet rs = pst.executeQuery();
				
	        while (rs.next()) 
	        {
	        	str += ( rs.getInt("Id")      + ", " + 
	        			 rs.getString("Nome") + ", " + 
	        			 rs.getString("Externa") + ";" );
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

	public static boolean ApagaWADL(Aplicacao aplic) throws ExcecaoBanco
	{
		int i = 0;
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst;
			
			i++;
			// Elimina vínculos dentro da tabela de Recursos
			pst = cnx.prepareStatement( "UPDATE NPRecursos " +
									    "SET IdRecursoPai = null " +
										"WHERE IdAplicacao = ?" );
			pst.setInt(1, aplic.getId());
			
			pst.executeUpdate();

			i++;
			pst = cnx.prepareStatement( "DELETE FROM NPParametros " +
										"WHERE IdAplicacao = ?" );

			pst.setInt(1, aplic.getId());

			pst.executeUpdate();

			i++;
			pst = cnx.prepareStatement( "DELETE FROM NPNosArvoreResposta " +
										"WHERE IdAplicacao = ?" );

			pst.setInt(1, aplic.getId());

			pst.executeUpdate();

			i++;
			pst = cnx.prepareStatement( "DELETE FROM NPRecursos " +
										"WHERE IdAplicacao = ?" );

			pst.setInt(1, aplic.getId());

			pst.executeUpdate();

			i++;
			pst = cnx.prepareStatement( "DELETE FROM NPAutenticacao " +
										"WHERE IdAplicacao = ?" );

			pst.setInt(1, aplic.getId());

			pst.executeUpdate();

			i++;
			pst = cnx.prepareStatement( "UPDATE NPAplicacao " +
				    					"SET WADLCarregado = 0 " +
				    					"WHERE Id = ? ");

			pst.setInt(1, aplic.getId());

			pst.executeUpdate();

			i++;
					
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException (" + i + "): " + e.getMessage());
		}
		
		return true;
	}

	public static boolean setWADLCarregado(Aplicacao aplic) throws ExcecaoBanco
	{
	    Connection cnx = null;
	    Persistencia p = Persistencia.getInstancia();
	    
		if ( (cnx = p.getConnection())  == null )
			return false;
		
		try 
		{
			PreparedStatement pst;
			pst = cnx.prepareStatement( "UPDATE NPAplicacao " +
									    "SET WADLCarregado = 1 " +
									    "WHERE Id = ? ");
			pst.setInt(1, aplic.getId());
			pst.executeUpdate();
	        pst.close();
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException:: " + e.getMessage());
		}
		
		return true;
	}
}
