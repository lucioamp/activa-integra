<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.TipoContato"%>
<% TipoContato tipoContato = (TipoContato)request.getAttribute("tipoContato"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#nome').html("<%= tipoContato.getTipoContato() %>".clearNull());
		}
	});
</script>

<div id="title">Detalhe do Tipo de Contato</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>