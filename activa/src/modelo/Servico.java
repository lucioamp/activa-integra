package modelo;

import interfaces.ServicoI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.ServicoDAO;

public class Servico {

	private long pkServico;
	private String nome;
	private String data;
	private String descricao;
	private String imagem;
	private String status;
	private String automatico;
	private Ambiente ambiente;
	private Membro membro;
		
	private static ServicoI dao;
	
	public Servico(){
		this.ambiente = new Ambiente();
		this.membro = new Membro();
	}
	
	public long getPkServico() {
		return pkServico;
	}

	public void setPkServico(long pkServico) {
		this.pkServico = pkServico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAutomatico() {
		return automatico;
	}

	public void setAutomatico(String automatico) {
		this.automatico = automatico;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private static ServicoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new ServicoDAO();
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
	
	public static Collection<Servico> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Servico> consultarPorAmbiente(Ambiente ambiente) throws CadastroException{
		return getDAO().consultarPorAmbiente(ambiente);
	}
	
	public Servico consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
