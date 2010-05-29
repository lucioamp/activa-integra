<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Topico"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.PostTopico"%>
<%@page import="util.Ferramenta"%>
<% Usuario usuario = (Usuario)session.getAttribute("membro"); %>
<% Topico topico = (Topico)request.getAttribute("topico"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();

			var listaMensagem = $this.find('table#mensagens tbody');
			
			var button_enviaMensagem = $this.find('button#enviaMensagem');
			button_enviaMensagem.click(function(e) {
				$this.find('span#caixaDeMensagem').show();
				$(this).hide();
			});

			var adicionarMensagem = function(id, membro, mensagem, data)
			{
				var tdMembro = $('<td></td>').html(membro);
				var tdData = $('<td></td>').html(data);
				var tdMensagem = $('<td></td>').html(mensagem);
				listaMensagem.append($('<tr></tr>').append(tdMembro).append(tdData).append(tdMensagem));
			};

			$this.find('button#enviarMensagem').click(function(e) {
				var mensagem = $this.find('textarea#textMensagem');

				if(mensagem.val().length < 5)
				{
					$this.alert('Por favor, digite pelo menos 5 lentras.', null, {clickOK: function() { mensagem.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroTopicoServlet?opcao=M',
					{pkTopico: <%= topico.getPkTopico() %>, mensagem: mensagem.val(), tipo: "incluir"},
					function(data) {
						var result = trim(data);

						if(result != "false")
						{
							$this.alert('A mensagem foi enviada com sucesso.', null, {clickOK: function() {
								adicionarMensagem(0, "<%= usuario.getNome() %>", mensagem.val(), result);
								mensagem.val('');
							}});
						}else
							$this.alert('Erro ao tentar enviar a mensagem.');
					}
				);
			});

			<%		
			@SuppressWarnings ("unchecked")
			Collection<PostTopico> mensagens = (Collection<PostTopico>)request.getAttribute("mensagens");

			for(PostTopico mensagem:mensagens)
				out.print("adicionarMensagem("+mensagem.getPkPostTopico()+", '"+mensagem.getMembro().getNome()+"'.clearNull(), '"+Ferramenta.clearScape(mensagem.getDescricao())+"'.clearNull(), '"+Ferramenta.formatarData(mensagem.getData(), true)+"'.clearNull());");
			%>
		};
	});
</script>

<div id="title">Ver T&oacute;pico</div>
<table class="tabelaResult">
	<thead>
		<tr>
			<th colspan="2">-</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Data:</td>
			<td><%= Ferramenta.formatarData(topico.getData(), true) %></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td style="text-align: left;"><pre><%= topico.getDescricao() %></pre>
		</tr>
	</tbody>
</table>
<table id="mensagens" class="tabelaResult">
	<caption>Mensagens<br />
	<button type="button" class="ui-state-default ui-corner-all"
		name="enviaMensagem" id="enviaMensagem">Enviar Mensagem</button>
	<span style="display: none;" id="caixaDeMensagem"> <textarea
		name="textMensagem" id="textMensagem" cols="50" rows="10"></textarea><br>
	<button type="button" class="ui-state-default ui-corner-all"
		name="enviarMensagem" id="enviarMensagem">Enviar</button>
	</span></caption>
	<thead>
		<tr>
			<th>Membro</th>
			<th>Data</th>
			<th>Mensagem</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>