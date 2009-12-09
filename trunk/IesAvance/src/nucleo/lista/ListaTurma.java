package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.Turma;

public class ListaTurma extends ListaNivelAvance 
{
	static final long serialVersionUID = 1; 

	@Override
	Turma criaNivel( ResultSet rs ) throws SQLException
	{
		return new Turma( rs );
	}
}
