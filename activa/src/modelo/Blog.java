package modelo;

import interfaces.BlogI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.BlogDAO;

public class Blog extends Servico{

	private static BlogI dao;

	private static BlogI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new BlogDAO();
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
	
	public static Collection<Blog> consultarTodosBlogs() throws CadastroException{
		return getDAO().consultarTodosBlogs();
	}
	
	public static Collection<Blog> consultarBlogsPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarBlogsPorAmbiente(ambiente);
	}
	
	public Blog consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
