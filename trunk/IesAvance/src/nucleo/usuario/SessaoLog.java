package nucleo.usuario;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.*;
import util.banco.*;

public class SessaoLog implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent sessionEvent) 
	{
		// Get the session that was created
		HttpSession session = sessionEvent.getSession();

		System.out.println("Sess�o Criada: " + session.getId());
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) 
	{
		// Get the session that was invalidated
		int res;
		HttpSession session = sessionEvent.getSession();
		
		try 
		{
			SessaoPersist pst = new SessaoPersist();

			// Log out
			System.out.println("Sess�o Terminada: " + session.getId());
			
			res = pst.FazLogout(session.getId());
			if ( res == 0 )
				System.out.println("N�o consegui escrever logout");
			System.out.println(res + " sess�es deslogadas");
		}
		catch (ExcecaoBanco e)
		{
			System.out.println("Exce��o: " + e.getMessage());
		}
	}
}
