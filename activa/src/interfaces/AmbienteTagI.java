package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.AmbienteTag;
import util.CadastroException;

public interface AmbienteTagI {

	public int incluir (AmbienteTag ambienteTag) throws CadastroException;
	//public int alterar (AmbienteTag ambienteTag) throws CadastroException;
	public int excluir (AmbienteTag ambienteTag) throws CadastroException;
	//public Collection<AmbienteTag> consultarTodos() throws CadastroException;
	public AmbienteTag consultar(AmbienteTag ambienteTag) throws CadastroException;
	public Collection<AmbienteTag> consultarPorAmbiente(Ambiente ambiente) throws CadastroException;
}
