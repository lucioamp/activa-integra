<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			$this.find('input#desableEffect').click(function(e) {
				jQuery.fx.off = $(this).attr('checked');
			}).attr('checked', (jQuery.fx.off == null ? false : true));
			
			$this.find('button#gerarForum').click(function(e) {
				e.preventDefault();
				$this.alert('Deseja gerar o f&oacute;rum baseandosse em Etapas e Tarefas do Ambiente?', null, {
					dialog: DIALOG_CONFIRM,
					clickOK: function() {
						$this.alert('F&oacute;rum gerado com sucesso.', null);
					}
				});
			
			});
		};
	});
</script>
<div id="PAINEL_CONTROLE">
<div id="conteudo">
<div id="title">Painel de Controle</div>
<table>
	<tbody>
		<tr>
			<td><input type="checkbox" name="desableEffect"
				id="desableEffect" /></td>
			<td valign="middle">Desativar efeitos.</td>
		</tr>
		<tr>
			<td colspan="2">
			<fieldset><legend>Forum</legend>
			<button type="button" class="ui-state-default ui-corner-all"
				name="gerarForum" id="gerarForum">Gerar Etapas/Tarefas</button>
			</fieldset>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>