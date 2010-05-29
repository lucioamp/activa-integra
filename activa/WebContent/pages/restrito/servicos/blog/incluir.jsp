<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			$this.find('button#incluir').click(function (e) {
				var nome = $this.find('input#no_curso');
				var desc = $this.find('textarea#ds_servico');

				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroBlogServlet?opcao=I',
					{nome: nome.val(), desc: desc.val()},
					function(data) {
						var result = trim(data);
	
						if(result == "true")
						{
							$this.alert('Blog incluido com sucesso.', null, {clickOK: function() {
								nome.val('');
								desc.val('');
							}});
						}else
							$this.alert('Erro ao tentar incluir o blog.');
					}
				);
			});
		};
	});
</script>

<div id="title">Incluir Blog</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_curso" id="no_curso" /></td>
		</tr>
		<tr>
			<td valign="top">Descri&ccedil;&atilde;o:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"></textarea></td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			</td>
		</tr>
	</tfoot>
</table>