package modelo;

import interfaces.VisaoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.VisaoDAO;

public class Visao {

	private long pkVisao;
	private String nome;
	private String descricao;
	private String data;
	private Meta meta;
	
	private static VisaoI dao;
	
	public Visao(){
		this.meta = new Meta();
	}
	
	public long getPkVisao() {
		return pkVisao;
	}

	public void setPkVisao(long pkVisao) {
		this.pkVisao = pkVisao;
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

	private static VisaoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new VisaoDAO();
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
	
	public static Collection<Visao> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Visao> consultarPorMeta(Meta meta) throws CadastroException{
		return getDAO().consultarPorMeta(meta);
	}
	
	public Visao consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
