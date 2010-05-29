package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.MicroBlog;
import util.CadastroException;

public interface MicroBlogI {

	public int incluir (MicroBlog microBlog) throws CadastroException;
	public int alterar (MicroBlog microBlog) throws CadastroException;
	public int excluir (MicroBlog microBlog) throws CadastroException;
	public Collection<MicroBlog> consultarTodos() throws CadastroException;
	public Collection<MicroBlog> consultarPorMembro(Membro membro) throws CadastroException;
	public void limparStatusPorMembro(Membro membro) throws CadastroException;
	public MicroBlog consultar(MicroBlog microBlog) throws CadastroException;
}
