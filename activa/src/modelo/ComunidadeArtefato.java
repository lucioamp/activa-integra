package modelo;

import interfaces.ComunidadeArtefatoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ComunidadeArtefatoDAO;

public class ComunidadeArtefato {

	private Comunidade comunidade;
	private Artefato artefato;
			
	private static ComunidadeArtefatoI dao;
	
	public ComunidadeArtefato(){
		this.comunidade = new Comunidade();
		this.artefato = new Artefato();
	}
	
	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public Artefato getArtefato() {
		return artefato;
	}

	public void setArtefato(Artefato artefato) {
		this.artefato = artefato;
	}

	private static ComunidadeArtefatoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ComunidadeArtefatoDAO();
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

	/*public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}*/
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	/*public static Collection<ComunidadeArtefato> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public ComunidadeArtefato consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<ComunidadeArtefato> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		return getDAO().consultarPorComunidade(comunidade);
	}
	
	public Collection<ComunidadeArtefato> consultarPorArtefato(Artefato artefato) throws CadastroException{
		return getDAO().consultarPorArtefato(artefato);
	}
}
