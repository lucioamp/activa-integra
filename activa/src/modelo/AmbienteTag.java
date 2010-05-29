package modelo;

import interfaces.AmbienteTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AmbienteTagDAO;

public class AmbienteTag {

	private Ambiente ambiente;
	private Tag tag;
			
	private static AmbienteTagI dao;
	
	public AmbienteTag(){
		this.ambiente = new Ambiente();
		this.tag = new Tag();
	}
	
	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static AmbienteTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AmbienteTagDAO();
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
	
	/*public static Collection<AmbienteTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public AmbienteTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<AmbienteTag> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarPorAmbiente(ambiente);
	}
}
