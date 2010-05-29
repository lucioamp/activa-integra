package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.Usuario;
import util.CadastroException;

public interface MembroI {

	public int incluir (Membro membro) throws CadastroException;
	public int alterar (Membro membro) throws CadastroException;
	public int excluir (Membro membro) throws CadastroException;
	public Collection<Membro> consultarTodosMembros() throws CadastroException;
	public Membro consultar(Membro membro) throws CadastroException;
	public boolean isMembro(Usuario usuario) throws CadastroException;
	public boolean isAluno(Usuario usuario) throws CadastroException;
	public boolean isProfessor(Usuario usuario) throws CadastroException;
}
