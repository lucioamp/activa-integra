package nucleo.wadl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import util.banco.ExcecaoBanco;

public class Metodo extends NivelWADL 
{
	private String id;
	private String name;

	private List<Resposta> respostas;
	private List<Parametro> parametros;

	private int idRecurso;
	private Autenticacao autenticacao;

	public Metodo()
	{
		super();
		
		id = "";
		name= "";

		idRecurso = -1;
		
		respostas = new ArrayList<Resposta>();
		parametros = new ArrayList<Parametro>();
	}
	
	public Metodo(int idAplicacao, String name, String id) 
	{
		super(idAplicacao);
		
		this.name = name;
		this.id  = id;

		idRecurso = -1;
		
		respostas = new ArrayList<Resposta>();
		parametros = new ArrayList<Parametro>();
	}

	public Metodo(ResultSet rs) throws SQLException, ExcecaoBanco
	{
		super(rs);

		int idAut;
		AutenticacaoPersist persist;
		
		id = rs.getString("Id");
		name = rs.getString("Name");

		respostas = new ArrayList<Resposta>();
		parametros = new ArrayList<Parametro>();

		idRecurso = getIntOrNull(rs, "IdRecurso");

		autenticacao = null;
		idAut = getIntOrNull(rs, "IdAutenticacao");;
		if ( idAut != -1 )
		{
			persist = new AutenticacaoPersist();
			autenticacao = (Autenticacao)persist.Busca(idAut);
		}
	}
	
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);

		pst.setString( 2, getId());
		pst.setString( 3, getName());
		setIntOrNull(pst, 4, getIdRecurso());
		setIntOrNull(pst, 5, autenticacao != null ? autenticacao.getIdBanco() : -1);
	}

	public String getId()							{ return id;				}
	public void setId(String id)					{ this.id = id;				}
	public String getName()							{ return name;				}
	public void setName(String name)				{ this.name = name;			}
	public List<Parametro> getParametros()			{ return parametros;		}
	public void setParametros(List<Parametro> lis)	{ parametros = lis;			}
	public void addParametro(Parametro par)			{ parametros.add(par);		}
	public List<Resposta> getRespostas()			{ return respostas;			}
	public void setRespostas(List<Resposta> lis)	{ respostas = lis;			}
	public void addResposta(Resposta res)			{ respostas.add(res);		}
	public int getIdRecurso()						{ return idRecurso;			}
	public void setIdRecurso(int idRec)				{ this.idRecurso = idRec;	}	

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public String toString() 
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("Método - ");
		sb.append("Id:" + getId());
		sb.append(", ");
		sb.append("Name:" + getName());
		sb.append(".");
		
		return sb.toString();
	}
	
	public void imprime()
	{
		System.out.println( toString() );

		System.out.println("No de Parametros '" + parametros.size() + "'.");
		
		Iterator it;
		
		it = parametros.iterator();
		while(it.hasNext()) 
		{
			Parametro par = (Parametro)it.next();
			
			par.imprime();
		}

		System.out.println("No de Respostas '" + respostas.size() + "'.");
		
		it = respostas.iterator();
		while(it.hasNext()) 
		{
			Resposta res = (Resposta)it.next();
			
			res.imprime();
		}
	}
	
	public void incluiDB( int idRecPai ) throws ExcecaoBanco
	{
		Iterator it;
		MetodoPersist persist;
		
		setIdRecurso( idRecPai );
		
		persist = new MetodoPersist();
		persist.Inclui(this);
		
		it = parametros.iterator();
		while(it.hasNext()) 
		{
			Parametro par = (Parametro)it.next();
			
			par.incluiDB(-1, getIdBanco());
		}
		
		it = respostas.iterator();
		while(it.hasNext()) 
		{
			Resposta res = (Resposta)it.next();
			
			res.incluiDB(getIdBanco());
		}
	}
	
	public void carregaParametros(List<Parametro> lis) throws ExcecaoBanco
	{
		ParametroPersist persist;
		
		persist = new ParametroPersist();
		persist.carregaParametros(getIdAplicacao(), -1, getIdBanco(), lis);
	}
}
