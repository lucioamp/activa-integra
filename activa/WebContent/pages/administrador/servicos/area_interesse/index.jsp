<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.AreaInteresse"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'area_interesse';
			
			adicionarInteresse = function(id, nome, desc)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../AreaInteresseServlet?opcao=D', {pkAreaInteresse: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../AreaInteresseServlet?opcao=D', {pkAreaInteresse: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../AreaInteresseServlet?opcao=E", {pkAreaInteresse: id}, function() {
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
						$this.newPage('../../AreaInteresseServlet?opcao=D', {pkAreaInteresse: id});
					}))
					.append($('<td></td>').html(desc))
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<AreaInteresse> areas = (Collection<AreaInteresse>)request.getAttribute("areas");
			for(AreaInteresse area:areas)
				out.print("adicionarInteresse("+area.getPkAreaInteresse()+", '"+area.getNome()+"'.clearNull(), '"+area.getDescricao()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>&Aacute;reas de Interesse <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
			<th>Descri&ccedil;&atilde;o</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>