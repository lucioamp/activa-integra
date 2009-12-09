package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.AplicacaoDaTurma;

public class ListaAplicacaoDaTurma extends ListaNivelAvance 
{
	static final long serialVersionUID = 1; 

	@Override
	AplicacaoDaTurma criaNivel( ResultSet rs ) throws SQLException
	{
		return new AplicacaoDaTurma( rs );
	}
}
