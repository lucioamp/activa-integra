package nucleo.output;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import nucleo.nivel.Aplicacao;
import nucleo.nivel.NivelAvance;
import nucleo.persist.AplicacaoPersist;
import nucleo.persist.NivelAvancePersist;

import util.banco.ExcecaoBanco;
import util.interpretador.ExcecaoParser;

import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.Item;

public class GeraSaidaAplicacao extends GeraSaidaAvance 
{
	@Override
	public String getNomeNivel() 
	{
		return "Aplicação";
	}

	public int getNumNivel()	{ return 0;		}
	public int getIdSuperior()	{ return -1;	}

	@Override
	public Channel criaCanal(int idSup, String urlBase) 
	{
		String nome, desc;
		
		nome = "Aplicação";
		desc = "Aplicações e Feeds disponíveis no sistema";
		
		return new Channel(nome, urlBase, desc);
	}

	public String montaURLBase()
	{
		String url;
		
		url = getUrlApp() + "/IesAvance/rest/" + getIdSessao() + "/aplicacao";
//		url += "/" + contexto.getIntNode("idAplic");
		
		return url;
	}

	public Item criaItem(NivelAvance obj, String urlBase)
	{
		Item novo;
		Aplicacao aplic;
		String urlItem, innerItem;
		
		aplic = (Aplicacao)obj;

		urlItem = urlBase;

		if ( aplic.isExterna()  && !aplic.isFeed() )
		{
			try
			{
				innerItem = aplic.listaRecursos();
			}
			catch ( ExcecaoBanco e )
			{
				setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						 "Nao pude acessar recursos da Aplicacao: " + 
						 e.getMessage() );
				return null;
			}			
			
			innerItem += "<br><p><form><input type='button' value='Carregar WADL' " + 
					     "onclick='abrePaginaSimples(\"" +
					  	 urlBase + "/" + obj.getId() +
			             "\", \"docDiv\");'></form></p>";
		}
		else
		{
			innerItem = "<p>" +
					    (aplic.isExterna() ? "Página de Feeds Externa" : "Aplicação Interna") + 
					    "</p>";
		}
		
		novo = new Item(obj.getNome(), urlItem, innerItem);
		
		novo.setAuthor(obj.getNomeResponsavel());
		novo.setPubDate(obj.getDataCadastro());
		
		return novo;
	}

	@Override
	public NivelAvancePersist getNivelPersist() 
	{
		try
		{
			return new AplicacaoPersist();
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude acessar a Aplicacao no banco: " + 
					 e.getMessage() );
		}

		return null;
	}

	@Override
	public NivelAvance criaObjNivel(int idUsuario, int idSup) 
	{
		String url;
		String tipoUrl;
		String nome = "";
		boolean externa;
		boolean feed;

		try 
		{
			nome = URLDecoder.decode(getParameter("nome"), "ISO-8859-1");
			url  = URLDecoder.decode(getParameter("url"), "ISO-8859-1");
			tipoUrl = getParameter("tipourl");
		}
		catch (Exception e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
			         "Erro ao decodificar parametro: " + e.getMessage() );
			return null;
		}
		
		externa = !tipoUrl.equals("1");
		feed    = tipoUrl.equals("2");
		
		return new Aplicacao( nome, idUsuario, url, externa, feed);
	}

	public boolean podeInserir()
	{
		return true;
	}
	
	public String obtemForm()  
	{

		if ( podeInserir() )
		{
			String campos, estrutura, itens;

			estrutura = "<f_nome/>\n<f_url/>\n<f_tipourl/>\n";
			/*
			campos = criarInputText("Nome da Aplicação:",   "f_nome") +
			         criarInputText("URL:", "f_url") +
			         criarRadio("", "f_tipourl",  criarItem("URL Avance", "1")) +
			         criarRadio("", "f_tipourl",  criarItem("Feed RSS externo", "2")) +
			         criarRadio("", "f_tipourl",  criarItem("Arquivo WADL", "3"));
			*/

    		itens = criarItem("URL Avance", "1") + 
   			        criarItem("Feed RSS externo", "2") +
   			        criarItem("Arquivo WADL", "3");

    		campos = criarInputText("Nome da Aplicação:",   "f_nome") +
	         		 criarInputText("URL:", "f_url") +
			         criarComboBox("Tipo da Aplicação:", "f_tipourl", itens);

			setUrlApp("http://localhost:8080");
			
			return criaFormXForms( "CriarAplicacao", montaURLBase(), estrutura, campos );
		}
		
		return null; 
	}
	
	public String carregaWADL()  
	{
		int id;
		Aplicacao aplica = null;
		AplicacaoPersist persist;
		
		id = contexto.getIntNode("idAplic");
		
		try
		{
			persist = new AplicacaoPersist();

			aplica = (Aplicacao)persist.Busca(id);

			aplica.carregaWADL();
		}
		catch ( ExcecaoBanco eb )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
					 "Nao pude acessar a Aplicacao no banco: " + 
					 eb.getMessage() );
			return null;
		}
		catch ( ExcecaoParser ep )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro ao fazer o parsing do WADL: " + 
					 ep.getMessage() );
			return null;
		}
		
		return listar(); 
	}

}

