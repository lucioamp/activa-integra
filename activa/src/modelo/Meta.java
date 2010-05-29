package modelo;

import interfaces.MetaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.MetaDAO;

public class Meta {

	private long pkMeta;
	private String descricao;
	private String duracao;
	private Ambiente ambiente;
	
	private static MetaI dao;
	
	public Meta(){
		this.ambiente = new Ambiente();
	}
	
	public long getPkMeta() {
		return pkMeta;
	}

	public void setPkMeta(long pkMeta) {
		this.pkMeta = pkMeta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	private static MetaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new MetaDAO();
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
	
	public static Collection<Meta> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Meta> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarPorAmbiente(ambiente);
	}
	
	public Meta consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
