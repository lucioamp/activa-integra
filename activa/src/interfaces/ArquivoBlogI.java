package interfaces;

import java.util.Collection;

import modelo.ArquivoBlog;
import modelo.Blog;
import util.CadastroException;

public interface ArquivoBlogI {

	public int incluir (ArquivoBlog arquivoBlog) throws CadastroException;
	public int alterar (ArquivoBlog arquivoBlog) throws CadastroException;
	public int excluir (ArquivoBlog arquivoBlog) throws CadastroException;
	public Collection<ArquivoBlog> consultarTodos() throws CadastroException;
	public Collection<ArquivoBlog> consultarPorBlog(Blog blog) throws CadastroException;
	public ArquivoBlog consultar(ArquivoBlog arquivoBlog) throws CadastroException;
}
