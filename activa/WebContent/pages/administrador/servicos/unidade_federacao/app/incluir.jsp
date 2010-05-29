<%@page import="modelo.Uf"%>
<%
	Uf uf = (Uf)request.getAttribute("uf");
	out.print("<script>object['adicionarUnidade'](4, '"+uf.getEstado()+"', '"+uf.getSigla()+"');</script>");
%>