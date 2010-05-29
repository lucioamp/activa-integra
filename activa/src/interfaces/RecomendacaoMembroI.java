package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.RecomendacaoMembro;
import util.CadastroException;

public interface RecomendacaoMembroI {

	public int incluir (RecomendacaoMembro recomendacaoMembro) throws CadastroException;
	/*public int alterar (RecomendacaoMembro recomendacaoMembro) throws CadastroException;*/
	public int excluir (RecomendacaoMembro recomendacaoMembro) throws CadastroException;
	public Collection<RecomendacaoMembro> consultarPorMembroReceptor(Membro membro) throws CadastroException;
	public RecomendacaoMembro consultar(RecomendacaoMembro recomendacaoMembro) throws CadastroException;
}
