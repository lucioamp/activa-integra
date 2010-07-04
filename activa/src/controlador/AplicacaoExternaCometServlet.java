package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.integra.ExecutorAplicacaoRequest;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;
import org.apache.commons.httpclient.NameValuePair;

import util.integra.ExecutorAplicacao;

public class AplicacaoExternaCometServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 719792464317818148L;

	private MessageSender messageSender = null;
	private static final Integer TIMEOUT = 30 * 60 * 1000;
	
	private HttpSession session;

	@Override
	public void destroy() {
		messageSender.stop();
		messageSender = null;

	}

	@Override
	public void init() throws ServletException {
		messageSender = new MessageSender();
		Thread messageSenderThread = new Thread(messageSender, "MessageSender[" + getServletContext().getContextPath()
				+ "]");
		messageSenderThread.setDaemon(true);
		messageSenderThread.start();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log("Caiu no lugar errado!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
	}

	@Override
	public void event(CometEvent event) throws IOException, ServletException {
		HttpServletRequest request = event.getHttpServletRequest();
		HttpServletResponse response = event.getHttpServletResponse();
		
		session = request.getSession(true);

		if (event.getEventType() == CometEvent.EventType.BEGIN) {
			request.setAttribute("org.apache.tomcat.comet.timeout", TIMEOUT);

			messageSender.setConnection(response);

			Executor executor = new Executor();
			new Thread(executor).start();
		} else if (event.getEventType() == CometEvent.EventType.ERROR) {
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.END) {
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.READ) {
			throw new UnsupportedOperationException("This servlet does not accept data");
		}

	}

	private class MessageSender implements Runnable {

		protected boolean running = true;
		protected final ArrayList<String> messages = new ArrayList<String>();
		private ServletResponse connection;

		private synchronized void setConnection(ServletResponse connection) {
			this.connection = connection;
			notify();
		}
		
		public void stop() {
            running = false;
        }

		public void send(String message) {
			synchronized (messages) {
				messages.add(message);
				messages.notify();
			}
		}

		public void run() {
			while (running) {
				if (messages.size() == 0) {
					try {
						synchronized (messages) {
							messages.wait();
						}
					} catch (InterruptedException e) {
						// Ignore
					}
				}
				String[] pendingMessages = null;
				synchronized (messages) {
					pendingMessages = messages.toArray(new String[0]);
					messages.clear();
				}
				try {
					if (connection == null) {
						try {
							synchronized (this) {
								wait();
							}
						} catch (InterruptedException e) {
							// Ignore
						}
					}
					PrintWriter writer = connection.getWriter();
					for (int j = 0; j < pendingMessages.length; j++) {
						final String forecast = pendingMessages[j] + "<br>";
						writer.println(forecast);
						log("Writing:" + forecast);
					}
					writer.flush();
					writer.close();
					connection = null;
					log("Closing connection");
				} catch (IOException e) {
					log("IOExeption sending message", e);
				}
			}
		}
	}

	private class Executor implements Runnable {

		public void run() {
			try {
				ExecutorAplicacaoRequest aplicacaoRequest = new ExecutorAplicacaoRequest();
				aplicacaoRequest.setIdUsuarioAplicacao(1L);
				aplicacaoRequest.setUrl("https://api.del.icio.us/v1/posts/all");
				aplicacaoRequest.setMetodo("GET");
				aplicacaoRequest.setUsuario("lucioamp");
				aplicacaoRequest.setSenha("7321007a");
				aplicacaoRequest.getParametros().add(new NameValuePair("results", "1"));

				ExecutorAplicacao executor = new ExecutorAplicacao();
				String[] resultado = executor.executaAplicação(aplicacaoRequest);

				String conteudoAtual = resultado[1];
				
				String conteudoAnterior = (String) session.getAttribute(String.valueOf(aplicacaoRequest.getIdUsuarioAplicacao()));
				
				String conteudoFinal = "";
				if (!conteudoAtual.equals(conteudoAnterior)) {
					conteudoFinal = conteudoAtual;
					
					session.setAttribute(String.valueOf(aplicacaoRequest.getIdUsuarioAplicacao()), conteudoFinal);
				}
				
				Thread.sleep(30000L);
				
				messageSender.send(conteudoAtual);
			} catch (Exception e) {
				log("Erro: " + e.getMessage());
			}
		}
	}

}
