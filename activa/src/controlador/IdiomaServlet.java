package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Idioma;
import modelo.Usuario;

/**
 * Servlet implementation class idiomaServlet
 */
public class IdiomaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdiomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		Usuario usuario = (Usuario)session.getAttribute("administrador");
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
			case 'A': //Alteração
			{
				long pkIdioma = Long.parseLong(request.getParameter("pkIdioma"));
				
				Idioma idioma = new Idioma();
				idioma.setPkIdioma(pkIdioma);
				idioma.setNome(request.getParameter("nome"));
				
				try {
					idioma.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("idiomas", Idioma.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/idioma/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkIdioma") != null)
				{
					long pkIdioma = Long.parseLong(request.getParameter("pkIdioma"));
					
					Idioma idioma = new Idioma();
					idioma.setPkIdioma(pkIdioma);

					try {
						idioma.consultar();
						request.setAttribute("idioma", idioma);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/idioma/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkIdioma = Long.parseLong(request.getParameter("pkIdioma"));
				
				Idioma idioma = new Idioma();
				idioma.setPkIdioma(pkIdioma);
							
				try {
					idioma.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este idioma.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String nome = request.getParameter("nome");
				
				Idioma idioma = new Idioma();
				idioma.setNome(nome);
				
				try {
					idioma.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}

}
