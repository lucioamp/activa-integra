package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import modelo.integra.ExecutorAplicacaoRequest;
import modelo.integra.UsuarioAplicacao;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

import util.AplicacaoExternaException;
import util.integra.ExecutorAplicacao;

public class AplicacaoExternaCometServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 719792464317818148L;

	private MessageSender messageSender = null;
	private static final Integer TIMEOUT = 30 * 60 * 1000;
	
	private HttpSession session;
	
	private List<Timer> executorLista = new ArrayList<Timer>();

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
			
			/*
			// Intervalo entre execuções
			try {
				synchronized(Thread.currentThread()){
					Thread.currentThread().wait(5000);		
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			*/

			messageSender.setConnection(response);
			
			Usuario usuario = (Usuario) session.getAttribute("membro");
			if (usuario != null) {
				UsuarioAplicacao usuarioAplicacaoReq = new UsuarioAplicacao();
				usuarioAplicacaoReq.setPkUsuario(usuario.getPkUsuario());
				
				// Parar as threads iniciadas
				for (Timer timer : executorLista) {
					timer.cancel();
				}

				executorLista.clear();
				try {
					List<UsuarioAplicacao> usuarioAplicacaoLista = UsuarioAplicacao
							.consultarNotificacaoAutomatica(usuarioAplicacaoReq);
					for (UsuarioAplicacao usuarioAplicacao : usuarioAplicacaoLista) {
						Executor executor = new Executor(usuarioAplicacao);
						
						Timer timer = new Timer(true);
						timer.schedule(executor, 0, 30000);
						
						executorLista.add(timer);
					}
				} catch (AplicacaoExternaException e) {
					e.printStackTrace();
				}
			}
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
						writer.println(pendingMessages[j]);
						log("Writing:" + pendingMessages[j]);
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

	private class Executor extends TimerTask {
		private ExecutorAplicacaoRequest aplicacaoRequest = new ExecutorAplicacaoRequest();
		
		public Executor(UsuarioAplicacao usuarioAplicacao) {
			try {
				aplicacaoRequest.setIdUsuarioAplicacao(usuarioAplicacao.getIdUsuarioAplicacao());
				aplicacaoRequest.setUsuario(usuarioAplicacao.getUsuario());
				aplicacaoRequest.setSenha(usuarioAplicacao.getSenha());
				
				aplicacaoRequest.aplicaRecurso(usuarioAplicacao.getIdRecurso());
				aplicacaoRequest.aplicaParametros(null);
			} catch (AplicacaoExternaException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				ExecutorAplicacao executor = new ExecutorAplicacao();
				String[] resultado = executor.executaAplicação(aplicacaoRequest);

				String conteudoAtual = "Aplica&ccedil;&atilde;o [" + aplicacaoRequest.getNomeAplicacao() + " - "
					+ aplicacaoRequest.getNomeRecurso() + "] retornou novos dados:<br/><br/>" + resultado[1];
				
				String conteudoAnterior = UsuarioAplicacao.consultaCache(aplicacaoRequest.getIdUsuarioAplicacao());
				
				String conteudoFinal = "";
				if (!conteudoAtual.equals(conteudoAnterior)) {
					conteudoFinal = conteudoAtual;
					
					UsuarioAplicacao.atualizaCache(aplicacaoRequest.getIdUsuarioAplicacao(), conteudoFinal);
					
					messageSender.send(conteudoFinal);
				}
			} catch (Exception e) {
				log("Erro: " + e.getMessage());
			}
		}
	}

}
