<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<% 
	@SuppressWarnings ("unchecked")
	Collection<Uf> colUf = (Collection<Uf>)request.getAttribute("colUf");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Collection"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Consultar Todos Ufs
<br />
<br />
<%
	for(Uf u:colUf){
%>
<br><%=u.getPkEstado() %>
-
<%=u.getEstado() %>
-
<%=u.getSigla() %>
--------
<a href="UfServlet?opcao=2&pkEstado=<%=u.getPkEstado() %>">Alterar</a>
-
<a href="UfServlet?opcao=3&pkEstado=<%=u.getPkEstado() %>">Excluir</a>
-
<a href="UfServlet?opcao=4&pkEstado=<%=u.getPkEstado() %>">Consultar</a>
<%	
	}
%>
</body>
</html>