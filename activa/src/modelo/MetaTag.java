package modelo;

import interfaces.MetaTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MetaTagDAO;

public class MetaTag {

	private Meta meta;
	private Tag tag;
			
	private static MetaTagI dao;
	
	public MetaTag(){
		this.meta = new Meta();
		this.tag = new Tag();
	}
	
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static MetaTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MetaTagDAO();
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
	
	/*public static Collection<MetaTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MetaTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<MetaTag> consultarPorMeta(Meta meta) throws CadastroException{
		return getDAO().consultarPorMeta(meta);
	}
}
