package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Membro;
import modelo.Usuario;
import util.CadastroException;

public interface AmbienteI {

	public int incluir (Ambiente ambiente) throws CadastroException;
	public int alterar (Ambiente ambiente) throws CadastroException;
	public int alterarImagem (Ambiente ambiente) throws CadastroException;
	public int excluir (Ambiente ambiente) throws CadastroException;
	public Collection<Ambiente> consultarTodos() throws CadastroException;
	public Collection<Ambiente> consultarTodosMenos(Usuario usuario) throws CadastroException;
	public Ambiente consultar(Ambiente ambiente) throws CadastroException;
	public Collection<Membro> consultarMembros(Ambiente ambiente) throws CadastroException;	
}
