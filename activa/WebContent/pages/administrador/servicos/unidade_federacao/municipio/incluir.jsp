<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			$this.find('button#incluir').click(function() {
				var municipio = $this.find('input#municipio');
				
				var nome = municipio.val();
				
				if(nome.length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { municipio.focus(); }});
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
							$mainPage.newPage('../../UfServlet?opcao=M', {id: id, nome: nome, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['municipios'])
										{
											if(object['municipios'][i].id == id)
											{
												delete object['municipios'][i];
												tr.clear();
											}
										}
									}
								});
							}
						}
					}
				});

				object['municipios'].push({id: id, nome: nome, virtual: true, trReferencia: tr});
				
				$oldPage.find('table#tabela_municipios tbody').append(tr.append(tdNome));

				botaoVoltar.click();
			});
		};
	});
</script>

<div id="title">Incluir Munic&iacute;pio</div>
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
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>