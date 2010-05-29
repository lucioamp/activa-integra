package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.MembroTag;
import util.CadastroException;

public interface MembroTagI {

	public int incluir (MembroTag membroTag) throws CadastroException;
	//public int alterar (MembroTag membroTag) throws CadastroException;
	public int excluir (MembroTag membroTag) throws CadastroException;
	//public Collection<MembroTag> consultarTodos() throws CadastroException;
	public MembroTag consultar(MembroTag membroTag) throws CadastroException;
	public Collection<MembroTag> consultarPorMembro(Membro membro) throws CadastroException;
}
