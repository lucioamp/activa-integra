package nucleo.wadl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;

public class Resposta extends NivelWADL 
{
	private String id;
	private String mediaType;
	private String element;
	private int status;
	private boolean respostaErro;

	private int idMetodo;
	
	private NoArvoreResposta estruturaXML;

	public Resposta() 
	{
		super();

		id = "";
		mediaType = "";
		element = "";
		status = 0;
		respostaErro = false;
		
		idMetodo = -1;
	}

	public Resposta( int idAplicacao, String id,  String mediaType, 
					 String element,  int status, boolean respostaErro ) 
	{
		super(idAplicacao);

		this.id = id;
		this.mediaType = mediaType;
		this.element = element;
		this.status = status;
		this.respostaErro = respostaErro;
		
		idMetodo = -1;
	}

	public Resposta(ResultSet rs) throws SQLException
	{
		super(rs);

		id = rs.getString("Id");
		mediaType = rs.getString("MediaType");
		element = rs.getString("Element");
		status = rs.getInt("Status");
		respostaErro = rs.getBoolean("RespostaErro");

		idMetodo = getIntOrNull(rs, "IdMetodo");
	}
	
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);

		pst.setString( 2, getId());
		pst.setString( 3, getMediaType());
		pst.setString( 4, getElement());
		pst.setInt( 5, getStatus());
		pst.setBoolean( 6, isRespostaErro());
		setIntOrNull(pst, 7, getIdMetodo());
	}

	public String getElement()						{ return element;			}
	public void setElement(String element)			{ this.element = element;	}
	public String getId()							{ return id;				}
	public void setId(String id)					{ this.id = id;				}
	public String getMediaType()					{ return mediaType;			}
	public void setMediaType(String mt)				{ mediaType = mt;			}
	public boolean isRespostaErro()					{ return respostaErro;		}
	public void setRespostaErro(boolean erro)		{ respostaErro = erro;		}
	public int getStatus()							{ return status;			}
	public void setStatus(int status)				{ this.status = status;		}
	public int getIdMetodo()						{ return idMetodo;			}
	public void setIdMetodo(int idMetodo)			{ this.idMetodo = idMetodo;	}	
	public NoArvoreResposta getEstruturaXML()		{ return estruturaXML;		}
	public void setEstruturaXML(NoArvoreResposta e)	{ estruturaXML = e;			}
	
	public String toString()
	{
		return "Resposta='" + id + "' fault='" + (respostaErro ? "true" : "false") + 
		       "' mediaType='" + mediaType + "' status='" + status + 
		       "' element='" + element + "'";
	}
	
	public void imprime()
	{
		System.out.println( toString() );

		if ( estruturaXML != null )
		{
			System.out.println("Tag '" + estruturaXML.getNomeParam() + "'.");
			
			estruturaXML.imprime();
		}
		else
			System.out.println("Sem Tag.");
	}
	
	public void incluiDB( int idMetPai ) throws ExcecaoBanco
	{
		RespostaPersist persist;
		
		setIdMetodo( idMetPai );
		
		persist = new RespostaPersist();
		persist.Inclui(this);

		if ( estruturaXML != null )
			estruturaXML.incluiDB( getIdBanco(), -1 );
	}
}
