<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Bairro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			function adicionarBairro(nome)
			{
				$this.find('table#tabela_bairros tbody').append('<tr><td>'+nome+'</td></tr>');
			}
			
			<%
				Municipio municipio = (Municipio)request.getAttribute("municipio");
				
				@SuppressWarnings ("unchecked")
				Collection<Bairro> colBairro = (Collection<Bairro>)request.getAttribute("colBairro");
				for(Bairro bairro:colBairro)
					out.print("adicionarBairro('"+bairro.getBairro()+"'.clearNull());");
			%>
			$this.find('label#nome').html("<%= municipio.getMunicipio() %>".clearNull());
			$this.find('label#uf').html("<%= municipio.getUf().getEstado() %>".clearNull());
		}
	});
</script>

<div id="title">Detalhe do Munic&iacute;pio</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><label id="uf"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_bairros" class="tabelaResult">
				<caption>Bairros</caption>
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