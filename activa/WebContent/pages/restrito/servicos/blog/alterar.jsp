<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Blog"%>
<%@page import="modelo.Aula"%>
<% Blog blog = (Blog)request.getAttribute("blog"); %>

<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, tab) {

			var object = $this.getObject();

			var id = '<%= blog.getPkServico() %>';

			var nome = $this.find('input#no_curso').val('<%= blog.getNome() %>');
			var desc = $this.find('textarea#ds_servico');
			$this.find('div#title').html('Altera��o de <%= blog.getNome() %>');
			
			$this.find('button#alterar').click(function (e) {
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descri��o.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroBlogServlet?opcao=A',
					{id: id, nome: nome.val(), desc: desc.val()},
					function(data) {
						var result = trim(data);

						if(result == "true")
							$this.alert('Blog alterado com sucesso.', null, {clickOK: function(e) {
								tab.tabs('load', object['id']);
							}});
						else
							$this.alert('Erro ao tentar alterar o blog.');
					}
				);
			});
		};
	});
</script>

<div id="title"></div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_curso" id="no_curso" /></td>
		</tr>
		<tr>
			<td valign="top">Descri&ccedil;&atilde;o:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"><%= blog.getDescricao() %></textarea></td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			</td>
		</tr>
	</tfoot>
</table>