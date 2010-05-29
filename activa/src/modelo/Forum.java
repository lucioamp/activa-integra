package modelo;

import interfaces.ForumI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ForumDAO;

public class Forum extends Servico{

	private CategoriaForum categoriaForum;
	private Tarefa tarefa;
	
	private static ForumI dao;
	
	public Forum(){
		this.categoriaForum = new CategoriaForum();
		this.tarefa = new Tarefa();
	}
	
	public CategoriaForum getCategoriaForum() {
		return categoriaForum;
	}

	public void setCategoriaForum(CategoriaForum categoriaForum) {
		this.categoriaForum = categoriaForum;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	private static ForumI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ForumDAO();
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
	
	public static Collection<Forum> consultarTodosForuns() throws CadastroException{
		return getDAO().consultarTodosForuns();
	}
	
	public static Collection<Forum> consultarPorCategoria(CategoriaForum categoriaForum) throws CadastroException{
		return getDAO().consultarPorCategoria(categoriaForum);
	}
	
	public Forum consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
