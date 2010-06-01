package controlador;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import modelo.integra.AplicacaoExterna;
import modelo.integra.Parametro;
import modelo.integra.Recurso;
import modelo.integra.UsuarioAplicacao;
import modelo.integra.UsuarioAplicacaoParametro;

/**
 * Servlet implementation class AplicacoesExternasServlet
 */
public class MembroAplicacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroAplicacaoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executa(request, response);
	}
	
	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("membro");
		if(usuario == null)
			return;
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
    	
		if (session.getAttribute("erro") != null) {
			request.setAttribute("erro", session.getAttribute("erro"));
			session.removeAttribute("erro");
		}
		
		char opcao = (
			request.getAttribute("opcao") != null ?
				request.getAttribute("opcao").toString()
			:
				request.getParameter("opcao").toString()
		).charAt(0);

		switch(opcao)
		{
			case 'C': //Consultar Todos
			{
				try {
					UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
					usuarioAplicacao.setPkUsuario(usuario.getPkUsuario());
					
					request.setAttribute("recursoLista", usuarioAplicacao.consultarPorUsuario());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'E': //Exclusão
			{
				try {
					UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
					usuarioAplicacao.setIdUsuarioAplicacao(Long.parseLong((String) request.getParameter("idUsuarioAplicacao")));
					
					usuarioAplicacao.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir.");
				}
				
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'S': //Selecionar recurso para inclusão
			{
				try {
					request.setAttribute("colAplicacao", AplicacaoExterna.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/selecionar.jsp").forward(request, response);
				
				break;
			}
			case 'I': //Inclusão
			{
				try {
					AplicacaoExterna aplicacao = new AplicacaoExterna();
					aplicacao.setIdAplicacao(Long.parseLong((String) request.getParameter("idAplicacao")));
					aplicacao.consultar();
					request.setAttribute("aplicacao", aplicacao);
					
					Recurso recurso = new Recurso();
					recurso.setIdRecurso(Long.parseLong((String) request.getParameter("idRecurso")));
					recurso.consultar(recurso);
					request.setAttribute("recurso", recurso);
					
					// Busca lista de parâmetros
					Parametro.consultarPorRecurso(recurso);
					
				} catch (Exception e) {
					request.setAttribute("erro", "Não foi possível buscar os dados da aplicação.");
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/incluir.jsp").forward(request, response);
				
				break;
			}
			case 'D': //Alteração/Detalhes
			{
				try {
					UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
					usuarioAplicacao.setIdUsuarioAplicacao(Long.parseLong((String) request.getParameter("idUsuarioAplicacao")));
					usuarioAplicacao.consultarPorId(usuarioAplicacao);
					request.setAttribute("usuarioAplicacao", usuarioAplicacao);
					
					AplicacaoExterna aplicacao = new AplicacaoExterna();
					aplicacao.setIdAplicacao(usuarioAplicacao.getIdAplicacao());
					aplicacao.consultar();
					request.setAttribute("aplicacao", aplicacao);
					
					Recurso recurso = new Recurso();
					recurso.setIdRecurso(usuarioAplicacao.getIdRecurso());
					recurso.consultar(recurso);
					request.setAttribute("recurso", recurso);
					
					// Busca lista de parâmetros
					Parametro.consultarPorRecurso(recurso);
					
					// Pega os valores dos parâmetros
					// TODO
					
				} catch (Exception e) {
					request.setAttribute("erro", "Não foi possível buscar os dados da aplicação.");
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/incluir.jsp").forward(request, response);
				
				break;
			}
			case 'A': //Salvar inclusão/alteração dos dados
			{
				String idUsuarioAplicacao = request.getParameter("idUsuarioAplicacao");
				Long idAplicacao = Long.valueOf(request.getParameter("idAplicacao"));
				Long idRecurso = Long.valueOf(request.getParameter("idRecurso"));
				String[] arrUsar = request.getParameter("arrUsar").split(",");
				String[] arrParamValor = request.getParameter("arrParamValor").split(",");
				String[] arrBloquear = request.getParameter("arrBloquear").split(",");
				Integer permissao = Integer.valueOf(request.getParameter("permissao"));
				String mostrarJanela = request.getParameter("mostrarJanela");
				Boolean twoWay = Boolean.valueOf(request.getParameter("twoWay"));
				Integer opcaoTwoWay = Integer.valueOf(request.getParameter("opcaoTwoWay"));
				String tempoValor = (String) request.getParameter("tempoValor");
				
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setPkUsuario(usuario.getPkUsuario());
				usuarioAplicacao.setIdAplicacao(idAplicacao);
				usuarioAplicacao.setIdRecurso(idRecurso);
				usuarioAplicacao.setPermissao(permissao);
				usuarioAplicacao.setMostrarJanela(mostrarJanela);
				if (twoWay) {
					usuarioAplicacao.setAtualizacaoAutomatica(opcaoTwoWay);
					try {
						usuarioAplicacao.setTempoValor(Integer.valueOf(tempoValor));
					} catch(NumberFormatException ex) {
						usuarioAplicacao.setMensagem("Campo Tempo é inválido.");
						
						request.setAttribute("jsonObject", usuarioAplicacao);
						request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
						break;
					}
				}
				
				try {
					if (idUsuarioAplicacao == null || idUsuarioAplicacao == "") {
						usuarioAplicacao.setIdUsuarioAplicacao(usuarioAplicacao.incluir());
					} else {
						usuarioAplicacao.setIdUsuarioAplicacao(Long.valueOf(idUsuarioAplicacao));
						usuarioAplicacao.atualizar();
					}
					
					// Atualiza com os parâmetros selecionados
					UsuarioAplicacaoParametro.excluir(usuarioAplicacao.getIdUsuarioAplicacao());
					
					for (int i = 0; i < arrUsar.length; i++) {
						Long idParametro = Long.valueOf(arrUsar[i]);
						
						UsuarioAplicacaoParametro parametro = new UsuarioAplicacaoParametro();
						parametro.setIdUsuarioAplicacao(usuarioAplicacao.getIdUsuarioAplicacao());
						parametro.setIdParametro(idParametro);
						
						String valorPadrao = "";
						if (arrParamValor.length > i) {
							valorPadrao = arrParamValor[i];
						}
						parametro.setValorPadrao(valorPadrao);	
						
						List<String> bloquearLista = Arrays.asList(arrBloquear);
						parametro.setBloquearValor(bloquearLista.contains(arrUsar[i]));
						
						parametro.incluir();
					}
					
					usuarioAplicacao.setMensagem("Edição do Mashup realizada com sucesso.");
					
				} catch (Exception e) {
					usuarioAplicacao.setMensagem("Não foi possível atualizar os dados.");
				}
				
				request.setAttribute("jsonObject", usuarioAplicacao);
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			case 'X': //Executar aplicação
			{
				request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/executar.jsp").forward(request, response);
				
				break;
			}
		}
	}
    
}
