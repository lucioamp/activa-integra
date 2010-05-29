package interfaces;

import java.util.Collection;

import modelo.Professor;
import util.CadastroException;

public interface ProfessorI {

	public int incluir (Professor professor) throws CadastroException;
	public int excluir (Professor professor) throws CadastroException;
	public Collection<Professor> consultarTodosProfessores() throws CadastroException;
	public Professor consultar(Professor professor) throws CadastroException;
	public boolean isProfessor(Professor professor) throws CadastroException;
}
