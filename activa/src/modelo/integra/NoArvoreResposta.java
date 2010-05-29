package modelo.integra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoArvoreResposta extends NivelWADL 
{
	private String nomeElem;		// Nome no path
	private String nomeParam;		// Atributo name
	private String style;
	private String type;
	private String path;
	private boolean ehAtributo;
	private String href;
	private int idLinkMetodo;
	private String pathLink;

	private int idNoPai;
	private int idResposta;

	private List<NoArvoreResposta> noArvore;

	// Iterador interno utilizado para percorrer a lista noArvore
	@SuppressWarnings("unchecked")
	private Iterator itArvore;

	public NoArvoreResposta() 
	{
		super();
		
		nomeElem  = "";
		nomeParam = "";
		style	  = "";
		type	  = "";
		path	  = "";
		ehAtributo = false;
		
		href = "";
		idLinkMetodo = -1;

		idNoPai = -1;
		idResposta = -1;
		
		pathLink = "";

		noArvore = new ArrayList<NoArvoreResposta>();
	}


	public NoArvoreResposta( int idAplicacao, String nomeElem, String nomeParam, 
			 				 String style, String type, boolean ehAtributo, String path ) 
	{
		this.nomeElem  = nomeElem;
		this.nomeParam = nomeParam;
		this.path      = path;
		this.style     = style;
		this.type      = type;
		this.ehAtributo = ehAtributo;
		
		href = "";
		idLinkMetodo = -1;

		idNoPai = -1;
		idResposta = -1;
		
		pathLink = "";

		noArvore = new ArrayList<NoArvoreResposta>();
	}

	public NoArvoreResposta(ResultSet rs) throws SQLException
	{
		nomeElem  = rs.getString("NomeElem");
		nomeParam = rs.getString("NomeParam");
		style     = rs.getString("Style");
		type      = rs.getString("Type");
		path      = rs.getString("Path");
		ehAtributo = rs.getBoolean("EhAtributo");
		
		idLinkMetodo = getIntOrNull(rs, "IdLinkMetodo");
		href = rs.getString("href");

		idNoPai =  getIntOrNull(rs, "IdNoPai");;
		idResposta = getIntOrNull(rs, "IdResposta");
		
		pathLink = rs.getString("PathLink");

		noArvore = new ArrayList<NoArvoreResposta>();
	}

	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		pst.setString( 2, getNomeElem());
		pst.setString( 3, getNomeParam());
		pst.setString( 4, getStyle());
		pst.setString( 5, getType());
		pst.setBoolean( 6, isEhAtributo());
		
		pst.setString( 7, getPath());
		setIntOrNull(pst, 8, getIdLinkMetodo());
		pst.setString( 9, getHref());
		setIntOrNull(pst, 10, getIdNoPai());
		setIntOrNull(pst, 11, getIdResposta());
	}

	public String getNomeElem()					{ return nomeElem;				}
	public void setNomeElem(String nome)		{ nomeElem = nome;				}
	public String getNomeParam()				{ return nomeParam;				}
	public void setNomeParam(String t)			{ nomeParam = t;				}
	public String getStyle()					{ return style;					}
	public void setStyle(String s)				{ style = s;					}
	public String getType()						{ return type;					}
	public void setType(String t)				{ type = t;						}
	public String getPath()						{ return path;					}
	public void setPath(String p)				{ path = p;						}

	public boolean isEhAtributo()				{ return ehAtributo;			}
	public void setEhAtributo(boolean f)		{ ehAtributo = f;				}

	public String getHref()						{ return href;					}
	public void setHref(String href)			{ this.href = href;				}
	public int getIdLinkMetodo()				{ return idLinkMetodo;			}
	public void setIdLinkMetodo(int id)			{ idLinkMetodo = id;			}

	public int getIdResposta()					{ return idResposta;			}
	public void setIdResposta(int id)			{ idResposta = id;				}
	public int getIdNoPai()						{ return idNoPai;				}
	public void setIdNoPai(int id)				{ idNoPai = id;					}

	public void addNoArvore(NoArvoreResposta n)	{ noArvore.add(n);				}
	public List<NoArvoreResposta> getNoArvore() { return noArvore;				}

	@SuppressWarnings("unchecked")
	public NoArvoreResposta getElemento(String nome)
	{
		Iterator it;
		
		it = noArvore.iterator();
		while(it.hasNext()) 
		{
			NoArvoreResposta elem = (NoArvoreResposta)it.next();
			if ( !elem.isEhAtributo() && elem.getNomeElem().equals(nome) )
				return elem;
		}
		
		return null;
	}

	public String toString()
	{
		return (isEhAtributo() ? "Propriedade" : "Elemento") + " -> " +
			   " nomeElem='" + nomeElem +  "'" +
		   	   " nomeparam='" + nomeParam + "' path='" + path +
		   	   " style='" + style + "' type='" + type +
		   	   "' href='" + href ;
	}
	
	@SuppressWarnings("unchecked")
	public void imprime()
	{
		Iterator it;

		System.out.println("Propriedades de '" + getNomeParam() + "'.");

		it = noArvore.iterator();
		while(it.hasNext()) 
		{
			NoArvoreResposta prop = (NoArvoreResposta)it.next();
			if ( prop.isEhAtributo() )
				prop.imprime();
		}

		System.out.println("Tags Internas de '" + getNomeParam() + "'.");

		it = noArvore.iterator();
		while(it.hasNext()) 
		{
			NoArvoreResposta elem = (NoArvoreResposta)it.next();
			if ( !elem.isEhAtributo() )
				elem.imprime();
		}
	}	
	
	public int numAtributos()
	{
		int num = 0;
		NoArvoreResposta no;
	
		itArvore = noArvore.iterator();
		while ( itArvore.hasNext() )
		{
			no = (NoArvoreResposta)itArvore.next();
			if ( no.ehAtributo )
				num++;
		}
		
		return num;
	}
	
	public NoArvoreResposta primeiroAtributo()
	{
		NoArvoreResposta no;
	
		itArvore = noArvore.iterator();
		while ( itArvore.hasNext() )
		{
			no = (NoArvoreResposta)itArvore.next();
			if ( no.ehAtributo )
				return no;
		}
		
		return null;
	}
	
	public NoArvoreResposta proximoAtributo()
	{
		NoArvoreResposta no;

		while ( itArvore.hasNext() )
		{
			no = (NoArvoreResposta)itArvore.next();
			if ( no.ehAtributo )
				return no;
		}
		
		return null;
	}

	public NoArvoreResposta primeiroElemento()
	{
		NoArvoreResposta no;
	
		itArvore = noArvore.iterator();
		while ( itArvore.hasNext() )
		{
			no = (NoArvoreResposta)itArvore.next();
			if ( !no.ehAtributo )
				return no;
		}
		
		return null;
	}
	
	public NoArvoreResposta proximoElemento()
	{
		NoArvoreResposta no;

		while ( itArvore.hasNext() )
		{
			no = (NoArvoreResposta)itArvore.next();
			if ( !no.ehAtributo )
				return no;
		}
		
		return null;
	}


	public String getPathLink() {
		return pathLink;
	}


	public void setPathLink(String pathLink) {
		this.pathLink = pathLink;
	}
}
