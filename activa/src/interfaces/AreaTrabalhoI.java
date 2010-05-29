package interfaces;

import java.util.Collection;

import modelo.AreaTrabalho;
import modelo.Membro;
import util.CadastroException;

public interface AreaTrabalhoI {

	public int incluir (AreaTrabalho areaTrabalho) throws CadastroException;
	public int alterar (AreaTrabalho areaTrabalho) throws CadastroException;
	public int excluir (AreaTrabalho areaTrabalho) throws CadastroException;
	public Collection<AreaTrabalho> consultarTodos() throws CadastroException;
	public AreaTrabalho consultar(AreaTrabalho areaTrabalho) throws CadastroException;
	public Collection<AreaTrabalho> consultarPorMembro(Membro membro) throws CadastroException;
}
