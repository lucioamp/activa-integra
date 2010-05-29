package interfaces;

import java.util.Collection;

import modelo.Ambiente;
import modelo.Artefato;
import util.CadastroException;

public interface ArtefatoI {

	public int incluir (Artefato artefato) throws CadastroException;
	public int alterar (Artefato artefato) throws CadastroException;
	public int excluir (Artefato artefato) throws CadastroException;
	public Collection<Artefato> consultarTodosArtefatos() throws CadastroException;
	public Collection<Artefato> consultarArtefatosPorAmbiente(Ambiente ambiente) throws CadastroException;
	public Artefato consultar(Artefato artefato) throws CadastroException;
}
