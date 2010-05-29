package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.TipoContato;
import modelo.Usuario;

/**
 * Servlet implementation class tipoContatoServlet
 */
public class TipoContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoContatoServlet() {
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
				long pkTipoContato = Long.parseLong(request.getParameter("pkTipo"));
				
				TipoContato tipoContato = new TipoContato();
				tipoContato.setPkTipoContato(pkTipoContato);
				tipoContato.setTipoContato(request.getParameter("tipoContato"));
				
				try {
					tipoContato.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("tiposContato", TipoContato.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/tipo_contato/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkTipo") != null)
				{
					long pkTipoContato = Long.parseLong(request.getParameter("pkTipo"));
					
					TipoContato tipoContato = new TipoContato();
					tipoContato.setPkTipoContato(pkTipoContato);

					try {
						tipoContato.consultar();
						request.setAttribute("tipoContato", tipoContato);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/tipo_contato/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkTipoContato = Long.parseLong(request.getParameter("pkTipo"));
				
				TipoContato tipoContato = new TipoContato();
				tipoContato.setPkTipoContato(pkTipoContato);
							
				try {
					tipoContato.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este tipo de contato.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String no_tipoContato = request.getParameter("tipoContato");
				
				TipoContato tipoContato = new TipoContato();
				tipoContato.setTipoContato(no_tipoContato);
				
				try {
					tipoContato.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}

}
