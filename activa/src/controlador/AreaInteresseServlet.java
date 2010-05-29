package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Ferramenta;

import modelo.AreaInteresse;
import modelo.AreaInteresseTag;
import modelo.Tag;
import modelo.Usuario;

/**
 * Servlet implementation class areaInteresseServlet
 */
public class AreaInteresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaInteresseServlet() {
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
				long pkAreaInteresse = Long.parseLong(request.getParameter("pkAreaInteresse"));
				String nome = request.getParameter("nome");
				String descricao = request.getParameter("descricao");
				String tags = request.getParameter("tags");

				AreaInteresse area = new AreaInteresse();
				area.setPkAreaInteresse(pkAreaInteresse);
				area.setNome(nome);
				area.setDescricao(descricao);
				
				Tag tag = new Tag();
				AreaInteresseTag areaTag = new AreaInteresseTag();
				try {
					area.alterar();
								
					areaTag.getAreaInteresse().setPkAreaInteresse(pkAreaInteresse);					
					ArrayList<HashMap<String, String>> listaTags = Ferramenta.stringToList(tags);
					for(HashMap<String, String> _tag:listaTags)
					{
						tag.setNome(_tag.get("nome"));
						tag.setDescricao(_tag.get("desc"));
						if(_tag.get("virtual") != null && _tag.get("virtual").equals("true"))
						{
							areaTag.getTag().setPkTag(tag.incluir());
							areaTag.incluir();
						}else if(_tag.get("alterar") != null && _tag.get("alterar").equals("true"))
						{
							tag.setPkTag(Long.parseLong(_tag.get("id")));
							tag.alterar();							
						}else if(_tag.get("deletar") != null && _tag.get("deletar").equals("true"))
						{
							tag.setPkTag(Long.parseLong(_tag.get("id")));
							tag.excluir();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
		
			case 'C': //Consultar Todos
			{				
				try {
					request.setAttribute("areas", AreaInteresse.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/area_interesse/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkAreaInteresse") != null)
				{
					long pkAreaInteresse = Long.parseLong(request.getParameter("pkAreaInteresse"));

					AreaInteresse area = new AreaInteresse();
					area.setPkAreaInteresse(pkAreaInteresse);

					try {
						area.consultar();
						request.setAttribute("area", area);
						request.setAttribute("colTag", AreaInteresseTag.consultarPorAreaInteresse(area));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/area_interesse/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkAreaInteresse = Long.parseLong(request.getParameter("pkAreaInteresse"));
				
				AreaInteresse area = new AreaInteresse();
				area.setPkAreaInteresse(pkAreaInteresse);
				try {
					area.excluir();	
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta area.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'I': //Inclusão
			{
				String nome = request.getParameter("nome");
				String descricao = request.getParameter("descricao");
				String tags = request.getParameter("tags");
				
				AreaInteresse area = new AreaInteresse();
				area.setNome(nome);
				area.setDescricao(descricao);
				
				Tag tag = new Tag();
				AreaInteresseTag areaTag = new AreaInteresseTag();
				try {
					areaTag.getAreaInteresse().setPkAreaInteresse(area.incluir());
					ArrayList<HashMap<String, String>> lista = Ferramenta.stringToList(tags);
					for(HashMap<String, String> object:lista)
					{
						tag.setNome(object.get("nome"));
						tag.setDescricao(object.get("desc"));
						areaTag.getTag().setPkTag(tag.incluir());
						areaTag.incluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}

}
