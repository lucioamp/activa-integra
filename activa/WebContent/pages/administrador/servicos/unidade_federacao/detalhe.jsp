<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			function adicionarMunicipio(nome)
			{
				$this.find('table#tabela_municipios tbody').append('<tr><td>'+nome+'</td></tr>');
			}
			
			<%
				Uf uf = (Uf)request.getAttribute("uf");
				
				@SuppressWarnings ("unchecked")
				Collection<Municipio> colMunicipio = (Collection<Municipio>)request.getAttribute("colMunicipio");
				for(Municipio municipio:colMunicipio)
					out.print("adicionarMunicipio('"+municipio.getMunicipio()+"'.clearNull());");
			%>
			$this.find('label#nome').html("<%= uf.getEstado() %>".clearNull());
			$this.find('label#sigla').html("<%= uf.getSigla() %>".clearNull());
		}
	});
</script>

<div id="title">Detalhe da Unidade de Federa&ccedil;&atilde;o</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>Sigla:</td>
			<td><label id="sigla"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_municipios" class="tabelaResult">
				<caption>Munic&iacute;pios</caption>
				<thead>
					<tr>
						<th>Nome</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
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