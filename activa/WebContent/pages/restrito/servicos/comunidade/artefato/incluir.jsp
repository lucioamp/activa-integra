<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			$this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#no_service');
				var nome_autor = $this.find('#no_autor');
				var anoPublicacao = $this.find('#nu_anoPublicacao');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome para o artefato.', null, {clickOK: function() { nome.focus(); }});
					return;
				}
				
				var id = rand(1, 9999);
				var tr = $('<tr></tr>').attr('id', id);
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(nome.val()).click(function(e) {
					e.preventDefault();
					$mainPage.newPage('servicos/comunidade/artefato/detalhe.jsp', null);
				}));
				var tdEditar = $('<td></td>');
				var tdExcluir = $('<td></td>');
				
				criarBotaoEditar(tdEditar).click(function() { $mainPage.newPage('servicos/comunidade/artefato/alterar.jsp', null);  });
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir este artefato?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() { delete object['tabs'][id]; tr.clear(); }
					});
				});
				
				$oldPage.find('table#tabela_artefatos tbody').append(tr.append(tdNome).append(tdEditar).append(tdExcluir));
			
				$this.remove();
				$oldPage.fadeIn(600);

				object['tabs'].push({id: id, nome: nome.val(), nome_autor: nome_autor.val(), anoPublicacao: anoPublicacao.val()});				
			});
		};
	});
</script>

<div id="title">Incluir Artefato</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" id="no_service" name="no_service" /></td>
		</tr>
		<tr>
			<td>Autor:</td>
			<td><input type="text" name="no_autor" id="no_autor" /></td>
		</tr>
		<tr>
			<td>Ano de Publica&ccedil;&atilde;o:</td>
			<td><input type="text" name="nu_anoPublicacao"
				id="nu_anoPublicacao" /></td>
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