package util.integra;

import java.io.IOException;

import modelo.integra.ExecutorAplicacaoRequest;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

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
		
//		client.getHostConfiguration().setProxy("proxy.houston.hp.com", 8080);
		
		//HttpMethod method = new GetMethod("http://search.twitter.com/search.atom?q=web");
		HttpMethod method = new GetMethod("https://api.del.icio.us/v1/posts/all?results=10");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);
		
		if (request.getUsuario() != null && request.getSenha() != null) {
			Credentials credentials = new UsernamePasswordCredentials(
					request.getUsuario(), request.getSenha());
			client.getState().setCredentials(AuthScope.ANY, credentials);
		}

		try {
			int status = client.executeMethod(method);
			
			response[0] = String.valueOf(status);
			
			// Status OK
			if (status == HttpStatus.SC_OK) {
				response[1] = method.getResponseBodyAsString();
				System.out.println(response);
			}
			else {
				response[1] = "<span style='color:red;'>" + method.getStatusLine().toString() + "</span>";
			}

			// Libera a conexão
			method.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			response[1] = "<span style='color:red;'>" + e.toString() + "</span>";
		}
		
		return response;
	}
	
	public String getJSONFormatado(JSONObject obj) throws JSONException {
		String retorno = obj.toString(4).replaceAll("\\n", "<br>").replaceAll(" ", "&nbsp;");
		retorno = retorno.substring(1, retorno.length() - 2);
		
		return retorno;
	}
}
