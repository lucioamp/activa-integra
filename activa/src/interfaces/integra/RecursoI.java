package interfaces.integra;

import modelo.integra.AplicacaoExterna;
import modelo.integra.Recurso;
import util.AplicacaoExternaException;

public interface RecursoI {

	public void consultarPorAplicacao(AplicacaoExterna aplicacao) throws AplicacaoExternaException;
	public int incluir (Recurso recurso) throws AplicacaoExternaException;
	public void consultar(Recurso recurso) throws AplicacaoExternaException;
}
