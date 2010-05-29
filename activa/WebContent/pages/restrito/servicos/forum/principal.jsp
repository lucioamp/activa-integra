<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Forum"%>
<%@page import="modelo.CategoriaForum"%>
<%@page import="modelo.Usuario"%>

<%@page import="modelo.Topico"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			object['cache'] = false;
			
			var adicionarCategoria = function(id, nome)
			{
				$this.find('table#tabelaForum tbody').append($('<tr></tr>').attr('id', id).html('<th colspan="5">- '+nome+'</th>'));
			};
			
			var adicionarForum = function(id, param, isOwner)
			{
				var tbody = $this.find('table#tabelaForum tbody');
				
				var tr = $('<tr></tr>');
				
				var tdDetalhe = $('<td></td>').css('text-align', 'left');
				var tdQntTopicos = $('<td></td>').html(param.qntTopicos);
				
				$('<a></a>').attr('href', '#').attr('title', 'Consultar').html(param.nome + (param.descricao != null && param.descricao.length > 0 ? ' ('+param.descricao+')' : '')).appendTo(tdDetalhe).click(function(e){
					e.preventDefault();
					tab.addTab(id+"_detalhe", '../../MembroForumServlet?opcao=F&pkForum='+id, 'Detalhe '+param.nome, $(this));
				});
				
				if(isOwner == true)
				{
					tr.contextMenu({
						buttons: {
							Abrir: {
								textBold: true,
								icon: "open",
								onClick: function() {
									tab.addTab(id+"_detalhe", '../../MembroForumServlet?opcao=F&pkForum='+id, 'Detalhe '+param.nome);
								}
							},
							Editar: {
								icon: "edit",
								onClick: function() {
									$_SESSION['forumTr'] = tr;
									tab.addTab(id+"_alterar", '../../MembroForumServlet?opcao=C&tipo=alterarForum&pkForum='+id, 'Alterar '+param.nome);
								}
							},
							Remover: {
								icon: "delete",
								onClick: function() {
									$this.alert('Se remover este forum, será removido seus tópicos e suas mensagens, tem certeza disso?', null, {
										width: 400,
										dialog: DIALOG_CONFIRM,
										clickOK: function() {
											$this.sendRequest('../../MembroForumServlet?opcao=E',	{ pkForum: id},
												function(data) {
													var result = trim(data);
	
													if(result == "true")
													{
														$this.alert('O forum foi excluido com sucesso.', null, {clickOK: function() {
															tr.remove();
															tab.removeTab(id, true);
														}});
													}else
														$this.alert('Erro ao tentar excluir o forum.');
												}
											);
										}
									});
								}
							}
						}
					});
				}					
				
				tbody.find('tr#'+param.categoria).after(tr.append(tdDetalhe).append(tdQntTopicos));
			};

			<%
				Usuario usuario = (Usuario)session.getAttribute("membro");
			
				@SuppressWarnings ("unchecked")
				Collection<CategoriaForum> categorias = (Collection<CategoriaForum>)request.getAttribute("categorias");
				for(CategoriaForum categoria:categorias)
				{
					out.print("adicionarCategoria("+categoria.getPkCatForum()+", '"+categoria.getNome()+"'.clearNull());");
					
					Collection<Forum> foruns = Forum.consultarPorCategoria(categoria);
					for(Forum forum:foruns)
						out.print("adicionarForum("+forum.getPkServico()+", {nome: '"+forum.getNome()+"'.clearNull(), descricao: '"+forum.getDescricao()+"'.clearNull(), qntTopicos: "+Topico.consultarPorForum(forum).size()+", categoria: "+categoria.getPkCatForum()+"}, "+(forum.getMembro().getPkUsuario() == usuario.getPkUsuario() ? "true" : "false")+");");
				}
			%>
		};
	});
</script>

<div id="tabs-1">
<table id="tabelaForum" class="tabelaResult">
	<thead>
		<tr>
			<th style="width: 50%;">&nbsp;</th>
			<th>T&oacute;picos</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>