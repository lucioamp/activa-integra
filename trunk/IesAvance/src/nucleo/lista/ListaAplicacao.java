package nucleo.lista;

import java.sql.ResultSet;
import java.sql.SQLException;

import nucleo.nivel.Aplicacao;
import nucleo.nivel.NivelAvance;

public class ListaAplicacao extends ListaNivelAvance 
{
	static final long serialVersionUID = 1; 

	@Override
	NivelAvance criaNivel(ResultSet rs) throws SQLException {
		return new Aplicacao( rs );
	}

}
