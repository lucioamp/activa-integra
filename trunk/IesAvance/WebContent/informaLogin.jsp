<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="nucleo.nivel.Turma"%>
<%@ page import="nucleo.persist.TurmaPersist"%>
<%@ page import="nucleo.usuario.Sessao"%>
<%@ page import="nucleo.usuario.SessaoPersist"%>
<%@ page import="nucleo.usuario.Usuario"%>
<%@ page import="nucleo.usuario.UsuarioPersist"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informa Situação de Login</title>
</head>
<body>
<%
	Sessao sessao;
	Usuario usuario;
	String nomeTurma;
	String nomeUsuario;
	
	try 
	{
		SessaoPersist sesPersist = new SessaoPersist();
		
		sessao = sesPersist.Busca(session.getId(), request.getRemoteAddr());
	}
	catch ( Exception e )
	{
		response.sendError( HttpServletResponse.SC_BAD_REQUEST ,
		    "Erro ao tentar recuperar a Sessão: " + e.getMessage() );
		return;
	}
	
	if ( sessao == null || sessao.getLogado() == 0 )
	{
		response.sendRedirect("login.html");
/*		
		RequestDispatcher proximo;
		
		proximo = request.getRequestDispatcher( "login.html" );
		proximo.forward(request, response);
*/		
		return;
	}
	

	try 
	{
		UsuarioPersist usuPersist = new UsuarioPersist();
		
		usuario = usuPersist.Busca(sessao.getIdUsuario());
	}
	catch ( Exception e )
	{
		response.sendError( HttpServletResponse.SC_BAD_REQUEST,
		    "Erro ao tentar recuperar dados do Usuario (" + sessao.getIdUsuario() + 
		    ") : " + e.getMessage() );
		return;
	}
	
	if ( usuario == null )
	{
		response.sendError( HttpServletResponse.SC_BAD_REQUEST,
							"Usuario (" + sessao.getIdUsuario() + ") não encontrado!" );
		return;
	}
	
	nomeUsuario = usuario.getNomeUsuario();
	
	nomeTurma = "";
	if ( sessao.getIdTurma() != 0 )
	{
		Turma turma;

		try 
		{
			TurmaPersist turPersist = new TurmaPersist();
			turma = turPersist.Busca(sessao.getIdTurma());
		}
		catch ( Exception e )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST,
			    "Erro ao tentar recuperar a Turma (" + sessao.getIdTurma() + 
			    ") : " + e.getMessage() );
			return;
		}
		
		if ( turma == null )
		{
			response.sendError( HttpServletResponse.SC_BAD_REQUEST,
				"Turma (" + sessao.getIdTurma() + ") não encontrada!" );
			return;
		}
		
		nomeTurma = turma.getNome();
	}
%>
Usuario: <%= nomeUsuario %><br/>
Turma:   <%= nomeTurma %><br/>

</body>
</html>