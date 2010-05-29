package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.RecomendacaoServico;
import util.CadastroException;

public interface RecomendacaoServicoI {

	public int incluir (RecomendacaoServico recomendacaoServico) throws CadastroException;
	/*public int alterar (RecomendacaoServico recomendacaoServico) throws CadastroException;*/
	public int excluir (RecomendacaoServico recomendacaoServico) throws CadastroException;
	public Collection<RecomendacaoServico> consultarPorMembroReceptor(Membro membro) throws CadastroException;
	public RecomendacaoServico consultar(RecomendacaoServico recomendacaoServico) throws CadastroException;
}
