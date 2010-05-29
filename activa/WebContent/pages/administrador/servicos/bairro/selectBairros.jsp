<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Bairro"%>
<%
@SuppressWarnings ("unchecked")
Collection<Bairro> bairros = (Collection<Bairro>)request.getAttribute("bairros");
for(Bairro bar:bairros)
	out.print("<option value=\""+bar.getPkBairro()+"\">"+bar.getBairro()+"</option>");
%>