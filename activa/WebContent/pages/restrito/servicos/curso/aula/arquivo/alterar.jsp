<script type="text/javascript">
	$(document).ready(function () {
		$('#alterarArquivo').click(function(e){
			modulo.alertMsg('O arquivo foi alterado com sucesso.', null, "OK", null, function() { modulo.mostrarModulo(); });
		});
	});
</script>
<div id="MODULO_SERVICO_ARQUIVO_ALTERAR" style='display: none'
	class="content">
<div id="title">Altera&ccedil;&atilde;o da Arquivo 1</div>
<div id="contentForm">
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 15%;">Nome:</td>
			<td><input type="text" name="no_arquivoBlog" id="no_arquivoBlog" /></td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3"><input type="button" name="alterarArquivo"
				id="alterarArquivo" value="Alterar" /></td>
		</tr>
	</tfoot>
</table>
</div>
</div>