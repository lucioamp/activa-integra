package controlador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import modelo.Usuario;
import modelo.integra.AplicacaoExterna;
import modelo.integra.ExecutorAplicacaoRequest;
import modelo.integra.Parametro;
import modelo.integra.Recurso;
import modelo.integra.UsuarioAplicacao;
import modelo.integra.UsuarioAplicacaoParametro;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import util.integra.ExecutorAplicacao;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		executa(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		executa(request, response);
	}

	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("membro");
		if (usuario == null)
			return;

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		if (session.getAttribute("erro") != null) {
			request.setAttribute("erro", session.getAttribute("erro"));
			session.removeAttribute("erro");
		}

		char opcao = (request.getAttribute("opcao") != null ? request.getAttribute("opcao").toString() : request
				.getParameter("opcao").toString()).charAt(0);

		switch (opcao) {
		case 'C': // Consultar Todos
		{
			try {
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setPkUsuario(usuario.getPkUsuario());

				request.setAttribute("recursoLista", usuarioAplicacao.consultarPorUsuario());
			} catch (Exception e) {
				System.out.println("Erro ao realizar o precessamento.");
			}

			request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/index.jsp").forward(request,
					response);

			break;
		}

		case 'E': // Exclusão
		{
			try {
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setIdUsuarioAplicacao(Long.parseLong((String) request
						.getParameter("idUsuarioAplicacao")));

				usuarioAplicacao.excluir();
			} catch (Exception e) {
				request.setAttribute("erro", "Não é possivel excluir.");
			}

			request.setAttribute("opcao", "C");
			executa(request, response);
			request.setAttribute("opcao", null);

			break;
		}

		case 'S': // Selecionar recurso para inclusão
		{
			try {
				request.setAttribute("colAplicacao", AplicacaoExterna.consultarTodos());
			} catch (Exception e) {
				System.out.println("Erro ao realizar o precessamento.");
			}

			request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/selecionar.jsp").forward(request,
					response);

			break;
		}
		case 'I': // Inclusão
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

			request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/incluir.jsp").forward(request,
					response);

			break;
		}
		case 'D': // Alteração/Detalhes
		{
			try {
				// Dados da configuração
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setIdUsuarioAplicacao(Long.parseLong((String) request
						.getParameter("idUsuarioAplicacao")));
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

				if (recurso.getParametros().size() > 0) {
					// Pega os valores dos parâmetros
					Collection<UsuarioAplicacaoParametro> parametroLista = UsuarioAplicacaoParametro
							.consultarPorUsuarioAplicacao(usuarioAplicacao.getIdUsuarioAplicacao());

					for (Parametro parametro : recurso.getParametros()) {
						for (UsuarioAplicacaoParametro usuarioParametro : parametroLista) {
							if (parametro.getIdParametro() == usuarioParametro.getIdParametro()) {
								parametro.setUsarParametro(true);
								parametro.setValorPadrao(usuarioParametro.getValorPadrao());
								parametro.setBloquearValor(usuarioParametro.isBloquearValor());
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				request.setAttribute("erro", "Não foi possível buscar os dados da aplicação.");
			}

			request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/incluir.jsp").forward(request,
					response);

			break;
		}
		case 'A': // Salvar inclusão/alteração dos dados
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
				
				if (opcaoTwoWay == 1) {
					try {
						usuarioAplicacao.setTempoValor(Integer.valueOf(tempoValor));
					} catch (NumberFormatException ex) {
						usuarioAplicacao.setMensagem("Campo Tempo é inválido.");

						request.setAttribute("jsonObject", usuarioAplicacao);
						request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
						break;
					}
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
					if (arrUsar[i] != null && !arrUsar[i].equals("")) {
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
				}

				usuarioAplicacao.setMensagem("Edição do Mashup realizada com sucesso.");

			} catch (Exception e) {
				usuarioAplicacao.setMensagem("Não foi possível atualizar os dados.");
			}

			request.setAttribute("jsonObject", usuarioAplicacao);
			request.getRequestDispatcher("pages/empty.jsp").forward(request, response);

			break;
		}
		case 'L': // Chamar tela para executar aplicação
		{
			try {
				// Dados da configuração
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setIdUsuarioAplicacao(Long.parseLong((String) request
						.getParameter("idUsuarioAplicacao")));
				usuarioAplicacao.consultarPorId(usuarioAplicacao);

				AplicacaoExterna aplicacao = new AplicacaoExterna();
				aplicacao.setIdAplicacao(usuarioAplicacao.getIdAplicacao());
				aplicacao.consultar();
				request.setAttribute("aplicacao", aplicacao);

				Recurso recurso = new Recurso();
				recurso.setIdRecurso(usuarioAplicacao.getIdRecurso());
				recurso.consultar(recurso);
				request.setAttribute("recurso", recurso);

				Collection<Parametro> parametroLista = Parametro.consultarPorUsuarioAplicacao(usuarioAplicacao
						.getIdUsuarioAplicacao());
				request.setAttribute("parametroLista", parametroLista);

				session.setAttribute("idUsuarioAplicacao", usuarioAplicacao.getIdUsuarioAplicacao());

			} catch (Exception e) {
				request.setAttribute("msg", "Não foi possível atualizar os dados.");
			}

			request.getRequestDispatcher("pages/restrito/servicos/aplicacoesExternas/executar.jsp").forward(request,
					response);

			break;
		}

		case 'X': // Executar aplicação
		{
			ExecutorAplicacaoRequest aplicacaoRequest = null;
			
			// Salva os dados para todas as aplicações executadas
			List<ExecutorAplicacaoRequest> aplicacaoRequestLista = getAplicacaoRequestLista(session);
			
			try {
				Long idUsuarioAplicacao = (Long) session.getAttribute("idUsuarioAplicacao");
				
				UsuarioAplicacao usuarioAplicacao = new UsuarioAplicacao();
				usuarioAplicacao.setIdUsuarioAplicacao(idUsuarioAplicacao);
				usuarioAplicacao.consultarPorId(usuarioAplicacao);
				
				int indiceAplicacao = getIndiceAplicacao(aplicacaoRequestLista, idUsuarioAplicacao);
	
				if (indiceAplicacao >= 0) {
					aplicacaoRequest = aplicacaoRequestLista.get(indiceAplicacao);
				} else {
					aplicacaoRequest = new ExecutorAplicacaoRequest();
					aplicacaoRequest.setIdUsuarioAplicacao(idUsuarioAplicacao);
					
					Recurso recurso = new Recurso();
					recurso.setIdRecurso(usuarioAplicacao.getIdRecurso());
					recurso.consultar(recurso);
					
//					recurso.setBase("http://search.twitter.com");
//					recurso.setPath("search.atom");
					
					String separador = "";
					if (recurso.getBase().substring(recurso.getBase().length() - 1) != "/") {
						separador = "/";
					}
	
					aplicacaoRequest.setUrl(recurso.getBase() + separador + recurso.getPath());
					aplicacaoRequest.setMetodo(recurso.getMetodo());
				}
	
				String usuarioParam = request.getParameter("usuario");
				String senhaParam = request.getParameter("senha");
	
				if (usuarioParam != null && !usuarioParam.equals("undefined") && senhaParam != null
						&& !senhaParam.equals("undefined")) {
					aplicacaoRequest.setUsuario(usuarioParam);
					aplicacaoRequest.setSenha(senhaParam);
				}
				
				// Pegar parâmetros
				List<Parametro> parametroLista = Parametro.consultarPorUsuarioAplicacao(usuarioAplicacao
						.getIdUsuarioAplicacao());
				
				String[] arrParamValor = request.getParameter("arrParamValor").split(",");
				
				// Adicionar parâmetros para executor
				aplicacaoRequest.getParametros().clear();
				for (int i = 0; i < parametroLista.size(); i++) {
					aplicacaoRequest.getParametros().add(
							new NameValuePair(parametroLista.get(i).getName(),
									arrParamValor[i]));
				}
				
//				aplicacaoRequest.getParametros().add(new NameValuePair("q", "web"));
	
			} catch (Exception e) {
				request.setAttribute("msg", "Não foi possível executar aplicação.");
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			ExecutorAplicacao executor = new ExecutorAplicacao();
			String[] resultado = executor.executaAplicação(aplicacaoRequest);

			if (Integer.parseInt(resultado[0]) == HttpStatus.SC_UNAUTHORIZED) {
				// Abrir janela de login
				request.setAttribute("msg", "login");
			} else {
				String retorno = "";

				try {
					// JSON - Converte para XML
					if (resultado[1].startsWith("{")) {
						JSONObject obj = new JSONObject(resultado[1]);
						resultado[1] = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><resultado>" + XML.toString(obj) + "</resultado>";
					}

					if (resultado[1].startsWith("<?xml")) {
						StringWriter xmlBuffer = new StringWriter();
						xmlBuffer.write(resultado[1].replace("UTF-8", "iso-8859-1").replaceAll("\\n", ""));

						ByteArrayInputStream xmlParseInputStream = new ByteArrayInputStream(xmlBuffer.toString().getBytes());

						TransformerFactory tFactory = TransformerFactory.newInstance();
						InputStream xslInput = getClass().getResourceAsStream("/xmlOutput.xsl");

						Transformer transformer = tFactory.newTransformer(new StreamSource(xslInput));

						ByteArrayOutputStream byte1 = new ByteArrayOutputStream();

						transformer.transform(new StreamSource(xmlParseInputStream), new StreamResult(byte1));

						retorno = byte1.toString();
						
						// TODO - Tentar converter para HTML. Verificar o uso do ROME ou SyndFeed aqui.
					}
					else {
						retorno = resultado[1];
					}

					request.setAttribute("msg", retorno);

				} catch (TransformerException e) {
					retorno = "Erro ao montar visualização do retorno.";
				} catch (JSONException e) {
					retorno = "Erro ao converter retorno para XML.";
				} finally {
					request.setAttribute("msg", retorno);
				}
			}

			// Atualiza na lista da sessão
			aplicacaoRequestLista.remove(aplicacaoRequest);
			aplicacaoRequestLista.add(aplicacaoRequest);
			session.setAttribute("aplicacaoRequestLista", aplicacaoRequestLista);

			request.getRequestDispatcher("pages/empty.jsp").forward(request, response);

			break;
		}
		}
	}

	@SuppressWarnings("unchecked")
	private List<ExecutorAplicacaoRequest> getAplicacaoRequestLista(HttpSession session) {
		List<ExecutorAplicacaoRequest> aplicacaoRequestLista = (List<ExecutorAplicacaoRequest>) session
				.getAttribute("aplicacaoRequestLista");

		if (aplicacaoRequestLista == null) {
			aplicacaoRequestLista = new ArrayList<ExecutorAplicacaoRequest>();
		}

		return aplicacaoRequestLista;
	}

	private int getIndiceAplicacao(List<ExecutorAplicacaoRequest> aplicacaoRequestLista, Long idUsuarioAplicacao) {
		int retValue = -1;

		for (int i = 0; i < aplicacaoRequestLista.size(); i++) {
			ExecutorAplicacaoRequest aplicacaoRequest = aplicacaoRequestLista.get(i);

			if (aplicacaoRequest.getIdUsuarioAplicacao().equals(idUsuarioAplicacao)) {
				retValue = i;
				break;
			}
		}

		return retValue;
	}

}
