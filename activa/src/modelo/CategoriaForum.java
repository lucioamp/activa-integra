package modelo;

import interfaces.CategoriaForumI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.CategoriaForumDAO;

public class CategoriaForum {

	private long pkCatForum;
	private String nome;
	private String descricao;
	private Etapa etapa;
		
	private static CategoriaForumI dao;
	
	public CategoriaForum(){
		this.etapa = new Etapa();
	}
	
	public long getPkCatForum() {
		return pkCatForum;
	}

	public void setPkCatForum(long pkCatForum) {
		this.pkCatForum = pkCatForum;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	private static CategoriaForumI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new CategoriaForumDAO();
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
	
	public static Collection<CategoriaForum> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<CategoriaForum> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarPorAmbiente(ambiente);
	}
	
	public CategoriaForum consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
