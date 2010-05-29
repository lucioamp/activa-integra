package interfaces;

import java.util.Collection;

import modelo.Aluno;
import util.CadastroException;

public interface AlunoI {

	public int incluir (Aluno aluno) throws CadastroException;
	public int excluir (Aluno aluno) throws CadastroException;
	public Collection<Aluno> consultarTodosAlunos() throws CadastroException;
	public Aluno consultar(Aluno aluno) throws CadastroException;
	public boolean isAluno(Aluno aluno) throws CadastroException;
}
