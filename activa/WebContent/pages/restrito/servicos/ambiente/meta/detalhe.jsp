<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Etapa"%>
<%@page import="modelo.MetaTag"%>
<%@page import="modelo.Meta"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			adicionarTag = function(tag) {
				var tr = $('<tr></tr>');
				var nomeLink = $('<a href="#"></a>').html(tag.nome).click(function(e) {
					e.preventDefault();
					object['tag'] = tag;
					$mainPage.newPage('servicos/ambiente/meta/tags/detalhe.jsp');
				});
				
				$this.find('table#tabela_tags tbody').append(tr
					.append($('<td></td>').html(nomeLink))
				);
			};

			adicionarEtapa = function(etapa) {
				var tr = $('<tr></tr>');
				var nomeLink = $('<a href="#"></a>').html(etapa.nome).click(function(e) {
					e.preventDefault();
					object['etapa'] = etapa;
					$mainPage.newPage('../../MembroAmbienteServlet?opcao=E', {id: etapa.id, tipo: "paginaDetalhe"});
				});
				
				$this.find('table#tabela_etapas tbody').append(tr
					.append($('<td></td>').html(nomeLink))
				);
			};

			for (var i in object['meta'].getTags())
				adicionarTag(object['meta'].getTags()[i]);

			for (var i in object['meta'].getEtapas())
				adicionarEtapa(object['meta'].getEtapas()[i]);

			$('label#ds_meta').html(object['meta'].getDescricao());
			$('label#ds_duracao').html(object['meta'].getDuracao());
		};
	});
</script>
<div id="title">Detalhe da Meta</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%;">Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_meta"></label></td>
		</tr>
		<tr>
			<td>Dura&ccedil;&atilde;o:</td>
			<td><label id="ds_duracao"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags</caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_etapas" class="tabelaResult">
				<caption>Etapas</caption>
				<thead>
					<tr>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
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
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>