<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Blog"%>

<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			object['cache'] = false;

			var adicionarBlog = function(id, nome, data, por,isOwner)
			{
				var tbody = $this.find('table tbody');
				
				var tr = $('<tr></tr>');
				var tdDetalhe = $('<td></td>');
				var tdData = $('<td></td>').html(data);
				var tdPor = $('<td></td>').html(por);
				
				var buttonDetalhe = $('<a href="#"></a>').html(nome).click(function(e){
					e.preventDefault();
					tab.addTab(id+"_detalhe", '../../MembroBlogServlet?opcao=C&tipo=detalheBlog&pkBlog='+id, 'Detalhe de '+nome);
				});
				tr.append(tdDetalhe.append(buttonDetalhe)).append(tdData).append(tdPor);

				if(isOwner == true)
				{
					tr.contextMenu({
						buttons: {
							Abrir: {
								textBold: true,
								icon: "open",
								onClick: function() {
									tab.addTab(id+"_detalhe", '../../MembroBlogServlet?opcao=C&tipo=detalheBlog&pkBlog='+id, 'Detalhe de '+nome);
								}
							},
							Editar: {
								icon: "edit",
								onClick: function() {
									tab.addTab(id+"_alterar", '../../MembroBlogServlet?opcao=C&tipo=alterarBlog&pkBlog='+id, 'Alterar '+nome);
								}
							},
							Remover: {
								icon: "delete",
								onClick: function() {
									$this.alert('Tem certeza que deseja excluir este blog?', null, {
										dialog: DIALOG_CONFIRM,
										clickOK: function() {
											$this.sendRequest('../../MembroBlogServlet?opcao=E',	{pkBlog: id},
												function(data) {
													var result = trim(data);
				
													if(result == "true")
													{
														$this.alert('O blog foi excluida com sucesso.', null, {clickOK: function() {
															tr.remove();
															tab.removeTab(id, true);
														}});
													}else
														$this.alert('Erro ao tentar excluir o blog.');
												}
											);
										}
									});
								}
							}
						}
					});
				}
				
				tbody.append(tr);
			};

			<%
			Usuario usuario = (Usuario)session.getAttribute("membro");
		
			@SuppressWarnings ("unchecked")
			Collection<Blog> blogs = (Collection<Blog>)request.getAttribute("blogs");
			for(Blog blog:blogs)
				out.print("adicionarBlog("+blog.getPkServico()+", '"+blog.getNome()+"'.clearNull(), '"+Ferramenta.formatarData(blog.getData(), true)+"'.clearNull(), '"+blog.getMembro().getNome()+"'.clearNull(), "+(blog.getMembro().getPkUsuario() == usuario.getPkUsuario() ? "true" : "false")+");");
			%>
		};
	});
</script>

<table class="tabelaResult">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Data</th>
			<th>Por</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>