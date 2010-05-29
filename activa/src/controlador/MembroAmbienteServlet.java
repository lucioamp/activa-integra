package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import org.json.JSONObject;

import util.Ferramenta;
import util.ModuloEvento;
import util.SubstituiString;

import modelo.Ambiente;
import modelo.AmbienteTag;
import modelo.Etapa;
import modelo.Membro;
import modelo.MembroAmbiente;
import modelo.Meta;
import modelo.MetaTag;
import modelo.Tag;
import modelo.Tarefa;
import modelo.Usuario;

/**
 * Servlet implementation class MembroAmbienteServlet
 */
public class MembroAmbienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MembroAmbienteServlet() {
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
	
	ModuloEvento eventos = new ModuloEvento();
	protected void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("membro");
		if(usuario == null)
			return;
		
		ModuloEvento eventos = usuario.getModuloPorNome("membroAmbiente");
		if(eventos == null)
			eventos = usuario.adicionarModulo("membroAmbiente");
		
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
			case 'A': //Alteração
			{
				request.setAttribute("msg", "true");
				ambiente.setDescricao(request.getParameter("desc").trim());
				ambiente.setNome(request.getParameter("nome").trim());
				
				Tag tag = new Tag();
				AmbienteTag ambienteTag = new AmbienteTag();
				try {
					ambiente.alterar();
					ambienteTag.setAmbiente(ambiente);					
					
					ArrayList<HashMap<String, String>> listaTags = Ferramenta.stringToList(request.getParameter("tags"));
					for(HashMap<String, String> _tag:listaTags)
					{
						tag.setNome(_tag.get("nome"));
						tag.setDescricao(_tag.get("desc"));
						if(_tag.get("virtual") != null && _tag.get("virtual").equals("true"))
						{
							ambienteTag.getTag().setPkTag(tag.incluir());
							ambienteTag.incluir();
						}else if(_tag.get("alterar") != null && _tag.get("alterar").equals("true"))
						{
							tag.setPkTag(Long.parseLong(_tag.get("id")));
							tag.alterar();							
						}else if(_tag.get("deletar") != null && _tag.get("deletar").equals("true"))
						{
							tag.setPkTag(Long.parseLong(_tag.get("id")));
							tag.excluir();
						}
					}
										
					Meta meta = new Meta();
					MetaTag metaTag = new MetaTag();
					Etapa etapa = new Etapa();
					Tarefa tarefa = new Tarefa();
					
					meta.setAmbiente(ambiente);
					ArrayList<HashMap<String, String>> listaMetas = Ferramenta.stringToList(request.getParameter("metas"));
					ArrayList<HashMap<String, String>> listaMetaTags = Ferramenta.stringToList(request.getParameter("metaTags"));
					ArrayList<HashMap<String, String>> listaEtapas = Ferramenta.stringToList(request.getParameter("etapas"));
					ArrayList<HashMap<String, String>> listaTarefas = Ferramenta.stringToList(request.getParameter("tarefas"));
					
					long metaId = 0;
					long etapaId = 0;
					for(HashMap<String, String> _meta:listaMetas)
					{
						meta.setDescricao(_meta.get("desc"));
						meta.setDuracao(_meta.get("duracao"));
						if(_meta.get("virtual") != null && _meta.get("virtual").equals("true"))
						{
							metaId = meta.incluir();
						}else if(_meta.get("alterar") != null && _meta.get("alterar").equals("true"))
						{
							meta.setPkMeta(Long.parseLong(_meta.get("id")));
							meta.alterar();
							metaId = meta.getPkMeta();
						}else if(_meta.get("deletar") != null && _meta.get("deletar").equals("true"))
						{
							meta.setPkMeta(Long.parseLong(_meta.get("id")));
							
							Collection<MetaTag> metatags = MetaTag.consultarPorMeta(meta);
							Collection<Etapa> etapas = Etapa.consultarPorMeta(meta);
							Collection<Tarefa> tarefas;
							
							for(MetaTag _metaTag:metatags)
								_metaTag.excluir();
							
							for(Etapa _etapa:etapas)
							{
								tarefas = Tarefa.consultarPorVisao(_etapa);
								for(Tarefa _tarefa:tarefas)
									_tarefa.excluir();
								
								_etapa.excluir();
							}

							meta.excluir();
							metaId = meta.getPkMeta();
						}
						
						if(metaId > 0)
						{
							for(HashMap<String, String> _tag:listaMetaTags)
							{
								if(_tag.get("metaid").equals(_meta.get("id").toString()))
								{
									tag.setNome(_tag.get("nome"));
									tag.setDescricao(_tag.get("desc"));
									metaTag.getMeta().setPkMeta(metaId);
									if(_tag.get("virtual") != null && _tag.get("virtual").equals("true"))
									{
										metaTag.getTag().setPkTag(tag.incluir());
										metaTag.incluir();
									}else if(_tag.get("alterar") != null && _tag.get("alterar").equals("true"))
									{
										tag.setPkTag(Long.parseLong(_tag.get("id")));
										tag.alterar();							
									}else if(_tag.get("deletar") != null && _tag.get("deletar").equals("true"))
									{
										tag.setPkTag(Long.parseLong(_tag.get("id")));
										metaTag.setTag(tag);
										metaTag.excluir();
										tag.excluir();
									}
								}
							}	
							
							for(HashMap<String, String> _etapa:listaEtapas)
							{
								if(_etapa.get("metaid").equals(_meta.get("id").toString()))
								{
									etapa.setData(Ferramenta.formatarData(_etapa.get("data"), false));
									etapa.setDescricao(_etapa.get("desc"));
									etapa.getMeta().setPkMeta(metaId);
									etapa.setNome(_etapa.get("nome"));
									
									if(_etapa.get("virtual") != null && _etapa.get("virtual").equals("true"))
									{
										etapaId = etapa.incluir();
									}else if(_etapa.get("alterar") != null && _etapa.get("alterar").equals("true"))
									{
										etapa.setPkEtapa(Long.parseLong(_etapa.get("id")));
										etapa.alterar();
										etapaId = etapa.getPkEtapa();
									}else if(_etapa.get("deletar") != null && _etapa.get("deletar").equals("true"))
									{
										etapa.setPkEtapa(Long.parseLong(_etapa.get("id")));
										Tarefa.excluirPorEtapa(etapa);
										etapa.excluir();
										etapaId = etapa.getPkEtapa();
									}
									
									for(HashMap<String, String> _tarefa:listaTarefas)
									{
										if(_tarefa.get("etapaid").equals(_etapa.get("id").toString()))
										{
											tarefa.setData(Ferramenta.formatarData(_tarefa.get("data"), false));
											tarefa.setDescricao(_tarefa.get("desc"));
											tarefa.setNome(_tarefa.get("nome"));
											tarefa.getVisao().setPkEtapa(etapaId);
											
											if(_tarefa.get("virtual") != null && _tarefa.get("virtual").equals("true"))
											{
												tarefa.incluir();
											}else if(_tarefa.get("alterar") != null && _tarefa.get("alterar").equals("true"))
											{
												tarefa.setPkTarefa(Long.parseLong(_tarefa.get("id")));
												tarefa.alterar();							
											}else if(_tarefa.get("deletar") != null && _tarefa.get("deletar").equals("true"))
											{
												tarefa.setPkTarefa(Long.parseLong(_tarefa.get("id")));
												tarefa.excluir();
											}
										}
									}
								}
							}
						}
					}
				}catch (Exception e) {
					request.setAttribute("msg", "false");
					System.out.println(e.toString());
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);				
				break;
			}
		
