package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.AlunoAula;
import modelo.Aula;
import util.CadastroException;

public interface AlunoAulaI {

	public int incluir (AlunoAula alunoAula) throws CadastroException;
	//public int alterar (AlunoAula alunoAula) throws CadastroException;
	public int excluir (AlunoAula alunoAula) throws CadastroException;
	public int excluirPorAula (AlunoAula alunoAula) throws CadastroException;
	//public Collection<AlunoAula> consultarTodos() throws CadastroException;
	public AlunoAula consultar(AlunoAula alunoAula) throws CadastroException;
	public Collection<AlunoAula> consultarPorAluno(Membro aluno) throws CadastroException;
	public Collection<AlunoAula> consultarPorAula(Aula aula) throws CadastroException;
}
