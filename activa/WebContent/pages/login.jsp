<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			$this.find('button#entrar').click(function()
			{
				var usuario = $this.find('#usuario');
				var senha = $this.find('#senha');
				
				if(usuario.val().length == 0)
					$this.alert('Por favor, digite o usu&aacute;rio.', 'Login', {clickOK: function() { usuario.focus(); }});
				else if(senha.val().length == 0)
					$this.alert('Por favor, digite a senha.', 'Login', {clickOK: function() { senha.focus(); }});
				else
				{
					$this.sendRequest('../../LoginServlet?opcao=3', {usuario: usuario.val(), senha: senha.val(), protocolo: PROTOCOLO}, function(data) {
						var result = trim(data);

						if(result == "processando")
							return;
						else if(result == "administrador")
							location.href = "../administrador/index.jsp";
						else if(result == "membro")
							location.href = "../restrito/index.jsp";
						else if(result != "")
							$(this).alert(result);
						else
							$this.find("form#form").submit(function() { return true; }).submit();
					});
				}
			});

			$this.find('button#cadastrar').click(function() {
				$this.dialog('option', 'hide', 'explode').dialog('close').dialog('destroy').remove();

				request.html($('body'), null, '../cadastrar.jsp', false, function() {
					$('#CADASTRAR').showDialog({
						minimize: false,
						maximize: false,
						modal: true,
						showCloseButton: false,
						title: 'Cadastrar',
						width: '290',
						draggable: false,
						reload: false
					});
				});
			});

			$this.find('button#home').click(function() {
				location.href = "../../index.jsp";
			});
		};
	});
</script>

<div id="login" class="conteudo" style="display: none;">
<form name="form" id="form" action="" method="post" onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="usuario" id="usuario" class="text"
				value="" /></td>
		</tr>
		<tr>
			<td>Senha:</td>
			<td><input type="password" name="senha" id="senha" class="text"
				value="" /></td>
		</tr>
	</tbody>
</table>
<p style="text-align: right;">
<button type="submit" class="ui-state-default ui-corner-all"
	name="entrar" id="entrar">Entrar</button>
<button type="button" class="ui-state-default ui-corner-all"
	name="cadastrar" id="cadastrar">Cadastre-se</button>
<button type="button" class="ui-state-default ui-corner-all"
	name="home" id="home">Home</button>
</p>
</form>
</div>