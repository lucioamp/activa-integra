package interfaces;

import java.util.Collection;

import modelo.Uf;

import util.CadastroException;

public interface UfI {	
	public int incluir (Uf uf) throws CadastroException;
	public int alterar (Uf uf) throws CadastroException;
	public int excluir (Uf uf) throws CadastroException;
	public Collection<Uf> consultarTodos() throws CadastroException;
	public Uf consultar(Uf uf) throws CadastroException;
}
