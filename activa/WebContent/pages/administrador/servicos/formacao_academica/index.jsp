<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.FormacaoAcademica"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'formacao_academica';
			
			adicionarFormacao = function(id, nome)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
					$this.newPage('../../FormacaoAcademicaServlet?opcao=D', {pkFormacao: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
							$this.newPage('../../FormacaoAcademicaServlet?opcao=D', {pkFormacao: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../FormacaoAcademicaServlet?opcao=E", {pkFormacao: id}, function() {
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
						$this.newPage('../../FormacaoAcademicaServlet?opcao=D', {pkFormacao: id});
					}))
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<FormacaoAcademica> formacoes = (Collection<FormacaoAcademica>)request.getAttribute("formacoes");
			for(FormacaoAcademica formacao:formacoes)
				out.print("adicionarFormacao("+formacao.getPkFormacaoAcademica()+", '"+formacao.getNome()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Forma&ccedil;&otilde;es Academica <a href="#"
		id="incluir" title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>