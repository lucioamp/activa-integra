package nucleo.output;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import util.banco.ExcecaoBanco;

import viecili.jrss.generator.elem.Channel;

import nucleo.nivel.Curso;
import nucleo.nivel.NivelAvance;
import nucleo.persist.CursoPersist;
import nucleo.persist.NivelAvancePersist;

public class GeraSaidaCurso extends GeraSaidaAvance 
{

	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#getNomeNivel()
	 */
	@Override
	public String getNomeNivel() 
	{
		return "curso";
	}

	public int getNumNivel()	{ return 0;		}
	public int getIdSuperior()	{ return -1;	}
	
	/*
	 * Funções utilizadas na geração de RSS do comando Listar
	 */
	/*
	 * Definição da Criação de Canal para a classe Curso
	 * @see nucleo.output.GeraSaidaAvance#criaCanal(int, java.lang.String)
	 */	
	public Channel criaCanal(int idSup, String urlBase)
	{
		String nome, desc;
		
		nome = "Cursos da plataforma AvaNCE";
		desc = "Usuario " + getIdUsuario();
		
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
			return new CursoPersist();
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude acessar o Curso no banco: " + 
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
		String nome = "";
		String instituicao = "";

		try 
		{
			nome = URLDecoder.decode(getParameter("nome"), "ISO-8859-1");
			instituicao = URLDecoder.decode(getParameter("instituicao"), "ISO-8859-1");
		}
		catch (Exception e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro ao decodificar parametro: " + e.getMessage());
			return null;
		}
		
		return new Curso( nome, idUsuario, instituicao);
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
			String campos, estrutura;

			estrutura = "<f_nome/>\n<f_instituicao/>\n";
			
			campos = criarInputText("Nome do Curso:",   "f_nome") +
			         criarInputText("Instituição:", "f_instituicao");
			
			return criaFormXForms( "CriarCurso", montaURLBase(), estrutura, campos );
		}
		
		return null; 
	}
}
