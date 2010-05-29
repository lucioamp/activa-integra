<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.RecursoJSON"%>
<%@page import="modelo.integra.Parametro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../AplicacaoExternaServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			$this.find('button#carregar').click(function() {
				var url = $this.find('#url');
				var opcaoCarregar = $this.find('#opcaoCarregar:checked');

				if(url.val().length == 0)
				{
					$this.alert('Por favor, digite a URL.', null, {clickOK: function() { url.focus(); }});
					return false;
				}
		
				$this.sendRequest('../../AplicacaoExternaServlet?opcao=P',
					{url: url.val(), opcaoCarregar: opcaoCarregar.val()},
					function(data) {
						var recursos = eval("(" + data + ')');

						var url = $this.find('#urlAplicacao');
						url.html('-');

						var tbody = $this.find('table tbody');
						tbody.find('tr').remove();

						if (recursos.length > 0) {
							
							url.html(recursos[0].base);
							url.css('color', 'blue').css('font-size', '12px');

							for ( var i = 0; i < recursos.length; i++) {
								var metodo = $('<span></span>');
								metodo.html(recursos[i].metodo);
								metodo.css('color', 'green').css('font-size', '12px');

								var path = $('<span></span>');
								path.html(recursos[i].path);
								path.css('color', 'blue').css('font-size', '12px');

								var recursoTitulo = $('<input type="text" id="recursoTitulo" style="width: 250px;" value="' + recursos[i].titulo + '"/>');

								var parametrosColuna = $('<td valign=top></td>');
								var parametros = recursos[i].parametros;
								if (parametros.length > 0) {
									for ( var j = 0; j < parametros.length; j++) {
										var name = $('<span></span>');
										name.html(parametros[j].name);
										name.css('color', 'blue').css('font-size', '12px');

										parametrosColuna.append(name).append('&nbsp;');

										if (parametros[j].required) {
											var required = $('<span></span>');
											required.html('(obrigatório)');
											required.css('color', 'red').css('font-size', '12px');

											parametrosColuna.append(required);
										}

										var paramTitulo = $('<input type="text" id="paramTitulo" style="width: 250px;" value="' + parametros[j].title + '"/>');

										parametrosColuna.append(paramTitulo);
									}
								}
								else {
									var empty = $('<span></span>');
									parametrosColuna.html(empty);
								}
								
								tbody.append($('<tr></tr>')
									.append($('<td valign=top></td>').html(metodo))
									.append($('<td valign=top></td>').html(path).append(recursoTitulo))
									.append(parametrosColuna)
								);
								tbody.append($('<tr></tr>').append($('<td colspan=3>&nbsp;</td>').css('border-bottom', '2px inset black')));
							}
							
						}
					}
				);
			});
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#nome');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite o nome da aplicação.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				var authBasica = $this.find('#authBasica:checked');
				var authOpenId = $this.find('#authOpenId:checked');
				var authGAuth = $this.find('#authGAuth:checked');

				var arrAuth = new Array();
				var indexAuth = 0;
				if (authBasica.is(':checked')) {
					arrAuth[indexAuth] = 1;
					indexAuth ++;
				}

				if (authOpenId.is(':checked')) {
					arrAuth[indexAuth] = 2;
					indexAuth ++;
				}

				if (authGAuth.is(':checked')) {
					arrAuth[indexAuth] = 3;
					indexAuth ++;
				}

				if(indexAuth == 0)
				{
					$this.alert('Por favor, escolha pelo menos uma forma de autenticação.', null, null);
					return false;
				}

				var recursoTitulo = $this.find('#recursoTitulo');
				
				if (recursoTitulo.length == 0)
				{
					$this.alert('Nenhuma aplicação foi carregada.', null, null);
					return false;
				}

				var arrRecursoTitulo = new Array();
				for ( var i = 0; i < recursoTitulo.length; i++) {
					arrRecursoTitulo[i] = recursoTitulo[i].value;
				}

				var paramTitulo = $this.find('#paramTitulo');
				
				var arrParamTitulo = new Array();
				for ( var i = 0; i < paramTitulo.length; i++) {
					arrParamTitulo[i] = paramTitulo[i].value;
				}

				var url = $this.find('#url');
				var opcaoCarregar = $this.find('#opcaoCarregar:checked');

				$this.sendRequest('../../AplicacaoExternaServlet?opcao=I',
					{nomeAplicacao : nome.val(), auth: arrAuth.join(","), recursoTitulo: arrRecursoTitulo.join(","), paramTitulo: arrParamTitulo.join(","), url: url.val(), opcaoCarregar: opcaoCarregar.val()},
					function() { botaoVoltar.click(); }
				);
			});

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
			%>
		}
	});
</script>

<div id="title">Aplicações Externas - Incluir Aplicação</div>
<table style="width: 100%;" border="0">
	<thead>
		<tr>
			<td width="50">URL:</td>
			<td><input type="text" name=url id="url" style="width: 500px;" value="http://localhost:8080/activa/wadl/delicious.wadl"/></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="radio" name="opcaoCarregar" id="opcaoCarregar" value="1" checked style="display:inline;"/> <label>Arquivo WADL</label> 
				<input type="radio" name="opcaoCarregar" id="opcaoCarregar" value="2" style="display:inline;"/> <label>API (Reflexão)</label>
			</td>
			<td align="right">
				<button type="button" class="ui-state-default ui-corner-all"
					name="carregar" id="carregar">Carregar</button>
			</td>
		</tr>
	</thead>
</table>
<hr/>
<table style="width: 100%;" border="0" cellspacing="0">
	<thead>
		<tr>
			<td colspan="3" align="center"><b>Dados da Aplicação</b></td>
		</tr>
		<tr>
			<td colspan="3">Nome da Aplicação: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
				<input type="text" name="nome" id="nome" style="width: 300px; display:inline;" value="Teste"/>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				Formas de Autenticação: &nbsp;&nbsp;&nbsp;
				<input type="checkbox" id="authBasica" style="display:inline;"/><label style="font-size: 12px;">Básica &nbsp;&nbsp;&nbsp;</label> 
				<input type="checkbox" id="authOpenId" style="display:inline;"/><label style="font-size: 12px;">OpenID &nbsp;&nbsp;&nbsp;</label> 
				<input type="checkbox" id="authGAuth" style="display:inline;"/><label style="font-size: 12px;">GAuth (Google)</label> 
			</td>
		</tr>
		<tr>
			<td colspan="3">
				URL Base: &nbsp;&nbsp;&nbsp;
				<span id="urlAplicacao">-</span> 
			</td>
		</tr>
		<tr>
			<td width="50%" colspan="2">Recursos:</td>
			<td width="50%">Parâmetros:</td>
		</tr>
	</thead>
	<tbody></tbody>
	<tfoot>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td align="right" colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			</td>
		</tr>
	</tfoot>
</table>
