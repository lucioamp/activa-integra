<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'unidade_federacao';
			
			adicionarUnidade = function(id, nome, sigla)
			{
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../UfServlet?opcao=D', {pkEstado: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../UfServlet?opcao=D', {pkEstado: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../UfServlet?opcao=E", {pkEstado: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});
				
				$this.find('table tbody').append(tr
					.append($('<td></td>').html($('<a href="#"></a>').html(nomeLink).click(function(e) {
						$this.newPage('../../UfServlet?opcao=D', {pkEstado: id});
					})))
					.append($('<td></td>').html(sigla))
				);
			}			

			<%
				if(request.getAttribute("erro") != null)
					out.print("$this.alert('"+request.getAttribute("erro")+"');");
			
				@SuppressWarnings ("unchecked")
				Collection<Uf> colUf = (Collection<Uf>)request.getAttribute("colUf");
				for(Uf u:colUf)
					out.print("adicionarUnidade("+u.getPkEstado()+", '"+u.getEstado()+"'.clearNull(), '"+u.getSigla()+"'.clearNull());");	
			%>
					
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp');
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Unidades de Federa&ccedil;&atilde;o <a href="#"
		id="incluir" title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
			<th>Sigla</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>