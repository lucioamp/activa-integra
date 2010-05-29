package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Meta;
import util.CadastroException;

public interface MetaI {

	public int incluir (Meta meta) throws CadastroException;
	public int alterar (Meta meta) throws CadastroException;
	public int excluir (Meta meta) throws CadastroException;
	public Collection<Meta> consultarTodos() throws CadastroException;
	public Collection<Meta> consultarPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Meta consultar(Meta meta) throws CadastroException;
}
