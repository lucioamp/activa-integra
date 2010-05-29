package interfaces;

import java.util.Collection;

import modelo.Membro;
import modelo.MembroIdioma;
import util.CadastroException;

public interface MembroIdiomaI {

	public int incluir (MembroIdioma membroIdioma) throws CadastroException;
	//public int alterar (MembroIdioma membroIdioma) throws CadastroException;
	public int excluir (MembroIdioma membroIdioma) throws CadastroException;
	//public Collection<MembroIdioma> consultarTodos() throws CadastroException;
	public MembroIdioma consultar(MembroIdioma membroIdioma) throws CadastroException;
	public Collection<MembroIdioma> consultarPorMembro(Membro membro) throws CadastroException;
}
