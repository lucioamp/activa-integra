package interfaces;

import java.util.Collection;

import modelo.CategoriaComunidade;
import util.CadastroException;

public interface CategoriaComunidadeI {

	public int incluir (CategoriaComunidade categoriaComunidade) throws CadastroException;
	public int alterar (CategoriaComunidade categoriaComunidade) throws CadastroException;
	public int excluir (CategoriaComunidade categoriaComunidade) throws CadastroException;
	public Collection<CategoriaComunidade> consultarTodos() throws CadastroException;
	public CategoriaComunidade consultar(CategoriaComunidade categoriaComunidade) throws CadastroException;
}
