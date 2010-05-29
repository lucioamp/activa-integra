package interfaces;

import java.util.Collection;

import modelo.ContatoInstituicao;
import modelo.Instituicao;
import util.CadastroException;

public interface ContatoInstituicaoI {

	public int incluir (ContatoInstituicao contatoInstituicao) throws CadastroException;
	public int alterar (ContatoInstituicao contatoInstituicao) throws CadastroException;
	public int excluir (ContatoInstituicao contatoInstituicao) throws CadastroException;
	public Collection<ContatoInstituicao> consultarTodos() throws CadastroException;
	public ContatoInstituicao consultar(ContatoInstituicao contatoInstituicao) throws CadastroException;
	public Collection<ContatoInstituicao> consultarPorInstituicao(Instituicao instituicao) throws CadastroException;
}
