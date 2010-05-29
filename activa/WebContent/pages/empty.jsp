<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="org.json.JSONArray"%>
<%
	if(request.getAttribute("msg") != null)
		out.print(request.getAttribute("msg"));

	if(request.getAttribute("json") != null) {
		JSONArray j = new JSONArray((Collection) request.getAttribute("json"));
		out.print(j.toString());
	}
		
%>