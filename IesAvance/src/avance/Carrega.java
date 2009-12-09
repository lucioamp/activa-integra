package avance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.HttpURLConnection;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Carrega
 *
 */
 public class Carrega extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
 {
	private static final long serialVersionUID  = 1;

	private int codigoErro;
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Carrega() 
	{
		super();
		codigoErro = -1;
	}   	
	
	public String ObtemPagina(String strURL, String authorization) 
								throws IOException, MalformedURLException
	{
	    String line;
		String retVal = null;
		
		URL u;
		HttpURLConnection uc = null;
	
		u = new URL(strURL);
   
		try 
		{
			uc = (HttpURLConnection)u.openConnection();
			
			if ( authorization != null )
				uc.setRequestProperty("Authorization", authorization);
	
			InputStream content = (InputStream)uc.getInputStream();
			BufferedReader in = new BufferedReader (new InputStreamReader(content));
	
			retVal = "";
			while ((line = in.readLine()) != null) 
			{
				retVal += line;
			}
		}
		catch( IOException e )
		{
			String msg = e.getMessage();
			
			if ( uc != null )
			{
				codigoErro = uc.getResponseCode();
				if ( codigoErro == 401 )
					msg = uc.getHeaderField("WWW-Authenticate");
			}
			else
				codigoErro = -1;
				
			throw new IOException(msg);
		}

		return retVal;
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String url;
		String saida = "";

		try 
		{
			url = URLDecoder.decode(request.getParameter("url"), "ISO-8859-1");
		}
		catch (Exception e)
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							    "AvaNCE(GET): Erro ao decodificar parâmetro (" + 
							    request.getParameter("url") + "):" + e.getMessage());
			return;
		}

		try
		{
			saida = ObtemPagina(url, null);
		}
		catch (MalformedURLException e) 
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST,
				    			"AvaNCE(GET): URL " + url + " mal formada (" + e.getMessage() + ")" );
			return;
		}
		catch (IOException e) 
		{
			if ( codigoErro == -1 )
				codigoErro = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
			response.sendError( codigoErro,	e.getMessage());
			return;
		}

		if ( saida.substring(0,5).equals("<?xml") )
			response.setContentType("application/rss+xml");
		else
			response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		// Substitui o tipo de caracter para funcionar no Explorer
//		saida = saida.replaceFirst("UTF-8", "ISO-8859-1");

		out.print(saida);
		
		out.close();
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Object aut;
		String url;
		String saida;
		String authorization = null;

		try 
		{
			url = URLDecoder.decode(request.getAttribute("url").toString(), "ISO-8859-1");
			
			aut = request.getAttribute("Authorization");
			if ( aut != null )
				authorization = aut.toString();
		}
		catch (Exception e)
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				    "AvaNCE(POST): Erro ao decodificar parâmetro (" + 
				    request.getParameter("url") + "):" + e.getMessage());
			return;
		}

		try
		{
			saida = ObtemPagina(url, authorization);
		}
		catch (MalformedURLException e) 
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST,
				    			"AvaNCE(POST): URL " + url + " mal formada (" + e.getMessage() + ")" );
			return;
		}
		catch (IOException e) 
		{
			if ( codigoErro == -1 )
				codigoErro = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
			response.sendError( codigoErro,	e.getMessage());
			return;
		}

		if ( saida.substring(0,5).equals("<?xml") )
			response.setContentType("application/rss+xml");
		else
			response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		// Substitui o tipo de caracter para funcionar no Explorer
//		saida = saida.replaceFirst("UTF-8", "ISO-8859-1");

		out.print(saida);
		
		out.close();
	}   	  	    
}