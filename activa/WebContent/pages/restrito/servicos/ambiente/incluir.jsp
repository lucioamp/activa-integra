<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.MembroAmbiente"%>
<%@page import="modelo.Usuario"%>

<%@page import="modelo.Ambiente"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			var meus_ambientes = $this.find('fieldset#meus_ambientes');
			var ambientes = $this.find('fieldset#ambientes');
			
			$this.find('button#remover').click(function(){
				var selecionados = meus_ambientes.find('input:checked');

				if(selecionados.length == 0)
				{
					$this.alert('Por favor, selecione um ambiente para ser removido.');
					return false;
				}

				var _ambientes = '';
				selecionados.each(function(i, o) {
					_ambientes += "id: "+$(o).attr('value')+"& ";
				});

				$this.sendRequest('../../LoginServlet?opcao=5', {ambientes: _ambientes, tipo: "remover"}, function(data) {
					var result = trim(data);

					if(result == "true")
					{
						$this.alert('Ambientes removidos com sucesso.', null, {clickOK: function() {
							selecionados.each(function(i, o) {
								var tr = $(o).parent('td').parent('tr');
								var id = $(o).attr('value');
								var nome = tr.find('td:first').html();
								tr.clear(function() {
									adicionarAmbiente(id, nome, ambientes, true);
								});
							});
						}});
					}else
						$this.alert('Erro ao tentar remover.');
				});
			});

			$this.find('button#adicionar').click(function(){
				var selecionados = ambientes.find('input:checked');

				if(selecionados.length == 0)
				{
					$this.alert('Por favor, selecione um ambiente para ser adicionado.');
					return false;
				}

				var _ambientes = '';
				selecionados.each(function(i, o) {
					_ambientes += "id: "+$(o).attr('value')+"& ";
				});

				$this.sendRequest('../../LoginServlet?opcao=5', {ambientes: _ambientes, tipo: "incluir"}, function(data) {
					var result = trim(data);

					if(result == "true")
					{
						$this.alert('Ambientes adicionado com sucesso.', null, {clickOK: function() {
							selecionados.each(function(i, o) {
								var tr = $(o).parent('td').parent('tr');
								var id = $(o).attr('value');
								var nome = tr.find('td:first').html();
								tr.clear(function() {
									adicionarAmbiente(id, nome, meus_ambientes, true);
								});
							});
						}});
					}else
						$this.alert('Erro ao tentar adicionar.');
				});
			});

			adicionarAmbiente = function(id, nome, referencia, effect)
			{
				var tr = $('<tr></tr>').hide();
				var td_nome = $('<td></td>').css('width', '100%').html(nome);
				var td_checkbox = $('<td></td>').append($('<input type="checkbox"/>').attr('value', id));
				
				referencia.find('table tbody').append(tr.
					append(td_nome).
					append(td_checkbox)
				);

				if(effect == true)
					tr.show('highlight');
				else
					tr.show();
			}
			
			$this.find('button#voltar').click(function(){
				$this.dialog('option', 'hide', 'explode').dialog('close').dialog('destroy').remove();
				showTelaAmbiente();
			});		

			<%			
			Usuario usuario = (Usuario)session.getAttribute("membro");
			
			Collection<MembroAmbiente> membroAmbientes = MembroAmbiente.consultarPorMembro(usuario);
			for(MembroAmbiente ambiente:membroAmbientes)
				out.print("adicionarAmbiente("+ambiente.getAmbiente().getPkAmbiente()+", '"+ambiente.getAmbiente().getNome()+"', meus_ambientes);");
			
			Collection<Ambiente> ambientes = Ambiente.consultarTodosMenos(usuario);
			for(Ambiente ambiente:ambientes)
				out.print("adicionarAmbiente("+ambiente.getPkAmbiente()+", '"+ambiente.getNome()+"', ambientes);");
			%>
		};
	});
</script>

<div id="novo_ambiente" class="conteudo"  style="display: none;">
<form name="form" id="form" action="" method="post"
	onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td>
				<fieldset style="width: 325;" id="meus_ambientes">
					<legend>Meus ambientes</legend>
					<table>
						<tbody>
						</tbody>
					</table>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;">
				<button type="submit" class="ui-state-default ui-corner-all"
					style="font-size: 12px;"
					name="remover" id="remover">Remover</button>
			</td>
		</tr>
		<tr>
			<td>
				<fieldset style="width: 325;" id="ambientes">
					<legend>Ambientes</legend>
					<table>
						<tbody>
						</tbody>
					</table>
				</fieldset>
			</td>
		</tr>
	</tbody>
</table>
<p style="text-align: right;">
<button type="submit" class="ui-state-default ui-corner-all"
	name="adicionar" id="adicionar">Adicionar</button>
<button type="submit" class="ui-state-default ui-corner-all"
	name="voltar" id="voltar">Voltar</button>
</p>
</form>
</div>