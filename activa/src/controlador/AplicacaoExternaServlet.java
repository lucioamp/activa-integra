package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import modelo.integra.AplicacaoExterna;
import modelo.integra.Metodo;
import modelo.integra.Parametro;
import modelo.integra.ParametroJSON;
import modelo.integra.Recurso;
import modelo.integra.RecursoJSON;
import util.integra.ExcecaoParser;
import util.integra.WADLParser;

/**
 * Servlet implementation class AplicacoesExternasServlet
 */
public class AplicacaoExternaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AplicacaoExternaServlet() {
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
	
    @SuppressWarnings("unchecked")
	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("administrador");
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
					request.setAttribute("colAplicacao", AplicacaoExterna.consultarTodos());
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
							
				request.getRequestDispatcher("pages/administrador/servicos/aplicacoesExternas/index.jsp").forward(request, response);
				
				break;
			}
		
			case 'E': //Exclusão
			{
				// TODO - Não remove na primeira vez que a index é carregada
				
				long idAplicacao = Long.parseLong(request.getParameter("idAplicacao"));

				// Exclui a aplicação
				AplicacaoExterna aplicacao = new AplicacaoExterna();
				aplicacao.setIdAplicacao(idAplicacao);
				
				try {
					aplicacao.excluir();
				} catch (Exception e) {
					request.setAttribute("erro", "Não é possivel excluir esta aplicação.");
				}
				
				request.setAttribute("opcao", "C");
				executa(request, response);
				request.setAttribute("opcao", null);
				
				break;
			}
			
			case 'I': //Inclusão
			{
				String nomeAplicacao = request.getParameter("nomeAplicacao");
				String auth = request.getParameter("auth");
				String recursoTitulo = request.getParameter("recursoTitulo");
				String paramTitulo = request.getParameter("paramTitulo");
				String url = request.getParameter("url");
				Integer opcaoCarregar = Integer.valueOf(request.getParameter("opcaoCarregar"));
				
				String[] arrRecursoTitulo = recursoTitulo.split(",");
				String[] arrParamTitulo = paramTitulo.split(",");
				String[] arrAuth = auth.split(",");
				
				List<RecursoJSON> colRecurso = (ArrayList<RecursoJSON>) session.getAttribute("colRecurso");
				
				if (colRecurso != null) {
					int paramIndex = 0;
					for (int i = 0; i < colRecurso.size(); i++) {
						RecursoJSON recurso = colRecurso.get(i);
						// Atualiza o título
						recurso.setTitulo(arrRecursoTitulo[i]);
						
						for (ParametroJSON parametro : recurso.getParametros()) {
							parametro.setTitle(arrParamTitulo[paramIndex]);
							
							paramIndex++;
						}
					}
					
					// Incluir no Banco de dados
					AplicacaoExterna aplicacao = new AplicacaoExterna();
					aplicacao.setNome(nomeAplicacao);
					aplicacao.setUrl(url);
					aplicacao.setIdProcessamento(opcaoCarregar);
					
					if (Arrays.binarySearch(arrAuth, "1") >= 0) {
						aplicacao.setAuthBasica(true);
					}
					
					if (Arrays.binarySearch(arrAuth, "2") >= 0) {
						aplicacao.setAuthOpenId(true);
					}
					
					if (Arrays.binarySearch(arrAuth, "3") >= 0) {
						aplicacao.setAuthGAuth(true);
					}
					
					// Dados dos recursos
					List<Recurso> recursoList = new ArrayList<Recurso>();
					for (RecursoJSON recursoJSON : colRecurso) {
						Recurso recurso = new Recurso();
						recurso.setBase(recursoJSON.getBase());
						recurso.setPath(recursoJSON.getPath());
						recurso.setMetodo(recursoJSON.getMetodo());
						recurso.setNome(recursoJSON.getTitulo());
						
						// Dados dos parâmetros
						List<Parametro> parametroList = new ArrayList<Parametro>();
						for (ParametroJSON parametroJSON : recursoJSON.getParametros()) {
							Parametro parametro = new Parametro();

							parametro.setName(parametroJSON.getName());
							parametro.setTitle(parametroJSON.getTitle());
							parametro.setRequired(parametroJSON.isRequired());
							
							parametroList.add(parametro);
						}
						recurso.setParametros(parametroList);
						
						recursoList.add(recurso);
					}
					aplicacao.setRecursoList(recursoList);
					
					try {
						aplicacao.incluir();
					} catch (Exception e) {
						session.setAttribute("erro", "Houve erro ao incluir a aplicação.");
					}
				}
				
				break;
			}
			case 'P': //Processar URL
			{
				String url = request.getParameter("url");
				Integer opcaoCarregar = Integer.valueOf(request.getParameter("opcaoCarregar"));
				
				switch (opcaoCarregar) {
				// Carrega WDL
				case 1:
					try {
						WADLParser parser = new WADLParser();
						List<Recurso> recursos = parser.parseWADL(url);

						List<RecursoJSON> colRecurso = new ArrayList<RecursoJSON>();
						adicionarRecurso(recursos, colRecurso);
						
						session.setAttribute("colRecurso", colRecurso);

						request.setAttribute("json", colRecurso);
						request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
						
						System.out.println("Carrega WADL finalizado.");
					} catch (ExcecaoParser e) {
						System.out.println("Erro ao realizar parser do WADL.");
					}
					break;
				// Carrega por Reflexão
				case 2:
					System.out.println("Carrega por Reflexão");
					break;
				default:
					break;
				}
				
				break;
			}
		}
	}
    
    private void adicionarRecurso(List<Recurso> recursos, List<RecursoJSON> colRecurso) {
    	for (Recurso recurso : recursos) {
    		// Se não tiver filho
			if (recurso.getRecursos().size() == 0) {
				RecursoJSON recursoBean = new RecursoJSON();
				
				recursoBean.setBase(recurso.getBase());
				recursoBean.setPath(recurso.getPath());
				
				if (!recurso.getMetodos().isEmpty()) {
					Metodo metodo = recurso.getMetodos().get(0);
					
					recursoBean.setMetodo(metodo.getName());
					recursoBean.setTitulo(metodo.getTitle());
					
					// Lista de parametros
					List<ParametroJSON> parametroLista = new ArrayList<ParametroJSON>();
					for (Parametro param : metodo.getParametros()) {
						ParametroJSON parametro = new ParametroJSON();
						parametro.setName(param.getName());
						parametro.setRequired(param.isRequired());
						parametro.setTitle(param.getTitle());
						
						parametroLista.add(parametro);
					}
					
					recursoBean.setParametros(parametroLista);
				}
				
				colRecurso.add(recursoBean);
			}
			
			// Adiciona os recursos filho
			adicionarRecurso(recurso.getRecursos(), colRecurso);
		}
    }

}
