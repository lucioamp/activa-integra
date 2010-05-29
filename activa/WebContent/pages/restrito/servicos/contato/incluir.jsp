<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.TipoContato"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../MembroContatoServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			
			var tipo = $this.find('select#tipo');
			$this.find('button#incluir').click(function() {
				var desc = $this.find('textarea#ds_contato');
				var tipo_val = tipo.val();
				var desc_val = desc.val();
				
				if(tipo_val == 0)
				{
					$this.alert('Por favor, selecione um tipo.', null, {clickOK: function() { tipo.focus(); }});
					return false;
				}

				if(desc_val.length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroContatoServlet?opcao=I',
					{tipo_val: tipo_val, desc_val: desc_val},
					function() { botaoVoltar.click(); }
				);
			});

			<%
			Collection<TipoContato> colTipo = TipoContato.consultarTodos();
			for(TipoContato tipo:colTipo)
				out.print("tipo.addOption('"+tipo.getPkTipoContato()+"', '"+tipo.getTipoContato()+"');");
			%>

			tipo.selectOptions("0");
		};
	});
</script>

<div id="title">Incluir Contato</div>
<table>
	<tbody>
		<tr>
			<td>Tipo:</td>
			<td><select name="tipo" id="tipo">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_contato" name="ds_contato" cols="50" rows="10"></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>