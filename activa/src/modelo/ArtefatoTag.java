package modelo;

import interfaces.ArtefatoTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ArtefatoTagDAO;

public class ArtefatoTag {

	private Artefato artefato;
	private Tag tag;
		
	private static ArtefatoTagI dao;
	
	public ArtefatoTag(){
		this.artefato = new Artefato();
		this.tag = new Tag();
	}
	
	public Artefato getArtefato() {
		return artefato;
	}

	public void setArtefato(Artefato artefato) {
		this.artefato = artefato;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static ArtefatoTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ArtefatoTagDAO();
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
	
	/*public static Collection<ArtefatoTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public ArtefatoTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<ArtefatoTag> consultarPorArtefato(Artefato artefato) throws CadastroException{
		return getDAO().consultarPorArtefato(artefato);
	}
}
