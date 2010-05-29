<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Municipio"%>
<%
@SuppressWarnings ("unchecked")
Collection<Municipio> municipios = (Collection<Municipio>)request.getAttribute("municipios");
for(Municipio mun:municipios)
	out.print("<option value=\""+mun.getPkMunicipio()+"\">"+mun.getMunicipio()+"</option>");
%>