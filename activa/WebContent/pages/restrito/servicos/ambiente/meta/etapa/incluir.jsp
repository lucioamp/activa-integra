<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
$(function() {
	modulo_ready = function($this, $oldPage, $mainPage) {
		var object = $mainPage.getObject();

		var etapa = {
			id: null, nome: null, desc: null, data: null,
			virtual: true, trReferencia: null, tarefas: new Array()
		};
		object['etapa'] = etapa;

		$this.find('a#incluirTarefa').click(function() {
			$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/incluir.jsp', null);
		});
	
		var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

		var data = $this.find('input#dt_etapa').datepick().setMask('99/99/9999');
		$this.find('button#incluir').click(function() {
			var nome = $this.find('input#no_etapa');
			var desc = $this.find('textarea#ds_etapa');

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
			var tdNome = $('<td></td>').html($('<a href="#"></a>').append(nome.val()).click(function(e) {
				e.preventDefault();
				object['etapa'] = etapa;
				$mainPage.newPage('../../MembroAmbienteServlet?opcao=E', {id: id, tipo: "paginaDetalhe"});
			}));

			tr.contextMenu({
				buttons: {
					Abrir: {
						textBold: true,
						icon: "open",
						onClick: function() {
							object['etapa'] = etapa;
							$mainPage.newPage('../../MembroAmbienteServlet?opcao=E', {id: id, tipo: "paginaDetalhe"});
						}
					},
					Editar: {
						icon: "edit",
						onClick: function() {
							object['etapa'] = etapa;
							$mainPage.newPage('../../MembroAmbienteServlet?opcao=E', {id: id, tipo: "paginaAlterar"});
						}
					},
					Remover: {
						icon: "delete",
						onClick: function() {
							$this.alert('Tem certeza que deseja excluir esta etapa?', null, {
								dialog: DIALOG_CONFIRM,
								clickOK: function() {
									for (var i in object['meta'].getEtapas())
									{
										if(object['meta'].getEtapas()[i].id == id)
										{
											object['meta'].getEtapas().splice(i, 1);
											tr.clear();
										}
									}
								}
							});
						}
					}
				}
			});

			$oldPage.find('table#tabela_etapas tbody').append(tr.append(tdNome));

			object['etapa'] = etapa;
			object['etapa'].id = id;
			object['etapa'].nome = nome.val();
			object['etapa'].desc = desc.val();
			object['etapa'].data = data.val();
			object['etapa'].trReferencia = tr;
			
			object['meta'].adicionarEtapa(object['etapa']);

			botaoVoltar.click();
		});
	};
});
</script>

<div id="title">Incluir Etapa</div>
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
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>