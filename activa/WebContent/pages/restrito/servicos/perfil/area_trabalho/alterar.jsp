<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.AreaTrabalho"%>
<% AreaTrabalho areaTrabalho = (AreaTrabalho)request.getAttribute("areaTrabalho"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../AreaTrabalhoServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			
			$this.find('button#alterar').click(function() {
				var no_areaTrabalho = $this.find('#no_areaTrabalho');
				var ds_areaTrabalho = $this.find('#ds_areaTrabalho');
				
				if(no_areaTrabalho.val().length == 0)
				{
					$this.alert('Por favor, preencha o campo nome.', null, {clickOK: function() { no_areaTrabalho.focus(); }});
					return false;
				}
				
				if(ds_areaTrabalho.val().length == 0)
				{
					$this.alert('Por favor, preencha o campo Descri&ccedil;&atilde;o.', null, {clickOK: function() { ds_areaTrabalho.focus(); }});
					return false;
				}


				$this.sendRequest('../../AreaTrabalhoServlet?opcao=A',
					{pkAreaTrabalho: <%= areaTrabalho.getPkAreaTrabalho() %>, no_areaTrabalho: no_areaTrabalho.val(), ds_areaTrabalho: ds_areaTrabalho.val()},
					function() { botaoVoltar.click(); }
				);
			});

			$this.find('input#no_areaTrabalho').val("<%= areaTrabalho.getNome() %>".clearNull());
			$this.find('textarea#ds_areaTrabalho').val("<%= areaTrabalho.getDescricao() %>".clearNull());			
		}
	});
</script>

<div id="title">Alterar Area de Trabalho</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_areaTrabalho"
				id="no_areaTrabalho" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_areaTrabalho" name="ds_areaTrabalho" cols="50" rows="10"></textarea></td>
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