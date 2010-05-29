package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import modelo.Ambiente;
import modelo.Bairro;
import modelo.ContatoUsuario;
import modelo.CursoExtensao;
import modelo.Membro;
import modelo.MembroAreaInteresse;
import modelo.MembroIdioma;
import modelo.Municipio;
import modelo.Uf;
import modelo.Usuario;
import util.CadastroException;
import util.Ferramenta;
import util.ModuloEvento;
import util.SubstituiString;

/**
 * Servlet implementation class MembroServlet
 */
public class MembroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroServlet() {
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
		Usuario usuarioSession = (Usuario)session.getAttribute("membro");
		if(usuarioSession == null)
			return;
		
		ModuloEvento eventos = usuarioSession.getModuloPorNome("membro");
		if(eventos == null)
			eventos = usuarioSession.adicionarModulo("membro");
		
		
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
		
		Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");
		
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
			case 'A':
			{
				Long pkBairro = Long.parseLong(request.getParameter("pkBairro"));
				Long pkInstituicao = Long.parseLong(request.getParameter("pkInstituicao"));
				
				Long numeroLogradouro = (
					request.getParameter("numero").length() == 0 ?
						0
					:
						Long.parseLong(request.getParameter("numero"))
				);
			
				usuarioSession.setApelido(request.getParameter("apelido"));
				usuarioSession.getBairro().setPkBairro(pkBairro);
				usuarioSession.setCep(request.getParameter("cep"));
				usuarioSession.setComplemento(request.getParameter("complemento"));
				usuarioSession.setCpf(Ferramenta.formatarCpf(request.getParameter("cpf")));
				usuarioSession.setDataNasc(Ferramenta.formatarData(request.getParameter("dataNasc"), false));
				usuarioSession.setEmail(request.getParameter("email"));
				usuarioSession.getInstituicao().setPkInstituicao(pkInstituicao);
				usuarioSession.setLogradouro(request.getParameter("logradouro"));
				usuarioSession.setNome(request.getParameter("nome"));
				usuarioSession.setNumero(numeroLogradouro);
				usuarioSession.setPerguntaChave(request.getParameter("perguntaChave"));
				usuarioSession.setRespostaChave(request.getParameter("respostaChave"));
				usuarioSession.setSenha(request.getParameter("senha"));
				usuarioSession.setTipoLogradouro(request.getParameter("tipoLogradouro"));
				
				Membro membro = new Membro();
				membro.setPkUsuario(usuarioSession.getPkUsuario());

				ContatoUsuario contatoUsuario = new ContatoUsuario();
				request.setAttribute("msg", "true");
				try {
					membro.consultar();
					membro.getFormacaoAcademica().setPkFormacaoAcademica(Long.parseLong(request.getParameter("formacaoAcademica")));
					membro.alterar();
					
					usuarioSession.alterar();
					
					usuarioSession.getBairro().consultar();
					
					contatoUsuario.getUsuario().setPkUsuario(usuarioSession.getPkUsuario());
					
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
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
			
			case 'C': // Consultar
			{
				String tipo = request.getParameter("tipo");
				if(tipo == null)
				{
					try {
						Membro membro = new Membro();
						membro.setPkUsuario(usuarioSession.getPkUsuario());
						request.setAttribute("membro", membro.consultar());
						request.setAttribute("contatos", ContatoUsuario.consultarPorUsuario(usuarioSession));
					} catch (CadastroException e) {}
					request.getRequestDispatcher("pages/restrito/servicos/perfil/usuario.jsp").forward(request, response);				
				}else if(tipo.equals("detalhe"))
				{
					Long pkMembro = Long.parseLong(request.getParameter("pkMembro"));
					Membro membro = new Membro();
					membro.setPkUsuario(pkMembro);
					
					Usuario usuario = new Usuario();
					usuario.setPkUsuario(pkMembro);
					
					MembroIdioma membroIdioma = new MembroIdioma();
					membroIdioma.setMembro(membro);
					
					MembroAreaInteresse areas = new MembroAreaInteresse();
					areas.setMembro(membro);
					
					try {
						request.setAttribute("usuario", usuario.consultar());
						request.setAttribute("membro", membro.consultar());
						request.setAttribute("idiomas", membroIdioma.consultarPorMembro(membro));
						request.setAttribute("interesses", areas.consultarPorMembro(membro));
						request.setAttribute("cursos", CursoExtensao.consultarPorMembro(membro));
					} catch (CadastroException e) {
						System.out.println(e.toString());						
					}
					request.getRequestDispatcher("pages/restrito/servicos/membro/detalhe.jsp").forward(request, response);		
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
			
			case 'I':
			{
				String tipo = request.getParameter("tipo");
				
				if(tipo.equals("enviar"))
				{
					try {
						DiskFileUpload fu = new DiskFileUpload();
						
						@SuppressWarnings("unchecked")
						List fileItems = fu.parseRequest(request);
						
						@SuppressWarnings("unchecked")
						Iterator i = fileItems.iterator();
						
						FileItem fi = (FileItem)i.next();
						
						InputStream in = fi.getInputStream();
						
						String nome[] = fi.getName().split("\\\\s*");
						
						String nomeArquivo = "";
						
						for(int j=0;j<nome.length;j++){
							if((j+1) == nome.length){
								nomeArquivo = SubstituiString.removeAcentuacao(nome[j].trim().toLowerCase());
								String[] vetor = nomeArquivo.split("\\.");
								nomeArquivo = Ferramenta.md5(usuarioSession.getNome()+usuarioSession.getPkUsuario())+"."+vetor[1];
							}
						}
						
						/*File file = new File(ambiente.getEnderecoArquivo()+"/"+usuarioSession.getImagem());
						if(file.exists())
							file.delete();
						*/
						usuarioSession.setImagem(nomeArquivo);
						usuarioSession.alterarImagem();
						
						File file = new File(ambiente.getEnderecoArquivo()+"/fotomembro/"+nomeArquivo);
						
						FileOutputStream fos = new FileOutputStream(file);
						
						int c;
						while((c = in.read()) != -1)
						fos.write(c);
						
						fos.close();
						System.out.println("arquivo salvo: "+ambiente.getEnderecoArquivo()+fi.getName());
						
						request.setAttribute("msg", "true");
					}catch(Exception e) {
						System.out.println(e.toString());
						request.setAttribute("msg", "false");
					}
					
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}
}
