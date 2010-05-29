<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = '<%= request.getParameter("id") %>';
			
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
				
				$this.sendRequest('../../MembroTagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: 'alterar'}, function(data) {
					for (var i in object['tags'])
					{
						if(object['tags'][i].id == id)
						{
							object['tags'][i].nome = nome;
							object['tags'][i].desc = desc;
							object['tags'][i].alterado = true;
							object['tags'][i].trReferencia.find('td:first').find('a').html(nome);
							$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});
							return;
						}
					}
				});

				$this.alert('Erro ao tentar alterar a tag.');
			});

			nomeTag.val('<%= request.getParameter("nome") %>'.clearNull());
			descTag.val('<%= request.getParameter("desc") %>'.clearNull());
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