package avance;

import java.io.IOException;
import java.io.PrintWriter;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import util.banco.ExcecaoBanco;
import util.interpretador.ExcecaoParser;
import util.interpretador.ResponseParser;

import nucleo.persist.AplicacaoPersist;
import nucleo.nivel.Aplicacao;

import nucleo.wadl.Recurso;
import nucleo.wadl.Metodo;
import nucleo.wadl.Parametro;
import nucleo.wadl.Autenticacao;
import nucleo.wadl.Resposta;
import nucleo.wadl.RespostaPersist;

/**
 * Servlet implementation class for Servlet: Executor
 *
 */
 public class Executor extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
 {
	 private static final long serialVersionUID  = 1;

    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Executor() 
	{
		super();
	}
	
	public static String encode( String source )
	{
		BASE64Encoder enc = new BASE64Encoder();
		return enc.encode(source.getBytes());
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String saida;
		String parSaida;
		String parStr;
		String parVet[];
		Aplicacao aplic;
		AplicacaoPersist persist;

		parStr = request.getPathInfo();
		if ( parStr.length() < 2 )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(GET): Número inválido de parâmetros!" );
			return;
		}
		
		parVet = parStr.substring(1).split("/");
		
		try
		{
			persist = new AplicacaoPersist();

			aplic = (Aplicacao)persist.Busca(parVet[0]);
		}
		catch ( ExcecaoBanco e )
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(GET): Erro ao recuperar informações da aplicacao: " +
								e.getMessage() );
			return;
		}
		
		if ( aplic == null )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(GET): Aplicacao " + parVet[0] + " inexistente!" );
			return;
		}
		
		int ultimo;
		int idRec = -1;
		Metodo met = null;
		Recurso rec = null,
	    		ultRec = null;
		Parametro par;
		List<Parametro> lisPar;
		boolean acheiMetodo = false;
		
		ultimo = parVet.length - 1;
		lisPar = new ArrayList<Parametro>();
		for ( int i = 1; i < parVet.length; i++ )
		{
			try
			{
				ultRec = rec;
				rec = aplic.getRecurso(parVet[i], idRec);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(GET): Erro ao recuperar informações do recurso (" +
									parVet[i] + "): " +
									e.getMessage() );
				return;
			}

			if ( rec == null )
			{
				if ( i < ultimo )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(GET): Recurso '" + parVet[i] + "' inexistente! " +
										" IdAplic = " + aplic.getId() +
										" IdRec = " + idRec );
					return;
				}

				try 
				{
					met = aplic.getMetodo(parVet[i], idRec);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(GET): Erro ao recuperar informações do Metodo (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}

				if ( met == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(GET): Metodo " + parVet[i] + " inexistente!" );
					return;
				}
				
				acheiMetodo = true;
				
				// Achei o Metodo, então pego os parâmetros
				try 
				{
					met.carregaParametros(lisPar);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(GET): Erro ao recuperar Parametros do Metodo (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}
			}
			else // Achei o Recurso, então pego os parâmetros
			{
				try 
				{
					rec.carregaParametros(lisPar);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(GET): Erro ao recuperar Parametros do recurso (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}

				idRec = rec.getIdBanco();
			}
		}
		
		if ( !acheiMetodo )
		{
			try 
			{
				met = aplic.getMetodo(null, idRec);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(GET): Erro ao recuperar informações do Metodo do recurso(" +
									parVet[ultimo] + "): " +
									e.getMessage() );
				return;
			}
			
			
			// Achei o Metodo, então pego os parâmetros
			try 
			{
				met.carregaParametros(lisPar);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(GET): Erro ao recuperar Parametros do Metodo do recurso (" +
									parVet[ultimo] + "): " +
									e.getMessage() );
				return;
			}
		}

		Iterator it;
		
		parSaida = "";
		it = lisPar.iterator();
		while(it.hasNext()) 
		{
			par = (Parametro)it.next();
			
			parSaida += par.getField();
		}

		// Se há parâmetros além do authorization, gera o formulário
		if ( parSaida.length() != 0 )
		{
			saida = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
			saida += "<?xml-stylesheet type=\"text/xsl\" href=\"/IesAvance/xform.xsl\"?>\n";
			saida += "<xforms>\n<model>\n<submission id=\"" + parVet[0];
			saida += "\" action=\"" + request.getRequestURI() + "\" method=\"urlencoded-post\"/>\n</model>\n";
			
			saida += parSaida;
			
			saida += "<submit submission=\"" + parVet[0] + "\">\n";
			saida += "<label>Enviar</label>\n</submit>\n</xforms>\n";
			
			PrintWriter out = response.getWriter();

			response.setContentType("application/rss+xml");

			// Substitui o tipo de caracter para funcionar no Explorer
			saida = saida.replaceFirst("UTF-8", "ISO-8859-1");
			
			out.print(saida);
			
			out.close();
		}
		else // Se só tem como parãmetros o authorizarion (ou nenhum), vai para a página
		{
			Object aut;
			String strURL;
			ResponseParser geraHTML;
			String authorization = null;

			saida = "";
			geraHTML = new ResponseParser();
			
			if ( rec == null ) rec = ultRec;

			strURL = rec.getBase() + "/" + rec.getPath();

			aut = request.getAttribute("Authorization");
			if ( aut != null )
				authorization = aut.toString();
			
			if ( met == null )
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(GET): Recurso nao tem metodo!");
			}
			
			List<Resposta> lisRes;
			RespostaPersist resPersist;
			
			lisRes = new ArrayList<Resposta>();
			resPersist = new RespostaPersist();
			try
			{
				resPersist.carregaListaRespostas(met.getIdBanco(), lisRes);
			}
//			catch (ExcecaoBanco e) 
			catch (Exception e)		
			{
				String saiErro = "";
				
				for ( int i = 0; i < e.getStackTrace().length; i++ )
					saiErro += e.getStackTrace()[i].toString() + " ++ \n";
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(GET): " + saiErro );
				return;
			}
			
			try
			{
				saida = geraHTML.parseResponse2HTML(strURL, authorization, lisRes);
			}
			catch (ExcecaoParser e) 
			{
				int cod = geraHTML.getCodigoErro();
				if ( cod == -1 )
					cod = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
				
				response.sendError( cod, e.getMessage());
				return;
			}
			catch (Exception e)		
			{
				String saiErro = "";
				
				for ( int i = 0; i < e.getStackTrace().length; i++ )
					saiErro += e.getStackTrace()[i].toString() + " ++ \n";
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(GET): " + saiErro );
				return;
			}

			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();

			out.print(saida);
			
			out.close();
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String parStr;
		String parVet[];
		Aplicacao aplic;
		AplicacaoPersist persist;

		parStr = request.getPathInfo();
		if ( parStr.length() < 2 )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(POST): Numero invalido de parametros!" );
			return;
		}
		
		parVet = parStr.substring(1).split("/");
		
		try
		{
			persist = new AplicacaoPersist();

			aplic = (Aplicacao)persist.Busca(parVet[0]);
		}
		catch ( ExcecaoBanco e )
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(POST): Erro ao recuperar informações da aplicacao: " +
								e.getMessage() );
			return;
		}
		
		if ( aplic == null )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(POST): Aplicacao " + parVet[0] + " inexistente!" );
			return;
		}
		
		int ultimo;
		int idRec = -1;
		Metodo met = null;
		Recurso rec = null,
			    ultRec = null;
		List<Parametro> lisPar;
		boolean acheiMetodo = false;
		
		ultimo = parVet.length - 1;
		lisPar = new ArrayList<Parametro>();
		for ( int i = 1; i < parVet.length; i++ )
		{
			try
			{
				ultRec = rec;
				rec = aplic.getRecurso(parVet[i], idRec);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(POST): Erro ao recuperar informações do recurso (" +
									parVet[i] + "): " +
									e.getMessage() );
				return;
			}

			if ( rec == null )
			{
				if ( i < ultimo )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): parStr = '" + request.getPathInfo() + "' " +
										"(" + i + ")" +
										" Recurso '" + parVet[i] + "' inexistente! " +
										" IdAplic = " + aplic.getId() +
										" IdRec = " + idRec );
					return;
				}

				try 
				{
					met = aplic.getMetodo(parVet[i], idRec);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(POST): Erro ao recuperar informações do Metodo (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}

				if ( met == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Metodo " + parVet[i] + " inexistente!" );
					return;
				}
				
				acheiMetodo = true;
				
				// Achei o Metodo, então pego os parâmetros
				try 
				{
					met.carregaParametros(lisPar);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(POST): Erro ao recuperar Parametros do Metodo (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}
			}
			else // Achei o Recurso, então pego os parâmetros
			{
				try 
				{
					rec.carregaParametros(lisPar);
				}
				catch ( ExcecaoBanco e )
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
										"AvaNCE(POST): Erro ao recuperar Parametros do recurso (" +
										parVet[i] + "): " +
										e.getMessage() );
					return;
				}

				idRec = rec.getIdBanco();
			}
		}
		
		if ( !acheiMetodo )
		{
			try 
			{
				met = aplic.getMetodo(null, idRec);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(POST): Erro ao recuperar informações do Metodo do recurso(" +
									parVet[ultimo] + "): " +
									e.getMessage() );
				return;
			}
			
			
			// Achei o Metodo, então pego os parâmetros
			try 
			{
				met.carregaParametros(lisPar);
			}
			catch ( ExcecaoBanco e )
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
									"AvaNCE(POST): Erro ao recuperar Parametros do Metodo do recurso (" +
									parVet[ultimo] + "): " +
									e.getMessage() );
				return;
			}
		}
		
		if ( rec == null ) rec = ultRec;
		
		Autenticacao aut;
		
		aut = rec.getAutenticacao();
		if ( aut != null )
		{
			String senha; 
			String usuario; 

			usuario = request.getParameter("usuAuth");
			senha   = request.getParameter("senAuth");
			
			if ( (usuario != null) && (senha != null) )
			{
				//*****  AQUI GUARDA NO BANCO   ************
				
				if ( aut.getAuthMode().toLowerCase().equals("basic") )
				{
					request.setAttribute("Authorization", 
										 "Basic " + encode(usuario + ":" + senha));
				}
			}
		}


		boolean prim;
		Iterator it;
		Parametro par;
		String parQuery;
		
		prim = true;
		parQuery = "";
		it = lisPar.iterator();
		while(it.hasNext()) 
		{
			par = (Parametro)it.next();
			
			if ( par.getStyle().equals("form") )
			{
				parStr = request.getParameter(par.getName());
				if ( (parStr != null) && (parStr.length() > 0) )
				{
					parQuery += (prim ? "?" : "&");
					parQuery += par.getName() + "=" + parStr;
					prim = false;
				}
			}
		}
		
