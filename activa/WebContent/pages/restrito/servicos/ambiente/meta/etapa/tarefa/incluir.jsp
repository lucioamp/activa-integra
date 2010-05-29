<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
$(function() {
	modulo_ready = function($this, $oldPage, $mainPage) {
		var object = $mainPage.getObject();
			
		var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

		var data = $this.find('input#dt_tarefa').datepick().setMask('99/99/9999');
		$this.find('button#incluir').click(function() {
			var nome = $this.find('input#no_tarefa');
			var desc = $this.find('textarea#ds_tarefa');

			if(nome.val().length == 0)
			{
				$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
				return;
			}
			
			if(desc.val().length == 0)
			{
				$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
				return;
			}

			if(data.val().length == 0)
			{
				$this.alert('Por favor, digite uma data.', null, {clickOK: function() { data.focus(); }});
				return;
			}
			
			var id = rand(1, 9999999);
			var tr = $('<tr></tr>').attr('id', id);

			var tarefa = {
				id: id, nome: nome.val(), desc: desc.val(), data: data.val(),
				virtual: true, trReferencia: tr
			};
			
			var tdNome = $('<td></td>').html($('<a href="#"></a>').append(nome.val()).click(function(e) {
				e.preventDefault();
				object['tarefa'] = tarefa;
				$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/detalhe.jsp');
			}));

			tr.contextMenu({
				buttons: {
					Abrir: {
						textBold: true,
						icon: "open",
						onClick: function() {
							object['tarefa'] = tarefa;
							$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/detalhe.jsp');
						}
					},
					Editar: {
						icon: "edit",
						onClick: function() {
							object['tarefa'] = tarefa;
							$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/alterar.jsp', null);
						}
					},
					Remover: {
						icon: "delete",
						onClick: function() {
							$this.alert('Tem certeza que deseja excluir esta tarefa?', null, {
								dialog: DIALOG_CONFIRM,
								clickOK: function() {
									for (var i in object['etapa'].tarefas)
									{
										if(object['etapa'].tarefas[i].id == id)
										{
											object['etapa'].tarefas.splice(i, 1);
											tr.clear();
										}
									}
								}
							});
						}
					}
				}
			});

			$oldPage.find('table#tabela_tarefas tbody').append(tr.append(tdNome));

			object['etapa'].tarefas.push(tarefa);

			botaoVoltar.click();
		});
	};
});
</script>

<div id="title">Incluir Tarefa</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" id="no_tarefa" name="no_tarefa" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_tarefa" name="ds_tarefa"></textarea></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><input type="text" id="dt_tarefa" name="dt_tarefa" /></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>