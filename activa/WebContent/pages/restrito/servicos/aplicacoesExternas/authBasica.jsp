<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			var janelaLoginForm = $this.find('#conteudo #form');
			janelaLoginForm.find('#entrar').click(function()
			{
				var usuario = janelaLoginForm.find('#usuario').val();
				var senha = janelaLoginForm.find('#senha').val();
				
				if(usuario.length == 0 || senha.length == 0)
					$this.alert('Por favor, digite usu&aacute;rio e senha.', 'Login');
				else
				{
					$this.dialog('option', 'hide', 'explode').dialog('close').dialog('destroy').remove();
					executaAposLogin(usuario, senha);
				}
			});
		};
	});
</script>

<div id="login" style="display: none;">
	<div id="conteudo">
		<form name="form" id="form" action="" method="post" onsubmit="return false;">
			<table>
				<tbody>
					<tr><td>Usuario:</td><td><input type="text" name="usuario" id="usuario" class="text" value="lucioamp"/></td></tr>
					<tr><td>Senha:</td><td><input type="password" name="senha" id="senha" class="text" value="7321007a" /></td></tr>
				</tbody>
			</table>
			<p style="text-align: right;"><button type="submit" class="ui-state-default ui-corner-all" name="entrar" id="entrar">Autenticar</button></p>
		</form>
	</div>
</div>