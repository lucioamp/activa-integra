package util.interpretador;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nucleo.wadl.Resposta;
import nucleo.wadl.NoArvoreResposta;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResponseParser 
{
	private Document dom;
	private int codigoErro;
	
	public int getCodigoErro()		{ return codigoErro;	}

	public String parseResponse2HTML( String url, String authorization,
									  List<Resposta> respostas ) throws ExcecaoParser 
	{
		Iterator it;
		boolean achei;
		Resposta res = null;

		// Obtem o objeto raiz
		Element raiz;
		
		// Faz o parsing do arquivo xml criando um objeto DOM
		parseXmlFile( url, authorization );
		
		raiz = dom.getDocumentElement();
String saida = "";
		achei = false;
		it = respostas.iterator();
		while(it.hasNext()) 
		{
			res = (Resposta)it.next();

saida += "++" + res.getElement() + "++";			
			if ( res.getElement().equals(raiz.getTagName()) )
			{
				achei = true;
				break;
			}
		}
		
		if ( !achei )
		{
			throw new ExcecaoParser("AvaNCE: Tag Raiz (" + raiz.getTagName() + ") inesperada!\n" + saida);
		}
		
		// Converte o XML para HTML
		return parseDocument( raiz, res.getEstruturaXML() );
	}

	private void parseXmlFile( String strURL, String authorization ) throws ExcecaoParser
	{
		int contador = 0;
		URL u;
		HttpURLConnection uc = null;

		codigoErro = -1;

		try 
		{ 
			u = new URL(strURL);

			contador++; //1

			try 
			{
				uc = (HttpURLConnection)u.openConnection();

				contador++;		//2		
				if ( authorization != null )
					uc.setRequestProperty("Authorization", authorization);
		
				InputStream content = (InputStream)uc.getInputStream();

				contador++;		//3		
				//get the factory
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				
				contador++;	//4				
				
				try 
				{
					//Using factory get an instance of document builder
					DocumentBuilder db = dbf.newDocumentBuilder();

					contador++;  //5				
					
					//parse using builder to get DOM representation of the XML file
					dom = db.parse( content );

					contador++; //6				
				}
				catch (ParserConfigurationException pce) 
				{
					throw new ExcecaoParser("AvaNCE: Erro Parser (" + strURL + "): " + pce.getMessage());
				}
				catch (SAXException se) 
				{
					throw new ExcecaoParser("AvaNCE: Erro SAX (" + strURL + "): " + se.getMessage());
				}
			}
			catch (IOException e) 
			{
				String msg = "Erro IO (" + contador + "):" + e.getMessage();
				
				if ( uc != null )
				{
					try
					{
						codigoErro = uc.getResponseCode();
					}
					catch (IOException ne) 
					{
						throw new ExcecaoParser("AvaNCE: Nao consegui obter codigo do Erro: " + 
												 ne.getMessage());
					}
					
					if ( codigoErro == 401 )
						msg = uc.getHeaderField("WWW-Authenticate");
				}
					
				throw new ExcecaoParser(msg);
			}
		}
		catch (MalformedURLException e) 
		{
			codigoErro = HttpServletResponse.SC_BAD_REQUEST;
			throw new ExcecaoParser("AvaNCE: URL mal formada (" + e.getMessage() + ")");
		}
	}

	// retorna todos os textos filhos de um no 
	public String extraiTexto( Node no )
	{
		String paragrafo;
		NodeList lisFilhos;
		
		if ( no == null )
			return "";

		paragrafo = "";
		lisFilhos = no.getChildNodes();
		if ( lisFilhos != null && lisFilhos.getLength() > 0 ) 
		{
			Node noFilho;
			
			for( int i = 0 ; i < lisFilhos.getLength(); i++ ) 
			{
				noFilho = lisFilhos.item(i);
				if ( noFilho.getNodeType() ==  Node.TEXT_NODE )
					if ( !noFilho.getNodeValue().equals("\n") )
						paragrafo += noFilho.getNodeValue();
			}
		}

		return paragrafo;
	}
	
	public String imprimeTag( Element docEle, NoArvoreResposta estrutura )
	{
		NodeList lisFilhos;
		String texto, saida = "";
		NoArvoreResposta estElemento;

		saida  = "<br/><table border='2' width='100%'><tr><td style='padding: 5pt 5pt 5pt 5pt;'>";
		saida += docEle.getTagName();
		texto = extraiTexto(docEle);
		if ( texto.length() > 0 )
			saida += "=" + texto;

		/*
		 * Imprime os atributos
		 */
		if ( docEle.hasAttributes() )
		{
			Attr atrib;
			NoArvoreResposta estAtributo;
			
			estAtributo = estrutura.primeiroAtributo();
			while ( estAtributo != null )
			{
				atrib = docEle.getAttributeNode( estAtributo.getNomeElem() );
				saida += "<br/>"  + estAtributo.getNomeElem() + "=" + atrib.getValue();

				estAtributo = estrutura.proximoAtributo();
			}
		}
		
		/*
		 * Imprime as tags filhas
		 */
		estElemento = estrutura.primeiroElemento();
		while ( estElemento != null )
		{
			lisFilhos = docEle.getElementsByTagName( estElemento.getNomeElem() );

			if ( lisFilhos != null && lisFilhos.getLength() > 0 ) 
			{
				Element elem;
				
				saida += "<dl>";
				for( int i = 0 ; i < lisFilhos.getLength(); i++ ) 
				{
					elem = (Element)lisFilhos.item(i);
					saida += "<dd style='padding-left: 20pt'>" + imprimeTag(elem, estElemento) + "</dd>";
				}
				saida += "</dl>";
			}

			estElemento = estrutura.proximoElemento();
		}
		
		saida += "</td></tr></table>";
		
		return saida;
	}
	
	public String parseDocument( Element docEle, NoArvoreResposta estrutura ) throws ExcecaoParser
	{
		return imprimeTag( docEle, estrutura );

/* Saída do Delicious - Configurada no momento da criação da aplicação
 * 		
		saida  = "<div align='center'><h1>" + docEle.getTagName() + "</h1>";
		
		if ( estrutura == null )
			return saida;
		
		if ( docEle.hasAttributes() )
		{
			Attr atrib;
			NoArvoreResposta estAtributo;
			
			saida += "<table border cellpadding='5' class='resultTab'>";
			saida += "<tr><th class='resultHeader'>Atributo</th><th class='resultHeader'>Valor</th></tr>";

			estAtributo = estrutura.primeiroAtributo();
			while ( estAtributo != null )
			{
				atrib = docEle.getAttributeNode( estAtributo.getNomeElem() );
				saida += "<tr><td class='resultCel'>"  + estAtributo.getNomeElem() + 
						 "</td><td class='resultCel'>" + atrib.getValue() + "</td></tr>";

				estAtributo = estrutura.proximoAtributo();
			}

			saida += "</table>";
		}

		NodeList nodes;
		NoArvoreResposta estElemento;

		estElemento = estrutura.primeiroElemento();
		while ( estElemento != null )
		{
			nodes = docEle.getElementsByTagName( estElemento.getNomeElem() );

			if ( nodes != null && nodes.getLength() > 0 ) 
			{
				int numCol;
				Attr atrib;
				Element elem;
				NoArvoreResposta estAtributo;

				numCol = estElemento.numAtributos();
				saida += "<table border='1' class='resultTab'><tr><th  class='resultHeader' colspan='" + numCol;
				saida += "'>Tags " + estElemento.getNomeElem() + "</th></tr>";
				
				saida += "<tr>";
				estAtributo = estElemento.primeiroAtributo();
				while ( estAtributo != null )
				{
					saida += "<th class='resultHeader'>" + estAtributo.getNomeElem() + "</th>";

					estAtributo = estElemento.proximoAtributo();
				}
				saida += "</tr>";

				for( int i = 0 ; i < nodes.getLength(); i++ ) 
				{
					elem = (Element)nodes.item(i);

					saida += "<tr>";
					estAtributo = estElemento.primeiroAtributo();
					while ( estAtributo != null )
					{
						atrib = elem.getAttributeNode( estAtributo.getNomeElem() );
						
						if ( estAtributo.getIdLinkMetodo() != -1 )
						{
							// Tem que juntar a base e o path do recurso com o nome do método. Isto
							//  já deve estar sendo feito onde é preparado a volta do formulário
							
							// saida += "<td class='resultCel'><a href='" + estAtributo.getPathLink() + "'>" + 
							//  		 atrib.getValue() + "</a></td>";
							
							
							// falta pegar o nome da aplicação e o método no banco...
							saida += "<td class='resultCel'>" +
							 "<form action='/IesAvance/Executor/API%20del.icio.us/" + estAtributo.getPathLink() + "' " +
						          " method='POST' onsubmit='abreFormulario(this, false); return false;'>" +
						     "<input type='hidden' name='" + estAtributo.getNomeParam() + "' value='" + atrib.getValue() + "'/>" +
						     "<input type='submit' value='" + atrib.getValue() + "'/>" +
						     "</form>" +
							 "</td>";
						}
						else
							saida += "<td class='resultCel'>" + atrib.getValue() + "</td>";

						estAtributo = estElemento.proximoAtributo();
					}
					saida += "</tr>";
				}
				
				saida += "</table>";
			}

			estElemento = estrutura.proximoElemento();
		}
		
		saida += "</div>";
*/		
	}
}
