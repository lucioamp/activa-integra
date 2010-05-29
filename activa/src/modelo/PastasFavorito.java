package modelo;
import java.util.Collection;

import util.CadastroException;
import util.Constantes;

import interfaces.PastasFavoritoI;
import dao.PastasFavoritoDAO;

public class PastasFavorito {

	private long pkPasta;
	private String nome;
	private PastasFavorito pasta;
	private Usuario usuario;
	
	private static PastasFavoritoI dao;
	
	public PastasFavorito(){
		this.usuario = new Usuario();
	}

	private static PastasFavoritoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new PastasFavoritoDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws CadastroException{
		return getDAO().incluir(this);
	}

	public int alterar() throws CadastroException{
		return getDAO().alterar(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}

	public void setPkPasta(long pkPasta) {
		this.pkPasta = pkPasta;
	}

	public long getPkPasta() {
		return pkPasta;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setPasta(PastasFavorito pasta) {
		this.pasta = pasta;
	}

	public PastasFavorito getPasta() {
		return pasta;
	}
	
	public PastasFavorito consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<PastasFavorito> consultarPorUsuario(Usuario usuario) throws CadastroException{
		return getDAO().consultarPorUsuario(usuario);
	}
	
	public static Collection<PastasFavorito> consultarPorPasta(PastasFavorito pasta) throws CadastroException{
		return getDAO().consultarPorPasta(pasta);
	}
	
	public static Collection<PastasFavorito> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}

	public void setUsuario(Usuario membro) {
		this.usuario = membro;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
