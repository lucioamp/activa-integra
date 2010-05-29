package interfaces;

import java.util.Collection;

import modelo.Idioma;
import util.CadastroException;

public interface IdiomaI {

	public int incluir (Idioma idioma) throws CadastroException;
	public int alterar (Idioma idioma) throws CadastroException;
	public int excluir (Idioma idioma) throws CadastroException;
	public Collection<Idioma> consultarTodos() throws CadastroException;
	public Idioma consultar(Idioma idioma) throws CadastroException;
}
