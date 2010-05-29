<script type="text/javascript">
	$(document).ready(function () {
		$('#incluirArquivo').click(function(e){
			modulo.alertMsg('O arquivo foi incluida com sucesso.', null, "OK", null, function() { modulo.mostrarMainModulo(); });
		});
	});
</script>
<div id="MODULO_SERVICO_ARQUIVO_INCLUIR" style='display: none'
	class="content">
<div id="title">Incluir Arquivo</div>
<div id="contentForm">
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_arquivo" id="no_arquivo" /></td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3"><input type="button" name="incluirArquivo"
				id="incluirArquivo" value="Inlcuir" /></td>
		</tr>
	</tfoot>
</table>
</div>
</div>