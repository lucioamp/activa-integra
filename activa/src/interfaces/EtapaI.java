package interfaces;

import java.util.Collection;

import modelo.Etapa;
import modelo.Meta;
import util.CadastroException;

public interface EtapaI {

	public int incluir (Etapa etapa) throws CadastroException;
	public int alterar (Etapa etapa) throws CadastroException;
	public int excluir (Etapa etapa) throws CadastroException;
	public Collection<Etapa> consultarTodos() throws CadastroException;
	public Collection<Etapa> consultarPorMeta(Meta meta) throws CadastroException;
	public Etapa consultar(Etapa etapa) throws CadastroException;
}
