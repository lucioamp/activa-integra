package nucleo.output;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;

import nucleo.usuario.Sessao;
import nucleo.usuario.SessaoPersist;
import nucleo.usuario.Usuario;
import nucleo.usuario.UsuarioPersist;

import avance.ConsumerOpenID;

import util.banco.ExcecaoBanco;
import viecili.jrss.generator.RSSFeedGeneratorImpl;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.RSS;

public class GeraSaidaUsuario extends GeraSaida 
{
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#getNomeNivel()
	 */
	public String getNomeNivel() 
	{
		return "usuario";
	}

	public String openID()  
	{
		return "";
	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#formularioInserir(java.lang.String)
	 */
	public String obtemForm()  
	{
		String campos, estrutura;

		estrutura = "<f_cpf/>\n<f_senha/>\n<f_confirma/>\n<f_nomeUsuario/>\n" +
					"<f_email/>\n<f_telefone/>\n<f_papelProf/>\n" +
					"<f_papelAluno/>\n<f_papelTutor/>\n<f_papelAdmin/>\n";
		
		campos = criarInputText("CPF:",   "f_cpf") +
		         criarPassword("Senha:", "f_senha") +
		         criarPassword("Confirmação:", "f_confirma") +
		         criarInputText("Nome:", "f_nomeUsuario") +
		         criarInputText("E-mail:", "f_email") +
		         criarInputText("Telefone:", "f_telefone") +
		         criarCheckbox("", "f_papelProf",  criarItem("Professor", "on")) +
				 criarCheckbox("", "f_papelAluno", criarItem("Aluno", "on")) +
				 criarCheckbox("", "f_papelTutor", criarItem("Tutor", "on")) +
				 criarCheckbox("", "f_papelAdmin", criarItem("Administrador", "on"));
		
		return criaFormXForms( "CadastrarUsuario", "/IesAvance/rest/usuario", estrutura, campos );
	}
	
	public String inserir() // throws Exception
	{
		String saida;

		saida = null;

		try 
		{
			UsuarioPersist persist = new UsuarioPersist();

			String cpf, senha, confirma, nome, email, telefone;
			boolean ehProf, ehAluno, ehTutor, ehAdmin;
		
			try 
			{
				cpf = getParameter("cpf");
				senha = URLDecoder.decode(getParameter("senha"), "ISO-8859-1");
				confirma = URLDecoder.decode(getParameter("confirma"), "ISO-8859-1");
				nome = URLDecoder.decode(getParameter("nomeUsuario"), "ISO-8859-1");
				email = URLDecoder.decode(getParameter("email"), "ISO-8859-1");
				telefone = getParameter("telefone");
				ehProf = getParameter("papelProf").equals("on");
				ehAluno = getParameter("papelAluno").equals("on");
				ehTutor = getParameter("papelTutor").equals("on");
				ehAdmin = getParameter("papelAdmin").equals("on");
			}
			catch (Exception e)
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Erro ao decodificar parametro: " + e.getMessage());
				return null;
			}
			
			if ( !senha.equals(confirma) )
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Senha nao confirmada!" );
				return null;
			}
			
			if ( persist.Existe(cpf) )
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
						 "Usuario Ja existente!" );
				return null;
			}
			
			Usuario usuarioNovo;
			
			usuarioNovo = new Usuario(-1, email, senha, 
									  nome, cpf, telefone, 
									  telefone, new Date(), ehProf, 
									  ehAluno, ehTutor, ehAdmin); 

			if ( !persist.Inclui(usuarioNovo) )
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Erro na inclusao de Usuario!" );
				return null;
			}

			// Produz um RSS com a mensagem confirmando a inclusão
			
			Channel channel = new Channel("Usuário inserido com sucesso!", 
					                      "/IesAvance/rest/usuario/form", nome);
			channel.setLanguage("pt");
			RSS rssListar = new RSS();
			rssListar.addChannel(channel);
			
			RSSFeedGeneratorImpl generator = new RSSFeedGeneratorImpl();
			
			try 
			{
				saida = generator.generateAsString(rssListar);
			}
			catch ( IOException e )
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Nao pude gerar o RSS: " + e.getMessage() );
			}
			
		}
		catch (ExcecaoBanco e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro na inclusao de Usuario: " + e.getMessage() );
		}
		
		return saida;
	}
	
	public String login()
	{
		String senha;
		String ipUsuario;
		String cpfUsuario;

		senha = getParameter("senha");
		cpfUsuario = getParameter("cpf");
		ipUsuario = getRemoteAddr();
		Usuario usuarioBanco;

		try 
		{
			Sessao sessaoBanco;
			UsuarioPersist usuPersist; 
			SessaoPersist sesPersist;

			if ( senha.length() == 0 )
			{
				setErro( HttpServletResponse.SC_BAD_REQUEST, 
						 "Senha nao informada!" );
				return null;
			}
				
			usuPersist = new UsuarioPersist();
			
			usuarioBanco = usuPersist.Busca(cpfUsuario, senha);

			if ( usuarioBanco == null )
			{
				setErro( HttpServletResponse.SC_UNAUTHORIZED, 
						 "Senha invalida ou login invalido!" );
				return null;
			}
			
			sesPersist = new SessaoPersist();
			
			sessaoBanco = sesPersist.Busca( getServerSessionId(), 
										    usuarioBanco.getIdUsuario(), 
										    ipUsuario );
			
			if ( sessaoBanco == null )
			{
				sessaoBanco = new Sessao( usuarioBanco.getIdUsuario(),
										  getServerSessionId(),
										  ipUsuario,
										  new Date( getServerSessionCreationTime() ),
										  new Date( getServerSessionLastAccessedTime() ),
										  0, 1 );
				
				// Para garantir que não há mais nenhuma 
				//     sessão ativa com o mesmo id
				sesPersist.FazLogout(getServerSessionId());
				sesPersist.Inclui(sessaoBanco);
			}
		}
		catch (ExcecaoBanco e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro de SQL: " + e.getMessage());
			return null;
		}

		GeraSaidaTurma turmas;
		
		turmas = new GeraSaidaTurma();
		
		turmas.setUrlApp( getUrlApp() );
		
		
		return turmas.exibeTurmas( getServerSessionId(), 
								   usuarioBanco.getIdUsuario(), 
								   usuarioBanco.getNomeUsuario() );
	}
	
	public String loginOpenID()
	{
		ConsumerOpenID consumer;
		
		try
		{
			consumer = ConsumerOpenID.Instancia();
		}
		catch (ConsumerException e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Nao foi possível criar o manager!" );
			return null;
		}
		
		try
		{
			if ( "true".equals(getParameter("is_return")) ) 
			{
				consumer.processReturn(getRequest(), getResponse());
			} 
			else 
			{
				String identifier = getParameter("openid_identifier");
				consumer.authRequest( identifier, getRequest(), getResponse() );
			}
		}
		catch (OpenIDException e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Erro ao redirecionar para openID: " + e.getMessage() );
		}

		return "dispatch";
	}
}
