package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Ferramenta;
import util.ModuloEvento;

import modelo.Administrador;
import modelo.Ambiente;
import modelo.MembroAmbiente;
import modelo.Usuario;

/**
 * Servlet implementation class for Servlet: LoginServlet
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		executa(request, response);
	}


	ModuloEvento eventos = new ModuloEvento();
	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
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
			case '1': // Verificar Sessão
			{
				if(session.getAttribute("membro") == null && session.getAttribute("administrador") == null)
					request.setAttribute("msg", "false");
				else
					request.setAttribute("msg", "true");
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
		
			case '2': // Destruir Sessão
			{
				session.invalidate();
				break;
			}
			
			case '3': // Verificar Usuário
			{
				String login = request.getParameter("usuario").trim();
				String senha = request.getParameter("senha").trim();
				try{
					if(Usuario.validaLogin(login, senha)){
						Usuario usuario = Usuario.consultarPorLogin(login);
						
						if(usuario != null)
						{
							//se for administrador
							if(Administrador.isAdministrador(usuario)){
								session.setAttribute("administrador", usuario);
								request.setAttribute("msg", "administrador");
							}
							//se for membro
							else{
								session.setAttribute("membro", usuario);
								request.setAttribute("msg", "membro");
							}
						}else
							request.setAttribute("msg", "Erro ao carregar o Usuário.");
					}else{
			    		request.setAttribute("msg", "Usuário ou senha estão incorretos.");
					}
				}catch (Exception e) {
		    		request.setAttribute("msg", "Erro ao realizar o login");
				}
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case '4': // Selecionar Ambiente
			{
		    	Usuario usuario = (Usuario)session.getAttribute("membro");
		    	if(usuario != null && usuario.getPkUsuario() != 0)
		    	{
		    		long pkAmbiente = Long.parseLong(request.getParameter("pkAmbiente"));
		    		Ambiente ambiente = new Ambiente();
		    		ambiente.setPkAmbiente(pkAmbiente);
		    		try {
		    			ambiente.consultar();
		    			
						ambiente.setEnderecoArquivo(getCaminhoFisicoAplicacao(request,response)+"/arquivo/");
						
		    			session.setAttribute("ambiente", ambiente);
		    			request.setAttribute("msg", "true");
		    			request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
		    			break;
		    		}catch (Exception e) {
						// TODO: handle exception
					}
		    	}
		    	
		    	request.setAttribute("msg", "false");
		    	request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case '5': // Incluir/Remover membroAmbientes
			{
		    	Usuario usuario = (Usuario)session.getAttribute("membro");
		    	if(usuario != null && usuario.getPkUsuario() != 0)
		    	{
		    		String ambientes = request.getParameter("ambientes").trim();
		    		Boolean incluir = (request.getParameter("tipo").trim().equals("incluir"));
			    		
		    		MembroAmbiente membroAmbiente = new MembroAmbiente();
		    		membroAmbiente.getMembro().setPkUsuario(usuario.getPkUsuario());
		    		
		    		try {
		    			System.out.println(ambientes);
						ArrayList<HashMap<String, String>> lista = Ferramenta.stringToList(ambientes);
						System.out.println("quantidade: "+lista.size());
						for(HashMap<String, String> object:lista)
						{
							if(object.get("id") != null)
							{
								membroAmbiente.getAmbiente().setPkAmbiente(Long.parseLong(object.get("id")));
								if(incluir)
									membroAmbiente.incluir();
								else
									membroAmbiente.excluir();
							}
						}
						
						request.setAttribute("msg", "true");
		    			request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
		    			break;
		    		}catch (Exception e) {
						// TODO: handle exception
					}
		    	}
		    	
		    	request.setAttribute("msg", "false");
		    	request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case '6': // Remover Ambiente
			{
				session.removeAttribute("ambiente");
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}
	
	public String getCaminhoFisicoAplicacao(HttpServletRequest request, HttpServletResponse response) {
		String caminho = new File( request.getRequestURI() ).getParent();
		File caminhoReal = new File( getServletContext().getRealPath( caminho ) );	
		return caminhoReal.getParent().toString();
	}
	
	public String getCaminhoLogicoArquivo(HttpServletRequest request, HttpServletResponse response) {			
		System.out.println(request.getRemoteAddr());
		if(request.getRemoteAddr().equals("127.0.0.1")){
			return "http://" + request.getRemoteAddr() + ":8080" + request.getContextPath() + "/rtf/";
		}	
		return "http://www.baliu.com.br/activa" + request.getContextPath();
	}
 }
