<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			$this.find('button#incluir').click(function() {
				var bairro = $this.find('input#bairro');
				var nome = bairro.val();
				
				if(nome.length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { bairro.focus(); }});
					return false;
				}

				var id = nome+rand(1, 999);
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html(nome);
				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$mainPage.newPage('../../MunicipioServlet?opcao=B', {id: id, nome: nome, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['bairros'])
										{
											if(object['bairros'][i].id == id)
											{
												delete object['bairros'][i];
												tr.clear();
											}
										}
									}
								});
							}
						}
					}
				});

				object['bairros'].push({id: id, nome: nome, virtual: true, trReferencia: tr});

				$oldPage.find('table#tabela_bairros tbody').append(tr.append(tdNome));

				botaoVoltar.click();
			});
		};
	});
</script>

<div id="title">Incluir Bairro</div>
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
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>