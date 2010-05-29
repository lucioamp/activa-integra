<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Blog"%>
<%@page import="modelo.Usuario"%>
<%
	Blog blog = (Blog)request.getAttribute("blog");
	Usuario usuario = (Usuario)session.getAttribute("membro");
%>

<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, tab) {
			var object = $this.getObject();

			var id = '<%= blog.getPkServico() %>';

			var nome = $this.find('label#no_curso').html('<%= blog.getNome() %>'.clearNull());
			var desc = $this.find('label#ds_servico');

			$this.find('div#title').html('Detalhe de <%= blog.getNome() %>');

			var tabelaAulas = $this.find('table#aulas');
		};
	});
</script>

<div id="title">Detalho do Blog</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><label id="no_curso"></label></td>
		</tr>
		<tr>
			<td valign="top">Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_servico"><%= blog.getDescricao() %></label></td>
		</tr>
	</tbody>
</table>