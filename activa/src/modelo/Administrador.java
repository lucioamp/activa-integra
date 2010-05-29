package modelo;

import interfaces.AdministradorI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AdministradorDAO;

public class Administrador extends Usuario {

	private static AdministradorI dao;
	
	private static AdministradorI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AdministradorDAO();
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

	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Administrador> consultarTodosAdministradores() throws CadastroException{
		return getDAO().consultarTodosAdministradores();
	}
	
	public Administrador consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static boolean isAdministrador(Usuario usuario) throws CadastroException{
		return getDAO().isAdministrador(usuario);
	}
}
