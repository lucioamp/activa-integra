package modelo;

import interfaces.AmbienteI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AmbienteDAO;

public class Ambiente {

	private long pkAmbiente;
	private String nome;
	private String data;
	private String descricao;
	private String imagem;
	private Membro professor;
	private String enderecoArquivo;
		
	private static AmbienteI dao;
	
	public Ambiente(){
		this.professor = new Membro();
	}
	
	public long getPkAmbiente() {
		return pkAmbiente;
	}

	public void setPkAmbiente(long pkAmbiente) {
		this.pkAmbiente = pkAmbiente;
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
	
	public Membro getProfessor() {
		return professor;
	}

	public void setProfessor(Membro professor) {
		this.professor = professor;
	}

	private static AmbienteI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AmbienteDAO();
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
	
	public int alterarImagem() throws CadastroException{
		return getDAO().alterarImagem(this);
	}
	
	public int excluir() throws CadastroException{
		return getDAO().excluir(this);
	}
	
	public static Collection<Ambiente> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Ambiente> consultarTodosMenos(Usuario usuario) throws CadastroException{
		return getDAO().consultarTodosMenos(usuario);
	}
	
	public Ambiente consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
	
	public Collection<Membro> consultarMembros() throws CadastroException{
		return getDAO().consultarMembros(this);
	}

	public void setEnderecoArquivo(String arquivo) {
		this.enderecoArquivo = arquivo;
	}

	public String getEnderecoArquivo() {
		return enderecoArquivo;
	}
}
