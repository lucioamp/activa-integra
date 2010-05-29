package util;

import java.sql.*;

public class ConnectionFactoryDefault extends ConnectionFactory  {

	/** Atributos basicos necessarios para efetuar uma conexao jdbc */
//	private static String url = "jdbc:postgresql://localhost:5432/cva";
	private static String url = "jdbc:mysql://localhost:3306/cva";

//	private static String driver = "org.postgresql.Driver";
	private static String driver = "com.mysql.jdbc.Driver";
		
//	private static String username = "postgres";
	private static String username = "root";

//	private static String password = "sasuss";
	private static String password = "root";

	/**
	 * Instancia o driver
	 */
	protected ConnectionFactoryDefault() throws  SQLException {
	
		try {

			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			throw new  SQLException(e.getMessage());
		}
	}

	/** Obtem a conexao */
	public  Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, username, password);

	}
	
	/*
	public static void main(String[] args){
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {		
			conn= ConnectionFactory.getInstance().getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	*/
	
	
}
