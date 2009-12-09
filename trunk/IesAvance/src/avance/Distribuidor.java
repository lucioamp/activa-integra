package avance;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.banco.ExcecaoBanco;

import util.interpretador.ExcecaoParser;

import nucleo.usuario.SessaoPersist;

import nucleo.output.GeraSaida;

/**
 * Servlet implementation class for Servlet: Distribuidor
 *
 */
public class Distribuidor extends javax.servlet.http.HttpServlet 
						  implements javax.servlet.Servlet 
{
	private static final long serialVersionUID  = 1;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Distribuidor() 
	{
		super();
	}  
	
	protected void fechaSessoesExpiradas() throws ExcecaoBanco
	{
		SessaoPersist sesPersist;
		ServletContext app = getServletContext();
		
		if ( app.getAttribute("Inicializado") == null )
		{
			app.setAttribute("Inicializado", "true");

			sesPersist = new SessaoPersist();
			sesPersist.LogoutGeral();
		}
	}
	
	protected void mostraSaida( HttpServletResponse response,
								String saida, String tipo ) throws IOException
	{
		if ( (saida == null) || (saida.length() == 0) )
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE: Não foi produzida nenhuma saída válida!" );
			return;
		}

		PrintWriter out = response.getWriter();
			
		response.setContentType( tipo );
		
		// Substitui o tipo de caracter para funcionar no Explorer
		saida = saida.replaceFirst("UTF-8", "ISO-8859-1");
		
		out.print(saida);
		
		out.close();
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int no = -1;
		GeraSaida gs;
		String parStr;
		String saida = "";
		AvanceRestParser parser;

		try 
		{
			fechaSessoesExpiradas();
		}
		catch (ExcecaoBanco e)
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(GET): Erro no Logout Geral: " + e.getMessage() );
			return;
		}
		
		parStr = request.getPathInfo();
		if ( parStr.length() < 2 )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(GET): Número inválido de parâmetros!" );
			return;
		}
		
		parser = new AvanceRestParser();
		
		try 
		{
			no = parser.interpretaGet(parStr);
			
			gs = parser.obtemGeraSaidaGet(no);
			
			gs.setContexto( parser.getContexto() );
			gs.setRequest( request );
			gs.setResponse( response );
			
			if ( parser.temAutenticacaoGet(no) )
			{
				if ( !gs.verificaAutenticacao() )
				{
					response.sendError( gs.getErrCod(), gs.getErrMsg() );
					return;
				}
			}
			
			saida = parser.executaGet( no, gs );
			
			if ( gs.houveErro() )
			{
				response.sendError( gs.getErrCod(), gs.getErrMsg() );
				return;
			}

			mostraSaida( response, saida, gs.getTipoSaida() );
		}
		catch (ExcecaoParser e)
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
					"AvaNCE(GET): URI(" + no + ") = '" + parStr + "' - Erro: " + e.getMessage() );
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int no = 0;
		String parStr;
		String saida = "";
		GeraSaida gs = null;
		AvanceRestParser parser;

		try 
		{
			fechaSessoesExpiradas();
		}
		catch (ExcecaoBanco e)
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(POST): Erro no Logout Geral: " + e.getMessage() );
			return;
		}
		
		parStr = request.getPathInfo();
		if ( parStr.length() < 2 )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(POST): Número inválido de parâmetros!" );
			return;
		}
		
		parser = new AvanceRestParser();
		
		try 
		{
			no = parser.interpretaPost(parStr);
			
			gs = parser.obtemGeraSaidaPost(no);
			
			gs.setContexto( parser.getContexto() );
			gs.setRequest( request );
			gs.setResponse( response );
			
			if ( parser.temAutenticacaoPost(no) )
			{
				if ( !gs.verificaAutenticacao() )
				{
					response.sendError( gs.getErrCod(), gs.getErrMsg() );
					return;
				}
			}
			
			saida = parser.executaPost( no, gs );
			
			if ( gs.houveErro() )
			{
				response.sendError( gs.getErrCod(), gs.getErrMsg() );
				return;
			}

			mostraSaida( response, saida, gs.getTipoSaida() );
		}
		catch (ExcecaoParser e)
		{
			if ( (gs != null) && gs.houveErro() )
				response.sendError( gs.getErrCod(), gs.getErrMsg() );
			else
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(POST): URI(" + no + ") = '" + parStr + 
									"' - Erro: " + e.getMessage() );
		}
	}   	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.doPut(request, response);
	}   	    
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.doDelete(request, response);
	}
}