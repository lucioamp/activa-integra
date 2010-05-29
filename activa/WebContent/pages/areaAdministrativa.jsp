
<%
	if(session.getAttribute("administrador") == null){
		response.sendRedirect("../index.jsp");
  	}else{
  		response.sendRedirect("pages/administrador/index.jsp");
  	}
%>