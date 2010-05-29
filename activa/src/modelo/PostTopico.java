package modelo;

import interfaces.PostTopicoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.PostTopicoDAO;

public class PostTopico {

	private long pkPostTopico;
	private String descricao;
	private String data;
	private Topico topico;
	private Membro membro;
	
	private static PostTopicoI dao;
	
	public PostTopico(){
		this.topico = new Topico();
		this.membro = new Membro();
	}
	
	public long getPkPostTopico() {
		return pkPostTopico;
	}

	public void setPkPostTopico(long pkPostTopico) {
		this.pkPostTopico = pkPostTopico;
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

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static PostTopicoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new PostTopicoDAO();
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
	
	public static Collection<PostTopico> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<PostTopico> consultarPorTopico(Topico topico) throws CadastroException{
		return getDAO().consultarPorTopico(topico);
	}
	
	public PostTopico consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
