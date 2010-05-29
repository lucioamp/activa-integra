package modelo;

import interfaces.AlunoCursoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AlunoCursoDAO;

public class AlunoCurso {

	private Membro aluno;
	private Curso curso;
			
	private static AlunoCursoI dao;
	
	public AlunoCurso(){
		this.aluno = new Membro();
		this.curso = new Curso();
	}
	
	public Membro getAluno() {
		return aluno;
	}

	public void setAluno(Membro aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	private static AlunoCursoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AlunoCursoDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws CadastroException{
		return getDAO().incluir(this);
	}

	/*public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}*/
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public int excluirPorCurso() throws CadastroException{
		return getDAO().excluirPorCurso(this);
	}
	
	/*public static Collection<AlunoCurso> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public AlunoCurso consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<AlunoCurso> consultarPorAluno(Membro aluno) throws CadastroException{
		return getDAO().consultarPorAluno(aluno);
	}
	
	public static Collection<AlunoCurso> consultarPorCurso(Curso curso) throws CadastroException{
		return getDAO().consultarPorCurso(curso);
	}
}
