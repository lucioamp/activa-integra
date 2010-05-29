<script type="text/javascript">
	$(document).ready(function () {
		$('#alterarArtefato').click(function (e) {
			modulo.alertMsg('O artefato foi alterado com sucesso.', null, "OK", null, function() { modulo.mostrarMainModulo(); });
		});
	});
</script>

<div id="title">Alterar Artefato</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" id="no_servico" name="no_service" /></td>
		</tr>
		<tr>
			<td>Autor:</td>
			<td><input type="text" name="no_autor" id="no_autor" /></td>
		</tr>
		<tr>
			<td>Ano de Publica&ccedil;&atilde;o:</td>
			<td><input type="text" name="nu_anoPublicacao"
				id="nu_anoPublicacao" /></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>