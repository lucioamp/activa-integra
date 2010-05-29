package interfaces;

import java.util.Collection;

import modelo.Visao;
import modelo.Meta;
import util.CadastroException;

public interface VisaoI {

	public int incluir (Visao visao) throws CadastroException;
	public int alterar (Visao visao) throws CadastroException;
	public int excluir (Visao visao) throws CadastroException;
	public Collection<Visao> consultarTodos() throws CadastroException;
	public Collection<Visao> consultarPorMeta(Meta meta) throws CadastroException;
	public Visao consultar(Visao visao) throws CadastroException;
}
