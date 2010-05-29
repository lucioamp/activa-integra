package interfaces;

import java.util.Collection;

import modelo.Municipio;
import modelo.Uf;
import util.CadastroException;

public interface MunicipioI {
	public int incluir (Municipio municipio) throws CadastroException;
	public int alterar (Municipio municipio) throws CadastroException;
	public int excluir (Municipio municipio) throws CadastroException;
	public Collection<Municipio> consultarTodos() throws CadastroException;
	public Municipio consultar(Municipio municipio) throws CadastroException;
	public Collection<Municipio> consultarPorUf(Uf uf) throws CadastroException;
}
