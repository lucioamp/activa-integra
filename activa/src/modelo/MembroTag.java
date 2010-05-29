package modelo;

import interfaces.MembroTagI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroTagDAO;

public class MembroTag {

	private Membro membro;
	private Tag tag;
			
	private static MembroTagI dao;
	
	public MembroTag(){
		this.membro = new Membro();
		this.tag = new Tag();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	private static MembroTagI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroTagDAO();
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
	
	/*public static Collection<MembroTag> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MembroTag consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<MembroTag> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
