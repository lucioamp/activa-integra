package modelo.integra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class NivelWADL 
{
	public NivelWADL() 
	{
		super();
	}
	
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
	
}
