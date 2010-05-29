<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#no_tag');
				var desc = $this.find('#ds_tag');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome para a tag.', null, {clickOK: function() { nome.focus(); }});
					return;
				}

				var id = nome.val()+rand(1, 999);
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(nome.val()).click(function(e) {
					e.preventDefault();
					$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome.val(), desc: desc.val(), tipo: "paginaDetalhe"});
				}));
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome.val(), desc: desc.val(), tipo: "paginaDetalhe"});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome.val(), desc: desc.val(), tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir esta tag?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['tags'])
										{
											if(object['tags'][i].id == id)
											{
												delete object['tags'][i];
												tr.clear();
											}
										}
									}
								});
							}
						}
					}
				});

				object['tags'].push({id: id, nome: nome.val(), desc: desc.val(), virtual: true, trReferencia: tr});

				$oldPage.find('table#tabela_tags tbody').append(tr.append(tdNome));

				botaoVoltar.click();
			});
		};
	});
</script>

<div id="title">Incluir Tag</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_tag" id="no_tag" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_tag" name="ds_tag"></textarea></td>
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