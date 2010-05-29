package interfaces;

import java.util.Collection;

import modelo.Tag;
import util.CadastroException;

public interface TagI {

	public int incluir (Tag tag) throws CadastroException;
	public int alterar (Tag tag) throws CadastroException;
	public int excluir (Tag tag) throws CadastroException;
	public Collection<Tag> consultarTodos() throws CadastroException;
	public Tag consultar(Tag tag) throws CadastroException;
}
