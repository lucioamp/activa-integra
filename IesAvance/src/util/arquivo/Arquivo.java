package util.arquivo;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

public class Arquivo 
{
	private int codigoErro;
	
	public int getCodigoErro()		{ return codigoErro;	}

	public static String carrega( String nomeArquivo ) throws ExcecaoArquivo  
	{
		BufferedReader 	in = null;
		String	linha, saida;
		
// Depuração: para obter o diretório atual (1)			
//		File dirAtual = new File(".");
		
		saida = "";
		try
		{
// Depuração: para obter o diretório atual (2)			
//			saida += dirAtual.getCanonicalPath();
			
			in = new BufferedReader(new FileReader(nomeArquivo));
			
			while ((linha = in.readLine()) != null)
				saida += linha;
		}
		catch (IOException e) 
		{
			throw new ExcecaoArquivo("Erro na leitura do arquivo ("
									 + e.getMessage() + ")" );
		}
		finally 
		{
			try 
			{
				if (in != null)
					in.close();
			}
			catch (IOException e) 
			{
				throw new ExcecaoArquivo("Erro no fechamento do arquivo ("
										 + e.getMessage() + ")" );
			}
		}
		
		return saida;
	}

	public static String carregaPagina( String strURL ) throws ExcecaoArquivo  
	{
	    String line;
		String retVal;
		
		URL u;
		URLConnection uc;
	
		retVal = "";
		try 
		{ 
			u = new URL(strURL);
	   
			try 
			{
				uc = u.openConnection();

				InputStream content = (InputStream)uc.getInputStream();
				BufferedReader in = new BufferedReader (new InputStreamReader(content));

				while ((line = in.readLine()) != null) 
				{
					retVal += line;
				}
			} 
			catch (IOException e) 
			{
				return("Erro de leitura (" + e.getMessage() + ")");
			}
		}
		catch (MalformedURLException e) 
		{
			return("URL mal formada (" + e.getMessage() + ")");
		}

		return retVal;
	}
	
	public String ObtemPagina(String strURL, String authorization) throws ExcecaoArquivo
	{
	    String line;
		String retVal = null;
		
		URL u;
		HttpURLConnection uc = null;

		codigoErro = -1;
		
		try 
		{
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
			catch (IOException e) 
			{
				String msg = e.getMessage();
				
				if ( uc != null )
				{
					try
					{
						codigoErro = uc.getResponseCode();
					}
					catch (IOException ne) 
					{
						throw new ExcecaoArquivo("Nao consegui obter codigo do Erro: " + 
												 ne.getMessage());
					}
					
					if ( codigoErro == 401 )
						msg = uc.getHeaderField("WWW-Authenticate");
				}
					
				throw new ExcecaoArquivo(msg);
			}
		}
		catch (MalformedURLException e) 
		{
			codigoErro = HttpServletResponse.SC_BAD_REQUEST;
			throw new ExcecaoArquivo("URL mal formada (" + e.getMessage() + ")");
		}

		return retVal;
	}
}
