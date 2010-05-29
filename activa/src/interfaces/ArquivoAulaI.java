package interfaces;

import java.util.Collection;

import modelo.Aula;
import modelo.ArquivoAula;
import util.CadastroException;

public interface ArquivoAulaI {

	public int incluir (ArquivoAula arquivoAula) throws CadastroException;
	public int alterar (ArquivoAula arquivoAula) throws CadastroException;
	public int excluir (ArquivoAula arquivoAula) throws CadastroException;
	public Collection<ArquivoAula> consultarTodos() throws CadastroException;
	public Collection<ArquivoAula> consultarPorAula(Aula aula) throws CadastroException;
	public ArquivoAula consultar(ArquivoAula arquivoAula) throws CadastroException;
}
