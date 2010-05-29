package modelo;

import interfaces.TopicoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.TopicoDAO;

public class Topico {

	private long pkTopico;
	private String descricao;
	private String data;
	private Forum forum;
	private Membro membro;
	
	private static TopicoI dao;
	
	public Topico(){
		this.forum = new Forum();
		this.membro = new Membro();
	}
	
	public long getPkTopico() {
		return pkTopico;
	}

	public void setPkTopico(long pkTopico) {
		this.pkTopico = pkTopico;
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

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static TopicoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new TopicoDAO();
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
	
	public static Collection<Topico> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Topico> consultarPorForum(Forum forum) throws CadastroException{
		return getDAO().consultarPorForum(forum);
	}
	
	public Topico consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
