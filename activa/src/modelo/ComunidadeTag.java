package modelo;

import interfaces.ComunidadeTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ComunidadeTagDAO;

public class ComunidadeTag {

	private Comunidade comunidade;
	private Tag tag;
		
	private static ComunidadeTagI dao;
	
	public ComunidadeTag(){
		this.comunidade = new Comunidade();
		this.tag = new Tag();
	}
	
	public Comunidade getComunidade() {
		return comunidade;
	}

	public void setComunidade(Comunidade comunidade) {
		this.comunidade = comunidade;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static ComunidadeTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ComunidadeTagDAO();
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
	
	/*public static Collection<ComunidadeTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public ComunidadeTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<ComunidadeTag> consultarPorComunidade(Comunidade comunidade) throws CadastroException{
		return getDAO().consultarPorComunidade(comunidade);
	}
}
