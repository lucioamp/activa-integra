package modelo;

import interfaces.PostComunidadeI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.PostComunidadeDAO;

public class PostComunidade {

	private long pkPostComunidade;
	private String descricao;
	private String data;
	private Comunidade comunidade;
	private Membro membro;
	
	private static PostComunidadeI dao;
	
	public PostComunidade(){
		this.comunidade = new Comunidade();
		this.membro = new Membro();
	}
	
	public long getPkPostComunidade() {
		return pkPostComunidade;
	}

	public void setPkPostComunidade(long pkPostComunidade) {
		this.pkPostComunidade = pkPostComunidade;
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

	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static PostComunidadeI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new PostComunidadeDAO();
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
	
	public static Collection<PostComunidade> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<PostComunidade> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		return getDAO().consultarPorComunidade(comunidade);
	}
	
	public PostComunidade consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
