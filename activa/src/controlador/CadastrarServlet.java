package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Ferramenta;
import util.ModuloEvento;

import modelo.Membro;
import modelo.Usuario;

/**
 * Servlet implementation class CadastrarServlet
 */
public class CadastrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarServlet() {
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
	
	ModuloEvento eventos = new ModuloEvento();
    protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
    	
		char opcao = (
				request.getAttribute("opcao") != null ?
					request.getAttribute("opcao").toString()
				:
					request.getParameter("opcao").toString()
				).charAt(0);
		
		if(!eventos.registrarEvento(opcao))
		{
			request.setAttribute("msg", "processando");
			request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
			return;
		}
		
		switch(opcao)
		{
			case 'I':
			{
				Long cpf = Ferramenta.formatarCpf(request.getParameter("cpf").trim());
				Boolean existeCpf = true;
				try {
					existeCpf = Usuario.existeCpf(cpf);
				} catch (Exception e) {}				
				if(existeCpf)
				{
					request.setAttribute("msg", "cpf");
					break;
				}
				
				String email = request.getParameter("email").trim();
				Boolean existeEmail = true;
				try {
					existeEmail = Usuario.existeEmail(email);
				} catch (Exception e) {}				
				if(existeEmail)
				{
					request.setAttribute("msg", "email");
					break;
				}
				
				Usuario usuario = new Usuario();
				usuario.setNome(request.getParameter("nome").trim());
				usuario.setCpf(cpf);
				usuario.setEmail(email);
				usuario.setSenha(request.getParameter("senha").trim());
				usuario.setStatus("A");
				
				try {
					int pkUsuario = usuario.cadastrar();
					usuario.setPkUsuario(pkUsuario);
										
					Membro membro = new Membro();
					membro.setPkUsuario(pkUsuario);
					membro.getFormacaoAcademica().setPkFormacaoAcademica(1);
					membro.setPermissao("A");
					membro.incluir();
					
					HttpSession session = request.getSession(true);					
					session.setAttribute("membro", usuario);
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			
			case 'V':
			{
				if(request.getParameter("tipo").equals("cpf"))
				{
					try {
						if(!Usuario.existeCpf(Ferramenta.formatarCpf(request.getParameter("value").trim())))
							request.setAttribute("msg", "false");
						else
							request.setAttribute("msg", "true");
					} catch (Exception e) { }
				}else if(request.getParameter("tipo").equals("email"))
				{
					try {
						if(!Usuario.existeEmail(request.getParameter("value").trim()))
							request.setAttribute("msg", "false");
						else
							request.setAttribute("msg", "true");
					} catch (Exception e) {  }
				}
				break;
			}
		}
		
		request.getRequestDispatcher("pages/empty.jsp").forward(request, response);		
		eventos.removerEvento(opcao, 1000);
	}

}
