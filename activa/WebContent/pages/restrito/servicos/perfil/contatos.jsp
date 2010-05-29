<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.ContatoUsuario"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'contato';
			
			adicionarContato = function(id,tipo,nome)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);				
				var tr = $('<tr></tr>');

				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../MembroContatoServlet?opcao=D', {pkContatoUsuario: id, tipo: "1", alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../MembroContatoServlet?opcao=E", {pkContatoUsuario: id}, function() {						
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});
				
				$this.find('table tbody').append(tr
					.append($('<td>'+tipo+'</td>'))
					.append($('<td>'+nome+'</td>'))						
					/*.append($('<td></td>').html(nomeLink).click(function(e) {
						$this.newPage('../../MembroContatoServlet?opcao=D', {pkContatoUsuario: id});
					}))*/
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<ContatoUsuario> contatos = (Collection<ContatoUsuario>)request.getAttribute("contatos");
			for(ContatoUsuario contato:contatos)
				out.print("adicionarContato("+contato.getPkContatoUsuario()+", '"+contato.getTipoContato().getTipoContato()+"'.clearNull(),'"+contato.getContato()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function(){
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Contatos<a href="#" id="incluir" title="Incluir"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Tipo</th>
			<th>Descrição</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>