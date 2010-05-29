package modelo;

import interfaces.ArquivoBlogI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ArquivoBlogDAO;

public class ArquivoBlog {

	private long pkArquivoBlog;
	private String nome;
	private Blog blog;
	
	private static ArquivoBlogI dao;

	public ArquivoBlog(){
		this.blog = new Blog();
	}
	
	public long getPkArquivoBlog() {
		return pkArquivoBlog;
	}

	public void setPkArquivoBlog(long pkArquivoBlog) {
		this.pkArquivoBlog = pkArquivoBlog;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	private static ArquivoBlogI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ArquivoBlogDAO();
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
	
	public static Collection<ArquivoBlog> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<ArquivoBlog> consultarPorBlog(Blog blog) throws CadastroException{
		return getDAO().consultarPorBlog(blog);
	}
	
	public ArquivoBlog consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
