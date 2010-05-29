package modelo;

import interfaces.AreaInteresseTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AreaInteresseTagDAO;

public class AreaInteresseTag {

	private AreaInteresse areaInteresse;
	private Tag tag;
		
	private static AreaInteresseTagI dao;
	
	public AreaInteresseTag(){
		this.areaInteresse = new AreaInteresse();
		this.tag = new Tag();
	}
	
	public AreaInteresse getAreaInteresse() {
		return areaInteresse;
	}

	public void setAreaInteresse(AreaInteresse areaInteresse) {
		this.areaInteresse = areaInteresse;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static AreaInteresseTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AreaInteresseTagDAO();
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
	
	/*public static Collection<AreaInteresseTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public AreaInteresseTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<AreaInteresseTag> consultarPorAreaInteresse(AreaInteresse areaInteresse) throws CadastroException{
		return getDAO().consultarPorAreaInteresse(areaInteresse);
	}
}
