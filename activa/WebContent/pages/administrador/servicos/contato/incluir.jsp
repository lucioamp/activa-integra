<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.TipoContato"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
		
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

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

				var tipo_nome = tipo.selectedTexts().toString();
				var id = tipo_val+rand(1, 9999999);
				var tr = $('<tr></tr>').attr('id', id);
				var tdTipo = $('<td></td>').html(tipo_nome);
				
				var tdDesc = $('<td></td>').html(desc_val);

				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
							$mainPage.newPage('../../ContatoServlet?opcao=D', {id: id, tipo_contato: tipo_val, desc: desc_val, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() { tr.clear(); }
								});
							}
						}
					}
				});

				object['contatos'].push({id: id, tipo: tipo_val, desc: desc_val, virtual: true, trReferencia: tr});

				$oldPage.find('table#tabela_contatos tbody').append(tr.append(tdTipo).append(tdDesc));

				botaoVoltar.click();
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
			<td><textarea id="ds_contato" name="ds_contato"></textarea></td>
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