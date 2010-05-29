<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			$this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
		};
	});
</script>

<div id="title">Detalhe Artefato</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="no_servico"></label></td>
		</tr>
		<tr>
			<td>Autor:</td>
			<td><label id="no_autor"></label></td>
		</tr>
		<tr>
			<td>Ano de Publica&ccedil;&atilde;o:</td>
			<td><label id="nu_anoPublicacao"></label></td>
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