package nucleo.output;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.usuario.Sessao;
import nucleo.usuario.SessaoPersist;

import util.interpretador.ContextList;

abstract public class GeraSaida 
{
	protected int errCod;
	protected String errMsg;
	
	protected String urlApp;
//	protected String urlInserir;
	
	protected String tipoSaida;
	
	private int idUsuario;
	private String remoteAddr;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	protected ContextList contexto;

	public GeraSaida()
	{
		urlApp = "";
		idUsuario = -1;
		remoteAddr = "";
		contexto = null;
		
		tipoSaida = "application/rss+xml";
		
		errMsg = null;
		errCod = HttpServletResponse.SC_NOT_IMPLEMENTED;
	}


	public HttpServletResponse getResponse()			{ return response;		}
	public void setResponse(HttpServletResponse res)	{ response = res;		}
	public HttpServletRequest getRequest()				{ return request;		}

	public void setRequest( HttpServletRequest req ) 
	{
		request = req;

		setUrlApp("localhost:8080");
		setRemoteAddr( request.getRemoteAddr() );
	}
	
	public String getParameter( String nome )
	{
		return request.getParameter(nome);
	}
	
	public String[] getParameterValues( String nome )
	{
		return request.getParameterValues(nome);
	}
	
	public String getRequestURL()
	{
		return request.getRequestURL().toString();
	}
	
	public String getServerSessionId()
	{
		return request.getSession().getId();
	}
	
	public long getServerSessionCreationTime()
	{
		return request.getSession().getCreationTime();
	}

	public long getServerSessionLastAccessedTime()
	{
		return request.getSession().getLastAccessedTime();
	}
	
	public int getIdUsuario()				{ return idUsuario;		}
	public void setIdUsuario(int id)		{ idUsuario = id;		}
	public String getRemoteAddr()			{ return remoteAddr;	}
	public void setRemoteAddr(String addr)	{ remoteAddr = addr;	}
	public String getUrlApp()				{ return urlApp;		}
	public void setUrlApp(String addr)		{ urlApp = addr;		}

	public String getTipoSaida()			{ return tipoSaida;		}
	public void setTipoSaida(String ts)		{ tipoSaida = ts;		}
	
	public String getErrMsg()				{ return errMsg;		}
	public int getErrCod()					{ return errCod;		}

	public void setErro(int cod, String msg)		   
	{ 
		errCod = cod;
		errMsg = "AvaNCE: " + msg;
	}
	
	public boolean houveErro()
	{
		return errMsg != null;
	}

	public ContextList getContexto()		 { return contexto;		}
	public void setContexto(ContextList ctx) { contexto = ctx;		}
	
	abstract public String getNomeNivel();
	
	/*
	 * Verifica se o idSessao é válido e a autenticação está correta
	 */
	public boolean verificaAutenticacao()
	{
		Sessao sessao;
		int idUsuario;
		String idSessao;
		
		if ( contexto == null )
			return false;
		
		idSessao = contexto.getStrNode("idSessao");

		try 
		{
			SessaoPersist sesPersist = new SessaoPersist();

// não faço a busca pelo IP para que a requisição possa ser feita através do Orkut.			
//			sessao = sesPersist.Busca(idSessao, getRemoteAddr());
			sessao = sesPersist.Busca(idSessao);
		}
		catch ( Exception e )
		{
			setErro( HttpServletResponse.SC_BAD_REQUEST ,
					 "Erro ao tentar recuperar a Sessao: " + e.getMessage());
			return false;
		}
		
		if ( sessao == null )
		{
			setErro( HttpServletResponse.SC_UNAUTHORIZED,
					 "Sessao invalida!" );
			return false;
		}
		
		idUsuario = sessao.getIdUsuario();
		setIdUsuario(idUsuario);

		return true;
	}
	
	/*
	 * Verifica privelégio de inserção, dependente do nivel
	 */
	public boolean podeInserir()
	{
		return true;
	}
	
	/*
	 * Retorna a URL da página de inserção
	 * Inserir pode ser limitado de acordo com o nível
	public String getUrlInserir()  { return podeInserir() ? urlInserir : null;	}
	 */

	public String obtemForm()      { return null; }


	/*
	 * RSS
	 */
	
	/*
	 * XForms
	 */
	public String criaFormXForms( String nome, String url, 
								  String estrutura, String campos )
	{
		String saida;
		
		// Todo criaXForms vai sempre resultar numa saída xforms.
		setTipoSaida("application/xforms+xml");
		
		saida = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		saida += "<?xml-stylesheet type=\"text/xsl\" href=\"/IesAvance/xform.xsl\"?>\n";
		saida += "<xforms>\n<model>\n<instance>" + estrutura + "</instance>";
		saida += "<submission id=\"" + nome;
		saida += "\" action=\"" + url + "\" method=\"urlencoded-post\"/>\n</model>\n";
		saida += campos;
		saida += "<submit submission=\"" + nome + "\">\n";
		saida += "<label>Enviar</label>\n</submit>\n</xforms>\n";

		return saida;
	}
	
	public String criarInputText(String label, String nome)
	{
		String saida;
		
		saida = "<input ref=\"" + nome + "\">\n<label>" + label + "</label>\n</input>\n";
		return saida;
	}
	
	public String criarTextArea(String label, String nome)
	{
		String saida;
		
		saida = "<textarea ref=\"" + nome + "\">\n<label>" + label + "</label>\n</textarea>\n";
		return saida;
	}
	
	public String criarPassword(String label, String nome)
	{
		String saida;
		
		saida = "<secret ref=\"" + nome + "\">\n<label>" + label + "</label>\n</secret>\n";
		return saida;
	}
	
	public String criarItem(String label, String valor)
	{
		String saida;
		
		saida = "<item>\n<label>" + label + "</label>\n<value>" + valor + "</value>\n</item>\n";		
		
		return saida;
	}
	
	public String criarCheckbox(String label, String nome, String itens)
	{
		String saida;
		
		saida = "<select ref=\"" + nome + "\" appearance=\"full\">\n<label>" + label + 
												"</label>\n" + itens + "</select>\n";
		return saida;
	}
	
	public String criarRadio(String label, String nome, String itens)
	{
		String saida;
		
		saida = "<select1 ref=\"" + nome + "\" appearance=\"full\">\n<label>" + label + 
												"</label>\n" + itens + "</select1>\n";
		return saida;
	}
	
	public String criarComboBox(String label, String nome, String itens)
	{
		String saida;
		
		saida = "<select1 ref=\"" + nome + "\">\n<label>" + label + 
											"</label>\n" + itens + "</select1>\n";
		return saida;
	}
	
}
