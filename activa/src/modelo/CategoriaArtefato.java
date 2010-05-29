package modelo;

import interfaces.CategoriaArtefatoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.CategoriaArtefatoDAO;

public class CategoriaArtefato {

	private long pkCatArtefato;
	private String nome;
	private String descricao;
		
	private static CategoriaArtefatoI dao;
	
	public long getPkCatArtefato() {
		return pkCatArtefato;
	}

	public void setPkCatArtefato(long pkCatArtefato) {
		this.pkCatArtefato = pkCatArtefato;
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

	private static CategoriaArtefatoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new CategoriaArtefatoDAO();
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
	
	public static Collection<CategoriaArtefato> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public CategoriaArtefato consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
