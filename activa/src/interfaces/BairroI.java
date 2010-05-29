package interfaces;

import java.util.Collection;

import modelo.Municipio;
import modelo.Bairro;
import util.CadastroException;

public interface BairroI {

	public int incluir (Bairro bairro) throws CadastroException;
	public int alterar (Bairro bairro) throws CadastroException;
	public int excluir (Bairro bairro) throws CadastroException;
	public Collection<Bairro> consultarTodos() throws CadastroException;
	public Bairro consultar(Bairro bairro) throws CadastroException;
	public Collection<Bairro> consultarPorMunicipio(Municipio municipio) throws CadastroException;
}
