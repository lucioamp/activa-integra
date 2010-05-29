package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.AlunoCurso;
import modelo.Curso;
import util.CadastroException;

public interface AlunoCursoI {

	public int incluir (AlunoCurso alunoCurso) throws CadastroException;
	//public int alterar (AlunoCurso alunoCurso) throws CadastroException;
	public int excluir (AlunoCurso alunoCurso) throws CadastroException;
	public int excluirPorCurso (AlunoCurso alunoCurso) throws CadastroException;
	//public Collection<AlunoCurso> consultarTodos() throws CadastroException;
	public AlunoCurso consultar(AlunoCurso alunoCurso) throws CadastroException;
	public Collection<AlunoCurso> consultarPorAluno(Membro aluno) throws CadastroException;
	public Collection<AlunoCurso> consultarPorCurso(Curso curso) throws CadastroException;
}
