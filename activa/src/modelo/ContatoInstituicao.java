package modelo;

import interfaces.ContatoInstituicaoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ContatoInstituicaoDAO;

public class ContatoInstituicao {

	private long pkContatoInstituicao;
	private TipoContato tipoContato;
	private Instituicao instituicao;
	private String contato;
		
	private static ContatoInstituicaoI dao;
	
	public ContatoInstituicao(){
		this.tipoContato = new TipoContato();
		this.instituicao = new Instituicao();
	}
	
	public long getPkContatoInstituicao() {
		return pkContatoInstituicao;
	}

	public void setPkContatoInstituicao(long pkContatoInstituicao) {
		this.pkContatoInstituicao = pkContatoInstituicao;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	private static ContatoInstituicaoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ContatoInstituicaoDAO();
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
	
	public static Collection<ContatoInstituicao> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public ContatoInstituicao consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public static Collection<ContatoInstituicao> consultarPorInstituicao(Instituicao instituicao) throws CadastroException{
		return getDAO().consultarPorInstituicao(instituicao);
	}
}
