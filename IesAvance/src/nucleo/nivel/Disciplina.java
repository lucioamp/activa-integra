package nucleo.nivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Disciplina extends NivelAvance
{
	protected int cargaHoraria;
	protected String ementa;
	protected int idCurso;
	
	/*
	 * Construtor Básico
	 */
	public Disciplina() 
	{
		super();

		cargaHoraria = 0;
		ementa = "";
		idCurso = -1;
	}

	/*
	 * Construtor Básico
	 */
	public Disciplina( String nome, int idResponsavel,
				  	   int cargaHoraria, String ementa, int idCurso) 
	{
		super(nome, idResponsavel);
		
		this.cargaHoraria = cargaHoraria;
		this.ementa = ementa;
		this.idCurso = idCurso;
	}

	public Disciplina(ResultSet rs) throws SQLException
	{
		super(rs);
		
		cargaHoraria = rs.getInt("CargaHoraria");
		ementa = rs.getString("Ementa");
		idCurso = rs.getInt("IdCurso");
	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorNomeComNomeResponsavel()
	 */
	public String getSelectPorNomeComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPDisciplina", "Nome");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdComNomeResponsavel()
	 */
	public String getSelectPorIdComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPDisciplina", "Id");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdOrigemComNomeResponsavel()
	 */
	public String getSelectPorIdOrigemComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPDisciplina", "IdCurso");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getStringInclusao()
	 */
	public String getStringInclusao()
	{
		return preparaInsert("NPDisciplina", "CargaHoraria, Ementa, IdCurso", "?, ?, ?" ); 
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#preparaStatetementInclusao(java.sql.PreparedStatement)
	 */
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);
		
		pst.setInt(  3, getCargaHoraria());
		pst.setString(  4, getEmenta());
		pst.setInt(  5, getIdCurso());
	}
	
	public int getCargaHoraria() 			{ return cargaHoraria;		}
	public void setCargaHoraria(int cH) 	{ cargaHoraria = cH;		}
	public String getEmenta() 				{ return ementa;			}
	public void setEmenta(String ementa) 	{ this.ementa = ementa;		}
	public int getIdCurso() 				{ return idCurso;			}
	public void setIdCurso(int idCurso) 	{ this.idCurso = idCurso;	}
}