			case 'C': //Consultar Todos
			{
				break;
			}
		
			case 'D': //Detalhe
			{
				
				try {
					request.setAttribute("ambiente", ambiente);
					request.setAttribute("tags", AmbienteTag.consultarPorAmbiente(ambiente));
					request.setAttribute("metas", Meta.consultarPorAmbiente(ambiente));
				} catch (Exception e) {
					System.out.println("Erro ao realizar o precessamento.");
				}
				
				request.getRequestDispatcher("pages/restrito/servicos/ambiente/"+
						(usuario.getPkUsuario() == ambiente.getProfessor().getPkUsuario()
							? "index" : "detalhe"
						)+".jsp").forward(request, response);
				break;
			}
			
			case 'E': //Etapas
			{
				String tipo = request.getParameter("tipo");
				Etapa etapa = new Etapa();
				etapa.setPkEtapa(Long.parseLong(request.getParameter("id")));
				
				try {
					request.setAttribute("tarefas", Tarefa.consultarPorVisao(etapa));
				}catch (Exception e) {}
				
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/etapa/alterar.jsp").forward(request, response);				
				else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/etapa/detalhe.jsp").forward(request, response);
				break;
			}
			
			case 'M': //Metas
			{
				String tipo = request.getParameter("tipo");
				
				/*if(tipo.equals("incluir"))
				{	
					try {
						JSONObject aa = new JSONObject(request.getParameter("meta"));
						System.out.println(aa.get("descricao"));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}else
				{*/
				Meta meta = new Meta();
				meta.setPkMeta(Long.parseLong(request.getParameter("id")));
				
				try {
					request.setAttribute("meta", meta.consultar());
					request.setAttribute("tags", MetaTag.consultarPorMeta(meta));
					request.setAttribute("etapas", Etapa.consultarPorMeta(meta));
				}catch (Exception e) {}
				
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
				{
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/alterar.jsp").forward(request, response);				
				}else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/detalhe.jsp").forward(request, response);
				//}
				break;
			}
			
