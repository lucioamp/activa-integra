<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.TipoContato"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'tipo_contato';
			
			adicionarTipo = function(id, nome)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../TipoContatoServlet?opcao=D', {pkTipo: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../TipoContatoServlet?opcao=D', {pkTipo: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../TipoContatoServlet?opcao=E", {pkTipo: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});
				
				$this.find('table tbody').append(tr
					.append($('<td></td>').html(nomeLink).click(function(e) {
						$this.newPage('../../TipoContatoServlet?opcao=D', {pkTipo: id});
					}))
				);
			}

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<TipoContato> tiposContato = (Collection<TipoContato>)request.getAttribute("tiposContato");
			for(TipoContato tipo:tiposContato)
				out.print("adicionarTipo("+tipo.getPkTipoContato()+", '"+tipo.getTipoContato()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Tipos de Contato <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>