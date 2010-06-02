<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.AplicacaoExterna"%>
<%@page import="modelo.integra.Recurso"%>
<%@page import="modelo.integra.Parametro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('button#executar').click(function() {
				var div = $this.find('#divResultado');
				div.html('');
				
				$this.sendRequest('../../MembroAplicacaoServlet?opcao=X',
					{}, 
					function(data) {
						div.html(data);
					}
				);
			});

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			// AplicacaoExterna
			AplicacaoExterna aplicacao = (AplicacaoExterna)request.getAttribute("aplicacao");
			out.print("$this.find('#idAplicacao').val('" + aplicacao.getIdAplicacao() + "');");
			
			out.print("var nomeAplicacao = $this.find('#nomeAplicacao');");
			out.print("nomeAplicacao.html('" + aplicacao.getNome() + "');");
			
			// Recurso
			Recurso recurso = (Recurso)request.getAttribute("recurso");
			out.print("$this.find('#idRecurso').val('" + recurso.getIdRecurso() + "');");
			
			out.print("var nomeRecurso = $this.find('#nomeRecurso');");
			out.print("nomeRecurso.html('" + recurso.getNome() + " (" + recurso.getMetodo() + ")');");
			
			// Parâmetros
			@SuppressWarnings ("unchecked")
			Collection<Parametro> parametroLista = (Collection<Parametro>)request.getAttribute("parametroLista");
			for (Parametro parametro:parametroLista) {
				out.print("var table = $this.find('table tbody');");
				out.print("var tr = $('<tr></tr>');");
				
				String disabled = "";
				if (parametro.isBloquearValor()) {
					disabled = "disabled=true";
				}
				
				out.print("var paramValor = $('<input type=text id=paramValor style=width:200px;display:inline; " + disabled + " />');");
				
				if (parametro.getValorPadrao() != null) {
					out.print("paramValor.val('" + parametro.getValorPadrao() + "');");	
				}
				
				out.print("table.append(tr.append($('<td width=200></td>')");
				out.print(".html('<span style=color:green;font-size:12px;>" + parametro.getTitle() + "</span>'))");
				out.print(".append($('<td></td>').html(paramValor))");
				out.print(");");
			}
			%>
			
		};
	});
</script>	
<div id="title">Aplicações Externas - Executar Aplicação</div>
<input type="hidden" id="idAplicacao">
<input type="hidden" id="idRecurso">
<table style="width: 100%;" border="0">
	<thead>
		<tr>
			<td colspan="2"><b><span id="nomeAplicacao" style="color: Blue;"></span></b></td>
		</tr>
		<tr>
			<td colspan="2"><b><span id="nomeRecurso" style="color: FireBrick; font-size: 12px;"></span></b></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
	</thead>
	<tbody></tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="executar" id="executar">Executar</button>
			</td>
		</tr>
	</tfoot>
</table>
<hr/>
<p align="center"><b>Dados Retornados</b></p>
<div id="divResultado"></div>