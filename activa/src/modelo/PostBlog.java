package modelo;

import interfaces.PostBlogI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.PostBlogDAO;

public class PostBlog {

	private long pkPostBlog;
	private String descricao;
	private String data;
	private Blog blog;
	private Membro membro;
	
	private static PostBlogI dao;
	
	public PostBlog(){
		this.blog = new Blog();
		this.membro = new Membro();
	}
	
	public long getPkPostBlog() {
		return pkPostBlog;
	}

	public void setPkPostBlog(long pkPostBlog) {
		this.pkPostBlog = pkPostBlog;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static PostBlogI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new PostBlogDAO();
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
	
	public static Collection<PostBlog> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<PostBlog> consultarPorBlog(Blog blog) throws CadastroException{
		return getDAO().consultarPorBlog(blog);
	}
	
	public PostBlog consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
