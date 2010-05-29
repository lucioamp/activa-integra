package interfaces;

import java.util.Collection;

//import modelo.Membro;
import modelo.MembroAmbiente;
import modelo.Usuario;
import util.CadastroException;

public interface MembroAmbienteI {

	public int incluir (MembroAmbiente membroAmbiente) throws CadastroException;
	//public int alterar (MembroAmbiente membroAmbiente) throws CadastroException;
	public int excluir (MembroAmbiente membroAmbiente) throws CadastroException;
	//public Collection<MembroAmbiente> consultarTodos() throws CadastroException;
	public MembroAmbiente consultar(MembroAmbiente membroAmbiente) throws CadastroException;
	public Collection<MembroAmbiente> consultarPorMembro(Usuario membro) throws CadastroException;
}
