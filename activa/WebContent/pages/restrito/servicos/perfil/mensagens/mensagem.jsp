<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
		};
	});
</script>

<div id="title">Mensagem <%= request.getParameter("tipo") %></div>
<table>
	<tbody>
		<tr>
			<td style="width: 10%;">Membro:</td>
			<td><%= request.getParameter("nome") %></td>
		</tr>
		<tr>
			<td>Assunto:</td>
			<td><%= request.getParameter("assunto") %></td>
		</tr>
		<tr>
			<td colspan="2"><textarea cols="60" rows="9" id="texto" readonly="readonly"
				name="texto"><%= request.getParameter("mensagem") %></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>