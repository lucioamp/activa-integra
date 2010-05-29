package interfaces;

import java.util.Collection;

import modelo.Usuario;
import modelo.PastasFavorito;
import util.CadastroException;

public interface PastasFavoritoI {

	public int incluir (PastasFavorito pasta) throws CadastroException;
	public int alterar (PastasFavorito pasta) throws CadastroException;
	public int excluir (PastasFavorito pasta) throws CadastroException;
	public PastasFavorito consultar(PastasFavorito pasta) throws CadastroException;
	public Collection<PastasFavorito> consultarPorUsuario(Usuario usuario) throws CadastroException;
	public Collection<PastasFavorito> consultarPorPasta (PastasFavorito pasta) throws CadastroException;
	public Collection<PastasFavorito> consultarTodos() throws CadastroException;
}
