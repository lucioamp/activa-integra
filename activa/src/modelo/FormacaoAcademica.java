package modelo;

import interfaces.FormacaoAcademicaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.FormacaoAcademicaDAO;

public class FormacaoAcademica {

	private long pkFormacaoAcademica;
	private String nome;
		
	private static FormacaoAcademicaI dao;
	
	public long getPkFormacaoAcademica() {
		return pkFormacaoAcademica;
	}

	public void setPkFormacaoAcademica(long pkFormacaoAcademica) {
		this.pkFormacaoAcademica = pkFormacaoAcademica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private static FormacaoAcademicaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new FormacaoAcademicaDAO();
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

	public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<FormacaoAcademica> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public FormacaoAcademica consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
