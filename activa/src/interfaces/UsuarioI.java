package interfaces;

import java.util.Collection;

import modelo.Usuario;
import util.CadastroException;

public interface UsuarioI {

	public int incluir (Usuario usuario) throws CadastroException;
	public int cadastrar (Usuario usuario) throws CadastroException;
	public int alterar (Usuario usuario) throws CadastroException;
	public int alterarImagem (Usuario usuario) throws CadastroException;
	public int excluir (Usuario usuario) throws CadastroException;
	public Collection<Usuario> consultarTodos() throws CadastroException;
	public Usuario consultar(Usuario usuario) throws CadastroException;
	public Usuario consultarPorLogin(String login) throws CadastroException;
	public boolean validaLogin(String login, String senha) throws CadastroException;
	public boolean estaAtivo(Usuario usuario) throws CadastroException;
	public boolean existeEmail(String email) throws CadastroException;
	public boolean existeCpf(Long cpf) throws CadastroException;
}
