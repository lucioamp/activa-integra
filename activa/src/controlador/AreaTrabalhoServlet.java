package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.AreaTrabalho;
import modelo.Usuario;

/**
 * Servlet implementation class AreaTrabalhoServlet
 */
public class AreaTrabalhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaTrabalhoServlet() {
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
		
		AreaTrabalho areaTrabalho = new AreaTrabalho();
		areaTrabalho.getMembro().setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case 'A': //Alteração
			{
				long pkAreaTrabalho = Long.parseLong(request.getParameter("pkAreaTrabalho"));
				
				areaTrabalho.setPkAreaTrabalho(pkAreaTrabalho);
				areaTrabalho.setNome(request.getParameter("no_areaTrabalho"));
				areaTrabalho.setDescricao(request.getParameter("ds_areaTrabalho"));				
				
				try {
					areaTrabalho.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("membroAreaTrabalho", AreaTrabalho.consultarPorMembro(areaTrabalho.getMembro()));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/perfil/area_trabalho/index.jsp").forward(request, response);
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkAreaTrabalho") != null)
				{
					long pkAreaTrabalho = Long.parseLong(request.getParameter("pkAreaTrabalho"));
					
					areaTrabalho.setPkAreaTrabalho(pkAreaTrabalho);

					try {
						areaTrabalho.consultar();
						request.setAttribute("areaTrabalho", areaTrabalho);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "consultar.jsp");
					request.getRequestDispatcher("pages/restrito/servicos/perfil/area_trabalho/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkAreaTrabalho = Long.parseLong(request.getParameter("pkAreaTrabalho"));
				
				areaTrabalho.setPkAreaTrabalho(pkAreaTrabalho);
							
				try {
					areaTrabalho.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta area de trabalho.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String no_areaTrabalho = request.getParameter("no_areaTrabalho");
				String ds_areaTrabalho = request.getParameter("ds_areaTrabalho");
				
				areaTrabalho.setMembro(areaTrabalho.getMembro());
				areaTrabalho.setNome(no_areaTrabalho);
				areaTrabalho.setDescricao(ds_areaTrabalho);
				
				try {
					areaTrabalho.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}
}
