package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactoryJNDI extends ConnectionFactory{

	private DataSource dataSource;
	
	protected ConnectionFactoryJNDI() throws SQLException 
	{
		
		//Obtem o Datasource do pool de conexoes, a partir da arvore jndi
		
		try {
			Context initContext = new InitialContext();
			dataSource  = (DataSource)initContext.lookup(Constantes.JNDI_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		
		
		
	}
	
	/** Obtem a conexao */
	public Connection getConnection() throws SQLException {

		return dataSource.getConnection();

	}

	
}
