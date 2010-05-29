package interfaces;

import java.util.Collection;

import modelo.FormacaoAcademica;
import util.CadastroException;

public interface FormacaoAcademicaI {

	public int incluir (FormacaoAcademica formacaoAcademica) throws CadastroException;
	public int alterar (FormacaoAcademica formacaoAcademica) throws CadastroException;
	public int excluir (FormacaoAcademica formacaoAcademica) throws CadastroException;
	public Collection<FormacaoAcademica> consultarTodos() throws CadastroException;
	public FormacaoAcademica consultar(FormacaoAcademica formacaoAcademica) throws CadastroException;
}
