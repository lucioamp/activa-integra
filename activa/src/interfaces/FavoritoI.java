package interfaces;

import java.util.Collection;

import modelo.PastasFavorito;
import modelo.Usuario;
import modelo.Favorito;
import util.CadastroException;

public interface FavoritoI {

	public int incluir (Favorito favorito) throws CadastroException;
	public int alterar (Favorito favorito) throws CadastroException;
	public int excluir (Favorito favorito) throws CadastroException;
	public int excluirPorPasta (PastasFavorito pasta) throws CadastroException;
	public Collection<Favorito> consultarTodos() throws CadastroException;
	public Collection<Favorito> consultarPorMembro(Usuario usuario) throws CadastroException;
	public Favorito consultar(Favorito favorito) throws CadastroException;
}
