package nucleo.nivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

abstract public class NivelAvance extends Nivel 
{
	int id;
	String nome;
	int idResponsavel;
	String nomeResponsavel;
	Date dataCadastro;
	
	public NivelAvance() 
	{
		super();

		id = -1;
		nome = "";
		idResponsavel = -1;
		nomeResponsavel = "";
	}

	/*
	 * Construtor Básico
	 */
	public NivelAvance( String nome, int idResponsavel ) 
	{
		super();
		
		this.id = -1;
		this.nome = nome;
		this.idResponsavel = idResponsavel;
		this.dataCadastro = new Date();

		// É mais seguro fazer a definição do nome do Responsavel 
		// posteriormente (campo não faz parte da tabela)
		this.nomeResponsavel = "";
	}

	/*
	 * Funções que manipulam result set de uma query SQL
	 */
	public NivelAvance( ResultSet rs ) throws SQLException
	{
		Date dt, tm;
		
		id   = rs.getInt("Id");
		nome = rs.getString("Nome");
		idResponsavel = rs.getInt("IdResponsavel");

		dt = rs.getDate("DataCadastro");
		tm = rs.getTime("DataCadastro");
		
		dataCadastro = new Date( dt.getTime() + tm.getTime() );

		// É mais seguro fazer a definição do nome do Responsavel 
		// posteriormente (campo não faz parte da tabela)
		nomeResponsavel = "";
	}

	public void setNomeResponsavel( ResultSet rs ) throws SQLException		
	{
		nomeResponsavel = rs.getString("NomeUsuario");
	}

	static public String getNome( ResultSet rs ) throws SQLException
	{ 
		return rs.getString("Nome");				
	}
	
	abstract public String getSelectPorNomeComNomeResponsavel();
	abstract public String getSelectPorIdComNomeResponsavel();
	abstract public String getSelectPorIdOrigemComNomeResponsavel();
	abstract public String getStringInclusao();
	
	protected String preparaSelectComNomeResponsavel(String nomeTabela, String campo)
	{
		return "select n.*, u.NomeUsuario " +
			   "from " + nomeTabela + " n, NPUsuario u " + 
			   "where (" + campo + " = ?) and (n.IdResponsavel = u.IdUsuario)";
	}

	protected String preparaInsert(String nomeTabela, String campos, String values)
	{
		return "insert into " + nomeTabela + 
			   " (Nome, IdResponsavel, " + campos + ", DataCadastro)" +
			   " values (?, ?, " + values + ", curdate())";
	}
	
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		pst.setString( 1, nome);
		pst.setInt( 2, idResponsavel);
	}

	/*
	 * Getters e Setters
	 */
	public int getId()								{ return id;				}
	public void setId(int id)						{ this.id = id;				}
	public String getNome()							{ return nome;				}
	public void setNome(String nome)				{ this.nome = nome;			}
	public int getIdResponsavel()					{ return idResponsavel;					}
	public void setIdResponsavel(int idResponsavel)	{ this.idResponsavel = idResponsavel;	}
	public String getNomeResponsavel()				{ return nomeResponsavel;			}
	public void setNomeResponsavel(String nome)		{ nomeResponsavel = nome;			}
	public Date getDataCadastro()					{ return dataCadastro;		}
	public void setDataCadastro(Date inclusao)		{ dataCadastro = inclusao;	}
}
