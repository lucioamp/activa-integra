<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.MembroAmbiente"%>
<%@page import="modelo.Usuario"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			$this.find('button#sair').click(function() {
				$('#menuBar').show();
				$this.dialog('close').dialog('destroy').remove();
			});
			
			$this.find('button#criar').click(function() {
				var nome = $this.find('input#nome');
				var desc = $this.find('textarea#desc');
				
				if(nome.val() == 0)
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
				else if(desc.val() == 0)
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
				else
				{
					$this.sendRequest('../../MembroAmbienteServlet?opcao=I', {nome: nome.val(), desc: desc.val()}, function(data) {
						var result = data.trim();
						if(result == "true")
						{
							$this.alert('Ambiente criado com sucesso.', null, {clickOK: function() { location.href = "index.jsp"; }});
						}else
							$this.alert('Erro ao tentar criar o novo ambiente.');
					});
				}
			});
		};
	});
</script>

<div id="novo_ambiente" class="conteudo" style="display: none;">
<form name="form" id="form" action="" method="post" onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td style="width: 70px;">Nome:</td>
			<td style="text-align: left;"><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td style="width: 70px;">Descrição:</td>
			<td style="text-align: left;"><textarea name="desc" id="desc"></textarea></td>
		</tr>
	</tbody>
</table>
<p style="text-align: right;">
	<button type="submit" class="ui-state-default ui-corner-all" name="criar" id="criar">Criar</button>
	<button type="submit" class="ui-state-default ui-corner-all" name="sair" id="sair">Sair</button>
</p>
</form>
</div>