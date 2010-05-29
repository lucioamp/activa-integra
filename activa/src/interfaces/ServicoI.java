package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Servico;
import util.CadastroException;

public interface ServicoI {

	public int incluir (Servico servico) throws CadastroException;
	public int alterar (Servico servico) throws CadastroException;
	public int excluir (Servico servico) throws CadastroException;
	public Collection<Servico> consultarTodos() throws CadastroException;
	public Collection<Servico> consultarPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Servico consultar(Servico servico) throws CadastroException;
}
