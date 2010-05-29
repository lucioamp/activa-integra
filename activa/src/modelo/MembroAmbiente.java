package modelo;

import interfaces.MembroAmbienteI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroAmbienteDAO;

public class MembroAmbiente {

	private Membro membro;
	private Ambiente ambiente;
			
	private static MembroAmbienteI dao;
	
	public MembroAmbiente(){
		this.membro = new Membro();
		this.ambiente = new Ambiente();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	private static MembroAmbienteI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroAmbienteDAO();
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
	
	/*public static Collection<MembroAmbiente> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MembroAmbiente consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<MembroAmbiente> consultarPorMembro(Usuario membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
