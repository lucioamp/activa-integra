<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.UsuarioAplicacao"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'aplicacoesExternas';

			adicionarAplicacao = function(idAplicacao, idRecurso, nome, recurso)
			{
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../MembroAplicacaoServlet?opcao=D', {idAplicacao: idAplicacao, idRecurso: idRecurso});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).empty().load("../../MembroAplicacaoServlet?opcao=E", {idAplicacao: idAplicacao, idRecurso: idRecurso}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});

				var linkExecutar = $('<a></a>').attr('href', '#').append('Executar').click(function(e) {
					$this.newPage('../../MembroAplicacaoServlet?opcao=X', {idAplicacao: idAplicacao, idRecurso: idRecurso});
				});
				
				$this.find('table tbody')
				.append(tr
					.append($('<td width=200></td>').html('<b>' + nome + '</b>'))
					.append($('<td width=400></td>').html(recurso))
					.append($('<td></td>').append(linkExecutar))
				);
			};

			var tbody = $this.find('table tbody');
			tbody.find('tr').remove();

			<%			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<UsuarioAplicacao> recursoLista = (Collection<UsuarioAplicacao>)request.getAttribute("recursoLista");
			if (recursoLista != null) {
				for (UsuarioAplicacao recurso:recursoLista) {
					out.print("adicionarAplicacao("+recurso.getIdAplicacao()+", "+recurso.getIdRecurso()+", '"+recurso.getNomeAplicacao()+"'.clearNull(), '"+recurso.getNomeRecurso()+"'.clearNull());");
				}
			}
			%>
						
			$this.find('a#incluir').click(function() {
				$this.newPage('../../MembroAplicacaoServlet?opcao=S', {});
			});
		};
	});
</script>

<table class="tabelaResult" border="0">
	<caption>Aplicações Externas <a href="#" id="incluir" title="Incluir Aplicação/Recurso"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Aplicação<br></th>
			<th>Recurso</th>
			<th></th>
		</tr>
	</thead>
	<tbody></tbody>
</table>
