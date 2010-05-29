package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.CursoExtensao;
import modelo.Usuario;

/**
 * Servlet implementation class CursoExtensaoServlet
 */
public class CursoExtensaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoExtensaoServlet() {
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
		
		CursoExtensao cursoExtensao = new CursoExtensao();
		cursoExtensao.getMembro().setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case 'A': //Alteração
			{
				long pkCursoExtensao = Long.parseLong(request.getParameter("pkCursoExtensao"));
				
				cursoExtensao.setPkCursoExtensao(pkCursoExtensao);
				cursoExtensao.setNome(request.getParameter("no_cursoExtensao"));
				cursoExtensao.setDescricao(request.getParameter("ds_cursoExtensao"));				
				
				try {
					cursoExtensao.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("membroCursoExtensao", CursoExtensao.consultarPorMembro(cursoExtensao.getMembro()));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/perfil/curso_extensao/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkCursoExtensao") != null)
				{
					long pkCursoExtensao = Long.parseLong(request.getParameter("pkCursoExtensao"));
					
					cursoExtensao.setPkCursoExtensao(pkCursoExtensao);

					try {
						cursoExtensao.consultar();
						request.setAttribute("cursoExtensao", cursoExtensao);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "consultar.jsp");
					request.getRequestDispatcher("pages/restrito/servicos/perfil/curso_extensao/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkCursoExtensao = Long.parseLong(request.getParameter("pkCursoExtensao"));
				
				cursoExtensao.setPkCursoExtensao(pkCursoExtensao);
							
				try {
					cursoExtensao.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este curso de extensao.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String no_cursoExtensao = request.getParameter("no_cursoExtensao");
				String ds_cursoExtensao = request.getParameter("ds_cursoExtensao");
				
				cursoExtensao.setMembro(cursoExtensao.getMembro());
				cursoExtensao.setNome(no_cursoExtensao);
				cursoExtensao.setDescricao(ds_cursoExtensao);
				
				try {
					cursoExtensao.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}
}
