package avance;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nucleo.usuario.Sessao;
import nucleo.usuario.SessaoPersist;
import nucleo.usuario.Usuario;
import nucleo.usuario.UsuarioPersist;
import util.banco.ExcecaoBanco;

/**
 * Servlet implementation class for Servlet: FinalizaLoginOPenID
 *
 */
 public class FinalizaLoginOPenID extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
 {
	private static final long serialVersionUID  = 1;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public FinalizaLoginOPenID() 
	{
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int idUsuario;
		String oper;
		String ipUsuario;
		Usuario usuarioBanco;
		UsuarioPersist usuPersist; 

		ipUsuario = request.getRemoteAddr();
		
		try 
		{
			usuPersist = new UsuarioPersist();
		}
		catch(ExcecaoBanco e)
		{
			response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
								"AvaNCE(POST): Erro na conex�o ao banco: " + e.getMessage());
			return;
		}
		
		oper = request.getParameter("operacao");
		if ( oper != null )
		{
			String idOpenID;
			String cpfUsuario;

			idOpenID =  request.getParameter("identifier");
			if ( idOpenID == null )
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(POST): Identificador openID inv�lido!" );
				return;
			}

			cpfUsuario =  request.getParameter("cpf");
			if ( cpfUsuario == null )
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(POST): CPF n�o definido!" );
				return;
			}

			if ( oper.equals("associar") )
			{
				String senha; 

				senha =  request.getParameter("senha");
				if ( senha == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Senha n�o definida!" );
					return;
				}
				
				try 
				{
					usuarioBanco = usuPersist.Busca(cpfUsuario, senha);
				}
				catch(ExcecaoBanco e)
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST,
										"AvaNCE(POST): Erro na Busca do usu�rio: " + e.getMessage());
					return;
				}
				
				if ( usuarioBanco == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Senha inv�lida ou login inv�lido!" );
					return;
				}

				idUsuario = usuarioBanco.getIdUsuario();
			}
			else if  ( oper.equals("cadastrar") )
			{
				String nomeUsuario, email, telefone;

				nomeUsuario =  request.getParameter("nomeUsuario");
				if ( nomeUsuario == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Nome do Usu�rio n�o definido!" );
					return;
				}

				email =  request.getParameter("email");
				if ( email == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Email n�o definido!" );
					return;
				}

				telefone =  request.getParameter("telefone");
				if ( telefone == null )
				{
					response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
										"AvaNCE(POST): Telefone n�o definido!" );
					return;
				}

				try 
				{
					Usuario usuarioNovo;
					
					if ( usuPersist.Existe(cpfUsuario) )
					{
						response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
											"AvaNCE(POST): Usuario J� existente!" );
						return;
					}
						
					usuarioNovo = new Usuario(-1, email, "", 
											  nomeUsuario, cpfUsuario, telefone, 
											  telefone, new Date(), false, 
											  false, false, false); 

					if ( !usuPersist.Inclui(usuarioNovo) )
					{
						response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
										    "AvaNCE(POST): Erro na inclus�o de Usuario!" );
						return;
					}

					usuarioBanco = usuPersist.Busca(cpfUsuario, "");
					
					if ( usuarioBanco == null )
					{
						response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
											"AvaNCE(POST): Erro na inclus�o de Usuario!" );
						return;
					}

					idUsuario = usuarioBanco.getIdUsuario();
				}
				catch (ExcecaoBanco e)
				{
					response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
										"AvaNCE(POST): Erro na inclus�o de Usuario: " + e.getMessage() );
					return;
				}
			}
			else
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(POST): Opera��o inv�lida: " + oper );
				return;
			}
			
			// Inserir na tabela NPUserOPenID
			try 
			{
				usuPersist.IncluiOpenID(idOpenID, idUsuario);
			}
			catch(ExcecaoBanco e)
			{
				response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
									"AvaNCE(POST): Erro na inclus�o do OpenID: " + e.getMessage());
				return;
			}
		}
		else
		{
			String idStr;

			idStr =  request.getParameter("idusuario");
			if ( idStr == null )
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST, 
									"AvaNCE(POST): Identificador do usu�rio inv�lido!" );
				return;
			}

			try 
			{
				idUsuario = Integer.parseInt(idStr);
			}
			catch ( Exception e )
			{
				response.sendError( HttpServletResponse.SC_BAD_REQUEST,
									"AvaNCE(POST): Identificador do usu�rio n�o num�rico!" );
				
				return;
			}
		}

		try 
		{
			Sessao sessaoBanco;
			SessaoPersist sesPersist;
			
			sesPersist = new SessaoPersist();
			
			sessaoBanco = sesPersist.Busca( request.getSession().getId(), 
											idUsuario, ipUsuario );
			
			if ( sessaoBanco != null )
			{
				System.out.println("IdSessao: " + sessaoBanco.getIdSessao());
				System.out.println("Logado: " + sessaoBanco.getLogado());
			}
			else
				System.out.println("Sessao == null");
			
			if ( sessaoBanco == null )
			{
				sessaoBanco = new Sessao( idUsuario,
										  request.getSession().getId(),
										  ipUsuario,
										  new Date( request.getSession().getCreationTime() ),
										  new Date( request.getSession().getLastAccessedTime() ),
										  0, 1 );
				
				// Para garantir que n�o h� mais nenhuma 
				//     sess�o ativa com o mesmo id
				sesPersist.FazLogout(request.getSession().getId());
				sesPersist.Inclui(sessaoBanco);
			}
		}
		catch (ExcecaoBanco e)
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST,
								"AvaNCE(POST): Erro de SQL: " + e.getMessage());
			return;
		}

		RequestDispatcher proximo;
		
		proximo = request.getRequestDispatcher( "/index.html" );
		proximo.forward(request, response);
	}   	  	    
}