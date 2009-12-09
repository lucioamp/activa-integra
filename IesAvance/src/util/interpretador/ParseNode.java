package util.interpretador;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParseNode 
{
	private int tipo;
	private String valor;
	private String objeto;
	private String metodo;
	private boolean auth;
	private int alter;
	private int prox;
	
	public ParseNode( int tipo, String valor, String objeto,
					  String metodo, boolean auth, 
					  int alter, int prox ) 
	{
		super();

		this.tipo = tipo;
		this.valor = valor;
		this.objeto = objeto;
		this.metodo = metodo;
		this.auth = auth;
		this.alter = alter;
		this.prox = prox;
	}

	public Object createObject(String prefixo) throws ExcecaoParser
	{
		String nome;
		Object obj = null;
		
		nome = prefixo + getObjeto();
		
		try
		{
			Class classDef;
			
			classDef = Class.forName(nome);
			
			obj = classDef.newInstance();
		}
		catch (InstantiationException e)
		{
			throw new ExcecaoParser("Instanciação: " + e.getMessage());
		}
		catch (IllegalAccessException e)
		{
			throw new ExcecaoParser("Acesso ilegal: " + e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			throw new ExcecaoParser("Classe não encontrada: " + e.getMessage());
		}
		
		return obj;
	}
	
	public String invoca(Object obj) throws ExcecaoParser
	{
		Method met = null;
		
		try
		{
			met = obj.getClass().getMethod(getMetodo(), (Class[])null);
			return (String)met.invoke(obj, (Object[])null);
		}
		catch (IllegalAccessException e)
		{
			throw new ExcecaoParser("Acesso ilegal: " + e.getMessage());
		}
		catch (InvocationTargetException e)
		{
			throw new ExcecaoParser("Invocação: " + e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			throw new ExcecaoParser("Metodo não encontrado: " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new ExcecaoParser( e.getMessage() );
		}
	}
	
	public String executa( ContextList contexto, String prefixo ) throws ExcecaoParser
	{
		Object obj;
		
		obj = createObject(prefixo);
		
		return invoca(obj);
	}

	public int getAlter()				 { return alter;		 }
	public void setAlter(int alter)		 { this.alter = alter;	 }
	public String getMetodo()			 { return metodo;		 }
	public void setMetodo(String metodo) { this.metodo = metodo; }
	public String getObjeto()			 { return objeto;		 }
	public void setObjeto(String objeto) { this.objeto = objeto; }
	public int getProx()				 { return prox;			 }
	public void setProx(int prox)		 { this.prox = prox;	 }
	public int getTipo()				 { return tipo;			 }
	public void setTipo(int tipo)		 { this.tipo = tipo;	 }
	public String getValor()			 { return valor;		 }
	public void setValor(String valor)	 { this.valor = valor;	 }
	public boolean isAuth()				 { return auth;			 }
	public void setAuth(boolean auth)	 { this.auth = auth;	 }
}
