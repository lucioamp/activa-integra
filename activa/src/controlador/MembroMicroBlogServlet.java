package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.MicroBlog;
import modelo.Usuario;
import util.ModuloEvento;

/**
 * Servlet implementation class MembroMicroblogServlet
 */
public class MembroMicroBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroMicroBlogServlet() {
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
		Usuario usuarioSession = (Usuario)session.getAttribute("membro");
		if(usuarioSession == null)
			return;

		ModuloEvento eventos = usuarioSession.getModuloPorNome("membroMicroBlog");
		if(eventos == null)
			eventos = usuarioSession.adicionarModulo("membroMicroBlog");
		
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
			case 'A': //Alterar Microblog
			{
				long pkMicroBlog = Long.parseLong(request.getParameter("pkMicroBlog"));
				
				MicroBlog microblog = null;
				ArrayList<MicroBlog> microBlogs = (ArrayList<MicroBlog>)usuarioSession.getMicroBlogs();
				for(MicroBlog _microBlog:microBlogs)
				{
					if(_microBlog.getPkMicroBlog() == pkMicroBlog)
					{
						microblog = _microBlog;
						break;
					}
				}
				
				request.setAttribute("msg", "false");
				if(microblog != null)
				{
					microblog.setSt_atual(1);
					try {
						MicroBlog.limparStatusPorMembro(microblog.getMembro());
						microblog.alterar();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						System.out.println(e);
						System.out.println("Erro ao realizar o precessamento.");
					}
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("microblogs", usuarioSession.getMicroBlogs());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/perfil/microblog.jsp").forward(request, response);
				
				break;
			}
		
			case 'E': // Excluir microBlog
			{
				long pkMicroBlog = Long.parseLong(request.getParameter("pkMicroBlog"));
				
				MicroBlog microBlog = new MicroBlog();
				microBlog.setPkMicroBlog(pkMicroBlog);
				try {
					microBlog.excluir();
					
					ArrayList<MicroBlog> microBlogs = (ArrayList<MicroBlog>)usuarioSession.getMicroBlogs();
					for(MicroBlog _microBlog:microBlogs)
					{
						if(_microBlog.getPkMicroBlog() == pkMicroBlog)
						{
							microBlogs.remove(_microBlog);
							break;
						}
					}
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este microblog.");
				}
				
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
		
			case 'I': // Incluir microBlog
			{
				String descricao = request.getParameter("descricao").trim();
				
				if(descricao.length() == 0)
					break;
				
				boolean result = true;
				
				ArrayList<MicroBlog> microBlogs = (ArrayList<MicroBlog>)usuarioSession.getMicroBlogs();
				for(MicroBlog microBlog:microBlogs)
				{
					if(microBlog.getDescricao().equals(descricao))
					{
						result = false;
						break;
					}
				}
				
				if(!result)
					break;
				
				MicroBlog microBlog = new MicroBlog();
				microBlog.getMembro().setPkUsuario(usuarioSession.getPkUsuario());
				microBlog.setDescricao(descricao);
				microBlog.setSt_atual(1);
				try {
					MicroBlog.limparStatusPorMembro(microBlog.getMembro());
					microBlog.setPkMicroBlog(microBlog.incluir());
					usuarioSession.getMicroBlogs().add(microBlog);
					
					if(microBlogs.size() > 10)
					{
						microBlog.setPkMicroBlog(microBlogs.get(0).getPkMicroBlog());
						microBlog.excluir();
						microBlogs.remove(0);
					}
				}catch (Exception e) { }
				
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
