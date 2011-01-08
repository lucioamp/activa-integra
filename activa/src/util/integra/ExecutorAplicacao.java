package util.integra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import modelo.integra.ExecutorAplicacaoRequest;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class ExecutorAplicacao {
	HttpMethodRetryHandler myretryhandler = new HttpMethodRetryHandler() {
		public boolean retryMethod(final HttpMethod method, final IOException exception, int executionCount) {
			if (executionCount >= 3) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof NoHttpResponseException) {
				// Retry if the server dropped connection on us
				return true;
			}
			if (!method.isRequestSent()) {
				// Retry if the request has not been sent fully or
				// if it's OK to retry methods that have been sent
				return true;
			}
			// otherwise do not retry
			return false;
		}
	};

	public String[] executaAplicação(ExecutorAplicacaoRequest request) {
		String[] response = new String[2];
		
		HttpClient client = new HttpClient();
		
		// Autenticação
		if (request.getUsuario() != null && request.getSenha() != null) {
			Credentials credentials = new UsernamePasswordCredentials(
					request.getUsuario(), request.getSenha());
			client.getState().setCredentials(AuthScope.ANY, credentials);
		}
		
//		client.getHostConfiguration().setProxy("proxy.houston.hp.com", 8080);
		
		try {
			if (request.getMetodo().equals("POST")) {
				PostMethod method = new PostMethod(request.getUrl());
				method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);
				
				for (NameValuePair parametro : request.getParametros()) {
					method.addParameter(parametro.getName(), parametro.getValue());
				}
				
				int status = client.executeMethod(method);
				
				response[0] = String.valueOf(status);
				
				// Status OK
				if (status == HttpStatus.SC_OK) {
					response[1] = "<span style='color:green;'>Dados enviados com sucesso.</span>";
				}
				else {
					response[1] = "<span style='color:red;'>" + method.getStatusLine().toString() + "</span>";
				}

				// Libera a conexão
				method.releaseConnection();
			}
			else {
				GetMethod method = new GetMethod(request.getUrl());
				method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);
				
				method.setQueryString(request.getParametros().toArray(new NameValuePair[request.getParametros().size()]));
				
				int status = client.executeMethod(method);
				
				response[0] = String.valueOf(status);
				
				// Status OK
				if (status == HttpStatus.SC_OK) {
					response[1] = formataRetorno(method.getResponseBodyAsString());	
				}
				else {
					response[1] = "<span style='color:red;'>" + method.getStatusLine().toString() + "</span>";
				}

				// Libera a conexão
				method.releaseConnection();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			response[1] = "<span style='color:red;'>" + e.toString() + "</span>";
		}
		
		return response;
	}
	
	private String formataRetorno(String responseBodyAsString) {
		String retorno = "";
		
		try {
			// JSON
			if (responseBodyAsString.startsWith("{")) {
				JSONObject obj = new JSONObject(responseBodyAsString);
				String xmlString = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><resultado>" + XML.toString(obj)
						+ "</resultado>";
				
				retorno = xmlParaHtml(xmlString);
			// XML
			} else if (responseBodyAsString.startsWith("<?xml")) {
				String xmlString = responseBodyAsString.replace("UTF-8", "iso-8859-1").replaceAll("\\n", "");
				// Tira os espaços entre as tags
				xmlString = xmlString.replaceAll("[\\s]+<", "<");				

				retorno = xmlParaHtml(xmlString);
			// RSS
			} else if (responseBodyAsString.indexOf("<feed") > -1 || responseBodyAsString.indexOf("<rss") > -1) {
				StringWriter xmlBuffer = new StringWriter();
				xmlBuffer.write(responseBodyAsString);
				ByteArrayInputStream xmlParseInputStream = new ByteArrayInputStream(xmlBuffer.toString().getBytes());

				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new InputStreamReader(xmlParseInputStream));
				SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
				retorno = entryToHtml(entry);
			} else {
				retorno = responseBodyAsString;
			}
		} catch (Exception e) {
			retorno = "<span style='color:red;'>" + e.getMessage() + "</span>";
		}

		return retorno;
	}
	
	private String xmlParaHtml(String xmlString) {
		StringWriter xmlBuffer = new StringWriter();
		xmlBuffer.write(xmlString);

		ByteArrayInputStream xmlParseInputStream = new ByteArrayInputStream(xmlBuffer.toString().getBytes());

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlParseInputStream);
	
			// Remove os comentários
			removeAll(document, Node.COMMENT_NODE, null);
			
	        Source source = new DOMSource(document);
	        
			TransformerFactory tFactory = TransformerFactory.newInstance();
			InputStream xslInput = getClass().getResourceAsStream("/xmlOutput.xsl");
	
			Transformer transformer = tFactory.newTransformer(new StreamSource(xslInput));
	
			ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
		
			transformer.transform(source, new StreamResult(byte1));
			
			return byte1.toString();
		} catch (Exception e) {
			
		}

		return "<span style='color:red;'>Erro ao converter para HTML.</span>";
	}
	
	private String entryToHtml(SyndEntry entry) {
		StringBuilder html = new StringBuilder("<h2>");
		html.append(entry.getTitle());
		html.append("</h2>");
		html.append(((SyndContentImpl) entry.getContents().get(0)).getValue());
		return html.toString();
	}

	public String getJSONFormatado(JSONObject obj) throws JSONException {
		String retorno = obj.toString(4).replaceAll("\\n", "<br>").replaceAll(" ", "&nbsp;");
		retorno = retorno.substring(1, retorno.length() - 2);
		
		return retorno;
	}
	
	public static void removeAll(Node node, short nodeType, String name) {
		if (node.getNodeType() == nodeType && (name == null || node.getNodeName().equals(name))) {
			node.getParentNode().removeChild(node);
		} else {
			NodeList list = node.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				removeAll(list.item(i), nodeType, name);
			}
		}
	}
}
