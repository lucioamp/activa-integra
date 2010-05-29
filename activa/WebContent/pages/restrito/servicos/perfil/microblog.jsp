<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.MicroBlog"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			adicionarMicroBlog = function(id, desc)
			{				
				var descLik = $('<a href="#"></a>').html(desc).click(function(e) {
					e.preventDefault();
					$this.sendRequest("../../MembroMicroBlogServlet?opcao=A", {pkMicroBlog: id}, function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('MicroBlog selecionado.');
							menuOptionConteudo.find('input#microBlog').val(desc);
						}else
							$this.alert('Erro ao tentar selecionar MicroBlog.');
					});
				});
				
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Selecionar: {
							textBold: true,
							onClick: function() {
								descLik.click();
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../MembroMicroBlogServlet?opcao=E", {pkMicroBlog: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});
				
				$this.find('table tbody').append(tr
					.append($('<td></td>').html(descLik))
				);
			}
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<MicroBlog> microblogs = (Collection<MicroBlog>)request.getAttribute("microblogs");
			for(MicroBlog microblog:microblogs)
				out.print("adicionarMicroBlog("+microblog.getPkMicroBlog()+", '"+microblog.getDescricao()+"'.clearNull());");	
			%>
			
		};
	});

</script>
<div id="title">Microblogs</div>
<table class="tabelaResult">
	<thead>
		<tr>
			<th>Descri&ccedil;&atilde;o (Selecionar)</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>