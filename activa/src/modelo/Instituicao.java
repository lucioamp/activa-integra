package modelo;

import interfaces.InstituicaoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.InstituicaoDAO;

public class Instituicao {

	private long pkInstituicao;
	private String nome;
	private String tipoLogradouro;
	private String logradouro;
	private long numero;
	private String complemento;
	private String cep;
	private Bairro bairro;
		
	private static InstituicaoI dao;
	
	public Instituicao(){
		this.bairro = new Bairro();
	}
	
	public long getPkInstituicao() {
		return pkInstituicao;
	}

	public void setPkInstituicao(long pkInstituicao) {
		this.pkInstituicao = pkInstituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	private static InstituicaoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new InstituicaoDAO();
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
	
	public static Collection<Instituicao> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	public Instituicao consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
