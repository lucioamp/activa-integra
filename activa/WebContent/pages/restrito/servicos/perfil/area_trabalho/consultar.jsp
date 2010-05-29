<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.AreaTrabalho"%>
<% AreaTrabalho areaTrabalho = (AreaTrabalho)request.getAttribute("areaTrabalho"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#no_AreaTrabalho').html("<%= areaTrabalho.getNome() %>".clearNull());
			$this.find('label#ds_AreaTrabalho').html("<%= areaTrabalho.getDescricao() %>".clearNull());			
		}
	});
</script>
<div id="title">Consultar Area de Trabalho</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="no_AreaTrabalho"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_AreaTrabalho"></label></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>