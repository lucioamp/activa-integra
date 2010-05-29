package interfaces;

import java.util.Collection;

import modelo.AvaliacaoServico;
import modelo.Servico;
import util.CadastroException;

public interface AvaliacaoServicoI {

	public int incluir (AvaliacaoServico avaliacaoServico) throws CadastroException;
	public int alterar (AvaliacaoServico avaliacaoServico) throws CadastroException;
	public int excluir (AvaliacaoServico avaliacaoServico) throws CadastroException;
	public Collection<AvaliacaoServico> consultarPorServico(Servico membro) throws CadastroException;
	public AvaliacaoServico consultar(AvaliacaoServico avaliacaoServico) throws CadastroException;
}
