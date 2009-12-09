package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.Curso;

public class ListaCurso extends ListaNivelAvance 
{
	static final long serialVersionUID = 1; 

	@Override
	Curso criaNivel( ResultSet rs ) throws SQLException
	{
		return new Curso( rs );
	}

}
