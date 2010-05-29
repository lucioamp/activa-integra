package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.FormacaoAcademica;
import modelo.Usuario;

/**
 * Servlet implementation class formacaoAcademicaServlet
 */
public class FormacaoAcademicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormacaoAcademicaServlet() {
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
				long pkFormacaoAcademica = Long.parseLong(request.getParameter("pkFormacao"));
				
				FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
				formacaoAcademica.setPkFormacaoAcademica(pkFormacaoAcademica);
				formacaoAcademica.setNome(request.getParameter("nome"));
				
				try {
					formacaoAcademica.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("formacoes", FormacaoAcademica.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/formacao_academica/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkFormacao") != null)
				{
					long pkFormacaoAcademica = Long.parseLong(request.getParameter("pkFormacao"));
					
					FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
					formacaoAcademica.setPkFormacaoAcademica(pkFormacaoAcademica);

					try {
						formacaoAcademica.consultar();
						request.setAttribute("formacao", formacaoAcademica);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/formacao_academica/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkFormacaoAcademica = Long.parseLong(request.getParameter("pkFormacao"));
				
				FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
				formacaoAcademica.setPkFormacaoAcademica(pkFormacaoAcademica);
							
				try {
					formacaoAcademica.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta formação.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String nome = request.getParameter("nome");
				
				FormacaoAcademica formacaoAcademica = new FormacaoAcademica();
				formacaoAcademica.setNome(nome);
				
				try {
					formacaoAcademica.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}

}
