<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Etapa"%>

<%@page import="modelo.Tarefa"%>
<%@page import="util.Ferramenta"%>

<%@page import="modelo.MetaTag"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = object['meta'].getId();

			$this.find('#incluirTag').click(function() {
				$mainPage.newPage('servicos/ambiente/meta/tags/incluir.jsp', null);
			});
			
			$this.find('a#incluirEtapas').click(function() {
				$mainPage.newPage('servicos/ambiente/meta/etapa/incluir.jsp', null);
			});

			var desc = $this.find('#ds_meta').val(object['meta'].getDescricao());
			var duracao = $this.find('#ds_duracao').val(object['meta'].getDuracao());
			$this.find('button#alterar').click(function() {
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
				
				object['meta'].setDescricao(desc.val());
				object['meta'].setDuracao(duracao.val());
				object['meta'].setAlterado(true);
				object['meta'].getTrReferencia().find('td:first').find('a').html(desc.val());
				$this.alert('Meta alterada com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});

			});

			adicionarTag = function(tag) {
				var tr = $('<tr></tr>');
				tag.trReferencia = tr;
				
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(tag.nome).click(function(e) {
					e.preventDefault();
					object['tag'] = tag;
					$mainPage.newPage('servicos/ambiente/meta/tags/detalhe.jsp');
				}));

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								object['tag'] = tag;
								$mainPage.newPage('servicos/ambiente/meta/tags/detalhe.jsp');
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								object['tag'] = tag;
								$mainPage.newPage('servicos/ambiente/meta/tags/alterar.jsp');
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										if(tag.virtual == true)
										{
											for (var i in object['meta'].getTags())
											{
												if(object['meta'].getTags()[i].id == id)
												{
													object['meta'].getTags().splice(i, 1);
													tr.clear();
												}
											}
										}else
										{
											tag.deletado = true;
											tr.clear();
										}
									}
								});
							}
						}
					}
				});

				$this.find('table#tabela_tags tbody').append(tr.append(tdNome));
			};

			adicionarEtapa = function(etapa)
			{
				var tr = $('<tr></tr>');

				etapa.trReferencia = tr;
				
				var tdNome = $('<td></td>').html($('<a href="#"></a>').append(etapa.nome).click(function(e) {
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
										if(etapa.virtual == true)
										{
											for (var i in object['meta'].getEtapas())
											{
												if(object['meta'].getEtapas()[i].id == id)
												{
													object['meta'].getEtapas().splice(i, 1);
													tr.clear();
												}
											}
										}else			
										{			
											etapa.deletado = true;
											tr.clear();
										}
									}
								});
							}
						}
					}
				});
				
				$this.find('table#tabela_etapas tbody').append(tr.append(tdNome));
			};

			var _etapa = null;

			for (var i in object['meta'].getTags())
				adicionarTag(object['meta'].getTags()[i]);

			for (var e in object['meta'].getEtapas())
			{
				_etapa = object['meta'].getEtapas()[e];
				adicionarEtapa(_etapa);
			}
		};
	});
</script>

<div id="title">Alterar Meta</div>
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
				<caption>Tags<a href="#" name="incluirTag"
					id="incluirTag" title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
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
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>