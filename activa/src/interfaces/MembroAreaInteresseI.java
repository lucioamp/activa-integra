package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.MembroAreaInteresse;
import util.CadastroException;

public interface MembroAreaInteresseI {

	public int incluir (MembroAreaInteresse membroAreaInteresse) throws CadastroException;
	//public int alterar (MembroAreaInteresse membroAreaInteresse) throws CadastroException;
	public int excluir (MembroAreaInteresse membroAreaInteresse) throws CadastroException;
	//public Collection<MembroAreaInteresse> consultarTodos() throws CadastroException;
	public MembroAreaInteresse consultar(MembroAreaInteresse membroAreaInteresse) throws CadastroException;
	public Collection<MembroAreaInteresse> consultarPorMembro(Membro membro) throws CadastroException;
}
