<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#tipo').html('<%= request.getParameter("tipo") %>'.clearNull());
			$this.find('label#ds_tag').val('<%= request.getParameter("desc") %>'.clearNull());
		};
	});
</script>

<div id="title">Detalhe do Contato</div>
<table>
	<tbody>
		<tr>
			<td>Tipo:</td>
			<td><label id="tipo"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_contato"></label></td>
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