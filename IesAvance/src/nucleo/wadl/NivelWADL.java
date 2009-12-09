package nucleo.wadl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class NivelWADL 
{
	private int idBanco;
	private int idAplicacao;
	
	public NivelWADL() 
	{
		super();
		
		idBanco = -1;
		idAplicacao = -1;
	}
	
	public NivelWADL( int idAplicacao ) 
	{
		super();
		
		idBanco = -1;
		this.idAplicacao = idAplicacao;
	}

	public NivelWADL(ResultSet rs) throws SQLException
	{
		super();
		
		idBanco = rs.getInt("IdBanco");
		idAplicacao = rs.getInt("IdAplicacao");
	}

	public int getIdAplicacao()					{ return idAplicacao;				}
	public void setIdAplicacao(int idAplicacao) { this.idAplicacao = idAplicacao;	}
	public int getIdBanco()						{ return idBanco;					}
	public void setIdBanco(int idBanco)			{ this.idBanco = idBanco;			}

	public static int getIntOrNull(ResultSet rs, String campo) throws SQLException
	{
		int idLido;

		idLido = rs.getInt(campo);
		return idLido != 0 ? idLido : -1;
	}
	
	public static void setIntOrNull(PreparedStatement pst, int pos, int id) throws SQLException
	{
		if ( id == -1 )
			pst.setNull( pos, java.sql.Types.INTEGER );
		else
			pst.setInt( pos, id);
	}
	
	void preparaStatetementInclusao(PreparedStatement pst) throws SQLException
	{
		pst.setInt( 1, getIdAplicacao());
	}
}
