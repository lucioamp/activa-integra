package modelo;

import interfaces.CategoriaComunidadeI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.CategoriaComunidadeDAO;

public class CategoriaComunidade {

	private long pkCatComunidade;
	private String nome;
	private String descricao;
		
	private static CategoriaComunidadeI dao;
	
	public long getPkCatComunidade() {
		return pkCatComunidade;
	}

	public void setPkCatComunidade(long pkCatComunidade) {
		this.pkCatComunidade = pkCatComunidade;
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

	private static CategoriaComunidadeI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new CategoriaComunidadeDAO();
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
	
	public static Collection<CategoriaComunidade> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public CategoriaComunidade consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
