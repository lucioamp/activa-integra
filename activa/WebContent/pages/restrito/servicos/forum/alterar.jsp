<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Forum"%>
<%@page import="modelo.Etapa"%>
<%@page import="modelo.Tarefa"%>
<%@page import="modelo.CategoriaForum"%>
<%@page import="util.Ferramenta"%>
<% Forum forum = (Forum)request.getAttribute("forum"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			var object = $this.getObject();

			if($_SESSION['forumTr'] != null)
			{
				object['forumTr'] = $_SESSION['forumTr'];
				
				delete $_SESSION['forumTr'];
				$_SESSION['forumTr'] = null;
			}

			var nome = $this.find('input#no_servico');
			var desc = $this.find('textarea#ds_servico');
			var categoria = $this.find('select#categoria');
			var tarefa = $this.find('select#tarefa');
			$this.find('button#alterar').click(function() {

				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				if(categoria.val() == 0)
				{
					$this.alert('Por favor, selecione uma categoria.', null, {clickOK: function() { categoria.focus(); }});
					return false;
				}

				if(tarefa.val() == 0)
				{
					$this.alert('Por favor, selecione uma tarefa.', null, {clickOK: function() { tarefa.focus(); }});
					return false;
				}				

				$this.sendRequest('../../MembroForumServlet?opcao=A',
					{pkServico: <%= forum.getPkServico() %>, nome: nome.val(), desc: desc.val(), pkCategoria: categoria.val(), pkTarefa: tarefa.val()},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Forum alterado com sucesso.', null, {clickOK: function() {
								//tab.tabs('select', 0);
								object['forumTr'].find('td:first a').html(nome.val()+(desc.val() != null && desc.val().length > 0 ? "("+desc.val()+")" : ""));
							}});
						}else
							$this.alert('Erro ao tentar alterar o forum.');
					}
				);
			});

			trTarefa = $this.find('tr#tarefa');
			categoria.change(function() {
				var pkCategoria = $(this).val();
				if(pkCategoria == 0)
				{
					trTarefa.hide();
					tarefa.empty().addOption("0", "");
				}else
				{
					trTarefa.show();
					$this.sendRequest('../../MembroForumServlet?opcao=C&tipo=consultarTarefa', {pkCategoria: pkCategoria},	function(data) {
						var result = trim(data);
						tarefa.empty().addOption("0", "").append(result);
					});
				}
			});

			<%
			@SuppressWarnings ("unchecked")
			Collection<CategoriaForum> categorias = (Collection<CategoriaForum>)request.getAttribute("categorias");
			for(CategoriaForum categoria:categorias)
				out.print("categoria.addOption('"+categoria.getPkCatForum()+"'.clearNull(), '"+categoria.getNome()+"'.clearNull());");
			%>

			nome.val('<%= forum.getNome() %>');
			desc.val('<%= Ferramenta.clearScape(forum.getDescricao()) %>'.clearNull());
			categoria.selectOptions('<%= forum.getCategoriaForum().getPkCatForum() %>');

			trTarefa.show();
			$this.sendRequest('../../MembroForumServlet?opcao=C&tipo=consultarTarefa', {pkCategoria: <%= forum.getCategoriaForum().getPkCatForum() %>},	function(data) {
				var result = trim(data);
				tarefa.empty().addOption("0", "").append(result).selectOptions('<%= forum.getTarefa().getPkTarefa() %>');
			});

			$this.find('div#title').html('Alterar (<%= forum.getNome() %>)');
		};
	});
</script>

<div id="title">Alterar Forum</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_servico" id="no_servico" /></td>
		</tr>
		<tr>
			<td style="width: 30%;">Categoria:</td>
			<td><select name="categoria" id="categoria">
				<option value="0">
			</select></td>
		</tr>
		<tr id="tarefa" style="display: none;">
			<td style="width: 30%;">Tarefa:</td>
			<td><select name="tarefa" id="tarefa">
				<option value="0">
			</select></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"></textarea></td>
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