package modelo;

import interfaces.ProfessorI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ProfessorDAO;

public class Professor extends Membro {

	private static ProfessorI dao;
	
	private static ProfessorI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ProfessorDAO();
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
	
	public static Collection<Professor> consultarTodosProfessores() throws CadastroException{
		return getDAO().consultarTodosProfessores();
	}
	
	public Professor consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static boolean isProfessor(Professor professor) throws CadastroException{
		return getDAO().isProfessor(professor);
	}
}
