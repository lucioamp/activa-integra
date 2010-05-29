package interfaces;

import java.util.Collection;

import modelo.PostTopico;
import modelo.Topico;
import util.CadastroException;

public interface PostTopicoI {

	public int incluir (PostTopico postTopico) throws CadastroException;
	public int alterar (PostTopico postTopico) throws CadastroException;
	public int excluir (PostTopico postTopico) throws CadastroException;
	public Collection<PostTopico> consultarTodos() throws CadastroException;
	public Collection<PostTopico> consultarPorTopico(Topico topico) throws CadastroException;
	public PostTopico consultar(PostTopico postTopico) throws CadastroException;
}
