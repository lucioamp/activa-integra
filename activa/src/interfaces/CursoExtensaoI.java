package interfaces;

import java.util.Collection;

import modelo.CursoExtensao;
import modelo.Membro;
import util.CadastroException;

public interface CursoExtensaoI {

	public int incluir (CursoExtensao cursoExtensao) throws CadastroException;
	public int alterar (CursoExtensao cursoExtensao) throws CadastroException;
	public int excluir (CursoExtensao cursoExtensao) throws CadastroException;
	public Collection<CursoExtensao> consultarTodos() throws CadastroException;
	public CursoExtensao consultar(CursoExtensao cursoExtensao) throws CadastroException;
	public Collection<CursoExtensao> consultarPorMembro(Membro membro) throws CadastroException;
}
