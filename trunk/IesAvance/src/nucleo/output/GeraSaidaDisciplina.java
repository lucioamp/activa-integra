package nucleo.output;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import nucleo.nivel.Disciplina;
import nucleo.nivel.NivelAvance;
import nucleo.persist.DisciplinaPersist;
import nucleo.persist.NivelAvancePersist;
import util.banco.ExcecaoBanco;
import viecili.jrss.generator.elem.Channel;

public class GeraSaidaDisciplina extends GeraSaidaAvance 
{
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaida#getNomeNivel()
	 */
	@Override
	public String getNomeNivel() {
		return "disciplina";
	}

	public int getNumNivel()	{ return 1;									}
	public int getIdSuperior()	{ return contexto.getIntNode("idCurso");	}
	
	/*
	 * (non-Javadoc)
	 * @see nucleo.output.GeraSaidaAvance#criaCanal(int, java.lang.String)
	 */
	public Channel criaCanal(int idSup, String urlBase)
	{
		String nome, desc;
		
		nome = "Disciplinas";
		desc = "Disciplinas do curso " + idSup;
		
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
			return new DisciplinaPersist();
		}
		catch ( ExcecaoBanco e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Nao pude acessar a Disciplina no banco: " + 
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
		int cargaHoraria;
		String chStr;
		String nome = "";
		String ementa = "";

		try 
		{
			nome = URLDecoder.decode(getParameter("nome"), "ISO-8859-1");
			chStr = getParameter("cargaHoraria");
			ementa = URLDecoder.decode(getParameter("ementa"), "ISO-8859-1");
		}
		catch (Exception e)
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Erro ao decodificar parametro: " + e.getMessage());
			return null;
		}
		
		try 
		{
			cargaHoraria = Integer.parseInt(chStr);
		}
		catch ( Exception e )
		{
			setErro( HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					 "Carga horaria nao numerica: " + e.getMessage());
			return null;
		}
		
		return new Disciplina( nome, idUsuario, cargaHoraria, ementa, idSup);
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

			estrutura = "<f_nome/>\n<f_cargaHoraria/>\n<f_ementa/>\n";
			
			campos = criarInputText("Nome da Disciplina:", "f_nome") +
			         criarInputText("Carga Horária:", "f_cargaHoraria") +
			         criarTextArea("Ementa:", "f_ementa");
			
			return criaFormXForms( "CriarDisciplina", montaURLBase(), estrutura, campos );
		}
		
		return null; 
	}
}
