package util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactoryProperties extends ConnectionFactory {

	private  Properties propriedades;
	protected ConnectionFactoryProperties() throws SQLException {
	
		try {

			//Trecho de codigo necessario para carregar as propriedades a partir de um arquivo
			propriedades = new Properties();
			InputStream stream;
			stream = this.getClass().getClassLoader().getResourceAsStream( "jdbc.properties" ) ;
		    propriedades.load(stream);
		    
		    
		    //TODO Carregar o driver para memoria
		    String driver=(String) propriedades.get("driver");
		    
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} 
	}

	/** Obtem a conexao */
	public  Connection getConnection() throws SQLException {

		//TODO obter a conexao, utilizando a url, nome do usuario e password
		String url= (String) propriedades.get("url");
		String username=(String) propriedades.get("username");
		String password=(String) propriedades.get("password");
		return DriverManager.getConnection(url, username, password);

	}

	

}