			case 'T': //Tarefas
			{
				String tipo = request.getParameter("tipo");
				
				if(tipo.equals("altetar"))
					System.out.println("alterando");
				else if(tipo.equals("paginaAlterar"))
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/etapa/tarefa/alterar.jsp").forward(request, response);				
				else if(tipo.equals("paginaDetalhe"))
					request.getRequestDispatcher("pages/restrito/servicos/ambiente/meta/etapa/tarefa/detalhe.jsp").forward(request, response);
				break;
			}
			
			case 'I': //
			{
				try {
					if(!Membro.isProfessor(usuario))
					{
						request.setAttribute("msg", "false");
						request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				String nome = request.getParameter("nome");
				Ambiente novoAmbiente = new Ambiente();
				novoAmbiente.setNome(nome);
				novoAmbiente.setDescricao(request.getParameter("desc"));
				novoAmbiente.setImagem("");
				novoAmbiente.getProfessor().setPkUsuario(usuario.getPkUsuario());
				novoAmbiente.setData(formatoData.format(new Date(System.currentTimeMillis())).toString());
				
				
				MembroAmbiente membroAmbiente = new MembroAmbiente();
				membroAmbiente.getMembro().setPkUsuario(usuario.getPkUsuario());
				try {
					membroAmbiente.getAmbiente().setPkAmbiente(novoAmbiente.incluir());
					membroAmbiente.incluir();
					
					session.setAttribute("ambiente", novoAmbiente);
					request.setAttribute("msg", "true");					
				} catch (Exception e) {
					System.out.println(e.toString());
					request.setAttribute("msg", "false");
				}
				
				request.getRequestDispatcher("pages/empty.jsp").forward(request, response);
				break;
			}
			
			case '0': // Enviar foto
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
							String[] vetor = nomeArquivo.split("\\.");
							nomeArquivo = Ferramenta.md5(Long.toString(ambiente.getPkAmbiente()))+"."+vetor[1];
						}
					}
					
					ambiente.setImagem(nomeArquivo);
					ambiente.alterarImagem();
					
					File file = new File(ambiente.getEnderecoArquivo()+"/fotoambiente/"+nomeArquivo);
					
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
				break;
			}
			
			case '1':
			{
				try {
					File file = new File(ambiente.getEnderecoArquivo()+"/fotoambiente/"+Ferramenta.md5(Long.toString(ambiente.getPkAmbiente()))+".jpg");
					if(file.exists())
					{
						ambiente.setImagem("/");
						ambiente.alterarImagem();
						file.delete();
					}
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
