<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.AplicacaoExterna"%>
<%@page import="modelo.integra.Recurso"%>
<%@page import="modelo.integra.Parametro"%>
<script type="text/javascript">
	var thisObj;
	var object;

	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			thisObj = $this;
			object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('button#executar').click(function() {
				//executaAplicacao();
				executaReverseAjax();
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

	var executaAplicacao = function(usuario, senha) 
	{
		var div = thisObj.find('#divResultado');
		div.html('');

		var paramValor = thisObj.find('#paramValor');

		var arrParamValor = new Array();
		for ( var i = 0; i < paramValor.length; i++) {
			arrParamValor[i] = paramValor[i].value;
		}
		
		thisObj.sendRequest('../../MembroAplicacaoServlet?opcao=X',
			{usuario: usuario, senha: senha, arrParamValor: arrParamValor.join(",")}, 
			function(data) {
				if (data.trim() == 'login') {
					basicLogin.showDialog();
				}
				else {
					div.html(data);
				}
			}
		);
	};

	var executaReverseAjax = function()
	{
		var request =  new XMLHttpRequest();
		request.open("POST", "http://localhost:8080/activa/AplicacaoExternaCometServlet", true);
		request.setRequestHeader("Content-Type",
		                         "application/x-javascript;");

		request.onreadystatechange = function() {
			if (request.readyState == 4) {
                if (request.status == 200){
				    if (request.responseText) {
				    	var div = thisObj.find('#divResultado');
				    	div.html(request.responseText);
				    }
                }

                executaReverseAjax();
		  	}
		};
		
		request.send(null);
	};

	var executaAposLogin = function(usuario, senha) 
	{
		object.focus();

		executaAplicacao(usuario, senha);
	};
	
	var basicLogin = function()
	{
		return {
			showDialog:function()
			{
				request.html($('body'), null, 'servicos/aplicacoesExternas/authBasica.jsp', false, function() {
					var login = $('#login');
					login.showDialog({
						minimize: false,
						maximize: false,
						modal: true,
						showCloseButton: true,
						show: 'fold',
						title: 'Autenticação na Aplicação',
						width: '290',
						position: 'center',
						resizable: false,
						draggable: false,
						reload: false
					});				
				});
				
				return this;
			}
		}
	}();

	
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