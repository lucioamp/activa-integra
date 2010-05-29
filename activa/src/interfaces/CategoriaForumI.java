package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.CategoriaForum;
import util.CadastroException;

public interface CategoriaForumI {

	public int incluir (CategoriaForum categoriaForum) throws CadastroException;
	public int alterar (CategoriaForum categoriaForum) throws CadastroException;
	public int excluir (CategoriaForum categoriaForum) throws CadastroException;
	public Collection<CategoriaForum> consultarTodos() throws CadastroException;
	public Collection<CategoriaForum> consultarPorAmbiente(Ambiente ambiente) throws CadastroException;
	public CategoriaForum consultar(CategoriaForum categoriaForum) throws CadastroException;
}
