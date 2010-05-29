package modelo;

import interfaces.TarefaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.TarefaDAO;

public class Tarefa {

	private long pkTarefa;
	private String nome;
	private String descricao;
	private String data;
	private Etapa visao;
	
	private static TarefaI dao;
	
	public Tarefa(){
		this.visao = new Etapa();
	}
	
	public long getPkTarefa() {
		return pkTarefa;
	}

	public void setPkTarefa(long pkTarefa) {
		this.pkTarefa = pkTarefa;
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

	public Etapa getVisao() {
		return visao;
	}

	public void setVisao(Etapa visao) {
		this.visao = visao;
	}

	private static TarefaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new TarefaDAO();
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
	
	public static int excluirPorEtapa(Etapa etapa) throws CadastroException{
		return getDAO().excluirPorEtapa(etapa);
	}
	
	public static Collection<Tarefa> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Tarefa> consultarPorVisao(Etapa visao) throws CadastroException{
		return getDAO().consultarPorVisao(visao);
	}
	
	public Tarefa consultar() throws CadastroException{
		return getDAO().consultar(this);
	}
}
