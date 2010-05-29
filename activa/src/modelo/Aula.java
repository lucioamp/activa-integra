package modelo;

import interfaces.AulaI;

import java.util.Collection;

import util.CadastroException;
import util.Constantes;
import dao.AulaDAO;

public class Aula {

	private long pkAula;
	private String assunto;
	private String aula;
	private long peso;
	private Curso curso;
	private String data;
	
	private static AulaI dao;

	public Aula(){
		this.curso = new Curso();
	}
	
	public long getPkAula() {
		return pkAula;
	}

	public void setPkAula(long pkAula) {
		this.pkAula = pkAula;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public long getPeso() {
		return peso;
	}

	public void setPeso(long peso) {
		this.peso = peso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	private static AulaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AulaDAO();
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
	
	public int excluirPorCurso() throws CadastroException{
		return getDAO().excluirPorCurso(this);
	}
	
	public static Collection<Aula> consultarTodos() throws CadastroException{
		return getDAO().consultarTodos();
	}
	
	public static Collection<Aula> consultarPorCurso(Curso curso) throws CadastroException{
		return getDAO().consultarPorCurso(curso);
	}
	
	public Aula consultar() throws CadastroException{
		return getDAO().consultar(this);
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
}
