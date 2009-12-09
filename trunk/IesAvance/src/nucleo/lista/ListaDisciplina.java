package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.Disciplina;

public class ListaDisciplina extends ListaNivelAvance 
{
	static final long serialVersionUID = 1; 

	@Override
	Disciplina criaNivel( ResultSet rs ) throws SQLException
	{
		return new Disciplina( rs );
	}
}
