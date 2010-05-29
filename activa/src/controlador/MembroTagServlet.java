package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class MembroTagServlet
 */
public class MembroTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroTagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}
	
    protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("membro");
		if(usuario == null)
			return;
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
		
		char opcao = (
				request.getAttribute("opcao") != null ?
					request.getAttribute("opcao").toString()
				:
					request.getParameter("opcao").toString()
				).charAt(0);
		
		switch(opcao)
		{
			case 'A': // Tag
			{
				break;
			}
		
			case 'C': //Consultar Todos
			{				
				break;
			}		
		
			case 'D': //Detalhe
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/tags/alterar.jsp").forward(request, response);				
				else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/tags/detalhe.jsp").forward(request, response);
				break;
			}
			
			case 'E': //Exclusão
			{
				break;
			}
			
			case 'I': //Inclusão
			{
				break;
			}
		}
	}
}
