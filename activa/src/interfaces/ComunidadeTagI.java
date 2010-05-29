package interfaces;

import java.util.Collection;

import modelo.Comunidade;
import modelo.ComunidadeTag;
import util.CadastroException;

public interface ComunidadeTagI {

	public int incluir (ComunidadeTag comunidadeTag) throws CadastroException;
	//public int alterar (ComunidadeTag comunidadeTag) throws CadastroException;
	public int excluir (ComunidadeTag comunidadeTag) throws CadastroException;
	//public Collection<ComunidadeTag> consultarTodos() throws CadastroException;
	public ComunidadeTag consultar(ComunidadeTag comunidadeTag) throws CadastroException;
	public Collection<ComunidadeTag> consultarPorComunidade(Comunidade comunidade) throws CadastroException;
}
