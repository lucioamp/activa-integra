package modelo;

import interfaces.AlunoAulaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AlunoAulaDAO;

public class AlunoAula {

	private Membro aluno;
	private Aula aula;
			
	private static AlunoAulaI dao;
	
	public AlunoAula(){
		this.aluno = new Membro();
		this.aula = new Aula();
	}
	
	public Membro getAluno() {
		return aluno;
	}

	public void setAluno(Membro aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	private static AlunoAulaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AlunoAulaDAO();
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
	
	public int excluirPorAula() throws CadastroException{
		return getDAO().excluirPorAula(this);
	}
	
	/*public static Collection<AlunoAula> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public AlunoAula consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<AlunoAula> consultarPorAluno(Membro aluno) throws CadastroException{
		return getDAO().consultarPorAluno(aluno);
	}
	
	public static Collection<AlunoAula> consultarPorAula(Aula aula) throws CadastroException{
		return getDAO().consultarPorAula(aula);
	}
}
