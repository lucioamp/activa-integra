package modelo.integra;

import interfaces.integra.AplicacaoExternaI;

import java.util.Collection;

import util.AplicacaoExternaException;
import util.Constantes;
import dao.integra.AplicacaoExternaDAO;

public class AplicacaoExterna {

	private long idAplicacao;
	private String nome;
	private String url;
	private Collection<Recurso> recursoList;
	
	private Integer idProcessamento;
	private boolean authBasica;
	private boolean authOpenId;
	private boolean authGAuth;
	
	/**
	 * @return the idProcessamento
	 */
	public Integer getIdProcessamento() {
		return idProcessamento;
	}

	/**
	 * @param idProcessamento the idProcessamento to set
	 */
	public void setIdProcessamento(Integer idProcessamento) {
		this.idProcessamento = idProcessamento;
	}

	/**
	 * @return the authBasica
	 */
	public boolean isAuthBasica() {
		return authBasica;
	}

	/**
	 * @param authBasica the authBasica to set
	 */
	public void setAuthBasica(boolean authBasica) {
		this.authBasica = authBasica;
	}

	/**
	 * @return the authOpenId
	 */
	public boolean isAuthOpenId() {
		return authOpenId;
	}

	/**
	 * @param authOpenId the authOpenId to set
	 */
	public void setAuthOpenId(boolean authOpenId) {
		this.authOpenId = authOpenId;
	}

	/**
	 * @return the authGAuth
	 */
	public boolean isAuthGAuth() {
		return authGAuth;
	}

	/**
	 * @param authGAuth the authGAuth to set
	 */
	public void setAuthGAuth(boolean authGAuth) {
		this.authGAuth = authGAuth;
	}

	private static AplicacaoExternaI dao;
	
	public AplicacaoExterna(){
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the recursoList
	 */
	public Collection<Recurso> getRecursoList() {
		return recursoList;
	}

	/**
	 * @param recursoList the recursoList to set
	 */
	public void setRecursoList(Collection<Recurso> recursoList) {
		this.recursoList = recursoList;
	}

	private static AplicacaoExternaI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new AplicacaoExternaDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int incluir() throws AplicacaoExternaException{
		long idAplicacao = getDAO().incluir(this);
		
		for (Recurso recurso : this.getRecursoList()) {
			recurso.setIdAplicacao(idAplicacao);
			int idRecurso = recurso.incluir();
			
			for (Parametro parametro : recurso.getParametros()) {
				parametro.setIdRecurso(idRecurso);
				parametro.incluir();
			}
		}
		
		return 0;
	}

	public int excluir() throws AplicacaoExternaException{
		return getDAO().excluir(this);
	}
	
	public static Collection<AplicacaoExterna> consultarTodos() throws AplicacaoExternaException{
		Collection<AplicacaoExterna> aplicacaoList = getDAO().consultarTodos();
		for (AplicacaoExterna aplicacao : aplicacaoList) {
			Recurso.consultarPorAplicacao(aplicacao);
		}
		
		return aplicacaoList;
	}
	
	public void consultar() throws AplicacaoExternaException{
		getDAO().consultar(this);
	}
	
}
