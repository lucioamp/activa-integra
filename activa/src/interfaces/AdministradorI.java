package interfaces;

import java.util.Collection;

import modelo.Administrador;
import modelo.Usuario;
import util.CadastroException;

public interface AdministradorI {

	public int incluir (Administrador administrador) throws CadastroException;
	public int excluir (Administrador administrador) throws CadastroException;
	public Collection<Administrador> consultarTodosAdministradores() throws CadastroException;
	public Administrador consultar(Administrador administrador) throws CadastroException;
	public boolean isAdministrador(Usuario usuario) throws CadastroException;
}
