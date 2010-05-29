package interfaces;

import java.util.Collection;

import modelo.Blog;
import modelo.PostBlog;
import util.CadastroException;

public interface PostBlogI {

	public int incluir (PostBlog postBlog) throws CadastroException;
	public int alterar (PostBlog postBlog) throws CadastroException;
	public int excluir (PostBlog postBlog) throws CadastroException;
	public Collection<PostBlog> consultarTodos() throws CadastroException;
	public Collection<PostBlog> consultarPorBlog(Blog blog) throws CadastroException;
	public PostBlog consultar(PostBlog postBlog) throws CadastroException;
}
