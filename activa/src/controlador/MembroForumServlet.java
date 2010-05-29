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

import modelo.Ambiente;
import modelo.CategoriaForum;
import modelo.Etapa;
import modelo.Forum;
import modelo.Membro;
import modelo.Meta;
import modelo.PostTopico;
import modelo.Servico;
import modelo.Tarefa;
import modelo.Topico;
import modelo.Usuario;
import util.ModuloEvento;

/**
 * Servlet implementation class MembroForumServlet
 */
public class MembroForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroForumServlet() {
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
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroForum");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroForum");
		
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
				long pkServico = Long.parseLong(request.getParameter("pkServico"));
				long pkCatForum = Long.parseLong(request.getParameter("pkCategoria"));
				long pkTarefa = Long.parseLong(request.getParameter("pkTarefa"));
				
				Forum forum = new Forum();
				forum.setPkServico(pkServico);
				Servico servico = new Servico();
				servico.setPkServico(pkServico);
				
				try {
					forum.consultar();
					servico.consultar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				forum.getCategoriaForum().setPkCatForum(pkCatForum);
				forum.getTarefa().setPkTarefa(pkTarefa);

				servico.setAutomatico("N");
				servico.setDescricao(request.getParameter("desc"));
				servico.setImagem("");
				servico.setMembro(membro);
				servico.setNome(request.getParameter("nome"));
				servico.setStatus("A");
				
				try {
					servico.alterar();
					forum.alterar();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
		
			case 'I': // Inclusão
			{
				String tipo = request.getParameter("tipo");
				
				if(tipo.equals("incluirCategoria"))
				{
					CategoriaForum categoriaForum = new CategoriaForum();
					categoriaForum.setDescricao(request.getParameter("desc"));
					categoriaForum.getEtapa().setPkEtapa(Long.parseLong(request.getParameter("pkEtapa")));
					categoriaForum.setNome(request.getParameter("nome"));
					
					try {
						categoriaForum.incluir();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
					
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}else if(tipo.equals("incluirForum"))
				{
					long pkCatForum = Long.parseLong(request.getParameter("pkCategoria"));
					long pkTarefa = Long.parseLong(request.getParameter("pkTarefa"));
					
					Forum forum = new Forum();
					forum.getCategoriaForum().setPkCatForum(pkCatForum);
					forum.getTarefa().setPkTarefa(pkTarefa);
					
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
					
					
					try {
						forum.setPkServico(servico.incluir());
						forum.incluir();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
					
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}
				
				break;
			}
			
			case 'C': // Consulta
			{
				String tipo = request.getParameter("tipo");
				
				if(tipo.equals("incluirCategoria"))
				{
					try {
						request.setAttribute("metas", Meta.consultarPorAmbiente(ambiente));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/forum/categoria/incluir.jsp").forward(request, response);	
				}else if(tipo.equals("consultarEtapa"))
				{
					long pkMeta = Long.parseLong(request.getParameter("pkMeta"));
					
					Etapa etapa = new Etapa();
					etapa.getMeta().setPkMeta(pkMeta);
					
					String options = "";
					try {
						Collection<Etapa> etapas = Etapa.consultarPorMeta(etapa.getMeta());
						
						for(Etapa _etapa:etapas)
							options += "<option value="+_etapa.getPkEtapa()+">"+_etapa.getNome()+"</option>";

					} catch (Exception e) {
						// TODO: handle exception
					}
				
					request.setAttribute("msg", options);
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}else if(tipo.equals("incluirForum"))
				{
					try {
						request.setAttribute("categorias", CategoriaForum.consultarPorAmbiente(ambiente));
						request.setAttribute("tarefas", Tarefa.consultarTodos());
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/forum/incluir.jsp").forward(request, response);	
				}else if(tipo.equals("alterarForum"))
				{
					long pkForum = Long.parseLong(request.getParameter("pkForum"));
					Forum forum = new Forum();
					forum.setPkServico(pkForum);
					
					try {
						forum.consultar();
						
						request.setAttribute("forum", forum);
						request.setAttribute("categorias", CategoriaForum.consultarPorAmbiente(ambiente));
						request.setAttribute("tarefas", Tarefa.consultarTodos());
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/forum/alterar.jsp").forward(request, response);	
				}else if(tipo.equals("consultarTarefa"))
				{
					long pkCategoria = Long.parseLong(request.getParameter("pkCategoria"));
					
					CategoriaForum categoria = new CategoriaForum();
					categoria.setPkCatForum(pkCategoria);
					
					String options = "";
					try {
						categoria.consultar();
												
						Collection<Tarefa> tarefas = Tarefa.consultarPorVisao(categoria.getEtapa());

						for(Tarefa tarefa:tarefas)
							options += "<option value="+tarefa.getPkTarefa()+">"+tarefa.getNome()+"</option>";

					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.setAttribute("msg", options);
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}else if(tipo.equals("principal"))
				{
					try {
						request.setAttribute("categorias", CategoriaForum.consultarPorAmbiente(ambiente));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/forum/principal.jsp").forward(request, response);
				}
				
				break;
			}
			
			case 'E': // Remover Forum
			{
				long pkForum = Long.parseLong(request.getParameter("pkForum"));
				Forum forum = new Forum();
				forum.setPkServico(pkForum);
				Servico servico = new Servico();
				servico.setPkServico(pkForum);
				
				try {
					Collection<Topico> topicos = Topico.consultarPorForum(forum);
					for(Topico topico:topicos)
					{
						Collection<PostTopico> posts = PostTopico.consultarPorTopico(topico);
						for(PostTopico post:posts)
							post.excluir();
						topico.excluir();
					}
					forum.excluir();
					servico.excluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			
			case 'F': // Detalhe do Forum
			{
				long pkForum = Long.parseLong(request.getParameter("pkForum"));

				Forum forum = new Forum();
				forum.setPkServico(pkForum);
				
				try {
					forum.consultar();
					request.setAttribute("forum", forum);
					request.setAttribute("topicos", Topico.consultarPorForum(forum));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/forum/detalhe.jsp").forward(request, response);
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}
}
