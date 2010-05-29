package modelo;

import interfaces.EtapaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.EtapaDAO;

public class Etapa {

	private long pkEtapa;
	private String nome;
	private String descricao;
	private String data;
	private Meta meta;
	
	private static EtapaI dao;
	
	public Etapa(){
		this.meta = new Meta();
	}
	
	public long getPkEtapa() {
		return pkEtapa;
	}

	public void setPkEtapa(long pkEtapa) {
		this.pkEtapa = pkEtapa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	private static EtapaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new EtapaDAO();
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
	
	public static Collection<Etapa> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Etapa> consultarPorMeta(Meta meta) throws CadastroException{
		return getDAO().consultarPorMeta(meta);
	}
	
	public Etapa consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
