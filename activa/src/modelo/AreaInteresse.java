package modelo;

import interfaces.AreaInteresseI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AreaInteresseDAO;

public class AreaInteresse {

	private long pkAreaInteresse;
	private String nome;
	private String descricao;
		
	private static AreaInteresseI dao;
	
	public long getPkAreaInteresse() {
		return pkAreaInteresse;
	}

	public void setPkAreaInteresse(long pkAreaInteresse) {
		this.pkAreaInteresse = pkAreaInteresse;
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

	private static AreaInteresseI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AreaInteresseDAO();
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
	
	public static Collection<AreaInteresse> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public AreaInteresse consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
