<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Ambiente"%>
<%@page import="modelo.Membro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var membro = $this.find('select#membro');
			$this.find('#enviarMensagem').click(function(){
				var texto = $this.find('textarea#texto');
				var assunto = $this.find('input#assunto');
				
				if(membro.val() == 0)
					$this.alert('Por favor, selecione um membro.', null, {clickOK: function() { membro.focus(); } });
				else if(assunto.val().length == 0)
					$this.alert('Por favor, digite um assunto.', null, {clickOK: function() { assunto.focus(); } });
				else if(texto.val().length < 5)
					$this.alert('O texto tem que possuir no m&iacute;nimo 5 letras.', null, {clickOK: function() { texto.focus(); } });
				else
				{
					$this.sendRequest('../../MembroMensagensServlet?opcao=0', {
						destino: membro.val(), assunto: assunto.val(), mensagem: texto.val()},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('A mensagem foi enviada com sucesso.', null, {
								clickOK: function() {
									texto.val('');
									assunto.val('');
									membro.val(0);
								}
							});					
						}else
						{
							$this.alert('Erro ao tentar envia a mensagem.');
						}
					});
				}
			});
			<%
			Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");
			
			Collection<Membro> membros = (Collection<Membro>)ambiente.consultarMembros();
			for(Membro membro:membros)
				out.print("membro.addOption('"+membro.getPkUsuario()+"'.clearNull(), '"+membro.getNome()+"'.clearNull());");
			%>

			membro.selectOptions("0");
		};
	});
</script>

<div id="title">Enviar Mensagem</div>
<table>
	<tbody>
		<tr>
			<td style="width: 10%;">Membro:</td>
			<td><select id="membro" name="membro">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Assunto:</td>
			<td><input type="text" id="assunto" name="assunto" /></td>
		</tr>
		<tr>
			<td colspan="2">Mensagem:</td>
		</tr>
		<tr>
			<td colspan="2"><textarea cols="60" rows="9" id="texto"
				name="texto"></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="enviarMensagem" id="enviarMensagem">Enviar</button>
			</td>
		</tr>
	</tfoot>
</table>