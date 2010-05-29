package interfaces;

import java.util.Collection;

import modelo.Tarefa;
import modelo.Etapa;
import util.CadastroException;

public interface TarefaI {

	public int incluir (Tarefa visao) throws CadastroException;
	public int alterar (Tarefa visao) throws CadastroException;
	public int excluir (Tarefa visao) throws CadastroException;
	public int excluirPorEtapa (Etapa etapa) throws CadastroException;
	public Collection<Tarefa> consultarTodos() throws CadastroException;
	public Collection<Tarefa> consultarPorVisao(Etapa visao) throws CadastroException;
	public Tarefa consultar(Tarefa visao) throws CadastroException;
}
