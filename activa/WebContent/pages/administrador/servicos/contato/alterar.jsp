<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.TipoContato"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

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

				$this.sendRequest('../../ContatoServlet?opcao=D', {id: id, tipo: tipo, desc: desc, tipo: 'alterar'}, function(data) {
					for (var i in object['contatos'])
					{
						if(object['contatos'][i].id == id)
						{
							object['contatos'][i].tipo = tipo;
							object['contatos'][i].desc = desc;
							object['contatos'][i].alterado = true;
							object['contatos'][i].trReferencia
								.find('td:first').html(tipoCampo.selectedTexts().toString())
								.next().html(desc);
							$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});
							return;
						}
					}
				});

				$this.alert('Erro ao tentar alterar o contato.');
			});

			<%
			Collection<TipoContato> colTipo = TipoContato.consultarTodos();
			for(TipoContato tipo:colTipo)
				out.print("tipoCampo.addOption('"+tipo.getPkTipoContato()+"'.clearNull(), '"+tipo.getTipoContato()+"'.clearNull());");
			%>

			tipoCampo.selectOptions('<%= request.getParameter("tipo_contato") %>'.clearNull());
			ds_contatoCampo.val('<%= request.getParameter("desc") %>'.clearNull());
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
			<td><input type="text" id="ds_contato" name="ds_contato"/></td>
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