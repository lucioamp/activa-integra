<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			meta = new Meta();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('#incluirTag').click(function() {
				$mainPage.newPage('servicos/ambiente/meta/tags/incluir.jsp', null);
			});
			
			$this.find('a#incluirEtapas').click(function() {
				$mainPage.newPage('servicos/ambiente/meta/etapa/incluir.jsp', null);
			});
			
			$this.find('button#incluir').click(function() {
				var desc = $this.find('#ds_meta');
				var duracao = $this.find('#ds_duracao');
				
				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição para meta.', null, {clickOK: function() { desc.focus(); }});
					return;
				}

				if(duracao.val().length == 0)
				{
					$this.alert('Por favor, digite uma duração para meta.', null, {clickOK: function() { duracao.focus(); }});
					return;
				}
				
				var id = rand(1, 9999999);
				var tr = $('<tr></tr>').attr('id', id);
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(desc.val()).click(function(e) {
					e.preventDefault();
					object['meta'] = meta;
					$mainPage.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaDetalhe"});
				}));

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								object['meta'] = meta;
								$mainPage.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaDetalhe"});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								object['meta'] = meta;
								$mainPage.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir esta meta?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['metas'])
										{
											if(object['metas'][i].getId() == id)
											{
												object['metas'].splice(i, 1);
												tr.clear();
											}
										}
									}
								});
							}
						}
					}
				});
				
				$oldPage.find('table#tabela_metas tbody').append(tr.append(tdNome));

				meta.setId(id);
				meta.setDescricao(desc.val());
				meta.setDuracao(duracao.val());
				meta.setVirtual(true);
				meta.setTrReferencia(tr);
				
				object['metas'].push(meta);	

				botaoVoltar.click();
			});
		};
	});
</script>

<div id="title">Incluir Meta</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%;">Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_meta" name="ds_meta"></textarea></td>
		</tr>
		<tr>
			<td>Dura&ccedil;&atilde;o:</td>
			<td><input type="text" name="ds_duracao" id="ds_duracao" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags<a href="#" id="incluirTag"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_etapas" class="tabelaResult">
				<caption>Etapas <a href="#" id="incluirEtapas"
					title="Incluir Etapas"><img src="../../images/icons/add.gif"></a></caption>
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