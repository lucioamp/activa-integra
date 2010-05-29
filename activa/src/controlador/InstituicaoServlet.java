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

import modelo.ContatoInstituicao;
import modelo.Instituicao;
import modelo.Usuario;

/**
 * Servlet implementation class InstituicaoServlet
 */
public class InstituicaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstituicaoServlet() {
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
				long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));
				String nome = request.getParameter("nome");
				String tipoLogradouro = request.getParameter("tp_logradouro");
				String logradouro = request.getParameter("no_logradouro");
				long numero = Long.parseLong(request.getParameter("nu_logradouro"));
				String complemento = request.getParameter("ds_complemento");
				String cep = request.getParameter("nu_cep");
				long pkBairro = Long.parseLong(request.getParameter("bairro"));
				String contatos = request.getParameter("contatos");
								
				Instituicao instituicao = new Instituicao();
				instituicao.setPkInstituicao(pkInstituicao);
				instituicao.setNome(nome);
				instituicao.setTipoLogradouro(tipoLogradouro);
				instituicao.setLogradouro(logradouro);
				instituicao.setNumero(numero);
				instituicao.setComplemento(complemento);
				instituicao.setCep(cep);
				instituicao.getBairro().setPkBairro(pkBairro);
				
				ContatoInstituicao contatoInstituicao = new ContatoInstituicao();
				contatoInstituicao.setInstituicao(instituicao);
				try {
					instituicao.alterar();
					
					ArrayList<HashMap<String, String>> listaContatos = Ferramenta.stringToList(contatos);
					for(HashMap<String, String> _contato:listaContatos)
					{
						contatoInstituicao.setPkContatoInstituicao(Long.parseLong(_contato.get("id")));
						contatoInstituicao.setContato(_contato.get("desc"));
						contatoInstituicao.getTipoContato().setPkTipoContato(Long.parseLong(_contato.get("tipo")));
						if(_contato.get("virtual") != null && _contato.get("virtual").equals("true"))
							contatoInstituicao.incluir();
						else if(_contato.get("alterar") != null && _contato.get("alterar").equals("true"))
							contatoInstituicao.alterar();						
						else if(_contato.get("deletar") != null && _contato.get("deletar").equals("true"))
							contatoInstituicao.excluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
		
			case 'C': //Consultar Todos
			{
				try {
					request.setAttribute("instituicoes", Instituicao.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/instituicao/index.jsp").forward(request, response);
				
				break;
			}		
		
			case 'D': //Detalhe
			{
				if(request.getParameter("pkInstituicao") != null)
				{
					long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));

					Instituicao instituicao = new Instituicao();
					instituicao.setPkInstituicao(pkInstituicao);

					try {
						instituicao.consultar();
						request.setAttribute("instituicao", instituicao);
						request.setAttribute("contatos", ContatoInstituicao.consultarPorInstituicao(instituicao));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					String link = (request.getParameter("alteracao") != null ? "alterar.jsp" : "detalhe.jsp");
					request.getRequestDispatcher("pages/administrador/servicos/instituicao/"+link).forward(request, response);
				}
				break;
			}
			
			case 'E': //Exclusão
			{
				long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));
				
				Instituicao instituicao = new Instituicao();
				instituicao.setPkInstituicao(pkInstituicao);
				try {
					instituicao.excluir();	
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta instituição.");
				}
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				break;
			}
			
			case 'I': //Inclusão
			{
				String nome = request.getParameter("nome");
				String tipoLogradouro = request.getParameter("tp_logradouro");
				String logradouro = request.getParameter("no_logradouro");
				long numero = Long.parseLong(request.getParameter("nu_logradouro"));
				String complemento = request.getParameter("ds_complemento");
				String cep = request.getParameter("nu_cep");
				long pkBairro = Long.parseLong(request.getParameter("bairro"));
				String contatos = request.getParameter("contatos");
								
				Instituicao instituicao = new Instituicao();
				instituicao.setNome(nome);
				instituicao.setTipoLogradouro(tipoLogradouro);
				instituicao.setLogradouro(logradouro);
				instituicao.setNumero(numero);
				instituicao.setComplemento(complemento);
				instituicao.setCep(cep);
				instituicao.getBairro().setPkBairro(pkBairro);
				
				ContatoInstituicao contatoInstituicao = new ContatoInstituicao();
				try {
					contatoInstituicao.getInstituicao().setPkInstituicao(instituicao.incluir());

					ArrayList<HashMap<String, String>> lista = Ferramenta.stringToList(contatos);
					for(HashMap<String, String> object:lista)
					{
						contatoInstituicao.getTipoContato().setPkTipoContato(Long.parseLong(object.get("tipo")));
						contatoInstituicao.setContato(object.get("desc"));
						contatoInstituicao.incluir();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
		}
	}
}
