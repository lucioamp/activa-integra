package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.UfDAO;
import interfaces.UfI;

public class Uf {

	private long pkEstado;
	private String estado;
	private String sigla;
	
	private static UfI dao;
	
	public long getPkEstado() {
		return pkEstado;
	}
	public void setPkEstado(long pkEstado) {
		this.pkEstado = pkEstado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	private static UfI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new UfDAO();
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
	
	public static Collection<Uf> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public Uf consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

}
