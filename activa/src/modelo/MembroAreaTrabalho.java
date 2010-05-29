package modelo;

import interfaces.MembroAreaTrabalhoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroAreaTrabalhoDAO;

public class MembroAreaTrabalho {

	private Membro membro;
	private AreaTrabalho areaTrabalho;
			
	private static MembroAreaTrabalhoI dao;
	
	public MembroAreaTrabalho(){
		this.membro = new Membro();
		this.areaTrabalho = new AreaTrabalho();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public AreaTrabalho getAreaTrabalho() {
		return areaTrabalho;
	}

	public void setAreaTrabalho(AreaTrabalho areaTrabalho) {
		this.areaTrabalho = areaTrabalho;
	}

	private static MembroAreaTrabalhoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroAreaTrabalhoDAO();
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
	
	/*public static Collection<MembroAreaTrabalho> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MembroAreaTrabalho consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<MembroAreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
