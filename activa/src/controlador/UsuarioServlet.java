package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Administrador;
import modelo.ContatoUsuario;
import modelo.Membro;
import modelo.MicroBlog;
import modelo.Usuario;
import util.Ferramenta;
import util.ModuloEvento;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
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
		Usuario usuarioSession = (Usuario)session.getAttribute("administrador");
		if(usuarioSession == null)
			return;
		
		ModuloEvento eventos = usuarioSession.getModuloPorNome("usuario");
		if(eventos == null)
			eventos = usuarioSession.adicionarModulo("usuario");
		
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
			case 'A': //Alteração
			{
				long pkUsuario = Long.parseLong(request.getParameter("pkUsuario"));
				Long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
				Long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));
				
				Long numeroLogradouro = (
					request.getParameter("numero").length() == 0 ?
						0
					:
						Long.parseLong(request.getParameter("numero"))
				);
			
				Usuario usuario = new Usuario();
				usuario.setPkUsuario(pkUsuario);
				usuario.setApelido(request.getParameter("apelido"));
				usuario.getBairro().setPkBairro(pkBairro);
				usuario.setCep(request.getParameter("cep"));
				usuario.setComplemento(request.getParameter("complemento"));
				usuario.setCpf(Ferramenta.formatarCpf(request.getParameter("cpf")));
				usuario.setDataNasc(Ferramenta.formatarData(request.getParameter("dataNasc"), false));
				usuario.setEmail(request.getParameter("email"));
				usuario.setImagem(request.getParameter("imagem"));
				usuario.getInstituicao().setPkInstituicao(pkInstituicao);
				usuario.setLogradouro(request.getParameter("logradouro"));
				usuario.setNome(request.getParameter("nome"));
				usuario.setNumero(numeroLogradouro);
				usuario.setPerguntaChave(request.getParameter("perguntaChave"));
				usuario.setRespostaChave(request.getParameter("respostaChave"));
				usuario.setSenha(request.getParameter("senha"));
				usuario.setStatus(request.getParameter("status"));
				usuario.setTipoLogradouro(request.getParameter("tipoLogradouro"));
				usuario.setImagem("/");

				ContatoUsuario contatoUsuario = new ContatoUsuario();
				try {
					usuario.alterar();
					
					contatoUsuario.getUsuario().setPkUsuario(pkUsuario);
					
					ArrayList<HashMap<String, String>> listaContatos = Ferramenta.stringToList(request.getParameter("contatos"));
					for(HashMap<String, String> _contato:listaContatos)
					{
						contatoUsuario.setPkContatoUsuario(Long.parseLong(_contato.get("id")));
						contatoUsuario.setContato(_contato.get("desc"));
						contatoUsuario.getTipoContato().setPkTipoContato(Long.parseLong(_contato.get("tipo")));
						if(_contato.get("virtual") != null && _contato.get("virtual").equals("true"))
							contatoUsuario.incluir();
						else if(_contato.get("alterar") != null && _contato.get("alterar").equals("true"))
							contatoUsuario.alterar();						
						else if(_contato.get("deletar") != null && _contato.get("deletar").equals("true"))
							contatoUsuario.excluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("usuarios", Usuario.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/usuario/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkUsuario") != null)
				{
					long pkUsuario = Long.parseLong(request.getParameter("pkUsuario"));

					Usuario usuario = new Usuario();
					usuario.setPkUsuario(pkUsuario);

					try {
						usuario.consultar();
						request.setAttribute("usuario", usuario);
						request.setAttribute("contatos", ContatoUsuario.consultarPorUsuario(usuario));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/usuario/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkUsuario = Long.parseLong(request.getParameter("pkUsuario"));
				
				Usuario usuario = new Usuario();
				usuario.setPkUsuario(pkUsuario);

				try {
					usuario.excluir();	
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir este usuário.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{				
				Boolean isAdministrador = Boolean.parseBoolean(request.getParameter("isAdministrador"));
				Long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
				Long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));
				Long cpf = Ferramenta.formatarCpf(request.getParameter("cpf"));
				
				Long numeroLogradouro = (
					request.getParameter("numero").length() == 0 ?
						0
					:
						Long.parseLong(request.getParameter("numero"))
				);
			
				Usuario usuario = new Usuario();
				usuario.setApelido(request.getParameter("apelido"));
				usuario.getBairro().setPkBairro(pkBairro);
				usuario.setCep(request.getParameter("cep"));
				usuario.setComplemento(request.getParameter("complemento"));
				usuario.setCpf(cpf);
				usuario.setDataNasc(Ferramenta.formatarData(request.getParameter("dataNasc"), false));
				usuario.setEmail(request.getParameter("email"));
				usuario.setImagem(request.getParameter("imagem"));
				usuario.getInstituicao().setPkInstituicao(pkInstituicao);
				usuario.setLogradouro(request.getParameter("logradouro"));
				usuario.setNome(request.getParameter("nome"));
				usuario.setNumero(numeroLogradouro);
				usuario.setPerguntaChave(request.getParameter("perguntaChave"));
				usuario.setRespostaChave(request.getParameter("respostaChave"));
				usuario.setSenha(request.getParameter("senha"));
				usuario.setStatus(request.getParameter("status"));
				usuario.setTipoLogradouro(request.getParameter("tipoLogradouro"));
				usuario.setImagem("/");

				ContatoUsuario contatoUsuario = new ContatoUsuario();
				try {
					int pkUsuario = usuario.incluir();
					
					contatoUsuario.getUsuario().setPkUsuario(pkUsuario);
					
					Membro membro = new Membro();
					membro.setPkUsuario(pkUsuario);
					membro.getFormacaoAcademica().setPkFormacaoAcademica(1);
					membro.setPermissao("A");
					membro.incluir();
					
					if(isAdministrador)
					{
						Administrador administrador = new Administrador();
						administrador.setPkUsuario(pkUsuario);
						administrador.incluir();						
					}

					ArrayList<HashMap<String, String>> lista = Ferramenta.stringToList(request.getParameter("contatos"));
					for(HashMap<String, String> object:lista)
					{
						contatoUsuario.getTipoContato().setPkTipoContato(Long.parseLong(object.get("tipo")));
						contatoUsuario.setContato(object.get("desc"));
						contatoUsuario.incluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			
			case 'M': // Adicionar microBlog
			{
				boolean result = true;
				
				String descricao = request.getParameter("descricao").trim();
				
				Collection<MicroBlog> microBlogs = (Collection<MicroBlog>)usuarioSession.getMicroBlogs();
				for(MicroBlog microBlog:microBlogs)
				{
					if(microBlog.getDescricao().equals(descricao))
					{
						result = false;
						break;
					}
				}
				
				if(!result)
					break;
				
				MicroBlog microBlog = new MicroBlog();
				microBlog.getMembro().setPkUsuario(usuarioSession.getPkUsuario());
				microBlog.setDescricao(descricao);				
				try {
					microBlog.incluir();
					usuarioSession.getMicroBlogs().add(microBlog);
					request.setAttribute("msg", "true");
				}catch (Exception e) {}
				
				break;
			}
			
			case 'P':
			{
				long pkUsuario = Long.parseLong(request.getParameter("pkUsuario"));
				String permissao =  request.getParameter("permissao");
				
				Membro membro = new Membro();
				membro.setPkUsuario(pkUsuario);
				try {					
					membro.consultar();					
					membro.setPermissao(permissao);
					membro.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				//request.setAttribute("opcao", "C");
				//executa(request, response);
				//request.setAttribute("opcao", null);
			}
			
			case 'S':
			{
				long pkUsuario = Long.parseLong(request.getParameter("pkUsuario"));
				String status =  request.getParameter("status");
				
				Usuario usuario = new Usuario();
				usuario.setPkUsuario(pkUsuario);
				try {
					usuario.consultar();
					usuario.setStatus(status);
					usuario.alterar();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
