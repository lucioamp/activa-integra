<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.AplicacaoExterna"%>
<%@page import="modelo.integra.Recurso"%>

<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var table = $this.find("table");
			var tbody = table.find('tbody');

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var adicionarPasta = function(id, nome, parent_id, append)
			{
				var _tr = tbody.find('tr[id="node-'+id+'"]');
				if(_tr.html() != null)
					return true;
				
				var option = table.createOptionTreeTable({id: id, nome: nome, parent_id: parent_id, isFile: false, append: append});

				var tr = option.getTr();
				var tdDetalhe = option.getTd();
				var span = option.getSpan();

				tr.append($('<td></td>').html('&nbsp;'));
			};

			var adicionarFavorito = function(id, nome, parent_id, append)
			{
				var option = table.createOptionTreeTable({id: id, nome: nome, parent_id: parent_id, isFile: true, append: append});

				var tr = option.getTr();
				var tdDetalhe = option.getTd();
				var span = option.getSpan();

				span.click(function(e) {
					e.preventDefault();

					$this.newPage('../../MembroAplicacaoServlet?opcao=I', {idAplicacao: parent_id, idRecurso: id});
				});
				span.css({"cursor":"pointer"});
				
				var tdUrl = $('<td></td>').html('&nbsp;');
				tr.append(tdUrl);
			};
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<AplicacaoExterna> colAplicacao = (Collection<AplicacaoExterna>)request.getAttribute("colAplicacao");
			
			Collection<Recurso> recursoLista = new ArrayList<Recurso>();
			for (AplicacaoExterna aplicacaoExterna:colAplicacao) {
				out.print("adicionarPasta("+aplicacaoExterna.getIdAplicacao()+", '"+aplicacaoExterna.getNome()+"'.clearNull(), "+ 0 +");");
				
				recursoLista.addAll(aplicacaoExterna.getRecursoList());
			}
			
			for (Recurso recurso:recursoLista) {
				out.print("adicionarFavorito("+recurso.getIdRecurso()+", '"+recurso.getNome().replaceAll("'", "")+"'.clearNull(), "+recurso.getIdAplicacao()+");");
			}
			%>	

			table._treeTable("Acervo Atual");
		};
	});
</script>	
<div id="title">Aplicações Externas - Selecionar Aplicação/Recurso</div>
<table class="tabelaResult" id="tabelaAE">
	<thead>
		<tr>
			<th colspan="2">Aplicação/Recurso</th>
		</tr>
	</thead>
	<tbody></tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>
