package interfaces.integra;

import java.util.Collection;

import modelo.integra.AplicacaoExterna;
import util.AplicacaoExternaException;

public interface AplicacaoExternaI {

	public long incluir (AplicacaoExterna aplicacao) throws AplicacaoExternaException;
	public int excluir (AplicacaoExterna aplicacao) throws AplicacaoExternaException;
	public Collection<AplicacaoExterna> consultarTodos() throws AplicacaoExternaException;
	public void consultar(AplicacaoExterna aplicacao) throws AplicacaoExternaException;
}
