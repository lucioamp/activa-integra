package modelo;

import interfaces.TagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.TagDAO;

public class Tag {

	private long pkTag;
	private String nome;
	private String descricao;
		
	private static TagI dao;
	
	public long getPkTag() {
		return pkTag;
	}

	public void setPkTag(long pkTag) {
		this.pkTag = pkTag;
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

	private static TagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new TagDAO();
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
	
	public static Collection<Tag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public Tag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
