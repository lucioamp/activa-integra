package modelo.integra;

import interfaces.integra.RecursoI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.AplicacaoExternaException;
import util.Constantes;
import dao.integra.RecursoDAO;

public class Recurso extends NivelWADL {

	private long idRecurso;
	private long idAplicacao;
	private String nome;
	private String base;
	private String path;
	private String metodo;
	
	private List<Parametro> parametros;
	
	private List<Metodo> metodos;
	private List<Recurso> recursos;
	
	public Recurso() 
	{
		super();

		nome = "";
		base = "";
		path = "";

		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
	}

	public Recurso(int idAplicacao, String nome, String base, String path) 
	{
		this.idAplicacao = idAplicacao;

		this.nome = nome;
		this.base = base;
		this.path = path;
		
		metodos = new ArrayList<Metodo>();
		recursos = new ArrayList<Recurso>();
	}
	
	/**
	 * @return the recursos
	 */
	public List<Recurso> getRecursos() {
		return recursos;
	}

	/**
	 * @param recursos the recursos to set
	 */
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	private static RecursoI dao;
	
	/**
	 * @return the idRecurso
	 */
	public long getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso the idRecurso to set
	 */
	public void setIdRecurso(long idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the idAplicacao
	 */
	public long getIdAplicacao() {
		return idAplicacao;
	}

	/**
	 * @param idAplicacao the idAplicacao to set
	 */
	public void setIdAplicacao(long idAplicacao) {
		this.idAplicacao = idAplicacao;
	}

	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * @return the metodo
	 */
	public String getMetodo() {
		return metodo;
	}

	/**
	 * @param metodo the metodo to set
	 */
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	/**
	 * @return the parametros
	 */
	public List<Parametro> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	@SuppressWarnings("unchecked")
	public void imprime( int nivel )
	{
		if (recursos.size() == 0) {
			System.out.println("Base: " + base + " Path: " + path);
			
//			System.out.print(" Nível: " + nivel);
//			System.out.print(" No de Metodos: " + metodos.size());
//			System.out.println("No de Recursos: " + recursos.size());
		}
		
		Iterator it;
		
		it = metodos.iterator();
		while(it.hasNext()) 
		{
			Metodo rec = (Metodo)it.next();
			
			rec.imprime();
		}
		
		it = recursos.iterator();
		while(it.hasNext()) 
		{
			Recurso rec = (Recurso)it.next();
			
			rec.imprime(nivel + 1);
		}
	}
	
	/**
	 * @return the metodos
	 */
	public List<Metodo> getMetodos() {
		return metodos;
	}

	/**
	 * @param metodos the metodos to set
	 */
	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}

	private static RecursoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new RecursoDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public static void consultarPorAplicacao(AplicacaoExterna aplicacao) throws AplicacaoExternaException{
		getDAO().consultarPorAplicacao(aplicacao);
	}
	
	public void consultar(Recurso recurso) throws AplicacaoExternaException{
		getDAO().consultar(recurso);
	}
	
	public int incluir() throws AplicacaoExternaException{
		return getDAO().incluir(this);
	}
	
}
