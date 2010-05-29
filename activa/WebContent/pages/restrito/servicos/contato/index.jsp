<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.ContatoUsuario"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'area_trabalho';
			
			adicionarContato = function(id, tipo, desc, tipo_id)
			{				
				var tr = $('<tr></tr>');

				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../MembroContatoServlet?opcao=D', {id: id, tipo_contato: tipo, desc: desc, tipo_id: tipo_id, tipo: "paginaAlterar"});	
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
					.append($('<td></td>').html(tipo))
					.append($('<td></td>').html(desc))
				);
			};
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<ContatoUsuario> contatos = (Collection<ContatoUsuario>)request.getAttribute("contatos");
			for(ContatoUsuario contato:contatos)
				out.print("adicionarContato("+contato.getPkContatoUsuario()+", '"+contato.getTipoContato().getTipoContato()+"', '"+contato.getContato()+"', "+contato.getTipoContato().getPkTipoContato()+");");	
			%>
			
			$this.find('a#incluir').click(function(){
				$this.newPage('servicos/contato/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Contatos<a href="#" id="incluir" title="Incluir"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th>Tipo</th>
			<th>Descrição</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>