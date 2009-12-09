package nucleo.nivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AplicacaoDaTurma extends NivelAvance 
{
	protected int idTurma;
	protected int idAplicacao;
	protected String url;
	protected boolean externa;

	/*
	 * Construtor Básico
	 */
	public AplicacaoDaTurma() 
	{
		super();

		idTurma = -1;
		idAplicacao = -1;
	}

	/*
	 * Construtor Básico
	 */
	public AplicacaoDaTurma( String nome, int idResponsavel, int idAplicacao, int idTurma ) 
	{
		super(nome, idResponsavel);

		this.idTurma = idTurma;
		this.idAplicacao = idAplicacao;
	}

	public AplicacaoDaTurma( ResultSet rs ) throws SQLException
	{
		super(rs);

		this.idTurma = rs.getInt("IdTurma");
	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorNomeComNomeResponsavel()
	 */
	public String getSelectPorNomeComNomeResponsavel()
	{
		return "select a.*, at.IdTurma, u.NomeUsuario " +
		   	   "from NPUsuario u, NPAplicacaoTurma AT " +
		   	   "left join NPAplicacao a on at.IdAplicacao = a.Id " +
		   	   "where (a.Nome = ?) and (a.IdResponsavel = u.IdUsuario)";
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdComNomeResponsavel()
	 */
	public String getSelectPorIdComNomeResponsavel()
	{
		return "select a.*, at.IdTurma, u.NomeUsuario " +
	   	   	   "from NPUsuario u, NPAplicacaoTurma AT " +
	   	   	   "left join NPAplicacao a on at.IdAplicacao = a.Id " +
	   	   	   "where (a.Id = ?) and (a.IdResponsavel = u.IdUsuario)";
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdOrigemComNomeResponsavel()
	 */
	public String getSelectPorIdOrigemComNomeResponsavel()
	{
		return  "select a.*, at.IdTurma, u.NomeUsuario " +
	   	   		"from NPUsuario u, NPAplicacaoTurma AT " +
	   	   		"left join NPAplicacao a on at.IdAplicacao = a.Id " +
	   	   		"where (at.IdTurma = ?) and (a.IdResponsavel = u.IdUsuario)";
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getStringInclusao()
	 */
	public String getStringInclusao()
	{
		return "insert into NPAplicacaoTurma" +  
		   	   " (IdAplicacao, IdTurma, DataCadastro)" +
		       " values (?, ?, curdate())"; 
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#preparaStatetementInclusao(java.sql.PreparedStatement)
	 */
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		pst.setInt( 1, getIdAplicacao());
		pst.setInt( 2, getIdTurma());
	}
	
	public int getIdAplicacao()				{ return idAplicacao;		}
	public void setIdAplicacao(int id)		{ idAplicacao = id;			}
	public int getIdTurma()				    { return idTurma;			}
	public void setIdTurma(int id)			{ idTurma = id;				}
	public String getUrl()					{ return url;				}
	public void setUrl(String url)			{ this.url = url; 			}
	public boolean isExterna()				{ return externa; 			}
	public void setExterna(boolean externa) { this.externa = externa;	}
}
