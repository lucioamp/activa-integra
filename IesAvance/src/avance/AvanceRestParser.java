package avance;

import util.interpretador.ExcecaoParser;
import util.interpretador.ParseNode;
import util.interpretador.RestParser;

import nucleo.output.GeraSaida;

public class AvanceRestParser extends RestParser 
{
	
	static protected ParseNode tabGet[] = { 

new ParseNode(   ERRO,             "",           null,          null, false,  0,  0),  /*  0 */
new ParseNode(RECURSO,      "usuario",      "Usuario",      "listar", false,  4,  2),  /*  1 */
new ParseNode(RECURSO,         "form",      "Usuario",   "obtemForm", false,  3,  0),  /*  2 */
new ParseNode(RECURSO,       "openID",      "Usuario", "loginOpenID", false,  0,  0),  /*  3 */
new ParseNode(  IDSTR,     "idSessao",           null,          null, false,  0,  5),  /*  4 */
new ParseNode(RECURSO,    "aplicacao",    "Aplicacao",      "listar",  true,  8,  6),  /*  5 */
new ParseNode(RECURSO,         "form",    "Aplicacao",   "obtemForm",  true,  7,  0),  /*  6 */
new ParseNode(  IDINT,      "idAplic",    "Aplicacao", "carregaWADL",  true,  0,  0),  /*  7 */
new ParseNode(RECURSO,        "turma",        "Turma", "exibeTurmas",  true, 10,  9),  /*  8 */
new ParseNode(  IDINT,      "idTurma", "AplicDaTurma",  "loginTurma",  true,  0,  0),  /*  9 */
new ParseNode(RECURSO,        "curso",        "Curso",      "listar",  true,  0, 11),  /* 10 */
new ParseNode(RECURSO,         "form",        "Curso",   "obtemForm",  true, 12,  0),  /* 11 */
new ParseNode(  IDINT,      "idCurso",   "Disciplina",      "listar",  true,  0, 13),  /* 12 */
new ParseNode(RECURSO,         "form",   "Disciplina",   "obtemForm",  true, 14,  0),  /* 13 */
new ParseNode(  IDINT, "idDisciplina",        "Turma",      "listar",  true,  0, 15),  /* 14 */
new ParseNode(RECURSO,         "form",        "Turma",   "obtemForm",  true, 16,  0),  /* 15 */
new ParseNode(  IDINT,      "idTurma", "AplicDaTurma",      "listar",  true,  0, 17),  /* 16 */
new ParseNode(RECURSO,         "form", "AplicDaTurma",   "obtemForm",  true,  0,  0)   /* 17 */

	};

	static protected ParseNode tabPost[] = {
		
new ParseNode(   ERRO,             "",           null,          null, false,  0,  0),  /*  0 */
new ParseNode(RECURSO,      "usuario",      "Usuario",     "inserir", false,  4,  2),  /*  1 */
new ParseNode(RECURSO,        "login",      "Usuario",       "login", false,  3,  0),  /*  2 */
new ParseNode(RECURSO,       "openID",      "Usuario", "loginOpenID", false,  0,  0),  /*  3 */
new ParseNode(  IDSTR,     "idSessao",           null,          null, false,  0,  5),  /*  4 */
new ParseNode(RECURSO,    "aplicacao",    "Aplicacao",     "inserir",  true,  6,  0),  /*  5 */
new ParseNode(RECURSO,        "curso",        "Curso",     "inserir",  true,  0,  7),  /*  6 */
new ParseNode(  IDINT,      "idCurso",   "Disciplina",     "inserir",  true,  0,  8),  /*  7 */
new ParseNode(  IDINT, "idDisciplina",        "Turma",     "inserir",  true,  0,  9),  /*  8 */
new ParseNode(  IDINT,      "idTurma", "AplicDaTurma",     "inserir",  true,  0,  0)   /*  9 */
		
	};

	public AvanceRestParser() 
	{
		super();
	}

	/*
	 * Rotinas de interpretação do grafo GET 
	 */
	public int interpretaGet(String parStr) throws ExcecaoParser
	{
		return interpreta(tabGet, parStr);
	}

	public String executaGet(int no) throws ExcecaoParser
	{
		return tabGet[no].executa( getContexto(), "nucleo.output.GeraSaida" );
	}

	public String executaGet(int no, GeraSaida obj) throws ExcecaoParser
	{
		return tabGet[no].invoca(obj);
	}

	public GeraSaida obtemGeraSaidaGet(int no) throws ExcecaoParser
	{
		return (GeraSaida)tabGet[no].createObject("nucleo.output.GeraSaida");
	}
	
	public boolean temAutenticacaoGet( int no )
	{
		return tabGet[no].isAuth();
	}

	/*
	 * Rotinas de interpretação do grafo POST 
	 */
	public int interpretaPost(String parStr) throws ExcecaoParser
	{
		return interpreta(tabPost, parStr);
	}

	public String executaPost(int no) throws ExcecaoParser
	{
		return tabPost[no].executa( getContexto(), "nucleo.output.GeraSaida" );
	}

	public String executaPost(int no, GeraSaida obj) throws ExcecaoParser
	{
		return tabPost[no].invoca(obj);
	}

	public GeraSaida obtemGeraSaidaPost(int no) throws ExcecaoParser
	{
		return (GeraSaida)tabPost[no].createObject("nucleo.output.GeraSaida");
	}
	
	public boolean temAutenticacaoPost( int no )
	{
		return tabPost[no].isAuth();
	}
}
