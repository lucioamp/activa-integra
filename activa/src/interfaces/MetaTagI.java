package interfaces;

import java.util.Collection;

import modelo.Meta;
import modelo.MetaTag;
import util.CadastroException;

public interface MetaTagI {

	public int incluir (MetaTag metaTag) throws CadastroException;
	//public int alterar (MetaTag metaTag) throws CadastroException;
	public int excluir (MetaTag metaTag) throws CadastroException;
	//public Collection<MetaTag> consultarTodos() throws CadastroException;
	public MetaTag consultar(MetaTag metaTag) throws CadastroException;
	public Collection<MetaTag> consultarPorMeta(Meta meta) throws CadastroException;
}
