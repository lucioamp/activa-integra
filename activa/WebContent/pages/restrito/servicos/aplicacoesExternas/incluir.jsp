<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.AplicacaoExterna"%>
<%@page import="modelo.integra.Recurso"%>
<%@page import="modelo.integra.Parametro"%>

<%@page import="modelo.integra.UsuarioAplicacao"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('button#listar').click(function() {
				$this.newPage('../../MembroAplicacaoServlet?opcao=C', {});
			});

			$this.find('button#salvar').click(function() {
				var idUsuarioAplicacao = $this.find('#idUsuarioAplicacao');
				var idAplicacao = $this.find('#idAplicacao');
				var idRecurso = $this.find('#idRecurso');
				var usar = $this.find('#usar');
				var paramValor = $this.find('#paramValor');
				var bloquear = $this.find('#bloquear');

				var permissao = $this.find('#permissao:checked');
				var mostrarJanela = $this.find('#mostrarJanela');
				var twoWay = $this.find('#twoWay');
				var opcaoTwoWay = $this.find('#opcaoTwoWay:checked');
				var tempoValor = $this.find('#tempoValor');
				

				var arrUsar = new Array();
				for ( var i = 0; i < usar.length; i++) {
					if (usar[i].checked) {
						arrUsar[i] = usar[i].value;
					}
				}

				var arrParamValor = new Array();
				for ( var i = 0; i < paramValor.length; i++) {
					arrParamValor[i] = paramValor[i].value;
				}
				
				var arrBloquear = new Array();
				for ( var i = 0; i < bloquear.length; i++) {
					if (bloquear[i].checked) {
						arrBloquear[i] = bloquear[i].value;
					}
				}

				$this.sendRequest('../../MembroAplicacaoServlet?opcao=A',
					{idUsuarioAplicacao: idUsuarioAplicacao.val(), idAplicacao : idAplicacao.val(), idRecurso: idRecurso.val(), arrUsar: arrUsar.join(","), arrParamValor : arrParamValor.join(","), 
					arrBloquear: arrBloquear.join(","), permissao: permissao.val(), mostrarJanela: mostrarJanela.val(), twoWay: twoWay.is(':checked'), 
					opcaoTwoWay: opcaoTwoWay.val(), tempoValor: tempoValor.val()},
					function(data) {
						var usuarioAplicacao = eval("(" + data + ')');
						
						var idUsuarioAplicacao = $this.find('#idUsuarioAplicacao');
						idUsuarioAplicacao.val(usuarioAplicacao.idUsuarioAplicacao);
						
						$this.alert(usuarioAplicacao.mensagem); 
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
			
			// UsuarioAplicacao
			UsuarioAplicacao usuarioAplicacao = (UsuarioAplicacao)request.getAttribute("usuarioAplicacao");
			if (usuarioAplicacao != null) {
				out.print("$this.find('#idUsuarioAplicacao').val('" + usuarioAplicacao.getIdUsuarioAplicacao() + "');");
				
				out.print("var permissao = $this.find('#permissao');");
				out.print("var mostrarJanela = $this.find('#mostrarJanela');");
				out.print("var twoWay = $this.find('#twoWay');");
				out.print("var opcaoTwoWay = $this.find('#opcaoTwoWay');");
				out.print("var tempoValor = $this.find('#tempoValor');");
				
				out.print("permissao.filter('[value=" + usuarioAplicacao.getPermissao() + "]').attr('checked', true);");
				out.print("mostrarJanela.val('" + usuarioAplicacao.getMostrarJanela() + "');");
				
				if (usuarioAplicacao.getAtualizacaoAutomatica() != null) {
					out.print("twoWay.attr('checked', true);");
					out.print("opcaoTwoWay.filter('[value=" + usuarioAplicacao.getAtualizacaoAutomatica() + "]').attr('checked', true);");
					out.print("tempoValor.val('" + usuarioAplicacao.getTempoValor() + "');");
				}
			}
			
			// Recurso
			Recurso recurso = (Recurso)request.getAttribute("recurso");
			out.print("$this.find('#idRecurso').val('" + recurso.getIdRecurso() + "');");
			
			out.print("var nomeRecurso = $this.find('#nomeRecurso');");
			out.print("nomeRecurso.html('" + recurso.getNome() + "');");
			
			if (recurso.getParametros() != null) {
				for (Parametro parametro:recurso.getParametros()) {
					String disabled = "";
					if (parametro.isRequired()) {
						disabled = "checked disabled=true";
					}
					else if (parametro.isUsarParametro()) {
						disabled = "checked";
					}
					
					String bloquearChecked = "";
					if (parametro.isBloquearValor()) {
						bloquearChecked = "checked";
					}
					
					out.print("var usarCheck = $('<input type=checkbox id=usar style=display:inline; " + disabled + " value=" + parametro.getIdParametro() + " />');");
					out.print("var paramValor = $('<input type=text id=paramValor style=width:200px;display:inline; />');");
					out.print("var bloquearCheck = $('<input type=checkbox id=bloquear style=display:inline; " + bloquearChecked + " value=" + parametro.getIdParametro() + " />');");
					
					if (parametro.getValorPadrao() != null) {
						out.print("paramValor.val('" + parametro.getValorPadrao() + "');");	
					}
					
					out.print("var table = $this.find('table tbody');");
					out.print("var tr = $('<tr></tr>');");
					
					out.print("table.append(tr.append($('<td></td>').html(usarCheck)");
					out.print(".append('&nbsp;')");
					out.print(".append('<span style=color:green;font-size:12px;>" + parametro.getTitle() + "</span>'))");
					out.print(".append($('<td width=200></td>').html(paramValor))");
					out.print(".append($('<td></td>').html(bloquearCheck).append('<span style=font-size:12px;>Bloquear Valor</span>'))");
					out.print(");");
					
				}
			}
			%>
		};
	});
</script>	
<div id="title">Aplicações Externas - Editar Mashup</div>
<input type="hidden" id="idUsuarioAplicacao">
<input type="hidden" id="idAplicacao">
<input type="hidden" id="idRecurso">
<table style="width: 100%;" border="0">
	<thead>
		<tr>
			<td colspan="3"><b><span id="nomeAplicacao" style="color: Blue;"></span></b></td>
		</tr>
		<tr>
			<td colspan="3"><b><span id="nomeRecurso" style="color: FireBrick; font-size: 12px;"></span></b></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	</thead>
	<tbody></tbody>
	<tfoot>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" width="70%">&nbsp;</td>
			<td style="font-size: 12px;">
				<fieldset style="width:230px;">
					<legend><span style="color: CornflowerBlue;">Permissão</span></legend>
					<input id="permissao" name="permissao" type="radio" value="1" style=display:inline; checked/>Somente Usuário<br>
					<input id="permissao" name="permissao" type="radio" value="2" style=display:inline;/>Compartilhado
				</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td style="font-size: 12px;">
				<fieldset style="width:230px;">
					<legend><span style="color: CornflowerBlue;">Mostrar na Janela</span></legend>
					<select id="mostrarJanela" style="width:220px;">
						<option value=""></option>
						<option value="Favoritos">Favoritos</option>
					</select>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
			<td style="font-size: 12px;">
				<fieldset style="width:230px;">
					<legend><span style="color: CornflowerBlue;">Habilitação Automática dos Dados</span></legend>
					<input type=checkbox id=twoWay style=display:inline;/>Habilitar Two-Way<br>
					&nbsp;&nbsp;&nbsp;<input id="opcaoTwoWay" name="opcaoTwoWay" type="radio" value="1" style=display:inline; checked/>Tempo
						&nbsp;<input type=text id=tempoValor style=width:60px;display:inline;/>&nbsp;<i>minutos</i><br>
					&nbsp;&nbsp;&nbsp;<input id="opcaoTwoWay" name="opcaoTwoWay" type="radio" value="2" style=display:inline;/>Novos Dados
				</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td align="right" colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name=""listar"" id="listar">Listar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="salvar" id="salvar">Salvar</button>
			</td>
		</tr>
	</tfoot>
</table>	