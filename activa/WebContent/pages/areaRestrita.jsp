
<% 
	if(session.getAttribute("membro") == null){
		response.sendRedirect("../index.jsp");
  	}else{
  		response.sendRedirect("pages/restrito/index.jsp");
  	}
%>