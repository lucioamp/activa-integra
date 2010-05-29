<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.CategoriaArtefato"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'categoria_artefato';
			
			adicionarCategoria = function(id, nome, desc)
			{				
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../CategoriaArtefatoServlet?opcao=D', {pkCategoria: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../CategoriaArtefatoServlet?opcao=D', {pkCategoria: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../CategoriaArtefatoServlet?opcao=E", {pkCategoria: id}, function() {
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
						$this.newPage('../../CategoriaArtefatoServlet?opcao=D', {pkCategoria: id});
					}))
					.append($('<td></td>').html(desc))
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<CategoriaArtefato> categorias = (Collection<CategoriaArtefato>)request.getAttribute("categorias");
			for(CategoriaArtefato categoria:categorias)
				out.print("adicionarCategoria("+categoria.getPkCatArtefato()+", '"+categoria.getNome()+"'.clearNull(), '"+categoria.getDescricao()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Categorias de Artefatos <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
			<th>Descri&ccedil;&atilde;o</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>