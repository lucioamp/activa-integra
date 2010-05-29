package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import modelo.AlunoAula;
import modelo.AlunoCurso;
import modelo.Ambiente;
import modelo.ArquivoAula;
import modelo.Aula;
import modelo.Curso;
import modelo.Membro;
import modelo.Servico;
import modelo.Usuario;
import util.Ferramenta;
import util.ModuloEvento;
import util.SubstituiString;

/**
 * Servlet implementation class MembroCursoServlet
 */
public class MembroCursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroCursoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroCurso");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroCurso");
		
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
		
		Membro membro = new Membro();
		membro.setPkUsuario(usuario.getPkUsuario());

		switch(opcao)
		{
			case 'A':
			{
				Long pkCurso = Long.parseLong(request.getParameter("id"));
				String nome = request.getParameter("nome");
				String desc = request.getParameter("desc");
				
				Curso curso = new Curso();
				curso.setPkServico(pkCurso);
				Servico servico = new Servico();
				servico.setPkServico(pkCurso);
				
				try {
					curso.consultar();
					servico.consultar();
					
					//curso.alterar();
					servico.setNome(nome);
					servico.setDescricao(desc);
					servico.alterar();
					
					Aula aula = new Aula();
					ArrayList<HashMap<String, String>> listaAulas = Ferramenta.stringToList(request.getParameter("aulas"));
					for(HashMap<String, String> _aula:listaAulas)
					{
						aula.setAssunto(_aula.get("assunto"));
						aula.setAula(_aula.get("desc"));
						aula.setCurso(curso);
						aula.setPeso(Long.parseLong(_aula.get("peso")));
						aula.setData(Ferramenta.formatarData(_aula.get("data"), false));
						
						if(_aula.get("virtual") != null && _aula.get("virtual").equals("true"))
						{
							File file = new File(ambiente.getEnderecoArquivo()+"/aula/"+Ferramenta.md5(Long.toString(aula.incluir())));  
							file.mkdir();
						}else if(_aula.get("alterar") != null && _aula.get("alterar").equals("true"))
						{
							aula.setPkAula(Long.parseLong(_aula.get("id")));
							aula.alterar();
						}else if(_aula.get("deletar") != null && _aula.get("deletar").equals("true"))
						{
							aula.setPkAula(Long.parseLong(_aula.get("id")));
							aula.excluir();
						}
					}
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;				
			}
			
			case 'C': // Consulta
			{
				String tipo = request.getParameter("tipo");
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("detalheCurso"))
				{
					Long pkCurso = Long.parseLong(request.getParameter("pkCurso"));
					Curso curso = new Curso();
					curso.setPkServico(pkCurso);
					
					try {
						curso.consultar();
						
						request.setAttribute("curso", curso);
						request.setAttribute("aulas", Aula.consultarPorCurso(curso));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/curso/detalhe.jsp").forward(request, response);				
				}else if(tipo.equals("alterarCurso"))
				{
					Long pkCurso = Long.parseLong(request.getParameter("pkCurso"));
					Curso curso = new Curso();
					curso.setPkServico(pkCurso);
					
					try {
						curso.consultar();
						
						request.setAttribute("curso", curso);
						request.setAttribute("aulas", Aula.consultarPorCurso(curso));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/curso/alterar.jsp").forward(request, response);
				}else if(tipo.equals("paginaCursoAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/curso/aula/alterar.jsp").forward(request, response);
				else if(tipo.equals("incluirCurso"))
				{
					try {
						//request.setAttribute("metas", Meta.consultarPorAmbiente(ambiente));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/curso/incluir.jsp").forward(request, response);	
				}else if(tipo.equals("principal"))
				{
					try {
						request.setAttribute("membro", usuario);
						request.setAttribute("cursos", Curso.consultarCursosPorAmbiente(ambiente));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					request.getRequestDispatcher("pages/restrito/servicos/curso/principal.jsp").forward(request, response);
				}
				
				break;
			}
			
			case 'E':
			{
				long pkCurso = Long.parseLong(request.getParameter("pkCurso"));
				
				Curso curso = new Curso();
				curso.setPkServico(pkCurso);
				
				Servico servico = new Servico();
				servico.setPkServico(pkCurso);
				
				Aula aula = new Aula();
				aula.setCurso(curso);
				
				AlunoAula alunoAula = new AlunoAula();
				alunoAula.setAula(aula);
				
				AlunoCurso alunoCurso = new AlunoCurso();
				alunoCurso.setCurso(curso);
				try {
					alunoAula.excluirPorAula();
					aula.excluirPorCurso();
					alunoCurso.excluirPorCurso();
					curso.excluir();
					servico.excluir();
					
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					System.out.println(e);
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case 'I':
			{		
				DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				Servico servico = new Servico();
				servico.setAmbiente(ambiente);
				servico.setAutomatico("N");
				servico.setData(formatoData.format(new Date(System.currentTimeMillis())).toString());
				servico.setDescricao(request.getParameter("desc"));
				servico.setImagem("");
				servico.setMembro(membro);
				servico.setNome(request.getParameter("nome"));
				servico.setStatus("A");
				
				Curso curso = new Curso();
				curso.setDataLiberacao(formatoData.format(new Date(System.currentTimeMillis())).toString());
				
				try {
					curso.setPkServico(servico.incluir());
					curso.incluir();
					
					Aula aula = new Aula();
					ArrayList<HashMap<String, String>> listaAulas = Ferramenta.stringToList(request.getParameter("aulas"));
					for(HashMap<String, String> _aula:listaAulas)
					{
						aula.setAssunto(_aula.get("assunto"));
						aula.setAula(_aula.get("desc"));
						aula.setCurso(curso);
						aula.setPeso(Long.parseLong(_aula.get("peso")));
						aula.setPkAula(aula.incluir());
						aula.setData(Ferramenta.formatarData(_aula.get("data"), false));
						
						File file = new File(ambiente.getEnderecoArquivo()+"/aula/"+Ferramenta.md5(Long.toString(aula.getPkAula())));  
						file.mkdir();
					}
					
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
			
			case '0': // Enviar arquivo pra aula
			{
				String tipo = request.getParameter("tipo");
				String codigo = request.getParameter("codigoAula");

				ArquivoAula arquivoAula = new ArquivoAula();
				arquivoAula.getAula().setPkAula(Long.parseLong(codigo));
				
				if(tipo.equals("enviar"))
				{
					try {
						DiskFileUpload fu = new DiskFileUpload();
						
						@SuppressWarnings ("unchecked")
						List fileItems = fu.parseRequest(request);
						
						@SuppressWarnings ("unchecked")
						Iterator i = fileItems.iterator();
						
						FileItem fi = (FileItem)i.next();
						
						InputStream in = fi.getInputStream();
						
						String nome[] = fi.getName().split("\\\\s*");
						
						String nomeArquivo = "";
						
						for(int j=0;j<nome.length;j++){
							if((j+1) == nome.length){
								nomeArquivo = SubstituiString.removeAcentuacao(nome[j].trim().toLowerCase());
							}
						}
						
						File file = new File(ambiente.getEnderecoArquivo()+"/aula/"+Ferramenta.md5(codigo));  
						if(!file.exists())
							file.mkdir();
						
						file = new File(ambiente.getEnderecoArquivo()+"/aula/"+Ferramenta.md5(codigo)+"/"+nomeArquivo);
						
						FileOutputStream fos = new FileOutputStream(file);
						
						int c;
						while((c = in.read()) != -1)
							fos.write(c);
						
						fos.close();
						System.out.println("arquivo salvo: "+ambiente.getEnderecoArquivo()+fi.getName());
						arquivoAula.setNome(Ferramenta.md5(codigo)+"/"+nomeArquivo);
						
						request.setAttribute("msg", arquivoAula.incluir()+":"+Ferramenta.md5(codigo)+"/"+nomeArquivo);
					}catch(Exception e) {
						System.out.println(e.toString());
						request.setAttribute("msg", "false");
					}
					
					request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				}
				break;
			}
			
			
			case '1': // Cadastrar aluno no curso.
			{
				Long codigoCurso = Long.parseLong(request.getParameter("codigoCurso"));
				Long codigoAluno = Long.parseLong(request.getParameter("codigoAluno"));
				
				AlunoCurso alunoCurso = new AlunoCurso();
				alunoCurso.getAluno().setPkUsuario(codigoAluno);
				alunoCurso.getCurso().setPkServico(codigoCurso);
				
				try {
					alunoCurso.incluir();
					request.setAttribute("msg", "true");
				} catch (Exception e) {
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case '3': // Excluir arquivo pra aula
			{
				Long codigo = Long.parseLong(request.getParameter("codigoArquivo"));

				ArquivoAula arquivoAula = new ArquivoAula();
				arquivoAula.setPkArquivoAula(codigo);
				try {
					arquivoAula.consultar();
					
					File file = new File(ambiente.getEnderecoArquivo()+"aula/"+arquivoAula.getNome());
					file.delete();
					arquivoAula.excluir();
					request.setAttribute("msg", "true");					
				} catch (Exception e) {
					System.out.println(e.toString());
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				
				break;
			}
		}
		
		eventos.removerEvento(opcao, 500);
	}

}
