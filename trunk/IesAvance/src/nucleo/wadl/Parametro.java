package nucleo.wadl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.banco.ExcecaoBanco;

public class Parametro extends NivelWADL 
{
	private String name;
	private String style;
	private String type;
	private String path;
	private boolean required;

	private int idRecurso;
	private int idMetodo;

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
		super(idAplicacao);
		
		this.name = name;
		this.style = style;
		this.type = type;
		this.path = path;
		this.required = required;

		idRecurso = -1;
		idMetodo = -1;
	}

	public Parametro(ResultSet rs) throws SQLException
	{
		super(rs);
		
		name = rs.getString("Name");
		style = rs.getString("style");
		type = rs.getString("type");
		path = rs.getString("path");
		required = rs.getBoolean("required");

		idRecurso = getIntOrNull(rs, "IdRecurso");
		idMetodo = getIntOrNull(rs, "IdMetodo");
	}

	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);

		pst.setString( 2, getName());
		pst.setString( 3, getStyle());
		pst.setString( 4, getType());
		pst.setString( 5, getPath());
		pst.setBoolean( 6, isRequired());
		setIntOrNull(pst, 7, getIdRecurso());
		setIntOrNull(pst, 8, getIdMetodo());
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

	public String toString()
	{
		return "Param='" + name + "' style='" + style + "'" +
			   " type='" + type + "' path='" + path +
			   "' required='" + (required ? "true" : "false") + "'";
	}
	
	public void imprime()
	{
		System.out.println( toString() );
	}	
	
	public void incluiDB( int id_Recurso, int id_Metodo ) throws ExcecaoBanco
	{
		ParametroPersist persist;
		
		setIdRecurso( id_Recurso );
		setIdMetodo( id_Metodo );
		
		persist = new ParametroPersist();
		persist.Inclui(this);
	}
	
	public String getField()
	{
		return "<input ref=\"" + getName() + "\">\n<label>" + 
				getName() + ": </label>\n</input>\n";
	}
}
