<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = object['tag'].id;
			
			var nomeTag = $this.find('input#no_tag');
			var descTag = $this.find('textarea#ds_tag');
			$this.find('#alterar').click(function (e) {
				
				var nome = nomeTag.val();
				var desc = descTag.val();
				
				if(nome.length == 0)
				{
					$this.alert('Por favor, digite um nome para a tag.', null, {clickOK: function() { nomeTag.focus(); }});
					return false;
				}
				
				object['tag'].nome = nome;
				object['tag'].desc = desc;
				object['tag'].alterado = true;
				object['tag'].trReferencia.find('td:first').find('a').html(nome);

				$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});
			});

			nomeTag.val(object['tag'].nome);
			descTag.val(object['tag'].desc);
		};
	});
</script>

<div id="title">Alterar Tag</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_tag" id="no_tag" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_tag" name="ds_tag" cols="50" rows="10"></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>