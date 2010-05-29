package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Bairro;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;

/**
 * Servlet implementation class BairroServlet
 */
public class BairroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BairroServlet() {
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
				long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
				long pkMunicipio = Long.parseLong(request.getParameter("municipio"));
				
				Bairro bairro = new Bairro();
				bairro.setBairro(request.getParameter("bairro"));
				bairro.setPkBairro(pkBairro);
				bairro.getMunicipio().setPkMunicipio(pkMunicipio);
				
				try {
					bairro.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			
			case 'B': // Bairro
			{
				long pkMunicipio = Long.parseLong(request.getParameter("pkMunicipio"));
				
				Municipio municipio = new Municipio();
				municipio.setPkMunicipio(pkMunicipio);
				
				try {
					request.setAttribute("bairros", Bairro.consultarPorMunicipio(municipio));
					request.getRequestDispatcher("pages/administrador/servicos/bairro/selectBairros.jsp").forward(request, response);
				} catch (Exception e) {
					//
				}
				
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("colBairro", Bairro.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/bairro/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				if(request.getParameter("inclusao") != null)
				{
					request.getRequestDispatcher("pages/administrador/servicos/bairro/incluir.jsp").forward(request, response);
				}else if(request.getParameter("pkBairro") != null)
				{
					long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
					
					Bairro bairro = new Bairro();
					bairro.setPkBairro(pkBairro);

					try {
						bairro.consultar();
						request.setAttribute("bairro", bairro);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/bairro/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
				
				Bairro bairro = new Bairro();
				bairro.setPkBairro(pkBairro);
							
				try {
					bairro.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este bairro.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String no_bairro = request.getParameter("bairro");
				long pkMunicipio = Long.parseLong(request.getParameter("municipio"));
				
				Bairro bairro = new Bairro();
				bairro.setBairro(no_bairro);
				bairro.getMunicipio().setPkMunicipio(pkMunicipio);
		
				try {
					bairro.incluir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			
			case 'M': // Município
			{
				long pkEstado = Long.parseLong(request.getParameter("pkEstado"));
				
				Uf uf = new Uf();
				uf.setPkEstado(pkEstado);
				
				try {
					request.setAttribute("municipios", Municipio.consultarPorUf(uf));
					request.getRequestDispatcher("pages/administrador/servicos/bairro/selectMunicipios.jsp").forward(request, response);
				} catch (Exception e) {
					//
				}
				
				break;
			}
		}
	}

}