/*		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Carrega");

		request.setAttribute("url", URLEncoder.encode(rec.getBase() + "/" 
									+ rec.getPath(), "ISO-8859-1"));

		dispatcher.forward(request, response);
*/
		Object objAut;
		String strURL, saida;
		ResponseParser geraHTML;
		String authorization = null;

		saida = "";
		geraHTML = new ResponseParser();
		
		if ( rec == null ) rec = ultRec;

		strURL = rec.getBase() + rec.getPath() + parQuery;

		objAut = request.getAttribute("Authorization");
		if ( objAut != null )
			authorization = objAut.toString();
		
		if ( met == null )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
								"AvaNCE(GET): Recurso nao tem metodo!");
		}

/*		
response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					"URL='" + strURL + "'<br/>" +
					"Auth='" + authorization + "'" );
*/
		
		List<Resposta> lisRes;
		RespostaPersist resPersist;

		lisRes = new ArrayList<Resposta>();
		resPersist = new RespostaPersist();
		try
		{
			resPersist.carregaListaRespostas(met.getIdBanco(), lisRes);
		}
//		catch (ExcecaoBanco e) 
		catch (Exception e)		
		{
			String saiErro = "";
			
			for ( int i = 0; i < e.getStackTrace().length; i++ )
				saiErro += e.getStackTrace()[i].toString() + " +/+ ";
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(POST): " + saiErro);
			return;
		}

		try
		{
			saida = geraHTML.parseResponse2HTML( strURL, authorization, lisRes );
		}
		catch (ExcecaoParser e) 
		{
			int cod = geraHTML.getCodigoErro();
			if ( cod == -1 )
				cod = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
			response.sendError( cod, "Erro: " + e.getMessage());
			return;
		}
		catch (Exception e)		
		{
			String saiErro = "";
			
			for ( int i = 0; i < e.getStackTrace().length; i++ )
				saiErro += e.getStackTrace()[i].toString() + " +/+ ";
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
								"AvaNCE(POST): " + saiErro);
			return;
		}

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		out.print(saida);
		
		out.close();
	}   	  	    
}