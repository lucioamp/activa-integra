package interfaces;

import java.util.Collection;

import modelo.AreaInteresse;
import util.CadastroException;

public interface AreaInteresseI {

	public int incluir (AreaInteresse areaInteresse) throws CadastroException;
	public int alterar (AreaInteresse areaInteresse) throws CadastroException;
	public int excluir (AreaInteresse areaInteresse) throws CadastroException;
	public Collection<AreaInteresse> consultarTodos() throws CadastroException;
	public AreaInteresse consultar(AreaInteresse areaInteresse) throws CadastroException;
}
