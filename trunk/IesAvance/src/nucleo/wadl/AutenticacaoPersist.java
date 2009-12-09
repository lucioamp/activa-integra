package nucleo.wadl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoPersist extends NivelWADLPersist {

	@Override
	NivelWADL createObjNivel(ResultSet rs) throws SQLException 
	{
		return new Autenticacao(rs);	
	}

	@Override
	String getNomeTabela()			{ return "NPAutenticacao";		}

	@Override
	protected String getStringInclusao() {
		return "insert into " + getNomeTabela() + 
		   " (IdAplicacao, Name, Style, Type, AuthMode)" +
	       " values (?, ?, ?, ?, ?)";
	}

}
