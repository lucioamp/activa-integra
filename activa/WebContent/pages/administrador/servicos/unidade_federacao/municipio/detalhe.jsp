<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			$this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
		};
	});
</script>

<div id="title">Detalhe do Munic&iacute;pio</div>
<table>
	<tbody>
		<tr>
			<td>Munic&iacute;pio:</td>
			<td><label id="no_municipio"></label></td>
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