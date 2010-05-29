package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.AvaliacaoMembro;
import util.CadastroException;

public interface AvaliacaoMembroI {

	public int incluir (AvaliacaoMembro avaliacaoMembro) throws CadastroException;
	public int alterar (AvaliacaoMembro avaliacaoMembro) throws CadastroException;
	public int excluir (AvaliacaoMembro avaliacaoMembro) throws CadastroException;
	public Collection<AvaliacaoMembro> consultarPorMembro(Membro membro) throws CadastroException;
	public AvaliacaoMembro consultar(AvaliacaoMembro avaliacaoMembro) throws CadastroException;
}
