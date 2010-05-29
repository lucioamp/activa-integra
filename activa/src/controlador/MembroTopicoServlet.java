package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Membro;
import modelo.PostTopico;
import modelo.Topico;
import modelo.Usuario;
import util.Ferramenta;
import util.ModuloEvento;

/**
 * Servlet implementation class MembroTopicoServlet
 */
public class MembroTopicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroTopicoServlet() {
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
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroTopico");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroTopico");
		
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
			case 'A': 
			{
				long pkTopico = Long.parseLong(request.getParameter("pkTopico"));
				Topico topico = new Topico();
				topico.setPkTopico(pkTopico);
				try {
					topico.consultar();
					topico.setDescricao(request.getParameter("desc"));
					topico.alterar();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case 'D': // Detalhe do Tópico
			{
				long pkTopico = Long.parseLong(request.getParameter("pkTopico"));				
				boolean isOwner = Boolean.parseBoolean(request.getParameter("isOwner"));
				
				Topico topico = new Topico();
				topico.setPkTopico(pkTopico);
				try {
					topico.consultar();
					request.setAttribute("topico", topico);
					request.setAttribute("mensagens", PostTopico.consultarPorTopico(topico));
				} catch (Exception e) {}
				
					
				String file = (isOwner == true ? "owner.jsp" : "detalhe.jsp");
				
				request.getRequestDispatcher("pages/restrito/servicos/forum/topico/"+file).forward(request, response);
				break;
			}
			
			case 'E': // Excluir
			{
				long pkTopico = Long.parseLong(request.getParameter("pkTopico"));				
				
				Topico topico = new Topico();
				topico.setPkTopico(pkTopico);
				
				try {
					Collection<PostTopico> posts = PostTopico.consultarPorTopico(topico);
					for(PostTopico post:posts)
						post.excluir();
					
					topico.excluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			
			case 'I': // Inclusão
			{
				long pkForum = Long.parseLong(request.getParameter("pkForum"));
				
				DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				Topico topico = new Topico();
				topico.getForum().setPkServico(pkForum);
				topico.setData(formatoData.format(new Date(System.currentTimeMillis())).toString());
				topico.setMembro(membro);
				topico.setDescricao(request.getParameter("desc"));
				
				try {
					topico.incluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			
			case 'M': // Mensagem
			{
				String tipo = request.getParameter("tipo");
				long pkTopico = Long.parseLong(request.getParameter("pkTopico"));
				
				DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				if(tipo.equals("consultar"))
				{
					
				}else if(tipo.equals("incluir"))
				{
					String msg = request.getParameter("mensagem");
					String data = formatoData.format(new Date(System.currentTimeMillis())).toString();
					
					PostTopico post = new PostTopico();
					post.setDescricao(msg);
					post.setMembro(membro);
					post.getTopico().setPkTopico(pkTopico);
					post.setData(data);
					
					try {
						post.incluir();
						request.setAttribute("msg", Ferramenta.formatarData(data, true));
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
					
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}
				
				break;
			}
			
			case 'T': // Detalhe do Topico
			{
				long pkTopico = Long.parseLong(request.getParameter("pkTopico"));
				
				Topico topico = new Topico();
				topico.setPkTopico(pkTopico);
				try {
					topico.consultar();
					request.setAttribute("topico", topico);
				} catch (Exception e) {}
				
									
				request.getRequestDispatcher("pages/restrito/servicos/forum/topico/alterar.jsp").forward(request, response);
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
