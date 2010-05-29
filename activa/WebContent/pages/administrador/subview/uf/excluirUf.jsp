<%@page import="modelo.Uf"%>
<% 
	Uf uf = (Uf)request.getAttribute("uf");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Excluir Uf
<br />
<br />
<%=uf.getPkEstado() %>
-
<%=uf.getEstado() %>
-
<%=uf.getSigla() %>

<form action="UfServlet?opcao=E" method="post"><input
	type="hidden" name="pkEstado" value="<%=uf.getPkEstado() %>" /> <input
	type="submit" name="enviar" value="Enviar" /></form>
</body>
</html>