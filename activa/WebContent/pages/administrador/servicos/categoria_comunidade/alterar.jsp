<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.CategoriaComunidade"%>
<% CategoriaComunidade categoria = (CategoriaComunidade)request.getAttribute("categoria"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../CategoriaComunidadeServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
						
			$this.find('button#alterar').click(function() {
				var nome = $this.find('#nome');
				var desc = $this.find('#desc');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}
				
				$this.sendRequest('../../CategoriaComunidadeServlet?opcao=A',
					{pkCategoria: <%= categoria.getPkCatComunidade() %>, nome: nome.val(), descricao: desc.val()},
					function() { botaoVoltar.click(); }
				);
			});

			$this.find('input#nome').val("<%= categoria.getNome() %>".clearNull());
		}
	});
</script>

<div id="title">Alterar Categoria de Comunidade</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="desc" name="desc"><%= categoria.getDescricao() %></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>