package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.CategoriaComunidade;
import modelo.Usuario;

/**
 * Servlet implementation class categoriaComunidadeServlet
 */
public class CategoriaComunidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaComunidadeServlet() {
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
				long pkCatComunidade = Long.parseLong(request.getParameter("pkCategoria"));
				String nome = request.getParameter("nome");
				String descricao = request.getParameter("descricao");

				CategoriaComunidade categoria = new CategoriaComunidade();
				categoria.setPkCatComunidade(pkCatComunidade);
				categoria.setNome(nome);
				categoria.setDescricao(descricao);

				try {
					categoria.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
		
			case 'C': //Consultar Todos
			{				
				try {
					request.setAttribute("categorias", CategoriaComunidade.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/categoria_comunidade/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkCategoria") != null)
				{
					long pkCatComunidade = Long.parseLong(request.getParameter("pkCategoria"));

					CategoriaComunidade categoria = new CategoriaComunidade();
					categoria.setPkCatComunidade(pkCatComunidade);

					try {
						categoria.consultar();
						request.setAttribute("categoria", categoria);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/categoria_comunidade/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkCatComunidade = Long.parseLong(request.getParameter("pkCategoria"));

				CategoriaComunidade categoria = new CategoriaComunidade();
				categoria.setPkCatComunidade(pkCatComunidade);
							
				try {
					categoria.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta categoria.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'I': //Inclusão
			{
				String nome = request.getParameter("nome");
				String descricao = request.getParameter("descricao");
				
				CategoriaComunidade categoria = new CategoriaComunidade();
				categoria.setNome(nome);
				categoria.setDescricao(descricao);
				
				try {
					categoria.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}

}
