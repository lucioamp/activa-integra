package nucleo.output;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import nucleo.nivel.Turma;
import nucleo.nivel.NivelAvance;
import nucleo.persist.TurmaPersist;
import nucleo.persist.NivelAvancePersist;

import nucleo.usuario.Usuario;
import nucleo.usuario.UsuarioPersist;

import util.banco.ExcecaoBanco;

import viecili.jrss.generator.RSSFeedGeneratorImpl;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.Item;
import viecili.jrss.generator.elem.RSS;

import java.text.SimpleDateFormat;

public class GeraSaidaTurma extends GeraSaidaAvance 
{
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#getNomeNivel()
	 */
	public String getNomeNivel() 
	{
		return "turma";
	}

	public int getNumNivel()	{ return 2;										}
	public int getIdSuperior()	{ return contexto.getIntNode("idDisciplina");	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaidaAvance#criaCanal(int, java.lang.String)
	 */
	public Channel criaCanal(int idSup, String urlBase)
	{
		String nome, desc;
		
		nome = "Turmas";
		desc = "Turmas da Disciplina " + idSup;
		
		return new Channel(nome, urlBase, desc);
	}

	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaidaAvance#getNivelPersist()
	 */
	public NivelAvancePersist getNivelPersist()
	{
		try
		{
			return new TurmaPersist();
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude acessar a Turma no banco: " + 
					 e.getMessage() );
		}

		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see forum.output.GeraSaidaForum#criaObjNivel(int, int)
	 */
	public NivelAvance criaObjNivel(int idUsuario, int idSup)
	{
		int idProf;
		String idStr, dataStr;
		String nome = "";
		Date dataInicio;
		Date dataFinal;

		SimpleDateFormat sdf;
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try 
		{
			nome = URLDecoder.decode(getParameter("nome"), "ISO-8859-1");
			dataStr = getParameter("dataInicio");
			// extrai a data
			dataInicio = sdf.parse(dataStr);
			
			dataStr = getParameter("dataFinal");
			// extrai a data
			dataFinal = sdf.parse(dataStr);

			idStr = getParameter("professor");
		}
		catch (Exception e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro ao decodificar parametro: " + e.getMessage());
			return null;
		}
		
		try 
		{
			idProf = Integer.parseInt(idStr);
		}
		catch ( Exception e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro ao obter o id do professor: " + e.getMessage());
			return null;
		}
		
		return new Turma( nome, idUsuario, dataInicio, dataFinal, idProf, -1, idSup);
	}
	
	/*
	 * (non-Javadoc)
	 * @see forum.output.GeraSaidaForum#podeInserir()
	 */
	public boolean podeInserir()
	{
		return true;
	}
	
	public String obtemForm()  
	{
		if ( podeInserir() )
		{
    		String listaProf;
    		String []professor;
    		String []linhaProf;
			String campos, estrutura, itens;

			try 
			{
				listaProf = UsuarioPersist.ListaUsuario( "EhProfessor" );
			}
			catch (ExcecaoBanco e) 
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Nao pude ler a lista de usuarios: " + e.getMessage());
				return null;
			}
			
    		linhaProf = listaProf.split(";");
    		
    		itens = "";
    		for ( int i = 0; i < linhaProf.length; i++ ) 
    		{
    			professor = linhaProf[i].split(",");
    			itens += criarItem(professor[2], professor[0]);
    		}

			estrutura = "<f_nome/>\n<f_dataInicio/>\n<f_dataFinal/>\n<f_professor/>\n";
			
			// Ementa está por enquanto como inputtext mas tem de ser uma textarea
			campos = criarInputText("Nome da Turma:", "f_nome") +
			         criarInputText("Data Início:", "f_dataInicio") +
			         criarInputText("Data Final:", "f_dataFinal") +
			         criarComboBox("Professor:", "f_professor", itens);
			
			return criaFormXForms( "CriarTurma", montaURLBase(), estrutura, campos );
		}
		
		return null; 
	}
	
	public String exibeTurmas()
	{
		Usuario usuarioBanco;
		UsuarioPersist usuPersist; 
		
		try 
		{
			usuPersist = new UsuarioPersist();
			
			usuarioBanco = usuPersist.Busca( getIdUsuario() );
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Erro ao recuperar informacoes do usuario: " +
					 e.getMessage() );
			return null;
		}

		if ( usuarioBanco == null )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Usuario nao encontrado!" );
			return null;
		}
		
		return exibeTurmas( getIdSessao(), getIdUsuario(), usuarioBanco.getNomeUsuario() );
	}
	
	public String exibeTurmas( String idSessao, int idUsuario, String nomeUsuario)
	{
		Item item;
		RSS rssListar;
		String str;
		String turma[];
		String turmaVet[];
		String urlTurma;
		String urlBase, desc;
		String saida = null;
		
		urlBase = getUrlApp() + "/IesAvance/rest/" + idSessao + "/" + getNomeNivel();
		desc = "Turmas do Usuario " + nomeUsuario;
		
		Channel channel = new Channel("Listagem de Turmas", urlBase, desc);
		
		channel.setLanguage("pt");
	
		try 
		{
			str = TurmaPersist.ListaTurma( idUsuario );
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude recuperar a lista de objetos: " + 
					 e.getMessage() );
			return saida;
		}

		// Se o usuário TEM alguma turma
		if ( str.length() != 0 )
		{
			turmaVet  = str.split(";");
			
			for (int i = 0; i < turmaVet.length; i++)
			{
				turma = turmaVet[i].split(",");
				urlTurma = urlBase + "/" + turma[0];
				item = new Item(turma[1], urlTurma, "");
				
				item.setAuthor(turma[2]);
				
				channel.addItem(item);
			}
		}
		
		rssListar = new RSS();
		rssListar.addChannel(channel);
		
		RSSFeedGeneratorImpl generator = new RSSFeedGeneratorImpl();
		
		try 
		{
			saida = generator.generateAsString(rssListar);
		}
		catch ( IOException e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude gerar o RSS: " + e.getMessage() );
		}
		
		return saida;
	}
}
