<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = '<%= request.getParameter("id") %>';
			
			var municipio = $this.find('input#municipio');
			$this.find('#alterar').click(function (e) {
				
				var nome = municipio.val();
				
				if(nome.length == 0)
				{
					$this.alert('Por favor, digite um munic&iacute;pio.', null, {clickOK: function() { municipio.focus(); }});
					return false;
				}
				
				$this.sendRequest('../../UfServlet?opcao=M', {id: id, nome: nome, tipo: 'alterar'}, function(data) {
					for (var i in object['municipios'])
					{
						if(object['municipios'][i].id == id)
						{
							object['municipios'][i].nome = nome;
							object['municipios'][i].alterado = true;
							object['municipios'][i].trReferencia.find('td:first').html(nome);
							$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});
							return;
						}
					}
				});

				$this.alert('Erro ao tentar alterar o municipio.');
			});

			municipio.val('<%= request.getParameter("nome") %>'.clearNull());
		};
	});
</script>

<div id="title">Alterar Munic&iacute;pio</div>
<table>
	<tbody>
		<tr>
			<td>Munic&iacute;pio:</td>
			<td><input type="text" name="municipio" id="municipio" /></td>
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