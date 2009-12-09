package util.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class Persistencia {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	protected static SimpleDateFormat bdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static protected Connection cnx = null;
	static protected Persistencia instancia = null;
	
	private Persistencia() throws ExcecaoBanco
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //.newInstance();
		
//		try 
//		{
////			DriverManager.registerDriver(new SQLServerDriver());
//	    }
//		catch (SQLException e) 
//		{
//			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
//		}
        
        //cnx = conecta("Avance", "ead_user", "m@uricyo", "Consultar Usuario");
		cnx = conecta("avance", "avance", "123456", "Consultar Usuario");
	}
	
	static public Persistencia getInstancia() throws ExcecaoBanco
	{
		if ( instancia == null )
			instancia = new Persistencia();
		
		return instancia;
	}
	
	public Connection getConnection()
	{
		return cnx;
	}
	
	public Connection conecta( String banco, 
							   String usuario, 
							   String senha, 
							   String descricao) throws ExcecaoBanco
	{
		try 
		{
//	        return DriverManager.getConnection("jdbc:sqlserver://Univac;" + 
//	        								  "databaseName=" + banco   + ";" +
//	        								  "user="         + usuario + ";" +
//	        								  "password="     + senha   + ";" +
//	        								  "applicationName=" + descricao);
			
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + banco, usuario, senha);
	    }
		catch (SQLException e) 
		{
			throw new ExcecaoBanco("Erro SQLException: " + e.getMessage());
		}
	}

	public GregorianCalendar DateToGregorian( Date d )
	{
		String dataStr;
		
		dataStr = sdf.format( d );
		
		return new GregorianCalendar( Integer.parseInt(dataStr.substring(6,10)),
									  Integer.parseInt(dataStr.substring(3,5)) - 1,
									  Integer.parseInt(dataStr.substring(0,2)));
	}

	public String GregorianToString( GregorianCalendar g )
	{
		return bdf.format(g.getTime());
	}
}
