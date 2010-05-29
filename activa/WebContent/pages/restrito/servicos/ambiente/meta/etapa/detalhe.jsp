<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Tarefa"%>

<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });
			
			adicionarTarefa = function(tarefa) {
				var tr = $('<tr></tr>');
				var nomeLink = $('<a href="#"></a>').html(tarefa.nome).click(function(e) {
					e.preventDefault();
					object['tarefa'] = tarefa;
					$mainPage.newPage('servicos/ambiente/meta/etapa/tarefa/detalhe.jsp');
				});
				
				$this.find('table#tabela_tarefas tbody').append(tr
					.append($('<td></td>').html(nomeLink))
				);
			};

			$('label#no_visao').html(object['etapa'].nome);
			$('label#ds_visao').html(object['etapa'].desc);
			$('label#dt_visao').html(object['etapa'].data);

			for (var i in object['etapa'].tarefas)
			{
				adicionarTarefa(object['etapa'].tarefas[i]);
			}
		};
	});
</script>

<div id="title">Detalhe da Etapa</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 20%;">Nome:</td>
			<td><label id="no_visao"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_visao"></label></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><label id="dt_visao"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tarefas" class="tabelaResult">
				<caption>Tarefa</caption>
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
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>