package interfaces.integra;

import java.util.Collection;

import modelo.integra.UsuarioAplicacaoParametro;
import util.AplicacaoExternaException;

public interface UsuarioAplicacaoParametroI {

	public void excluirPorUsuarioAplicacao (long idUsuarioAplicacao) throws AplicacaoExternaException;
	public void incluir (UsuarioAplicacaoParametro usuarioAplicacaoParametro) throws AplicacaoExternaException;
	public Collection<UsuarioAplicacaoParametro> consultarPorUsuarioAplicacao(long idUsuarioAplicacao) throws AplicacaoExternaException;
}
