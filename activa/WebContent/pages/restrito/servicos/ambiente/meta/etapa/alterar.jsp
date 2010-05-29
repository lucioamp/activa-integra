<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
$(function() {
	modulo_ready = function($this, $oldPage, $mainPage) {
		var object = $mainPage.getObject();

		$this.find('a#incluirTarefa').click(function() {
			$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/incluir.jsp', null);
		});
	
		var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

		var nome = $this.find('input#no_etapa').val(object['etapa'].nome);
		var desc = $this.find('textarea#ds_etapa').val(object['etapa'].desc);
		
		var data = $this.find('input#dt_etapa').datepick().setMask('99/99/9999').val(object['etapa'].data);
		$this.find('button#alterar').click(function() {

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
			
			object['etapa'].nome = nome.val();
			object['etapa'].desc = desc.val();
			object['etapa'].data = data.val();
			object['etapa'].alterado = true;
			object['etapa'].trReferencia.find('td:first').find('a').html(object['etapa'].nome);

			botaoVoltar.click();
		});

		adicionarTarefa = function(tarefa)
		{
			var tr = $('<tr></tr>');

			tarefa.trReferencia = tr;
			
			var tdNome = $('<td></td>').html($('<a href="#"></a>').append(tarefa.nome).click(function(e) {
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
							$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/alterar.jsp');
						}
					},
					Remover: {
						icon: "delete",
						onClick: function() {
							$this.alert('Tem certeza que deseja excluir esta tarefa?', null, {
								dialog: DIALOG_CONFIRM,
								clickOK: function() {
									if(tarefa.virtual == true)
									{
										for (var i in object['etapa'].tarefas)
										{
											if(object['etapa'].tarefas[i].id == id)
											{
												object['etapa'].tarefas.splice(i, 1);
												tr.clear();
											}
										}
									}else
									{
										tarefa.deletado = true;
										tr.clear();
									}
								}
							});
						}
					}
				}
			});

			tr.append(tdNome);

			$this.find('table#tabela_tarefas tbody').append(tr);
		};

		for (var i in object['etapa'].tarefas)
		{
			adicionarTarefa(object['etapa'].tarefas[i]);
		}
	};
});
</script>

<div id="title">Alterar Etapa</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%;">Nome:</td>
			<td><input type="text" id="no_etapa" name="no_etapa" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_etapa" name="ds_etapa"></textarea></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><input type="text" id="dt_etapa" name="dt_etapa" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<table id="tabela_tarefas" class="tabelaResult">
					<caption>Tarefas <a href="#" id="incluirTarefa" title="Incluir Tarefa"><img src="../../images/icons/add.gif"></a></caption>
					<thead>
						<tr>
							<th><img src="../../images/icons/b_search.png" />Nome</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>