package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ContatoUsuario;
import modelo.Usuario;

/**
 * Servlet implementation class ContatoUsuarioServlet
 */
public class MembroContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroContatoServlet() {
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
		
		ContatoUsuario contatoUsuario = new ContatoUsuario();
		contatoUsuario.setUsuario(usuario);
		
		switch(opcao)
		{
			case 'A': //Alteração
			{
				String tipo = request.getParameter("tipo");
				String desc = request.getParameter("desc");
								
				contatoUsuario.setPkContatoUsuario(Long.parseLong(request.getParameter("id")));
				contatoUsuario.getTipoContato().setPkTipoContato(Long.parseLong(tipo));
				contatoUsuario.setContato(desc);
				
				try {
					contatoUsuario.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("contatos", ContatoUsuario.consultarPorUsuario(usuario));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/contato/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/contato/alterar.jsp").forward(request, response);				
				else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/contato/detalhe.jsp").forward(request, response);
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkContatoUsuario = Long.parseLong(request.getParameter("pkContatoUsuario"));
				contatoUsuario.setPkContatoUsuario(pkContatoUsuario);
							
				try {
					contatoUsuario.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este contato.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String tipo = request.getParameter("tipo_val");
				String desc = request.getParameter("desc_val");
				
				
				contatoUsuario.getTipoContato().setPkTipoContato(Long.parseLong(tipo));
				contatoUsuario.setContato(desc);
				
				try {
					contatoUsuario.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}
}
