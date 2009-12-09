package avance;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageExtension;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.openid4java.message.sreg.SRegMessage;
import org.openid4java.message.sreg.SRegRequest;
import org.openid4java.message.sreg.SRegResponse;

public class ConsumerOpenID 
{
	private ConsumerManager manager;
	
	private static ConsumerOpenID instancia;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	private ConsumerOpenID() throws ConsumerException  
	{
		manager = new ConsumerManager();
		manager.setAssociations(new InMemoryConsumerAssociationStore());
		manager.setNonceVerifier(new InMemoryNonceVerifier(5000));
	}
	
	static public ConsumerOpenID Instancia() throws ConsumerException
	{
		if ( instancia == null )
		{
			instancia = new ConsumerOpenID();
		}
	
		return instancia;
	}

	public void processReturn(HttpServletRequest req, HttpServletResponse resp)
					throws OpenIDException 
	{
		try
		{
			Identifier identifier = verifyResponse(req, resp);
			
			if (identifier == null) 
			{
				req.getRequestDispatcher("/index.html").forward(req, resp);
			}
			else 
			{
				req.setAttribute("identifier", identifier.getIdentifier());
				req.getRequestDispatcher("/OpenIDValido.jsp").forward(req, resp);
			}
		} 
		catch (IOException e) 
		{
			throw new OpenIDException("Erro na autenticação: " + e.getMessage());
		}
		catch (ServletException e) 
		{
			throw new OpenIDException("Erro no servlet: " + e.getMessage());
		}
	}

	// --- placing the authentication request ---
	public String authRequest( String userSuppliedString,
							   HttpServletRequest httpReq, 
							   HttpServletResponse httpResp )
		throws OpenIDException 
	{
		try {
			// configure the return_to URL where your application will receive
			// the authentication responses from the OpenID provider
			// String returnToUrl = "http://example.com/openid";
			String returnToUrl = httpReq.getRequestURL().toString()	+ "?is_return=true";
		
			// perform discovery on the user-supplied identifier
			List discoveries;
			try 
			{
				discoveries = manager.discover(userSuppliedString);
			}
			catch (DiscoveryException e)
			{
				throw new OpenIDException("Não pude encontrar o identificador - " +
										  "String : '" + userSuppliedString + 
										  "' Erro: '" + e.getMessage() + "'");
			}

			// attempt to associate with the OpenID provider
			// and retrieve one service endpoint for authentication
			DiscoveryInformation discovered = manager.associate(discoveries);
		
			// store the discovery information in the user's session
			httpReq.getSession().setAttribute("openid-disc", discovered);
		
			// obtain a AuthRequest message to be sent to the OpenID provider
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);
		
			// Attribute Exchange example: fetching the 'email' attribute
			FetchRequest fetch = FetchRequest.createFetchRequest();
			SRegRequest sregReq = SRegRequest.createFetchRequest();
		
			if ("1".equals(httpReq.getParameter("email"))) {
				fetch.addAttribute("email",
						"http://schema.openid.net/contact/email", false);
				sregReq.addAttribute("email", false);
			}
			if ("1".equals(httpReq.getParameter("fullname"))) {
				fetch.addAttribute("fullname",
						"http://schema.openid.net/contact/fullname", false);
				sregReq.addAttribute("fullname", false);
			}
		
			// attach the extension to the authentication request
			if (!sregReq.getAttributes().isEmpty()) {
				authReq.addExtension(sregReq);
			}
		
			if (!discovered.isVersion2()) {
				// Option 1: GET HTTP-redirect to the OpenID Provider endpoint
				// The only method supported in OpenID 1.x
				// redirect-URL usually limited ~2048 bytes
				httpResp.sendRedirect(authReq.getDestinationUrl(true));
				return null;
			} else {
				// Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)
		
				RequestDispatcher dispatcher = httpReq.getRequestDispatcher("/formredirection.jsp");
				
				httpReq.setAttribute("parameterMap", httpReq.getParameterMap());
				httpReq.setAttribute("message", authReq);
		
				dispatcher.forward(httpReq, httpResp);
			}
		} 
		catch (IOException e) 
		{
			throw new OpenIDException("Erro na autenticação: " + e.getMessage());
		}
		catch (ServletException e) 
		{
			throw new OpenIDException("Erro no servlet: " + e.getMessage());
		}
		
		return null;
	}
	
	// --- processing the authentication response ---
	public Identifier verifyResponse(HttpServletRequest httpReq, 
								 	 HttpServletResponse httpResp) 
									 throws OpenIDException 
	{
		// extract the parameters from the authentication response
		// (which comes in as a HTTP request from the OpenID provider)
		ParameterList response = new ParameterList(httpReq.getParameterMap());
	
		// retrieve the previously stored discovery information
		DiscoveryInformation discovered = (DiscoveryInformation)httpReq.getSession().getAttribute("openid-disc");
	
		// extract the receiving URL from the HTTP request
		StringBuffer receivingURL = httpReq.getRequestURL();
		String queryString = httpReq.getQueryString();
		if (queryString != null && queryString.length() > 0)
			receivingURL.append("?").append(httpReq.getQueryString());
	
		// verify the response; ConsumerManager needs to be the same
		// (static) instance used to place the authentication request
		VerificationResult verification = manager.verify(receivingURL.toString(), 
														 response, discovered);
	
		// examine the verification result and extract the verified
		// identifier
		Identifier verified = verification.getVerifiedId();
		if (verified != null) 
		{
			AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
	
			if (authSuccess.hasExtension(SRegMessage.OPENID_NS_SREG)) 
			{
				MessageExtension ext = authSuccess.getExtension(SRegMessage.OPENID_NS_SREG);
				if (ext instanceof SRegResponse) 
				{
					SRegResponse sregResp = (SRegResponse) ext;
					for (Iterator iter = sregResp.getAttributeNames().iterator(); iter.hasNext();) 
					{
						String name = (String) iter.next();
						String value = sregResp.getParameterValue(name);
						httpReq.setAttribute(name, value);
					}
				}
			}
			if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) 
			{
				FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);
	
				List aliases = fetchResp.getAttributeAliases();
				for (Iterator iter = aliases.iterator(); iter.hasNext();) 
				{
					String alias = (String) iter.next();
					List values = fetchResp.getAttributeValues(alias);
					if (values.size() > 0) 
					{
						httpReq.setAttribute(alias, values.get(0));
					}
				}
			}
	
			return verified; // success
		}
		
		return null;
	}
	
}
