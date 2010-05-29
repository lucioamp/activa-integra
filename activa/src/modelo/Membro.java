package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroDAO;
import interfaces.MembroI;
import modelo.Membro;

public class Membro extends Usuario {

	private FormacaoAcademica formacaoAcademica;
	private String permissao;
	
	private static MembroI dao;
	
	public Membro(){
		this.formacaoAcademica = new FormacaoAcademica();
	}
	
	public FormacaoAcademica getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}
	
	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	private static MembroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroDAO();
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
	
	public static Collection<Membro> consultarTodosMembros() throws CadastroException{
		return getDAO().consultarTodosMembros();
	}
	
	public Membro consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static boolean isMembro(Usuario usuario) throws CadastroException{
		return getDAO().isMembro(usuario);
	}
	
	public static boolean isAluno(Usuario usuario) throws CadastroException{
		return getDAO().isAluno(usuario);
	}
	
	public static boolean isProfessor(Usuario usuario) throws CadastroException{
		return getDAO().isProfessor(usuario);
	}
}
