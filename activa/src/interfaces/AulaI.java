package interfaces;

import java.util.Collection;

import modelo.Aula;
import modelo.Curso;
import util.CadastroException;

public interface AulaI {

	public int incluir (Aula aula) throws CadastroException;
	public int alterar (Aula aula) throws CadastroException;
	public int excluir (Aula aula) throws CadastroException;
	public int excluirPorCurso(Aula aula) throws CadastroException;
	public Collection<Aula> consultarTodos() throws CadastroException;
	public Collection<Aula> consultarPorCurso(Curso curso) throws CadastroException;
	public Aula consultar(Aula aula) throws CadastroException;
}
