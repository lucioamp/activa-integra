package nucleo.nivel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import nucleo.persist.AplicacaoPersist;

import util.banco.ExcecaoBanco;
import util.interpretador.DomParser;
import util.interpretador.ExcecaoParser;

import nucleo.wadl.Recurso;
import nucleo.wadl.RecursoPersist;
import nucleo.wadl.Metodo;
import nucleo.wadl.MetodoPersist;

public class Aplicacao extends NivelAvance 
{
	protected String url;
	protected String urlWADL;
	protected boolean externa;
	protected boolean feed;
	protected boolean WADLCarregado;

	/*
	 * Construtor Básico
	 */
	public Aplicacao() 
	{
		super();

		externa = false;
		feed = false;
		WADLCarregado = false;
		url = "";
		urlWADL = "";
	}

	/*
	 * Construtor Básico
	 */
	public Aplicacao( String nome, int idResponsavel, String url, 
					  boolean externa, boolean feed ) 
	{
		super(nome, idResponsavel);

		this.externa = externa;
		this.feed = feed;
		this.url = url;
		WADLCarregado = false;
		urlWADL = "";
	}

	public Aplicacao( ResultSet rs ) throws SQLException
	{
		super(rs);

		externa = rs.getBoolean("Externa");
		feed = rs.getBoolean("Feed");
		url = rs.getString("Url");
		urlWADL = rs.getString("UrlWADL");
		WADLCarregado = rs.getBoolean("WADLCarregado");
	}
	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorNomeComNomeResponsavel()
	 */
	public String getSelectPorNomeComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPAplicacao", "Nome");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdComNomeResponsavel()
	 */
	public String getSelectPorIdComNomeResponsavel()
	{
		return preparaSelectComNomeResponsavel("NPAplicacao", "Id");
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getSelectPorIdOrigemComNomeResponsavel()
	 */
	public String getSelectPorIdOrigemComNomeResponsavel()
	{
		return "select n.*, u.NomeUsuario " +
			   "from NPAplicacao n, NPUsuario u " + 
			   "where n.IdResponsavel = u.IdUsuario";
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#getStringInclusao()
	 */
	public String getStringInclusao()
	{
		return preparaInsert("NPAplicacao", "Url, Externa, Feed, urlWADL, WADLcarregado", 
							 "?, ?, ?, ?, ?" ); 
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.nivel.NivelAvance#preparaStatetementInclusao(java.sql.PreparedStatement)
	 */
	public void preparaStatetementInclusao( PreparedStatement pst ) throws SQLException
	{
		super.preparaStatetementInclusao(pst);
		
		pst.setString(  3, getUrl());
		pst.setBoolean(  4, isExterna());
		pst.setBoolean(  5, isFeed());
		pst.setString(  6, getUrlWADL());
		pst.setBoolean(  7, isWADLCarregado());
	}
	
	public void carregaWADL() throws ExcecaoBanco, ExcecaoParser
	{
		DomParser dp;
		
		AplicacaoPersist.ApagaWADL(this);
		
		// Cria a instância
		dp = new DomParser();
		
		// Faz o parsing do arquivo WADL
		dp.parseWADL2BD( getId(), getUrl() );
		
		WADLCarregado = true;
		AplicacaoPersist.setWADLCarregado(this);
	}
	
	public Recurso getRecurso(String nome, int idRecPai) throws ExcecaoBanco 
	{
		Recurso rec;
		RecursoPersist persist;
		
		persist = new RecursoPersist();
		rec = persist.Busca(getId(), nome, idRecPai);
		
		return rec;
	}
	
	public Metodo getMetodo(String nome, int idRecPai) throws ExcecaoBanco 
	{
		Metodo met;
		MetodoPersist persist;
		
		persist = new MetodoPersist();
		met = persist.Busca(getId(), nome, idRecPai);
		
		return met;
	}
	
	public int carregaListaRecursos(List<Recurso> lisRec) throws ExcecaoBanco 
	{
		return (new RecursoPersist()).carregaListaRecursos(getId(), lisRec);
	}
	
	public String listaRecursos () throws ExcecaoBanco
	{
		List<Recurso> recursos;
		String str;
		
		recursos = new ArrayList<Recurso>();
		
		str = "<p>Aplicação Externa</p>" +
			  "<p>WADL " + (isWADLCarregado() ? "já" : "não") + 
			  " foi carregado</p>";

		if ( carregaListaRecursos(recursos) > 0 )
		{
			Recurso rec;
			Iterator it;

			str += "<br><p>Recursos:</p>";

			it = recursos.iterator();
			while (it.hasNext()) 
			{
				rec = (Recurso)it.next();
				
				str += "<a href='' onClick='return abrePaginaSimples(";
				str += "\"/IesAvance/Executor/" + getNome() + "/"; 
				str += rec.getPath() + "\",  \"docDiv\");'>";
				str += rec.getPath() + "</a><br>";
			}
		}
		
		return str;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public boolean isExterna() {
		return externa;
	}
	public void setExterna(boolean externa) {
		this.externa = externa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(int idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isFeed() {
		return feed;
	}
	public void setFeed(boolean feed) {
		this.feed = feed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlWADL() {
		return urlWADL;
	}

	public void setUrlWADL(String urlWADL) {
		this.urlWADL = urlWADL;
	}

	public boolean isWADLCarregado() {
		return WADLCarregado;
	}

	public void setWADLCarregado(boolean carregado) {
		WADLCarregado = carregado;
	}
}
