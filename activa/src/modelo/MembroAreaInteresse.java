package modelo;

import interfaces.MembroAreaInteresseI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroAreaInteresseDAO;

public class MembroAreaInteresse {

	private Membro membro;
	private AreaInteresse areaInteresse;
			
	private static MembroAreaInteresseI dao;
	
	public MembroAreaInteresse(){
		this.membro = new Membro();
		this.areaInteresse = new AreaInteresse();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public AreaInteresse getAreaInteresse() {
		return areaInteresse;
	}

	public void setAreaInteresse(AreaInteresse areaInteresse) {
		this.areaInteresse = areaInteresse;
	}

	private static MembroAreaInteresseI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroAreaInteresseDAO();
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
	
	/*public static Collection<MembroAreaInteresse> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MembroAreaInteresse consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<MembroAreaInteresse> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
