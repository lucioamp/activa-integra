<script type="text/javascript">
	$(document).ready(function () {
		$('#adicionarArtefato').click(function (e) {
			modulo.alertMsg('O artefato foi incluida com sucesso.', null, "OK", null, function() { modulo.mostrarMainModulo(); });
		});
	});
</script>
<div id="MODULO_SERVICO_COMUNIDADE_ARTEFATO_INCLUIR"
	style="display: none;" class="content">
<div id="title">Incluir Artefato</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" id="no_service" name="no_service" /></td>
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
			<td><input type="button" name="adicionarArtefato"
				id="adicionarArtefato" value="Incluir" /></td>
		</tr>
	</tfoot>
</table>
</div>