package util.integra;

import java.io.IOException;

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

	public String executaAplicação() {
		String response = "";
		
		HttpClient client = new HttpClient();
		
		HttpMethod method = new GetMethod("http://search.twitter.com/search.atom?q=web");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myretryhandler);
		
		Credentials credentials = new UsernamePasswordCredentials("lucioamp", "73210077");
		client.getState().setCredentials(AuthScope.ANY, credentials);

		try {
			int status = client.executeMethod(method);
			
			// Status OK
			if (status == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
				System.out.println(response);
			}
			else {
				response = "<span style='color:red;'>" + method.getStatusLine().toString() + "</span>";
			}

			// Libera a conexão
			method.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			response = "<span style='color:red;'>" + e.toString() + "</span>";
		}
		
		return response;
	}
	
	public String getJSONFormatado(JSONObject obj) throws JSONException {
		String retorno = obj.toString(4).replaceAll("\\n", "<br>").replaceAll(" ", "&nbsp;");
		retorno = retorno.substring(1, retorno.length() - 2);
		
		return retorno;
	}
}
