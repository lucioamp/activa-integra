package interfaces.integra;

import modelo.integra.Parametro;
import modelo.integra.Recurso;
import util.AplicacaoExternaException;

public interface ParametroI {

	public long incluir (Parametro parametro) throws AplicacaoExternaException;
	public void consultarPorRecurso(Recurso recurso) throws AplicacaoExternaException;
}
