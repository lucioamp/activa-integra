<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = '<%= request.getParameter("id") %>';
			
			var bairro = $this.find('input#bairro');
			$this.find('#alterar').click(function (e) {
				
				var nome = bairro.val();
				
				if(nome.length == 0)
				{
					$this.alert('Por favor, digite um munic&iacute;pio.', null, {clickOK: function() { bairro.focus(); }});
					return false;
				}
				
				$this.sendRequest('../../MunicipioServlet?opcao=B', {id: id, nome: nome, tipo: 'alterar'}, function(data) {
					for (var i in object['bairros'])
					{
						if(object['bairros'][i].id == id)
						{
							object['bairros'][i].nome = nome;
							object['bairros'][i].alterado = true;
							object['bairros'][i].trReferencia.find('td:first').html(nome);
							$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});
							return;
						}
					}
				});

				$this.alert('Erro ao tentar alterar o bairro.');
			});

			bairro.val('<%= request.getParameter("nome") %>'.clearNull());
		};
	});
</script>

<div id="title">Alterar Bairro</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="bairro" id="bairro" /></td>
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