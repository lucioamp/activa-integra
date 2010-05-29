<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var pkForum = <%= request.getParameter("pkForum") %>;

			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$oldPage.empty().load('../../MembroForumServlet?opcao=F', {pkForum: pkForum}, function() {
					modulo_ready($oldPage.parent('div'));
					$oldPage.fadeIn(600);
				});
			});
			
			$this.find('button#incluir').click(function() {
				var desc = $this.find('textarea#ds_topico');

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroTopicoServlet?opcao=I',
					{desc: desc.val(), pkForum: pkForum},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Tópico incluido com sucesso.', null, {clickOK: function() {
								botaoVoltar.click();
							}});
						}else
							$this.alert('Erro ao tentar incluir o tópico.');
					}
				);
			});
		};
	});
</script>

<div id="title">Criar T&oacute;pico</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea name="ds_topico" id="ds_topico" cols="50" rows="10"></textarea></td>
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