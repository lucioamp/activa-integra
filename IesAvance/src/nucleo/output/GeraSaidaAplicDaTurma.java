package nucleo.output;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

import util.banco.ExcecaoBanco;

import nucleo.usuario.SessaoPersist;
import nucleo.nivel.Aplicacao;
import nucleo.nivel.AplicacaoDaTurma;
import nucleo.persist.AplicacaoDaTurmaPersist;
import nucleo.nivel.NivelAvance;
import nucleo.persist.NivelAvancePersist;
import nucleo.persist.AplicacaoPersist;

import viecili.jrss.generator.RSSFeedGeneratorImpl;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.Item;
import viecili.jrss.generator.elem.RSS;

public class GeraSaidaAplicDaTurma extends GeraSaidaAvance 
{
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#getNomeNivel()
	 */
	public String getNomeNivel() 
	{
		return "aplicacao";
	}

	public int getNumNivel()	{ return 3;									}
	public int getIdSuperior()	{ return contexto.getIntNode("idTurma");	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaidaAvance#criaCanal(int, java.lang.String)
	 */
	public Channel criaCanal(int idSup, String urlBase)
	{
		String nome, desc;
		
		nome = "Aplicações da Turma";
		desc = "Aplicações disponíveis para os membros da Turma";
		
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
			return new AplicacaoDaTurmaPersist();
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude acessar o Tema no banco: " + 
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
		return null;
	}
	
	/*
	 * Inserir especializado devolvendo RSS
	 */
	public String inserir()
	{
		int idSup;
		int idAplic;
		String aplica[];
		NivelAvancePersist persist;
		
		/*
		 * Verifica a permissão de criação
		 */
		if ( !podeInserir() )
			return null;
		
		idSup = getIdSuperior();
		
		persist = getNivelPersist();
		if ( persist == null )
			return null;
		
		aplica = getParameterValues("aplica");

		for (int i = 0; i < aplica.length; i++)
		{
			if ( !aplica[i].equals("off") )
			{
				try 
				{
					idAplic = Integer.parseInt(aplica[i]);
				}
				catch ( Exception e )
				{
					setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							 "Erro ao obter o id da aplicacao: " + e.getMessage() );
					return null;
				}
				
				try
				{
					AplicacaoDaTurma nova;
					
					nova = new AplicacaoDaTurma("", getIdUsuario(), idAplic, idSup);

					persist.Inclui(nova);
				}
				catch ( ExcecaoBanco e )
				{
					setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							 "Nao pude incluir o novo elemento: " + 
							 e.getMessage() );
					return null;
				}
			}
		}

		return listar();
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
    		String listaAplic;
    		String []aplica;
    		String []linhaAplic;
			String campos, estrutura;

			try 
			{
				listaAplic = AplicacaoPersist.Lista();
			}
			catch (ExcecaoBanco e) 
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Nao pude ler a lista de aplicacoes: " + e.getMessage());
				return null;
			}
			
			linhaAplic = listaAplic.split(";");

			estrutura = "<f_aplica/>\n";
			
			campos = "";
    		for ( int i = 0; i < linhaAplic.length; i++ ) 
    		{
    			aplica = linhaAplic[i].split(",");
    			campos += criarCheckbox("", "f_aplica",  criarItem(aplica[1], aplica[0]));
    		}
			
			return criaFormXForms( "CriarAplicacaoDaTurma", montaURLBase(), estrutura, campos );
		}
		
		return null; 
	}
	
	public String loginTurma()
	{
		Item item;
		RSS rssListar;
		String str;
		String img;
		
		String idAplic;
		String url;
		String nome;
		String feed;
		String externa;
		String urlAplica;
		String aplicacao[];
		String aplicaVet[];


		String urlBase, desc;
		String saida = null;
		
		int idTurma; 
		SessaoPersist sesPersist;
		
		idTurma = contexto.getIntNode("idTurma");
		
		try
		{
			sesPersist = new SessaoPersist();
			sesPersist.AtualizaTurma( getIdSessao(), idTurma );
		}
		catch ( ExcecaoBanco e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Nao pude registrar turma na sessao: " + 
					 e.getMessage() );
			return saida;
		}
		 
		 
		urlBase = getUrlApp() + "/IesAvance/rest/" + getIdSessao() + "/turma/" + idTurma;
		desc = "Aplicações disponíveis para os membros da Turma";
		
		Channel channel = new Channel("Página da Turma", urlBase, desc);
		
		channel.setLanguage("pt");
	
		try 
		{
			str = AplicacaoDaTurmaPersist.Listar(idTurma);
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude recuperar a lista de aplicacoes: " + 
					 e.getMessage() );
			return saida;
		}

		aplicaVet  = str.split(";");
		
		for (int i = 0; i < aplicaVet.length; i++)
		{
			aplicacao = aplicaVet[i].split(",");
			idAplic = aplicacao[0];
			nome = aplicacao[1];
			externa = aplicacao[2];
			feed = aplicacao[3];
			url =  aplicacao[4];
			
			if ( externa.equals("false") )  // Aplicação Interna
			{
				urlAplica = url + "/" + getIdSessao() + "/" + idTurma;
				desc = "Aplicação Interna";
				img = "AVANCE_AZUL_BITMAP_60px.jpg";
				desc = "<img src=\"/IesAvance/img/" + img + 
			       "\" height=\"28\" border=\"0\" align=\"left\" alt=\"Feed RSS\" hspace=\"3\"/>" + desc;
			}
			else							 
			{
				try 
				{
					url = URLEncoder.encode(url, "ISO-8859-1");
				}
				catch (Exception e)
				{
					setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							 "Erro ao codificar URL: " + e.getMessage() );
					return "";
				}

				urlAplica = "/IesAvance/Carrega?url=" + url;
				
				if ( feed.equals("false") ) {	// Aplicação Externa
					
					// desc = "Aplicação Externa";
					img = "delicious-icon.bmp";
					desc = "<img src=\"/IesAvance/img/" + img + 
				       "\" height=\"28\" border=\"0\" align=\"left\" alt=\"Feed RSS\" hspace=\"3\"/>";

					Aplicacao aplic;
					AplicacaoPersist aplicPersist;
					
					try
					{
						aplicPersist = new AplicacaoPersist();
						aplic = (Aplicacao)aplicPersist.Busca(Integer.valueOf(idAplic).intValue());

						desc += aplic.listaRecursos();
					}
					catch (ExcecaoBanco e)
					{
						setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
								 "Erro ao obter Aplicação: " + e.getMessage() );
						return "";
					}

				}
				else {						// Feed RSS Externo
					desc = "Feed Externo";
					img = "feed-icon-28x28.png";
					desc = "<img src=\"/IesAvance/img/" + img + 
				       "\" height=\"28\" border=\"0\" align=\"left\" alt=\"Feed RSS\" hspace=\"3\"/>" + desc;
				}
			}

			item = new Item(nome, urlAplica, desc);
			
			channel.addItem(item);
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
