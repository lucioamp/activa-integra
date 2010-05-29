<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.CursoExtensao"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'curso_extensao';
			
			adicionarCursoExtensao = function(id, nome)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../CursoExtensaoServlet?opcao=D', {pkCursoExtensao: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../CursoExtensaoServlet?opcao=D', {pkCursoExtensao: id, alteracao: true});	
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
									$this.getPage(1).load("../../CursoExtensaoServlet?opcao=E", {pkCursoExtensao: id}, function() {						
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
						$this.newPage('../../CursoExtensaoServlet?opcao=D', {pkCursoExtensao: id});
					}))
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<CursoExtensao> cursos = (Collection<CursoExtensao>)request.getAttribute("membroCursoExtensao");
			for(CursoExtensao cr:cursos)
				out.print("adicionarCursoExtensao("+cr.getPkCursoExtensao()+", '"+cr.getNome()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function(){
				$this.newPage('servicos/perfil/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Cursos de Extensão<a href="#" id="incluir" title="Incluir"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>