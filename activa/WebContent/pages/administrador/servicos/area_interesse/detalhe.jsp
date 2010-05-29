<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.AreaInteresse"%>
<%@page import="modelo.AreaInteresseTag"%>
<%@page import="modelo.Tag"%>
<% AreaInteresse area = (AreaInteresse)request.getAttribute("area"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			function adicionarTag(nome)
			{
				$this.find('table#tabela_tags tbody').append('<tr><td>'+nome+'</td></tr>');
			}
			
			<%
			@SuppressWarnings ("unchecked")
			Collection<AreaInteresseTag> colTag = (Collection<AreaInteresseTag>)request.getAttribute("colTag");
			for(AreaInteresseTag area_tag:colTag)
				out.print("adicionarTag('"+area_tag.getTag().getNome()+"'.clearNull());");
			%>
			
			$this.find('label#nome').html("<%= area.getNome() %>".clearNull());
		}
	});
</script>

<div id="title">Detalhe da &Aacute;rea de Interesse</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="desc"><%= area.getDescricao() %></label></td>
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
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>