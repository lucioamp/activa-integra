package modelo;

import interfaces.ContatoUsuarioI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ContatoUsuarioDAO;

public class ContatoUsuario {

	private long pkContatoUsuario;
	private TipoContato tipoContato;
	private Usuario usuario;
	private String contato;
		
	private static ContatoUsuarioI dao;
	
	public ContatoUsuario(){
		this.tipoContato = new TipoContato();
		this.usuario = new Usuario();
	}
	
	public void setPkContatoUsuario(long pkContatoUsuario) {
		this.pkContatoUsuario = pkContatoUsuario;
	}

	public long getPkContatoUsuario() {
		return pkContatoUsuario;
	}
	
	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	private static ContatoUsuarioI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ContatoUsuarioDAO();
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
	
	public static Collection<ContatoUsuario> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public ContatoUsuario consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<ContatoUsuario> consultarPorUsuario(Usuario usuario) throws CadastroException{
		return getDAO().consultarPorUsuario(usuario);
	}
}
