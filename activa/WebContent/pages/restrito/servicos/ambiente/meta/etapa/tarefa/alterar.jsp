<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
$(function() {
	modulo_ready = function($this, $oldPage, $mainPage) {
		var object = $mainPage.getObject();
			
		var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

		var nome = $this.find('input#no_tarefa').val(object['tarefa'].nome);
		var desc = $this.find('textarea#ds_tarefa').val(object['tarefa'].desc);

		var data = $this.find('input#dt_tarefa').datepick().setMask('99/99/9999').val(object['tarefa'].data);
		$this.find('button#alterar').click(function() {

			if(nome.val().length == 0)
			{
				$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
				return;
			}
			
			if(desc.val().length == 0)
			{
				$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
				return;
			}

			if(data.val().length == 0)
			{
				$this.alert('Por favor, digite uma data.', null, {clickOK: function() { data.focus(); }});
				return;
			}
			
			object['tarefa'].nome = nome.val();
			object['tarefa'].desc = desc.val();
			object['tarefa'].data = data.val();
			object['tarefa'].alterado = true;
			object['tarefa'].trReferencia.find('td:first').find('a').html(object['tarefa'].nome);

			botaoVoltar.click();
		});
	};
});
</script>

<div id="title">Alterar Tarefa</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" id="no_tarefa" name="no_tarefa" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_tarefa" name="ds_tarefa"></textarea></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><input type="text" id="dt_tarefa" name="dt_tarefa" /></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>