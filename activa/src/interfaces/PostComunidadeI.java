package interfaces;

import java.util.Collection;

import modelo.PostComunidade;
import modelo.Comunidade;
import util.CadastroException;

public interface PostComunidadeI {

	public int incluir (PostComunidade postComunidade) throws CadastroException;
	public int alterar (PostComunidade postComunidade) throws CadastroException;
	public int excluir (PostComunidade postComunidade) throws CadastroException;
	public Collection<PostComunidade> consultarTodos() throws CadastroException;
	public Collection<PostComunidade> consultarPorComunidade(Comunidade comunidade) throws CadastroException;
	public PostComunidade consultar(PostComunidade postComunidade) throws CadastroException;
}
