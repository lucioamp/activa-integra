<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Tarefa"%>
<script type="text/javascript">
	$(document).ready(function () {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });


			$('label#no_tarefa').html(object['tarefa'].nome);
			$('label#ds_tarefa').html(object['tarefa'].desc);
			$('label#dt_tarefa').html(object['tarefa'].data);
		};
	});
</script>
<div id="title">Detalhe da Tarefa</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="no_tarefa"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_tarefa"></label></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><label id="dt_tarefa"></label></td>
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