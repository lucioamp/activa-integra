<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Bairro"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Uf"%>
<% Bairro bairro = (Bairro)request.getAttribute("bairro"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../BairroServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});

			var nome = $this.find('input#nome');
			var uf = $this.find('select#uf');
			var municipio = $this.find('select#municipio');
			$this.find('button#alterar').click(function() {				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				if(municipio.val() == 0)
				{
					$this.alert('Por favor, selecione um munic&iacute;pio.', null, {clickOK: function() { municipio.focus(); }});
					return false;
				}

				$this.sendRequest('../../BairroServlet?opcao=A',
					{pkBairro: <%= bairro.getPkBairro() %>, bairro: nome.val(), municipio: municipio.val()},
					function() { botaoVoltar.click(); }
				);
			});

			trMunicipio = $this.find('tr#municipio');
			uf.change(function() {
				var pkUf = $(this).val();
				if(pkUf == 0)
				{
					trMunicipio.hide();
					municipio.empty().addOption("0", "");
				}else
				{
					trMunicipio.show();
					$this.sendRequest('../../BairroServlet?opcao=M', {pkEstado: pkUf},	function(data) {
						municipio.empty().addOption("0", "").append(data);
					});
				}
			});

			<%
			Collection<Uf> colUf = Uf.consultarTodos();
			for(Uf uf:colUf)
				out.print("uf.addOption('"+uf.getPkEstado()+"', '"+uf.getEstado()+"'.clearNull());");
			
			Collection<Municipio> colMun = Municipio.consultarPorUf(bairro.getMunicipio().getUf());
			for(Municipio mun:colMun)
				out.print("municipio.addOption('"+mun.getPkMunicipio()+"', '"+mun.getMunicipio()+"'.clearNull());");
			%>

			uf.selectOptions("<%= bairro.getMunicipio().getUf().getPkEstado() %>".clearNull());
			municipio.selectOptions("<%= bairro.getMunicipio().getPkMunicipio() %>".clearNull());
			nome.val("<%= bairro.getBairro() %>".clearNull());
			

		}
	});
</script>

<div id="title">Alterar Bairro</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Uf:</td>
			<td><select name="uf" id="uf">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr id="municipio">
			<td>Munic&iacute;pio:</td>
			<td><select name="municipio" id="municipio">
				<option value="0"></option>
			</select></td>
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