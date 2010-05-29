package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Idioma;
import modelo.MembroIdioma;
import modelo.Usuario;

/**
 * Servlet implementation class MembroIdiomaServlet
 */
public class MembroIdiomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroIdiomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
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
		
		MembroIdioma membroIdioma = new MembroIdioma();
		membroIdioma.getMembro().setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case 'A': //Alteração
			{
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("idiomas", Idioma.consultarTodos());
					request.setAttribute("membroIdiomas", membroIdioma.consultarPorMembro(membroIdioma.getMembro()));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/perfil/idiomas.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkIdioma = Long.parseLong(request.getParameter("pkIdioma"));
				membroIdioma.getIdioma().setPkIdioma(pkIdioma);
				
				try {
					membroIdioma.excluir();
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				break;
			}
			
			case 'I': //Inclusão
			{
				long pkIdioma = Long.parseLong(request.getParameter("pkIdioma"));
				membroIdioma.getIdioma().setPkIdioma(pkIdioma);
				
				try {
					membroIdioma.incluir();
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				break;
			}
		}
	}
}
