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
import modelo.Uf;
import modelo.Usuario;

/**
 * Servlet implementation class UfServlet
 */
public class UfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UfServlet() {
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
				long pkEstado = Long.parseLong(request.getParameter("pkEstado"));
				String estado = request.getParameter("estado");
				String sigla = request.getParameter("sigla");
				String municipios = request.getParameter("municipios");
				
				Uf uf = new Uf();
				uf.setPkEstado(pkEstado);
				uf.setEstado(estado);
				uf.setSigla(sigla);
				
				Municipio municipio = new Municipio();	
				try {
					uf.alterar();
					
					municipio.getUf().setPkEstado(pkEstado);
					ArrayList<HashMap<String, String>> listaMunicipios = Ferramenta.stringToList(municipios);
					for(HashMap<String, String> _municipio:listaMunicipios)
					{
						municipio.setMunicipio(_municipio.get("nome"));

						if(_municipio.get("virtual") != null && _municipio.get("virtual").equals("true"))
							municipio.incluir();
						else if(_municipio.get("alterar") != null && _municipio.get("alterar").equals("true"))
						{
							municipio.setPkMunicipio(Long.parseLong(_municipio.get("id")));
							municipio.alterar();							
						}else if(_municipio.get("deletar") != null && _municipio.get("deletar").equals("true"))
						{
							municipio.setPkMunicipio(Long.parseLong(_municipio.get("id")));
							municipio.excluir();
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
					request.setAttribute("colUf", Uf.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/unidade_federacao/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				long pkEstado = Long.parseLong(request.getParameter("pkEstado"));
				
				Uf uf = new Uf();
				uf.setPkEstado(pkEstado);

				try {
					uf.consultar();
					request.setAttribute("colMunicipio", Municipio.consultarPorUf(uf));
					request.setAttribute("uf", uf);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.getRequestDispatcher("pages/administrador/servicos/unidade_federacao/"
					+(request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp")
				).forward(request, response);
				
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkEstado = Long.parseLong(request.getParameter("pkEstado"));
				
				Uf uf = new Uf();
				uf.setPkEstado(pkEstado);
							
				try {
					uf.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta UF, pois existe municípios relacionados.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'I': //Inclusão
			{
				String estado = request.getParameter("estado");
				String sigla = request.getParameter("sigla");
				String municipios = request.getParameter("municipios");
				
				Uf uf = new Uf();
				uf.setEstado(estado);
				uf.setSigla(sigla);
				
				Municipio municipio = new Municipio();				
				try {
					municipio.getUf().setPkEstado(uf.incluir());					
					for(String nome:municipios.split(":"))
					{
						nome.trim();
						if(nome.length() > 0)
						{
							municipio.setMunicipio(nome.trim());
							municipio.incluir();
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			
			case 'M': // Município
			{
				String tipo = request.getParameter("tipo");
				//String id = request.getParameter("id");
				//String nome = request.getParameter("nome");
				
				if(tipo.equals("alterar"))
				{
					System.out.println("alterando");					
				}else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/administrador/servicos/unidade_federacao/municipio/alterar.jsp").forward(request, response);
				
				break;
			}
		}
	}

}
