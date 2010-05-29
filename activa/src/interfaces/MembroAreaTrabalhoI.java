package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.MembroAreaTrabalho;
import util.CadastroException;

public interface MembroAreaTrabalhoI {

	public int incluir (MembroAreaTrabalho membroAreaTrabalho) throws CadastroException;
	//public int alterar (MembroAreaTrabalho membroAreaTrabalho) throws CadastroException;
	public int excluir (MembroAreaTrabalho membroAreaTrabalho) throws CadastroException;
	//public Collection<MembroAreaTrabalho> consultarTodos() throws CadastroException;
	public MembroAreaTrabalho consultar(MembroAreaTrabalho membroAreaTrabalho) throws CadastroException;
	public Collection<MembroAreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException;
}
