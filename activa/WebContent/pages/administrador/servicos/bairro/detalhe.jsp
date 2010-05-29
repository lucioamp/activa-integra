<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.Bairro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

		<%
		Bairro bairro = (Bairro)request.getAttribute("bairro");
		%>
		$this.find('label#nome').html("<%= bairro.getBairro() %>".clearNull());
		$this.find('label#uf').html("<%= bairro.getMunicipio().getUf().getEstado() %>".clearNull());
		$this.find('label#municipio').html("<%= bairro.getMunicipio().getMunicipio() %>".clearNull());
			
			
		}
	});
</script>

<div id="title">Detalhe do Bairro</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>Uf:</td>
			<td><label id="uf"></label></td>
		</tr>
		<tr>
			<td>Munic&iacute;pio:</td>
			<td><label id="municipio"></label></td>
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