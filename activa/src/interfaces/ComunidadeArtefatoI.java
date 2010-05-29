package interfaces;

import java.util.Collection;

import modelo.Comunidade;
import modelo.ComunidadeArtefato;
import modelo.Artefato;
import util.CadastroException;

public interface ComunidadeArtefatoI {

	public int incluir (ComunidadeArtefato comunidadeArtefato) throws CadastroException;
	//public int alterar (ComunidadeArtefato alunoArtefato) throws CadastroException;
	public int excluir (ComunidadeArtefato comunidadeArtefato) throws CadastroException;
	//public Collection<ComunidadeArtefato> consultarTodos() throws CadastroException;
	public ComunidadeArtefato consultar(ComunidadeArtefato comunidadeArtefato) throws CadastroException;
	public Collection<ComunidadeArtefato> consultarPorComunidade(Comunidade comunidade) throws CadastroException;
	public Collection<ComunidadeArtefato> consultarPorArtefato(Artefato artefato) throws CadastroException;
}
