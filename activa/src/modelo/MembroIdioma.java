package modelo;

import interfaces.MembroIdiomaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MembroIdiomaDAO;

public class MembroIdioma {

	private Membro membro;
	private Idioma idioma;
			
	private static MembroIdiomaI dao;
	
	public MembroIdioma(){
		this.membro = new Membro();
		this.idioma = new Idioma();
	}
	
	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	private static MembroIdiomaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MembroIdiomaDAO();
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
	
	/*public static Collection<MembroIdioma> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}*/
	
	public MembroIdioma consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<MembroIdioma> consultarPorMembro(Membro membro) throws CadastroException{
		return getDAO().consultarPorMembro(membro);
	}
}
