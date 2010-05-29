package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Blog;
import util.CadastroException;

public interface BlogI {

	public int incluir (Blog blog) throws CadastroException;
	public int alterar (Blog blog) throws CadastroException;
	public int excluir (Blog blog) throws CadastroException;
	public Collection<Blog> consultarTodosBlogs() throws CadastroException;
	public Collection<Blog> consultarBlogsPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Blog consultar(Blog blog) throws CadastroException;
}
