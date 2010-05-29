package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Comunidade;
import util.CadastroException;

public interface ComunidadeI {

	public int incluir (Comunidade comunidade) throws CadastroException;
	public int alterar (Comunidade comunidade) throws CadastroException;
	public int excluir (Comunidade comunidade) throws CadastroException;
	public Collection<Comunidade> consultarTodosComunidades() throws CadastroException;
	public Collection<Comunidade> consultarComunidadesPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Comunidade consultar(Comunidade comunidade) throws CadastroException;
}
