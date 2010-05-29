<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Forum"%>
<%@page import="modelo.Topico"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.PostTopico"%>
<% Forum forum = (Forum)request.getAttribute("forum"); %>

<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
						
			var object = $this.getObject();
			
			var pkForum = <%= request.getParameter("pkForum") %>;
			
			$('button#criarTopico').click(function(e) {
				$this.newPage('servicos/forum/topico/incluir.jsp', {pkForum: pkForum});
			});
			
			var adicionarTopico = function(id, nome, qntMensagem, isOwner)
			{
				var tbody = $this.find('table#tableTopicos tbody');
				
				var tr = $('<tr></tr>');
				var tdTopico = $('<td></td>');
				var tdQnt = $('<td></td>').html(qntMensagem);
				
				$('<a></a>').attr('href', '#').html(nome).appendTo(tdTopico).click(function(e) {
					e.preventDefault();

					tab.addTab(id, '../../MembroTopicoServlet?opcao=D&pkTopico='+id+'&isOwner='+isOwner, 'Topico '+nome);
					$_SESSION['topicoTr'] = tr;
				});
				
				tbody.append(tr.append(tdTopico).append(tdQnt));
			};

			<%
			Usuario usuario = (Usuario)session.getAttribute("membro");
		
			@SuppressWarnings ("unchecked")
			Collection<Topico> topicos = (Collection<Topico>)request.getAttribute("topicos");

			for(Topico topico:topicos)
				out.print("adicionarTopico("+topico.getPkTopico()+", '"+topico.getDescricao()+"'.clearNull(), "+PostTopico.consultarPorTopico(topico).size()+", "+(topico.getMembro().getPkUsuario() == usuario.getPkUsuario() ? "true" : "false")+");");
			%>

			$this.find('div#title').html('Forum (<%= forum.getNome() %>)');
		}
	});
</script>

<div id="title">Forum (Forum1)</div>

<p style="text-align: right;">
<button type="button" class="ui-state-default ui-corner-all"
	name="criarTopico" id="criarTopico">Criar T&oacute;pico</button>
</p>

<table class="tabelaResult">
	<tbody>
		<tr><td>Data:</td><td><%= Ferramenta.formatarData(forum.getData(), true) %></td></tr>
		<tr><td>Descrição:</td><td><%= forum.getDescricao()%></td></tr>
		<tr><td>Categoria:</td><td><%= forum.getCategoriaForum().getNome() %></td></tr>
		<tr><td>Tarefa:</td><td><%= forum.getTarefa().getNome() %></td></tr>
	</tbody>
</table>
<br/><br/>

<table id="tableTopicos" class="tabelaResult">
	<thead>
		<tr>
			<th style="width: 85%;">Tópico</th>
			<th>Mensagens</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>