package nucleo.nivel;

import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Turma extends NivelAvance 
{
	protected Date dataInicio;
	protected Date dataFinal;
	protected int idDisciplina;
	protected int idProfessor;
	protected int idTutor;
	
	/*
	 * Construtor Básico
	 */
	public Turma() 
	{
		super();

		dataInicio = new Date();
		dataFinal = new Date();
		idDisciplina = -1;
		idProfessor = -1;
		idTutor = -1;
	}

	/*
	 * Construtor Básico
	 */
	public Turma( String nome, int idResponsavel, Date dataInicio, Date dataFinal,
			      int idProfessor, int idTutor, int idDisciplina ) 
	{
		super(nome, idResponsavel);

		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.idDisciplina = idDisciplina;
		this.idProfessor = idProfessor;
		this.idTutor = idTutor;
	}

	public Turma( ResultSet rs ) throws SQLException
	{
		super(rs);

		this.dataInicio = rs.getDate("DataInicio");
		this.dataFinal = rs.getDate("DataFinal");
		this.idDisciplina = rs.getInt("IdDisciplina");
		this.idProfessor = rs.getInt("IdProfessor");
		this.idTutor = rs.getInt("IdTutor");
	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorNomeComNomeResponsavel()
	 */
	public String getSelectPorNomeComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPTurma", "Nome");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdComNomeResponsavel()
	 */
	public String getSelectPorIdComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPTurma", "Id");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdOrigemComNomeResponsavel()
	 */
	public String getSelectPorIdOrigemComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPTurma", "IdDisciplina");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getStringInclusao()
	 */
	public String getStringInclusao()
	{
		return preparaInsert("NPTurma", "DataInicio, DataFinal, IdDisciplina, IdProfessor, IdTutor", 
							 "?, ?, ?, ?, ?" ); 
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#preparaStatetementInclusao(java.sql.PreparedStatement)
	 */
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);
		
		pst.setDate(  3, new java.sql.Date(getDataInicio().getTime()) );
		pst.setDate(  4, new java.sql.Date(getDataFinal().getTime()) );
		pst.setInt(  5, getIdDisciplina());
		pst.setInt(  6, getIdProfessor());
		if ( getIdTutor() != -1 )
			pst.setInt(  7,  getIdTutor() );
		else
			pst.setNull(7, java.sql.Types.INTEGER);
	}

	public Date getDataInicio() 				    { return dataInicio;				}
	public void setDataInicio(Date dataInicio) 	    { this.dataInicio = dataInicio;		}
	public Date getDataFinal() 					    { return dataFinal;					}
	public void setDataFinal(Date dataFinal) 	    { this.dataFinal = dataFinal;		}
	public int getIdDisciplina() 				    { return idDisciplina;				}
	public void setIdDisciplina(int idDisciplina)   { this.idDisciplina = idDisciplina;	}
	public int getIdProfessor() 				    { return idProfessor;				}
	public void setIdProfessor(int idProfessor)     { this.idProfessor = idProfessor;	}
	public int getIdTutor() 					    { return idTutor;					}
	public void setIdTutor(int idTutor) 		    { this.idTutor = idTutor;			}
}
