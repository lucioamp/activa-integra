package interfaces;

import java.util.Collection;

import modelo.ContatoUsuario;
import modelo.Usuario;
import util.CadastroException;

public interface ContatoUsuarioI {

	public int incluir (ContatoUsuario contatoUsuario) throws CadastroException;
	public int alterar (ContatoUsuario contatoUsuario) throws CadastroException;
	public int excluir (ContatoUsuario contatoUsuario) throws CadastroException;
	public Collection<ContatoUsuario> consultarTodos() throws CadastroException;
	public ContatoUsuario consultar(ContatoUsuario contatoUsuario) throws CadastroException;
	public Collection<ContatoUsuario> consultarPorUsuario(Usuario usuario) throws CadastroException;
}
