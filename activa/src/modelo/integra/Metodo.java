package modelo.integra;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Metodo extends NivelWADL 
{
	private String id;
	private String name;
	private String title;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

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
		this.name = name;
		this.id  = id;

		idRecurso = -1;
		
		respostas = new ArrayList<Resposta>();
		parametros = new ArrayList<Parametro>();
	}

	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		pst.setString( 2, getId());
		pst.setString( 3, getName());
		setIntOrNull(pst, 4, getIdRecurso());
//		setIntOrNull(pst, 5, autenticacao != null ? autenticacao.getIdBanco() : -1);
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
		
		sb.append("Método: ");
		sb.append("Id:" + getId());
		sb.append(", ");
		sb.append("Name:" + getName());
		sb.append(", ");
		sb.append("Title:" + getTitle());
		
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public void imprime()
	{
		System.out.println("   " +  toString() );

//		System.out.println("   No de Parametros '" + parametros.size() + "'.");
		
		Iterator it;
		
		it = parametros.iterator();
		while(it.hasNext()) 
		{
			Parametro par = (Parametro)it.next();
			
			par.imprime();
		}

//		System.out.println("No de Respostas '" + respostas.size() + "'.");
		
		it = respostas.iterator();
		while(it.hasNext()) 
		{
			Resposta res = (Resposta)it.next();
			
//			res.imprime();
		}
	}
	
}
