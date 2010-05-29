package interfaces;

import java.util.Collection;

import modelo.Instituicao;
import util.CadastroException;

public interface InstituicaoI {

	public int incluir (Instituicao instituicao) throws CadastroException;
	public int alterar (Instituicao instituicao) throws CadastroException;
	public int excluir (Instituicao instituicao) throws CadastroException;
	public Collection<Instituicao> consultarTodos() throws CadastroException;
	public Instituicao consultar(Instituicao instituicao) throws CadastroException;
}
