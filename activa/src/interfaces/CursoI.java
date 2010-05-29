package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Curso;
import util.CadastroException;

public interface CursoI {

	public int incluir (Curso curso) throws CadastroException;
	public int alterar (Curso curso) throws CadastroException;
	public int excluir (Curso curso) throws CadastroException;
	public Collection<Curso> consultarTodosCursos() throws CadastroException;
	public Collection<Curso> consultarCursosPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Curso consultar(Curso curso) throws CadastroException;
}
