<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Topico"%>
<%@page import="util.Ferramenta"%>
<% Topico topico = (Topico)request.getAttribute("topico"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			var desc = $this.find('textarea#ds_topico');
			$this.find('button#alterar').click(function() {

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroTopicoServlet?opcao=A',
					{desc: desc.val(), pkTopico: <%= topico.getPkTopico() %>},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Tópico alterado com sucesso.', null, {clickOK: function() {
								$oldPage.find('pre').html(desc.val());
								botaoVoltar.click();
							}});
						}else
							$this.alert('Erro ao tentar alterar o tópico.');
					}
				);
			});
			
			desc.val('<%= Ferramenta.clearScape(topico.getDescricao()) %>'.clearNull());
		};
	});
</script>

<div id="title">Alterar T&oacute;pico</div>
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
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>