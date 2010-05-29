package modelo;

import interfaces.MicroBlogI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MicroBlogDAO;

public class MicroBlog {

	private long pkMicroBlog;
	private String descricao;
	private Membro membro;
	private int st_atual;
		
	private static MicroBlogI dao;
	
	public MicroBlog(){
		this.membro = new Membro();
	}
	
	public long getPkMicroBlog() {
		return pkMicroBlog;
	}

	public void setPkMicroBlog(long pkMicroBlog) {
		this.pkMicroBlog = pkMicroBlog;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static MicroBlogI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MicroBlogDAO();
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
	
	public static Collection<MicroBlog> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<MicroBlog> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
	
	public static void limparStatusPorMembro(Membro membro) throws CadastroException{
		getDAO().limparStatusPorMembro(membro);
	}
	
	public MicroBlog consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

	public void setSt_atual(int st_stual) {
		this.st_atual = st_stual;
	}

	public int getSt_atual() {
		return st_atual;
	}
	
	public boolean selecionado()
	{
		return st_atual == 1;
	}
}
