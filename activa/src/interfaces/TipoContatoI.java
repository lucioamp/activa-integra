package interfaces;

import java.util.Collection;

import modelo.TipoContato;
import util.CadastroException;

public interface TipoContatoI {

	public int incluir (TipoContato tipoContato) throws CadastroException;
	public int alterar (TipoContato tipoContato) throws CadastroException;
	public int excluir (TipoContato tipoContato) throws CadastroException;
	public Collection<TipoContato> consultarTodos() throws CadastroException;
	public TipoContato consultar(TipoContato tipoContato) throws CadastroException;
}
