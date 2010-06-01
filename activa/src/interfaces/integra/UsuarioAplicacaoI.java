package interfaces.integra;

import java.util.Collection;

import modelo.integra.UsuarioAplicacao;
import util.AplicacaoExternaException;

public interface UsuarioAplicacaoI {

	public Collection<UsuarioAplicacao> consultarPorUsuario(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
	public int excluir (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
	public long incluir (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
	public void atualizar (UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
	public void consultarUsuarioAplicacao(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
	public void consultarPorId(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException;
}
