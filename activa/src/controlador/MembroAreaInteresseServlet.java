package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.AreaInteresse;
import modelo.MembroAreaInteresse;
import modelo.Usuario;

/**
 * Servlet implementation class MembroAreaInteresse
 */
public class MembroAreaInteresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroAreaInteresseServlet() {
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
		
		MembroAreaInteresse membroAreaInteresse = new MembroAreaInteresse();
		membroAreaInteresse.getMembro().setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case 'A': //Alteração
			{
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("areas", AreaInteresse.consultarTodos());
					request.setAttribute("membroInteresses", membroAreaInteresse.consultarPorMembro(membroAreaInteresse.getMembro()));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/perfil/areas_interesse.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkAreaInteresse = Long.parseLong(request.getParameter("pkAreaInteresse"));
				membroAreaInteresse.getAreaInteresse().setPkAreaInteresse(pkAreaInteresse);
				
				try {
					membroAreaInteresse.excluir();
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				break;
			}
			
			case 'I': //Inclusão
			{
				long pkAreaInteresse = Long.parseLong(request.getParameter("pkAreaInteresse"));
				membroAreaInteresse.getAreaInteresse().setPkAreaInteresse(pkAreaInteresse);
				
				try {
					membroAreaInteresse.incluir();
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				break;
			}
		}
	}

}
