<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.CursoExtensao"%>
<% CursoExtensao cursoExtensao = (CursoExtensao)request.getAttribute("cursoExtensao"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#no_cursoExtensao').html("<%= cursoExtensao.getNome() %>".clearNull());
			$this.find('label#ds_cursoExtensao').html("<%= cursoExtensao.getDescricao() %>".clearNull());			
		}
	});
</script>
<div id="title">Consultar Curso de Extens&atilde;o</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="no_cursoExtensao"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="ds_cursoExtensao"></label></td>
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