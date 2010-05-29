package modelo;

import interfaces.ComunidadeI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ComunidadeDAO;

public class Comunidade extends Servico{

	private CategoriaComunidade categoriaComunidade;
	
	private static ComunidadeI dao;

	public Comunidade(){
		this.categoriaComunidade = new CategoriaComunidade();
	}
	
	public CategoriaComunidade getCategoriaComunidade() {
		return categoriaComunidade;
	}

	public void setCategoriaComunidade(CategoriaComunidade categoriaComunidade) {
		this.categoriaComunidade = categoriaComunidade;
	}

	private static ComunidadeI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ComunidadeDAO();
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

	public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Comunidade> consultarTodosComunidades() throws CadastroException{
		return getDAO().consultarTodosComunidades();
	}
	
	public static Collection<Comunidade> consultarComunidadesPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarComunidadesPorAmbiente(ambiente);
	}
	
	public Comunidade consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
