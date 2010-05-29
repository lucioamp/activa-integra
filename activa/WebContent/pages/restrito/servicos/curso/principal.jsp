<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Curso"%>

<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			object['cache'] = false;

			var adicionarCurso = function(id, nome, data, isOwner)
			{
				var tbody = $this.find('table tbody');
				
				var tr = $('<tr></tr>');
				var tdDetalhe = $('<td></td>');
				var tdData = $('<td></td>').html(data);
				
				var buttonDetalhe = $('<a href="#"></a>').html(nome).click(function(e){
					e.preventDefault();
					tab.addTab(id+"_detalhe", '../../MembroCursoServlet?opcao=C&tipo=detalheCurso&pkCurso='+id, 'Detalhe de '+nome);
				});
				tr.append(tdDetalhe.append(buttonDetalhe)).append(tdData);

				if(isOwner == true)
				{
					tr.contextMenu({
						buttons: {
							Abrir: {
								textBold: true,
								icon: "open",
								onClick: function() {
									tab.addTab(id+"_detalhe", '../../MembroCursoServlet?opcao=C&tipo=detalheCurso&pkCurso='+id, 'Detalhe de '+nome);
								}
							},
							Editar: {
								icon: "edit",
								onClick: function() {
									tab.addTab(id+"_alterar", '../../MembroCursoServlet?opcao=C&tipo=alterarCurso&pkCurso='+id, 'Alterar '+nome);
								}
							},
							Remover: {
								icon: "delete",
								onClick: function() {
									$this.alert('Tem certeza que deseja excluir este curso?', null, {
										dialog: DIALOG_CONFIRM,
										clickOK: function() {
											$this.sendRequest('../../MembroCursoServlet?opcao=E',	{pkCurso: id},
												function(data) {
													var result = trim(data);
				
													if(result == "true")
													{
														$this.alert('O curso foi excluida com sucesso.', null, {clickOK: function() {
															tr.remove();
															tab.removeTab(id, true);
														}});
													}else
														$this.alert('Erro ao tentar excluir o curso.');
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
			Collection<Curso> cursos = (Collection<Curso>)request.getAttribute("cursos");
			for(Curso curso:cursos)
				out.print("adicionarCurso("+curso.getPkServico()+", '"+curso.getNome()+"'.clearNull(), '"+Ferramenta.formatarData(curso.getData(), true)+"', "+(curso.getMembro().getPkUsuario() == usuario.getPkUsuario() ? "true" : "false")+");");
			%>
		};
	});
</script>

<table class="tabelaResult">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Data</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>