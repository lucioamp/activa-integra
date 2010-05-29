package modelo;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.BairroDAO;
import interfaces.BairroI;

public class Bairro {

	private long pkBairro;
	private String bairro;
	private Municipio municipio;
	
	private static BairroI dao;
	
	public Bairro(){
		this.municipio = new Municipio(); 
	}
	
	public long getPkBairro() {
		return pkBairro;
	}
	public void setPkBairro(long pkBairro) {
		this.pkBairro = pkBairro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	private static BairroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new BairroDAO();
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
	
	public static Collection<Bairro> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public Bairro consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<Bairro> consultarPorMunicipio(Municipio municipio) throws CadastroException{
		return getDAO().consultarPorMunicipio(municipio);
	}
}
