package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Membro;
import modelo.Mensagem;
import modelo.Usuario;
import util.ModuloEvento;

/**
 * Servlet implementation class MembroMensagensServlet
 */
public class MembroMensagensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroMensagensServlet() {
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
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroMensagens");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroMensagens");
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
		
		//Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");

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
		
		Membro membro = new Membro();
		membro.setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case '0': // Enviar Mensagem 
			{				
				Mensagem mensagem = new Mensagem();
				mensagem.setAssunto(request.getParameter("assunto"));
				mensagem.getMembroDestino().setPkUsuario(Long.parseLong(request.getParameter("destino")));
				mensagem.setMembroOrigem(membro);
				mensagem.setMensagem(request.getParameter("mensagem"));
				
				try {
					mensagem.incluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			
			case '1': // Excluir Mensagem
			{
				Mensagem mensagem = new Mensagem();
				mensagem.setPkMensagem(Long.parseLong(request.getParameter("pkMensagem")));
				try {
					mensagem.excluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
		
			case 'E': // Enviadas
			{
				try {
					request.setAttribute("mensagens", Mensagem.consultarEnviadas(membro));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/perfil/mensagens/enviadas.jsp").forward(request, response);				
				break;
			}
		
			case 'P': // Principal
			{
				try {
					request.setAttribute("quantidadeEnviadas", Mensagem.consultarEnviadas(membro).size());
					request.setAttribute("quantidadeRecebidas", Mensagem.consultarRecebidas(membro).size());					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/perfil/mensagens/principal.jsp").forward(request, response);				
				break;
			}
			
			case 'R': // Recebidas
			{
				try {
					request.setAttribute("mensagens", Mensagem.consultarRecebidas(membro));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/perfil/mensagens/recebidas.jsp").forward(request, response);				
				break;
			}
			
			case 'M': // Mensagem
			{
				request.getRequestDispatcher("pages/restrito/servicos/perfil/mensagens/mensagem.jsp").forward(request, response);
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
