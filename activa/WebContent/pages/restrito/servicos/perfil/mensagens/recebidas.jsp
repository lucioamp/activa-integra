<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Mensagem"%>
<%@page import="util.Ferramenta"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			object['cache'] = false;

			var adicionarMensagem = function(id, nome, assunto, mensagem)
			{
				mostrarMensagem = function(e) {
					e.preventDefault();
					$this.newPage('../../MembroMensagensServlet?opcao=M', {nome: nome, assunto: assunto, mensagem: mensagem, tipo: "Recebida"});
				};
				
				var tr = $('<tr></tr>').css('cursor', 'pointer');

				var tdNome = $('<td></td>').html(nome).click(mostrarMensagem);
				var tdAssunto = $('<td></td>').html(assunto).click(mostrarMensagem);
				
				var tdExcluir = $('<td></td>');
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
							$this.sendRequest('../../MembroMensagensServlet?opcao=1', {pkMensagem: id}, function(data) {
								var result = trim(data);	
								if(result == "true")
									tr.clear();
								else
									$this.alert('Erro ao tentar excluir a mensagem.');
							});
						}
					});
				});
				$this.find('table tbody').append(tr.append(tdNome).append(tdAssunto)/*.append(tdExcluir)*/);
			};
			
			<%
			@SuppressWarnings ("unchecked")
			Collection<Mensagem> mensagens = (Collection<Mensagem>)request.getAttribute("mensagens");
			for(Mensagem mensagem:mensagens)
				out.print("adicionarMensagem("+mensagem.getPkMensagem()+", '"+mensagem.getMembroOrigem().getNome()+"'.clearNull(), '"+mensagem.getAssunto()+"'.clearNull(), '"+Ferramenta.clearScape(mensagem.getMensagem())+"'.clearNull());");
			%>
		};
	});
</script>

<div id="title">Mensagens Recebidas</div>
<table class="tabelaResult">
	<thead>
		<tr>
			<th>De</th>
			<th>Assunto</th>
			<!--<th>Excluir</th> -->
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>