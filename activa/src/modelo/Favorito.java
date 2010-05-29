package modelo;

import interfaces.FavoritoI;

import java.util.ArrayList;
import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.FavoritoDAO;

public class Favorito {

	private long pkFavorito;
	private String nomeLink;
	private String descricaoLink;
	private String url;
	private Membro membro;
	private PastasFavorito pasta;
		
	private static FavoritoI dao;
	
	public Favorito(){
		this.membro = new Membro();
		this.pasta = new PastasFavorito();
	}
	
	public long getPkFavorito() {
		return pkFavorito;
	}

	public void setPkFavorito(long pkFavorito) {
		this.pkFavorito = pkFavorito;
	}

	public String getNomeLink() {
		return nomeLink;
	}

	public void setNomeLink(String nomeLink) {
		this.nomeLink = nomeLink;
	}

	public String getDescricaoLink() {
		return descricaoLink;
	}

	public void setDescricaoLink(String descricaoLink) {
		this.descricaoLink = descricaoLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static FavoritoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new FavoritoDAO();
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
	
	public static int excluirPorPasta(PastasFavorito pasta) throws CadastroException{
		return getDAO().excluirPorPasta(pasta);
	}
	
	public static Collection<Favorito> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Favorito> consultarPorMembro(Usuario usuario) throws CadastroException{
		return getDAO().consultarPorMembro(usuario);
	}
	
	public Favorito consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

	public void setPasta(PastasFavorito pasta) {
		this.pasta = pasta;
	}

	public PastasFavorito getPasta() {
		return pasta;
	}
	
	public Collection<PastasFavorito> getPastas()
	{
		ArrayList<PastasFavorito> pastas = new ArrayList<PastasFavorito>();
		
		PastasFavorito pasta = this.getPasta();
		try {
			while(true)
			{
				if(pasta != null && pasta.getPkPasta() > 0)
				{
					pastas.add(pasta);
					pasta = pasta.getPasta();
				}else
					break;
			}
			
			int size =  pastas.size();		
			for(int i = 0; i < size; i++)
			{
				pastas.add(pastas.get(i));
				pastas.remove(i);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		return pastas;
	}
}
