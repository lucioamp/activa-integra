package modelo.integra;

import interfaces.integra.UsuarioAplicacaoI;

import java.util.Collection;

import util.AplicacaoExternaException;
import util.Constantes;
import dao.integra.UsuarioAplicacaoDAO;

public class UsuarioAplicacao {
	private Long idUsuarioAplicacao;

	private long pkUsuario;
	private long idAplicacao;
	private String nomeAplicacao;
	
	private long idRecurso;
	private String nomeRecurso;
	
	private Integer permissao;
	private String mostrarJanela;
	private Integer atualizacaoAutomatica;
	private Integer tempoValor;
	
	private String mensagem;
	
	private static UsuarioAplicacaoI dao;

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the idUsuarioAplicacao
	 */
	public Long getIdUsuarioAplicacao() {
		return idUsuarioAplicacao;
	}

	/**
	 * @param idUsuarioAplicacao the idUsuarioAplicacao to set
	 */
	public void setIdUsuarioAplicacao(Long idUsuarioAplicacao) {
		this.idUsuarioAplicacao = idUsuarioAplicacao;
	}

	/**
	 * @return the permissao
	 */
	public Integer getPermissao() {
		return permissao;
	}

	/**
	 * @param permissao the permissao to set
	 */
	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

	/**
	 * @return the mostrarJanela
	 */
	public String getMostrarJanela() {
		return mostrarJanela;
	}

	/**
	 * @param mostrarJanela the mostrarJanela to set
	 */
	public void setMostrarJanela(String mostrarJanela) {
		this.mostrarJanela = mostrarJanela;
	}

	/**
	 * @return the atualizacaoAutomatica
	 */
	public Integer getAtualizacaoAutomatica() {
		return atualizacaoAutomatica;
	}

	/**
	 * @param atualizacaoAutomatica the atualizacaoAutomatica to set
	 */
	public void setAtualizacaoAutomatica(Integer atualizacaoAutomatica) {
		this.atualizacaoAutomatica = atualizacaoAutomatica;
	}

	/**
	 * @return the tempoValor
	 */
	public Integer getTempoValor() {
		return tempoValor;
	}

	/**
	 * @param tempoValor the tempoValor to set
	 */
	public void setTempoValor(Integer tempoValor) {
		this.tempoValor = tempoValor;
	}

	/**
	 * @return the pkUsuario
	 */
	public long getPkUsuario() {
		return pkUsuario;
	}

	/**
	 * @param pkUsuario the pkUsuario to set
	 */
	public void setPkUsuario(long pkUsuario) {
		this.pkUsuario = pkUsuario;
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
	 * @return the nomeAplicacao
	 */
	public String getNomeAplicacao() {
		return nomeAplicacao;
	}

	/**
	 * @param nomeAplicacao the nomeAplicacao to set
	 */
	public void setNomeAplicacao(String nomeAplicacao) {
		this.nomeAplicacao = nomeAplicacao;
	}

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
	 * @return the nomeRecurso
	 */
	public String getNomeRecurso() {
		return nomeRecurso;
	}

	/**
	 * @param nomeRecurso the nomeRecurso to set
	 */
	public void setNomeRecurso(String nomeRecurso) {
		this.nomeRecurso = nomeRecurso;
	}

	private static UsuarioAplicacaoI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new UsuarioAplicacaoDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public int excluir() throws AplicacaoExternaException{
		return getDAO().excluir(this);
	}
	
	public Collection<UsuarioAplicacao> consultarPorUsuario() throws AplicacaoExternaException{
		return getDAO().consultarPorUsuario(this);
	}
	
	public long incluir() throws AplicacaoExternaException{
		return getDAO().incluir(this);
	}
	
	public void atualizar() throws AplicacaoExternaException{
		getDAO().atualizar(this);
	}
	
	public void consultarUsuarioAplicacao(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException {
		getDAO().consultarUsuarioAplicacao(usuarioAplicacao);
	}
	
	public void consultarPorId(UsuarioAplicacao usuarioAplicacao) throws AplicacaoExternaException {
		getDAO().consultarPorId(usuarioAplicacao);
	}
}
