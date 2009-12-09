package nucleo.usuario;

import java.sql.*;
import java.util.Date;

public class Sessao {
	protected int idUsuario;
	protected String idSessao;
	protected String endIPUsuario;
	protected Date dataCriacao;
	protected Date dataUltimoAcesso;
	protected int idTurma;
	protected int logado;

	public Sessao(int idUsuario, String idSessao, String endIPUsuario, 
				  Date dataCriacao, Date dataUltimoAcesso, int idTurma, int logado) 
	{
		super();

		this.idUsuario = idUsuario;
		this.idSessao = idSessao;
		this.endIPUsuario = endIPUsuario;
		this.dataCriacao = dataCriacao;
		this.dataUltimoAcesso = dataUltimoAcesso;
		this.idTurma = idTurma;
		this.logado = logado;
	}
	
	public Sessao(ResultSet rs) throws SQLException
	{
		super();
	
		this.idUsuario = rs.getInt("IdUsuario");
		this.idSessao = rs.getString("IdSessao");
		this.endIPUsuario = rs.getString("endIPUsuario");
		this.dataCriacao = rs.getDate("dataCriacao");
		this.dataUltimoAcesso = rs.getDate("dataUltimoAcesso");
		this.idTurma = rs.getInt("IdTurma");
		this.logado = rs.getInt("logado");
	}
	public String getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}
	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}
	public int getLogado() {
		return logado;
	}
	public void setLogado(int logado) {
		this.logado = logado;
	}
	public String getEndIPUsuario() {
		return endIPUsuario;
	}
	public void setEndIPUsuario(String endIPUsuario) {
		this.endIPUsuario = endIPUsuario;
	}
}
