package modelo;

import interfaces.AlunoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AlunoDAO;

public class Aluno extends Membro {

	private static AlunoI dao;
	
	private static AlunoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AlunoDAO();
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

	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Aluno> consultarTodosAlunos() throws CadastroException{
		return getDAO().consultarTodosAlunos();
	}
	
	public Aluno consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static boolean isAluno(Aluno aluno) throws CadastroException{
		return getDAO().isAluno(aluno);
	}
}
