package modelo.integra;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import util.AplicacaoExternaException;

public class ExecutorAplicacaoRequest {
	private Long idUsuarioAplicacao;
	
	private String nomeAplicacao;
	private String nomeRecurso;
	
	private String usuario;
	private String senha;

	private String url;
	private List<NameValuePair> parametros = new ArrayList<NameValuePair>();
	private String metodo;
	
	public void aplicaRecurso(long idRecurso) throws AplicacaoExternaException {
		Recurso recurso = new Recurso();
		recurso.setIdRecurso(idRecurso);
		recurso.consultar(recurso);
		
		AplicacaoExterna aplicacao = new AplicacaoExterna();
		aplicacao.setIdAplicacao(recurso.getIdAplicacao());
		aplicacao.consultar();
		
//		recurso.setBase("http://search.twitter.com");
//		recurso.setPath("search.atom");
		
		nomeAplicacao = aplicacao.getNome();
		nomeRecurso = recurso.getNome();
		
		url = recurso.getBase() + "/" + recurso.getPath();
		metodo = recurso.getMetodo();
	}
	
	public void aplicaParametros(String[] arrParamValor) throws AplicacaoExternaException {
		// Pegar parâmetros
		List<Parametro> parametroLista = Parametro.consultarPorUsuarioAplicacao(idUsuarioAplicacao);

		// Adicionar parâmetros para executor
		parametros.clear();
		for (int i = 0; i < parametroLista.size(); i++) {
			String valor = parametroLista.get(i).getValorPadrao();
			if (arrParamValor != null) {
				valor = arrParamValor[i];
			}
			
			parametros.add(new NameValuePair(parametroLista.get(i).getName(), valor));
		}
		
//		aplicacaoRequest.getParametros().add(new NameValuePair("q", "web"));
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getIdUsuarioAplicacao() {
		return idUsuarioAplicacao;
	}

	public void setIdUsuarioAplicacao(Long idUsuarioAplicacao) {
		this.idUsuarioAplicacao = idUsuarioAplicacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idUsuarioAplicacao == null) ? 0 : idUsuarioAplicacao
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExecutorAplicacaoRequest other = (ExecutorAplicacaoRequest) obj;
		if (idUsuarioAplicacao == null) {
			if (other.idUsuarioAplicacao != null)
				return false;
		} else if (!idUsuarioAplicacao.equals(other.idUsuarioAplicacao))
			return false;
		return true;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public List<NameValuePair> getParametros() {
		return parametros;
	}

	public void setParametros(List<NameValuePair> parametros) {
		this.parametros = parametros;
	}

	public String getNomeAplicacao() {
		return nomeAplicacao;
	}

	public void setNomeAplicacao(String nomeAplicacao) {
		this.nomeAplicacao = nomeAplicacao;
	}

	public String getNomeRecurso() {
		return nomeRecurso;
	}

	public void setNomeRecurso(String nomeRecurso) {
		this.nomeRecurso = nomeRecurso;
	}

}
