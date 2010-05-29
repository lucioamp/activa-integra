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

			var id = '<%= request.getParameter("id") %>';
			
			var tipoCampo = $this.find('select#tipo');
			var ds_contatoCampo = $this.find('input#ds_contato');
			$this.find('#alterar').click(function (e) {
				
				var tipo = tipoCampo.val();
				var desc = ds_contatoCampo.val();
				
				if(tipo == 0)
				{
					$this.alert('Por favor, selecione um tipo.', null, {clickOK: function() { tipoCampo.focus(); }});
					return false;
				}

				if(desc.length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { ds_contatoCampo.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroContatoServlet?opcao=A',
					{id: id, tipo: tipo, desc: desc},
					function() { botaoVoltar.click(); }
				);
			});

			<%
			Collection<TipoContato> colTipo = TipoContato.consultarTodos();
			for(TipoContato tipo:colTipo)
				out.print("tipoCampo.addOption('"+tipo.getPkTipoContato()+"', '"+tipo.getTipoContato()+"');");
			%>

			tipoCampo.selectOptions('<%= request.getParameter("tipo_id") %>');
			ds_contatoCampo.val('<%= request.getParameter("desc") %>');
		};
	});
</script>

<div id="title">Alterar Contato</div>
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
			<td><input type="text" id="ds_contato" name="ds_contato" /></td>
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