package controlador;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ContatoUsuario;
import modelo.Favorito;
import modelo.PastasFavorito;
import modelo.Usuario;

/**
 * Servlet implementation class ContatoUsuarioServlet
 */
public class MembroFavoritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroFavoritoServlet() {
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
		
		response.setContentType("text/html");
		response.setCharacterEncoding("ISO-8859-1");
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);

		char opcao = (
			request.getAttribute("opcao") != null ?
				request.getAttribute("opcao").toString()
			:
				request.getParameter("opcao").toString()
		).charAt(0);
		
		ContatoUsuario contatoUsuario = new ContatoUsuario();
		contatoUsuario.setUsuario(usuario);
		
		switch(opcao)
		{
			case 'A': //Alteração
			{
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("favoritos", Favorito.consultarPorMembro(usuario));
					request.setAttribute("pastas", PastasFavorito.consultarPorUsuario(usuario));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/favorito/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'D': //Detalhe
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/favorito/alterar.jsp").forward(request, response);				
				else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/favorito/detalhe.jsp").forward(request, response);
				break;
			}
			
			case 'E': //Exclusão
			{

				break;
			}
			
			case 'F': //Favoritos
			{
				String tipo = request.getParameter("tipo");
				
				Favorito favorito = new Favorito();
				if(tipo.equals("alterarNome"))
				{
					Long pkFavorito = Long.parseLong(request.getParameter("pkFavorito"));
					favorito.setPkFavorito(pkFavorito);
					
					try {
						favorito.consultar();
						favorito.setNomeLink(request.getParameter("nome"));
						
						favorito.alterar();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}else if(tipo.equals("alterarUrl"))
				{
					Long pkFavorito = Long.parseLong(request.getParameter("pkFavorito"));
					favorito.setPkFavorito(pkFavorito);
					
					try {
						favorito.consultar();
						favorito.setUrl(request.getParameter("url"));
						
						favorito.alterar();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}else if(tipo.equals("alterarPasta"))
				{
					Long pkFavorito = Long.parseLong(request.getParameter("pkFavorito"));
					Long pkPasta = Long.parseLong(request.getParameter("pkPasta"));
					
					System.out.println(pkFavorito);
					System.out.println(pkPasta);
					
					favorito.setPkFavorito(pkFavorito);
					
					try {
						favorito.consultar();
						favorito.getPasta().setPkPasta(pkPasta);						
						
						favorito.alterar();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}else if(tipo.equals("incluir"))
				{
					favorito.getMembro().setPkUsuario(usuario.getPkUsuario());
					favorito.setNomeLink(request.getParameter("nome"));
					favorito.setUrl(request.getParameter("url"));
					favorito.setDescricaoLink("");
					
					if(request.getParameter("parent_id") != null)
						favorito.getPasta().setPkPasta(Long.parseLong(request.getParameter("parent_id")));
					
					try {
						request.setAttribute("msg", favorito.incluir());
					} catch (Exception e) {
						System.out.println(e.toString());
						request.setAttribute("msg", "false");
					}
				}else if(tipo.equals("excluir"))
				{
					Long pkFavorito = Long.parseLong(request.getParameter("pkFavorito"));
					favorito.setPkFavorito(pkFavorito);
					try {
						favorito.excluir();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case 'I': //Inclusão
			{

				break;
			}
			
			case 'P': //Pasta
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("incluir"))
				{
					PastasFavorito pasta = new PastasFavorito();
					pasta.setNome(request.getParameter("nome"));
					pasta.getUsuario().setPkUsuario(usuario.getPkUsuario());
					
					if(request.getParameter("parent_id") != null)
					{
						PastasFavorito _pasta = new PastasFavorito();
						_pasta.setPkPasta(Long.parseLong(request.getParameter("parent_id")));
						pasta.setPasta(_pasta);
					}
					
					try {
						request.setAttribute("msg", pasta.incluir());
					} catch (Exception e) {
						System.out.println(e.toString());
						request.setAttribute("msg", "false");
					}					
				}else if(tipo.equals("alterar"))
				{
					Long pkPasta = Long.parseLong(request.getParameter("pkPasta"));
					Long pkParent = Long.parseLong(request.getParameter("pkParent"));
					
					PastasFavorito pasta = new PastasFavorito();
					pasta.setPkPasta(pkPasta);
					
					try {
						pasta.consultar();
						
						if(request.getParameter("nome") != null)
							pasta.setNome(request.getParameter("nome"));
					
						if(pkParent > 0 && pasta.getPasta() != null)
						{
							pasta.setPasta(new PastasFavorito());
							pasta.getPasta().setPkPasta(pkParent);
						}else
							pasta.setPasta(null);
						
						pasta.alterar();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}else if(tipo.equals("excluir"))
				{
					Long pkPasta = Long.parseLong(request.getParameter("pkPasta"));
					PastasFavorito pasta = new PastasFavorito();
					pasta.setPkPasta(pkPasta);
					
					try {
						excluirPastas(pasta);
						pasta.excluir();
						request.setAttribute("msg", "true");
					} catch (Exception e) {
						request.setAttribute("msg", "false");
					}
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
		}
	}
    
    private void excluirPastas(PastasFavorito pasta)
    {
    	try {
    		Collection<PastasFavorito> pastas = PastasFavorito.consultarPorPasta(pasta);
    		for(PastasFavorito _pasta:pastas)
    		{
    			excluirPastas(_pasta);
    			_pasta.excluir();
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
