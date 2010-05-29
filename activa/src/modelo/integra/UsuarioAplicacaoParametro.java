package modelo.integra;

import interfaces.integra.UsuarioAplicacaoParametroI;
import util.AplicacaoExternaException;
import util.Constantes;
import dao.integra.UsuarioAplicacaoParametroDAO;

public class UsuarioAplicacaoParametro {
	private long idUsuarioAplicacao;

	private long idParametro;
	private String valorPadrao;
	private boolean bloquearValor;
	
	private static UsuarioAplicacaoParametroI dao;

	/**
	 * @return the idUsuarioAplicacao
	 */
	public long getIdUsuarioAplicacao() {
		return idUsuarioAplicacao;
	}

	/**
	 * @param idUsuarioAplicacao the idUsuarioAplicacao to set
	 */
	public void setIdUsuarioAplicacao(long idUsuarioAplicacao) {
		this.idUsuarioAplicacao = idUsuarioAplicacao;
	}

	/**
	 * @return the idParametro
	 */
	public long getIdParametro() {
		return idParametro;
	}

	/**
	 * @param idParametro the idParametro to set
	 */
	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}

	/**
	 * @return the valorPadrao
	 */
	public String getValorPadrao() {
		return valorPadrao;
	}

	/**
	 * @param valorPadrao the valorPadrao to set
	 */
	public void setValorPadrao(String valorPadrao) {
		this.valorPadrao = valorPadrao;
	}

	/**
	 * @return the bloquearValor
	 */
	public boolean isBloquearValor() {
		return bloquearValor;
	}

	/**
	 * @param bloquearValor the bloquearValor to set
	 */
	public void setBloquearValor(boolean bloquearValor) {
		this.bloquearValor = bloquearValor;
	}

	private static UsuarioAplicacaoParametroI getDAO(){
		if(dao == null){
			switch (Constantes.DATABASE_ATUAL){
			case Constantes.DATABASE_POSTGRE:
				dao = new UsuarioAplicacaoParametroDAO();
				break;

			default:
				break;
			}
		}
		return dao; 
	}
	
	public static void excluir(long idUsuarioAplicacao) throws AplicacaoExternaException{
		getDAO().excluirPorUsuarioAplicacao(idUsuarioAplicacao);
	}
	
	public void incluir() throws AplicacaoExternaException{
		getDAO().incluir(this);
	}
	
}
