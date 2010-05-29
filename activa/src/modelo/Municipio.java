package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MunicipioDAO;
import interfaces.MunicipioI;

public class Municipio {

	private long pkMunicipio;
	private String municipio;
	private Uf uf;
	
	private static MunicipioI dao;
	
	public Municipio(){
		this.uf = new Uf(); 
	}
	
	public long getPkMunicipio() {
		return pkMunicipio;
	}
	public void setPkMunicipio(long pkMunicipio) {
		this.pkMunicipio = pkMunicipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	private static MunicipioI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MunicipioDAO();
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
	
	public static Collection<Municipio> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public Municipio consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<Municipio> consultarPorUf(Uf uf) throws CadastroException{
		return getDAO().consultarPorUf(uf);
	}
}
