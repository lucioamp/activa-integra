package nucleo.output;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import nucleo.nivel.NivelAvance;
import nucleo.persist.NivelAvancePersist;
import nucleo.lista.ListaNivelAvance;

import util.banco.ExcecaoBanco;

import viecili.jrss.generator.RSSFeedGeneratorImpl;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.Item;
import viecili.jrss.generator.elem.RSS;

abstract public class GeraSaidaAvance extends GeraSaida 
{
	protected int numNivel;
	static protected String nomeNivel[] = { "Curso", "Disciplina", "Turma" };
	
	public GeraSaidaAvance()
	{
	}
	
	public String getIdSessao()
	{
		return contexto.getStrNode("idSessao");
	}
	
	abstract public int getNumNivel();
	abstract public int getIdSuperior();
	
	/*
	 * Monta a url rest de acordo com o n�vel
	 */
	public String montaURLBase()
	{
		String url;
		
		url = getUrlApp() + "/IesAvance/rest/" + getIdSessao() + "/curso";
		for ( int i = 0; i < getNumNivel(); i++ )
			url += "/" + contexto.getIntNode("id" + nomeNivel[i]);
		
		return url;
	}
	
	/*
	 * Cria��o do Canal do RSS, dependente do tipo de Nivel
	 */
	abstract public Channel criaCanal(int idSup, String urlBase);

	/*
	 * Retorna a lista de objetos do n�vel, dependente do tipo de Nivel
	 */
	abstract public NivelAvancePersist getNivelPersist();

	/*
	 * Retorna a lista de objetos do n�vel, dependente do tipo de Nivel
	 */
	abstract public NivelAvance criaObjNivel(int idUsuario, int idSup);

	/*
	 * Cria��o de um item no RSS, gen�rico mas pode ser personalizada pelo n�vel
	 */
	public Item criaItem(NivelAvance obj, String urlBase)
	{
		Item novo;
		String urlItem;

		urlItem = urlBase + "/" + obj.getId();

		novo = new Item(obj.getNome(), urlItem, "-");
		
		novo.setAuthor(obj.getNomeResponsavel());
		novo.setPubDate(obj.getDataCadastro());
		
		return novo;
	}
	
	/*
	 * Listar gen�rico devolvendo RSS
	 */
	public String listar()
	{
		// Gera��o de RSS
		Item item;
		Channel canal;
		RSS rssListar;
		RSSFeedGeneratorImpl gerador;

		// Vari�veis do contexto
		int idSup; 					// Nivel Superior
		NivelAvancePersist persist;
		String urlBase, saida;
		ListaNivelAvance lisNivelAtual;	// Nivel Atual

		saida = null;
		
		/*
		 * Obtem o contexto da requisi��o
		 */
		idSup = getIdSuperior();
		urlBase = montaURLBase();

		/*
		 * Cria o canal de acordo com o n�vel
		 */
		canal = criaCanal(idSup, urlBase);
		canal.setLanguage("pt");

		/*
		 * Obtem a lista de objetos do n�vel
		 */
		persist = getNivelPersist();
		if ( persist == null )
			return saida;
		
		try
		{
			lisNivelAtual = persist.Lista(idSup);
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude recuperar a lista de objetos: " + 
					 e.getMessage() );
			return saida;
		}
		
		if ( lisNivelAtual.size() > 0 ) 
		{
			/*
			 * Percorre a lista de objetos criando um �tem para cada um
			 */
			for (int i = 0; i < lisNivelAtual.size(); i++)
			{
				item = criaItem(lisNivelAtual.get(i), urlBase);
				canal.addItem(item);
			}
		}

		/*
		 * Cria o link para incluir um objeto novo
		 */
		item = new Item("Novo(a)", urlBase + "/form", "");
		canal.addItem( item );

		urlBase = getUrlApp() + "/IesAvance/rest/" + getIdSessao() + "/curso";
		for ( int i = 0; i < getNumNivel(); i++ )
		{
			item = new Item(nomeNivel[i] + "s", urlBase, "");
			canal.addItem( item );
			urlBase += "/" + contexto.getIntNode("id" + nomeNivel[i]);
		}
		
		/*
		 * Prepara o gerador de RSS
		 */
		rssListar = new RSS();
		rssListar.addChannel(canal);

		/*
		 * Gera��o do RSS
		 */
		gerador = new RSSFeedGeneratorImpl();
		
		try
		{
			/*
			 * Pode ser usado para depurar o RSS gerado.
			 * 
			 * gerador.generateToFile(rssListar, new java.io.File("d:\\tmp\\saida.xml"));
			 */
			saida = gerador.generateAsString(rssListar);
			// System.out.println(saida);
			
		}
		catch ( IOException e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude gerar o RSS: " + e.getMessage() );
		}
		
		return saida;
	}
	
	/*
	 * Inserir gen�rico devolvendo RSS
	 */
	public String inserir()
	{
		NivelAvance novo;
		NivelAvancePersist persist;
		int idSup, idUsuario;
		
		/*
		 * Verifica a permiss�o de cria��o
		 */
		if ( !podeInserir() )
			return null;
		
		idUsuario = getIdUsuario();
		idSup = getIdSuperior();
		
		novo = criaObjNivel(idUsuario, idSup);
		if ( novo == null )
			return null;

		persist = getNivelPersist();
		if ( persist == null )
			return null;

		try
		{
			persist.Inclui(novo);
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude incluir o novo elemento: " + 
					 e.getMessage() );
			return null;
		}

		return listar();
	}

	/*
	 * Alterar gen�rico devolvendo RSS
	 */
	public String alterar()
	{
		setErro( HttpServletResponse.SC_NOT_IMPLEMENTED,
				 "Alterar ainda nao implementado!" );
		return "";
	}

	/*
	 * Remover gen�rico devolvendo RSS
	 */
	public String remover()
	{
		setErro( HttpServletResponse.SC_NOT_IMPLEMENTED,
				 "Remover ainda nao implementado!" );
		return "";
	}

	/*
	 * Remover Tudo gen�rico devolvendo RSS
	 */
	public String removerTudo()
	{
		setErro( HttpServletResponse.SC_NOT_IMPLEMENTED,
				 "Remover Tudo ainda nao implementado!" );
		return "";
	}
}
