package interfaces.integra;

import java.util.Collection;

import modelo.integra.Parametro;
import modelo.integra.Recurso;
import util.AplicacaoExternaException;

public interface ParametroI {

	public long incluir (Parametro parametro) throws AplicacaoExternaException;
	public void consultarPorRecurso(Recurso recurso) throws AplicacaoExternaException;
	public Collection<Parametro> consultarPorUsuarioAplicacao(long idUsuarioAplicacao) throws AplicacaoExternaException;
}
