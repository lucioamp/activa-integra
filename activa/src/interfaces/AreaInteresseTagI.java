package interfaces;

import java.util.Collection;

import modelo.AreaInteresse;
import modelo.AreaInteresseTag;
import util.CadastroException;

public interface AreaInteresseTagI {

	public int incluir (AreaInteresseTag areaInteresseTag) throws CadastroException;
	//public int alterar (AreaInteresseTag areaInteresseTag) throws CadastroException;
	public int excluir (AreaInteresseTag areaInteresseTag) throws CadastroException;
	//public Collection<AreaInteresseTag> consultarTodos() throws CadastroException;
	public AreaInteresseTag consultar(AreaInteresseTag areaInteresseTag) throws CadastroException;
	public Collection<AreaInteresseTag> consultarPorAreaInteresse(AreaInteresse areaInteresse) throws CadastroException;
}
