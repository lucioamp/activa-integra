package interfaces.integra;

import modelo.integra.UsuarioAplicacaoParametro;
import util.AplicacaoExternaException;

public interface UsuarioAplicacaoParametroI {

	public void excluirPorUsuarioAplicacao (long idUsuarioAplicacao) throws AplicacaoExternaException;
	public void incluir (UsuarioAplicacaoParametro usuarioAplicacaoParametro) throws AplicacaoExternaException;
}
