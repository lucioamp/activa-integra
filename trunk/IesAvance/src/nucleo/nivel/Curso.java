package nucleo.nivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Curso extends NivelAvance 
{
	protected String instituicao;

	/*
	 * Construtor Básico
	 */
	public Curso() 
	{
		instituicao = "";
	}

	/*
	 * Construtor Básico
	 */
	public Curso( String nome, int idResponsavel, String instituicao ) 
	{
		super(nome, idResponsavel);
		
		this.instituicao = instituicao;
	}

	public Curso(ResultSet rs) throws SQLException
	{
		super(rs);
		
		instituicao = rs.getString("Instituicao");
	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorNomeComNomeResponsavel()
	 */
	public String getSelectPorNomeComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPCurso", "Nome");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdComNomeResponsavel()
	 */
	public String getSelectPorIdComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPCurso", "Id");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdOrigemComNomeResponsavel()
	 */
	public String getSelectPorIdOrigemComNomeResponsavel()
	{
		return "select n.*, u.NomeUsuario " +
		   	   "from NPCurso n, NPUsuario u " + 
		   	   "where n.IdResponsavel = u.IdUsuario";
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getStringInclusao()
	 */
	public String getStringInclusao()
	{
		return preparaInsert("NPCurso", "Instituicao", "?" ); 
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#preparaStatetementInclusao(java.sql.PreparedStatement)
	 */
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);
		
		pst.setString(  3, getInstituicao());
	}
	
	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
}
