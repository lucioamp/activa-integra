<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Membro"%>
<% Usuario usuario = (Usuario)session.getAttribute("membro");%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.find('#tabs').TabsGenerate(null, function(e) {
			});
		};
	});
</script>
<div id="tabs">
<ul>
	<li><a href="../../MembroBlogServlet?opcao=C&tipo=principal">Principal</a></li>
	<li><a href="../../MembroBlogServlet?opcao=C&tipo=incluirBlog">Incluir Blog</a></li>
</ul>
</div>