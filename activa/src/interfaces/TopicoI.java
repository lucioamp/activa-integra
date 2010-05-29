package interfaces;

import java.util.Collection;

import modelo.Topico;
import modelo.Forum;
import util.CadastroException;

public interface TopicoI {

	public int incluir (Topico topico) throws CadastroException;
	public int alterar (Topico topico) throws CadastroException;
	public int excluir (Topico topico) throws CadastroException;
	public Collection<Topico> consultarTodos() throws CadastroException;
	public Collection<Topico> consultarPorForum(Forum forum) throws CadastroException;
	public Topico consultar(Topico topico) throws CadastroException;
}
