package modelo.integra;

import interfaces.integra.ParametroI;
import util.AplicacaoExternaException;
import util.Constantes;
import dao.integra.ParametroDAO;

public class Parametro extends NivelWADL 
{
	private long idParametro;
	private String name;
	private String style;
	private String type;
	private String path;
	private boolean required;

	private int idRecurso;
	private int idMetodo;
	
	// Campos para a edição de mashups
	private boolean usarParametro;
	private String valorPadrao;
	private boolean bloquearValor;
	
	/**
	 * @return the usarParametro
	 */
	public boolean isUsarParametro() {
		return usarParametro;
	}

	/**
	 * @param usarParametro the usarParametro to set
	 */
	public void setUsarParametro(boolean usarParametro) {
		this.usarParametro = usarParametro;
	}

	/**
	 * @return the valorPadrao
	 */
	public String getValorPadrao() {
		return valorPadrao;
	}

	/**
	 * @param valorPadrao the valorPadrao to set
	 */
	public void setValorPadrao(String valorPadrao) {
		this.valorPadrao = valorPadrao;
	}

	/**
	 * @return the bloquearValor
	 */
	public boolean isBloquearValor() {
		return bloquearValor;
	}

	/**
	 * @param bloquearValor the bloquearValor to set
	 */
	public void setBloquearValor(boolean bloquearValor) {
		this.bloquearValor = bloquearValor;
	}

	private String title;
	
	private static ParametroI dao;

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

	public Parametro() 
	{
		super();
		
		name = "";
		style = "";
		type = "";
		path = "";
		required = false;

		idRecurso = -1;
		idMetodo = -1;
	}

	public Parametro( int idAplicacao, String name, String style, 
					  String type,     String path, boolean required ) 
	{
		this.name = name;
		this.style = style;
		this.type = type;
		this.path = path;
		this.required = required;

		idRecurso = -1;
		idMetodo = -1;
	}

	public String getName()						{ return name;					}
	public void setName(String name)			{ this.name = name;				}
	public boolean isRequired()					{ return required;				}
	public void setRequired(boolean required)	{ this.required = required;		}
	public String getStyle()					{ return style;					}
	public void setStyle(String style)			{ this.style = style;			}
	public String getType()						{ return type;					}
	public void setType(String type)			{ this.type = type;				}
	public String getPath()						{ return path;					}
	public void setPath(String path)			{ this.path = path;				}
	public int getIdMetodo()					{ return idMetodo;				}
	public void setIdMetodo(int idMetodo)		{ this.idMetodo = idMetodo;		}
	public int getIdRecurso()					{ return idRecurso;				}
	public void setIdRecurso(int idRecurso)		{ this.idRecurso = idRecurso;	}

	/**
	 * @return the idParametro
	 */
	public long getIdParametro() {
		return idParametro;
	}

	/**
	 * @param idParametro the idParametro to set
	 */
	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	public String toString()
	{
		return "Param='" + name + "' style='" + style + "'" +
			   " type='" + type + "' path='" + path +
			   "' required='" + (required ? "true" : "false") + "'" +
			   " title='" + title + "'";
	}
	
	public void imprime()
	{
		System.out.println("      " + toString() );
	}	
	
	public String getField()
	{
		return "<input ref=\"" + getName() + "\">\n<label>" + 
				getName() + ": </label>\n</input>\n";
	}
	
	private static ParametroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ParametroDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws AplicacaoExternaException{
		getDAO().incluir(this);
		
		return 0;
	}
	
	public static void consultarPorRecurso(Recurso recurso) throws AplicacaoExternaException{
		getDAO().consultarPorRecurso(recurso);
	}
}
