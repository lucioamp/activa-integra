package interfaces;

import java.util.Collection;

import modelo.CategoriaArtefato;
import util.CadastroException;

public interface CategoriaArtefatoI {

	public int incluir (CategoriaArtefato categoriaArtefato) throws CadastroException;
	public int alterar (CategoriaArtefato categoriaArtefato) throws CadastroException;
	public int excluir (CategoriaArtefato categoriaArtefato) throws CadastroException;
	public Collection<CategoriaArtefato> consultarTodos() throws CadastroException;
	public CategoriaArtefato consultar(CategoriaArtefato categoriaArtefato) throws CadastroException;
}
