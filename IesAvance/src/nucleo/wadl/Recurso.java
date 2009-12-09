package nucleo.wadl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import util.banco.ExcecaoBanco;

public class Recurso extends NivelWADL
{
	private String nome;
	private String base;
	private String path;
	private List<Metodo> metodos;
	private List<Recurso> recursos;
	private List<Parametro> parametros;

	private int idRecursoPai;
	private Autenticacao autenticacao;
	
	public Recurso() 
	{
		super();

		nome = "";
		base = "";
		path = "";
		idRecursoPai = -1;
		autenticacao = null;

		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
		parametros = new ArrayList<Parametro>();
	}

	public Recurso(int idAplicacao, String nome, String base, String path) 
	{
		super(idAplicacao);

		this.nome = nome;
		this.base = base;
		this.path = path;
		idRecursoPai = -1;
		autenticacao = null;
		
		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
		parametros = new ArrayList<Parametro>();
	}

	public Recurso(ResultSet rs) throws SQLException, ExcecaoBanco
	{
		super(rs);

		int id;
		AutenticacaoPersist persist;

		nome = rs.getString("nome");
		base = rs.getString("base");
		path = rs.getString("path");
		idRecursoPai = getIntOrNull(rs, "IdRecursoPai");
		
		autenticacao = null;
		id = getIntOrNull(rs, "IdAutenticacao");;
		if ( id != -1 )
		{
			persist = new AutenticacaoPersist();
			autenticacao = (Autenticacao)persist.Busca(id);
		}

		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
		parametros = new ArrayList<Parametro>();
	}
	
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);

		pst.setString( 2, getNome());
		pst.setString( 3, getBase());
		pst.setString( 4, getPath());
		setIntOrNull(pst, 5, getIdRecursoPai());
		setIntOrNull(pst, 6, autenticacao != null ? autenticacao.getIdBanco() : -1);
	}

	public String getNome()							{ return nome;				}
	public void setNome(String nome)				{ this.nome = nome;			}
	public String getBase()							{ return base;				}
	public void setBase(String base)				{ this.base = base;			}
	public String getPath()							{ return path;				}
	public void setPath(String path)				{ this.path = path;			}
	public int getIdRecursoPai()					{ return idRecursoPai;		}
	public void setIdRecursoPai(int idPai)			{ idRecursoPai = idPai;		}
	public List<Metodo> getMetodos()				{ return metodos;			}
	public void setMetodos(List<Metodo> lis)		{ metodos = lis;			}
	public void addMetodo(Metodo metodo)			{ metodos.add(metodo);		}
	public List<Recurso> getRecursos()				{ return recursos;			}
	public void setRecursos(List<Recurso> lis)		{ recursos = lis;			}
	public void addRecurso(Recurso recurso)			{ recursos.add(recurso);	}
	public List<Parametro> getParametros()			{ return parametros;		}
	public void setParametros(List<Parametro> lis)	{ parametros = lis;			}
	public void addParametro(Parametro par)			{ parametros.add(par);		}

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public String toString()
	{
		return "Recurso path='" + path + "'";
	}
	
	public void imprime( int nivel )
	{
		System.out.println( "nivel: " + nivel + " - " + toString() );

		System.out.println("No de Metodos '" + metodos.size() + "'.");
		
		Iterator it;
		
		it = metodos.iterator();
		while(it.hasNext()) 
		{
			Metodo met = (Metodo)it.next();
			
			met.imprime();
		}

		System.out.println("No de Recursos '" + recursos.size() + "'.");
		
		it = recursos.iterator();
		while(it.hasNext()) 
		{
			Recurso rec = (Recurso)it.next();
			
			rec.imprime(nivel + 1);
		}
	}
	
	public void incluiDB( int idRecPai ) throws ExcecaoBanco
	{
		Iterator it;
		RecursoPersist persist;
		
		setIdRecursoPai( idRecPai );
		
		persist = new RecursoPersist();
		persist.Inclui(this);
		
		it = parametros.iterator();
		while(it.hasNext()) 
		{
			Parametro par = (Parametro)it.next();
			
			par.incluiDB(getIdBanco(), -1);
		}
		
		it = metodos.iterator();
		while (it.hasNext()) 
		{
			Metodo met = (Metodo)it.next();
			
			met.incluiDB(getIdBanco());
		}

		it = recursos.iterator();
		while(it.hasNext()) 
		{
			Recurso rec = (Recurso)it.next();
			
			rec.incluiDB(getIdBanco());
		}
	}
	
	public void carregaParametros(List<Parametro> lis) throws ExcecaoBanco
	{
		ParametroPersist persist;
		
		persist = new ParametroPersist();
		persist.carregaParametros(getIdAplicacao(), getIdBanco(), -1, lis);
	}
}
