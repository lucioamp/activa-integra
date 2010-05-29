<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.util.Collection"%>
<% 
	//if(session.getAttribute("administrador") == null)
	//	response.sendRedirect("../../index.jsp");

	//Usuario usuario = Usuario.consultarPorLogin("adm@gmail.com");
	//session.setAttribute("administrador", new Usuario());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<title>Desktop</title>
<link rel="stylesheet" type="text/css" href="../../css/designer.css">
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.corner.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.selectboxes.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.filestyle.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.contextMenu.js"></script>
<script type="text/javascript" src="../../js/plugins/ui.datepicker-pt-BR.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.meio.mask.min.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.datepick.min.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.datepick-pt-BR.js"></script>
<script type="text/javascript" src="../../js/functions.js"></script>
<script type="text/javascript" src="../../js/globalEvent.js"></script>
<script type="text/javascript" src="../../js/events/login.js"></script>
<script type="text/javascript" src="../../js/events/menu_adm.js"></script>
<script type="text/javascript" src="../../js/events/desktop_adm.js"></script>
<script type="text/javascript" src="../../js/classes/String.js"></script>
<script type="text/javascript">
			$(document).ready(function () {
				globalEvent("basico");
				<%
					if(request.getAttribute("msg") != null)
						out.print("$(this).alert('"+request.getAttribute("msg")+"');");
				
					if(session.getAttribute("administrador") == null)
						out.print("loginEvent.showDialog();");
					else
					{
						out.print("globalEvent('adm');");
					}
				%>
			});
		</script>
</head>
<body>
<div id="desktop"></div>
<div id="menuOption">
<div id="conteudo">
<div id="servicos">
<ul>
	<li></li>
</ul>
</div>
<div id="sub_servicos">
<div id="enderecos" style="display: none;">
<ul>
	<li></li>
</ul>
</div>
<div id="perfis" style="display: none;">
<ul>
	<li></li>
</ul>
</div>
</div>
<div id="perfil" style="left: 3px; top: 231px;">
<p><a href="#SAIR">Sair</a></p>
</div>
</div>
</div>
<div id="menuBar"><a href="#" id="botaoIniciar"><img
	src="../../images/button_start.gif" alt="Iniciar" /></a>
<div>
<table id="minimizedWindows" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td></td>
		</tr>
	</tbody>
</table>
</div>
</div>
</body>
</html>