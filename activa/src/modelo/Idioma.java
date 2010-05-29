package modelo;

import interfaces.IdiomaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.IdiomaDAO;

public class Idioma {

	private long pkIdioma;
	private String nome;
		
	private static IdiomaI dao;
	
	public long getPkIdioma() {
		return pkIdioma;
	}

	public void setPkIdioma(long pkIdioma) {
		this.pkIdioma = pkIdioma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private static IdiomaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new IdiomaDAO();
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
	
	public static Collection<Idioma> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public Idioma consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
