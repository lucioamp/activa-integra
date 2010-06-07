package modelo.integra;

public class ExecutorAplicacaoRequest {
	private Long idUsuarioAplicacao;
	
	private String usuario;
	private String senha;

	private String url;

	private String parametros;

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
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

}
