package controlador;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

public class AplicacaoExternaCometServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 719792464317818148L;

	private MessageSender messageSender = null;
	private static final Integer TIMEOUT = 60 * 1000;

	@Override
	public void destroy() {
//		messageSender.stop();
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

		if (event.getEventType() == CometEvent.EventType.BEGIN) {
			request.setAttribute("org.apache.tomcat.comet.timeout", TIMEOUT);
			log("Begin for session: " + request.getSession(true).getId());
			messageSender.setConnection(response);
			Weatherman weatherman = new Weatherman(95118, 32408);
			new Thread(weatherman).start();
		} else if (event.getEventType() == CometEvent.EventType.ERROR) {
			log("Error for session: " + request.getSession(true).getId());
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.END) {
			log("End for session: " + request.getSession(true).getId());
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.READ) {
			throw new UnsupportedOperationException("This servlet does not accept data");
		}

	}

	private class MessageSender implements Runnable {

	    protected boolean running = true;
	    protected final ArrayList<String> messages = new ArrayList<String>();
	    private ServletResponse connection;
	    private synchronized void setConnection(ServletResponse connection){
	        this.connection = connection;
	        notify();
	    }
	    public void send(String message) {
	        synchronized (messages) {
	            messages.add(message);
	            log("Message added #messages=" + messages.size());
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
	                if (connection == null){
	                    try{
	                        synchronized(this){
	                            wait();
	                        }
	                    } catch (InterruptedException e){
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

	private class Weatherman implements Runnable {

		private final List<URL> zipCodes;
		private final String YAHOO_WEATHER = "http://weather.yahooapis.com/forecastrss?p=";

		public Weatherman(Integer... zips) {
			zipCodes = new ArrayList<URL>(zips.length);
			for (Integer zip : zips) {
				try {
					zipCodes.add(new URL(YAHOO_WEATHER + zip));
				} catch (Exception e) {
					// dont add it if it sucks
				}
			}
		}

		public void run() {
			int i = 0;
			while (i >= 0) {
				int j = i % zipCodes.size();
				SyndFeedInput input = new SyndFeedInput();
				try {
					SyndFeed feed = input.build(new InputStreamReader(zipCodes.get(j).openStream()));
					SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
					messageSender.send(entryToHtml(entry));
					Thread.sleep(30000L);
				} catch (Exception e) {
					// just eat it, eat it
				}
				i++;
			}
		}

		private String entryToHtml(SyndEntry entry) {
			StringBuilder html = new StringBuilder("<h2>");
			html.append(entry.getTitle());
			html.append("</h2>");
			html.append(entry.getDescription().getValue());
			return html.toString();
		}
	}

}
