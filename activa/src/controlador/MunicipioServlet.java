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

import modelo.Municipio;
import modelo.Bairro;
import modelo.Uf;
import modelo.Usuario;

/**
 * Servlet implementation class UfServlet
 */
public class MunicipioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MunicipioServlet() {
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
				long pkMunicipio = Long.parseLong(request.getParameter("pkMunicipio"));
				String no_municipio = request.getParameter("municipio");
				long uf = Long.parseLong(request.getParameter("uf"));
				String bairros = request.getParameter("bairros");
				
				Municipio municipio = new Municipio();
				municipio.setPkMunicipio(pkMunicipio);
				municipio.setMunicipio(no_municipio);
				municipio.getUf().setPkEstado(uf);
				
				
				Bairro bairro = new Bairro();	
				try {
					municipio.alterar();
					
					bairro.getMunicipio().setPkMunicipio(pkMunicipio);
					
					ArrayList<HashMap<String, String>> listaBairros = Ferramenta.stringToList(bairros);
					for(HashMap<String, String> _bairro:listaBairros)
					{
						bairro.setBairro(_bairro.get("nome"));
	
						if(_bairro.get("virtual") != null && _bairro.get("virtual").equals("true"))
							bairro.incluir();
						else if(_bairro.get("alterar") != null && _bairro.get("alterar").equals("true"))
						{
							bairro.setPkBairro(Long.parseLong(_bairro.get("id")));
							bairro.alterar();							
						}else if(_bairro.get("deletar") != null && _bairro.get("deletar").equals("true"))
						{
							bairro.setPkBairro(Long.parseLong(_bairro.get("id")));
							bairro.excluir();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			
			case 'B': // Bairro
			{
				String tipo = request.getParameter("tipo");
				
				if(tipo.equals("alterar"))
				{
					System.out.println("alterando");					
				}else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/administrador/servicos/municipio/bairro/alterar.jsp").forward(request, response);
				
				break;
			}
		
			case 'C': //Consultar Todos
			{				
				try {
					request.setAttribute("colMun", Municipio.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/municipio/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				if(request.getParameter("inclusao") != null)
				{
					try {
						request.setAttribute("colUf", Uf.consultarTodos());
					} catch (Exception e) {
						request.setAttribute("erro", "Erro ao adiquirir as Unidades de Federação.");
					}
					request.getRequestDispatcher("pages/administrador/servicos/municipio/incluir.jsp").forward(request, response);
					
				}else if(request.getParameter("pkMunicipio") != null)
				{
					long pkMunicipio = Long.parseLong(request.getParameter("pkMunicipio"));
					
					Municipio municipio = new Municipio();
					municipio.setPkMunicipio(pkMunicipio);

					try {
						municipio.consultar();
						request.setAttribute("colBairro", Bairro.consultarPorMunicipio(municipio));
						request.setAttribute("municipio", municipio);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = "detalhe.jsp";
					if(request.getParameter("alteracao") != null)
					{
						link = "alterar.jsp";
						try {
							request.setAttribute("colUf", Uf.consultarTodos());
						} catch (Exception e) {
							request.setAttribute("erro", "Erro ao adiquirir as Unidades de Federação.");
						}
					}
					
					request.getRequestDispatcher("pages/administrador/servicos/municipio/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkMunicipio = Long.parseLong(request.getParameter("pkMunicipio"));
				
				Municipio municipio = new Municipio();
				municipio.setPkMunicipio(pkMunicipio);
							
				try {
					municipio.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este município, pois existe bairros relacionados.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'I': //Inclusão
			{
				String no_municipio = request.getParameter("municipio");
				long pkUf = Long.parseLong(request.getParameter("uf"));
				String bairros = request.getParameter("bairros");
				
				Municipio municipio = new Municipio();
				municipio.setMunicipio(no_municipio);
				municipio.getUf().setPkEstado(pkUf);
								
				Bairro bairro = new Bairro();				
				try {
					bairro.getMunicipio().setPkMunicipio(municipio.incluir());
					ArrayList<HashMap<String, String>> listaBairros = Ferramenta.stringToList(bairros);
					for(HashMap<String, String> _bairro:listaBairros)
					{
						bairro.setBairro(_bairro.get("nome"));
						bairro.incluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
		}
	}

}
