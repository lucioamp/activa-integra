package interfaces;

import java.util.Collection;

import modelo.Artefato;
import modelo.ArtefatoTag;
import util.CadastroException;

public interface ArtefatoTagI {

	public int incluir (ArtefatoTag artefatoTag) throws CadastroException;
	//public int alterar (ArtefatoTag artefatoTag) throws CadastroException;
	public int excluir (ArtefatoTag artefatoTag) throws CadastroException;
	//public Collection<ArtefatoTag> consultarTodos() throws CadastroException;
	public ArtefatoTag consultar(ArtefatoTag artefatoTag) throws CadastroException;
	public Collection<ArtefatoTag> consultarPorArtefato(Artefato artefato) throws CadastroException;
}
