package interfaces;

import java.util.Collection;

import modelo.CategoriaForum;
import modelo.Forum;
import util.CadastroException;

public interface ForumI {

	public int incluir (Forum Forum) throws CadastroException;
	public int alterar (Forum Forum) throws CadastroException;
	public int excluir (Forum Forum) throws CadastroException;
	public Collection<Forum> consultarTodosForuns() throws CadastroException;
	public Collection<Forum> consultarPorCategoria(CategoriaForum categoriaForum) throws CadastroException;
	public Forum consultar(Forum Forum) throws CadastroException;
}
