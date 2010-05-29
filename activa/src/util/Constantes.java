package util;

public class Constantes {

	public static final int CONNECTION_FACTORY_JNDI = 1;
	public static final int CONNECTION_FACTORY_PROPERTIES = 2;
	public static final int CONNECTION_FACTORY_DEFAULT = 3;
	//public static final int CONNECTION_FACTORY_ATUAL = CONNECTION_FACTORY_JNDI;
	public static final int CONNECTION_FACTORY_ATUAL = CONNECTION_FACTORY_PROPERTIES ;
	public static final String JNDI_NAME="java:/comp/env/jdbc/sasuss";
	
	public static final int DATABASE_ORACLE=1;
	public static final int DATABASE_MYSQL=2;
	public static final int DATABASE_MEMORY=3;
	public static final int DATABASE_ACCESS=4;
	public static final int DATABASE_POSTGRE=5;
	public static final int DATABASE_ATUAL=DATABASE_POSTGRE;
	
	

}
