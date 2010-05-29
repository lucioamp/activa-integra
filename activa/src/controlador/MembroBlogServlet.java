package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import modelo.Ambiente;
import modelo.Blog;
import modelo.Membro;
import modelo.Servico;
import modelo.Usuario;
import util.ModuloEvento;

/**
 * Servlet implementation class MembroBlogServlet
 */
public class MembroBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroBlogServlet() {
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
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroBlog");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroBlog");
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
		
		Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");

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
				Long pkBlog = Long.parseLong(request.getParameter("id"));
				String nome = request.getParameter("nome");
				String desc = request.getParameter("desc");
				
				Blog blog = new Blog();
				blog.setPkServico(pkBlog);
				Servico servico = new Servico();
				servico.setPkServico(pkBlog);
				
				try {
					blog.consultar();
					servico.consultar();
					
					servico.setNome(nome);
					servico.setDescricao(desc);
					servico.alterar();

					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;				
			}
			
			case 'C': // Consulta
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("detalheBlog"))
				{
					Long pkBlog = Long.parseLong(request.getParameter("pkBlog"));
					Blog blog = new Blog();
					blog.setPkServico(pkBlog);
					
					try {
						blog.consultar();
						request.setAttribute("blog", blog);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/blog/detalhe.jsp").forward(request, response);				
				}else if(tipo.equals("alterarBlog"))
				{
					Long pkBlog = Long.parseLong(request.getParameter("pkBlog"));
					Blog blog = new Blog();
					blog.setPkServico(pkBlog);
					
					try {
						blog.consultar();
						
						request.setAttribute("blog", blog);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/blog/alterar.jsp").forward(request, response);
				}else if(tipo.equals("incluirBlog"))
				{
					
					request.getRequestDispatcher("pages/restrito/servicos/blog/incluir.jsp").forward(request, response);	
				}else if(tipo.equals("principal"))
				{
					try {
						request.setAttribute("membro", usuario);
						request.setAttribute("blogs", Blog.consultarBlogsPorAmbiente(ambiente));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/blog/principal.jsp").forward(request, response);
				}
				break;
			}
			
			case 'E':
			{
				long pkBlog = Long.parseLong(request.getParameter("pkBlog"));
				
				Blog blog = new Blog();
				blog.setPkServico(pkBlog);
				
				Servico servico = new Servico();
				servico.setPkServico(pkBlog);

				try {
					blog.excluir();
					servico.excluir();
					
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					System.out.println(e);
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case 'I':
			{
				DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				Servico servico = new Servico();
				servico.setAmbiente(ambiente);
				servico.setAutomatico("N");
				servico.setData(formatoData.format(new Date(System.currentTimeMillis())).toString());
				servico.setDescricao(request.getParameter("desc"));
				servico.setImagem("");
				servico.setMembro(membro);
				servico.setNome(request.getParameter("nome"));
				servico.setStatus("A");
				
				Blog blog = new Blog();

				try {
					blog.setPkServico(servico.incluir());
					blog.incluir();
					
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
