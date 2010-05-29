<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
		
			var nome = $this.find('input#nome');
			var cpf = $this.find('input#cpf').setMask('999.999.999-99').blur();
			var email = $this.find('input#email').blur();
			var senha = $this.find('input#senha');
			var conf_senha = $this.find('input#conf_senha');

			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.dialog('option', 'hide', 'explode').dialog('close').dialog('destroy').remove();
				loginEvent.showDialog();
			});
			
			$this.find('button#cadastrar').click(function(e) {
				e.preventDefault();
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(cpf.val().length == 0)
				{
					$this.alert('Por favor, digite um cpf.', null, {clickOK: function() { cpf.focus(); }});
					return false;
				}

				if(cpf.val().length < 14)
				{
					$this.alert('O cpf se encontra incompleto.', null, {clickOK: function() { cpf.focus(); }});
					return false;
				}

				if(!email.isEmail())
				{
					$this.alert('Por favor, digite um email correto.', null, {clickOK: function() { email.focus(); }});
					return false;
				}

				if(senha.val().length == 0)
				{
					$this.alert('Por favor, digite uma senha.', null, {clickOK: function() { senha.focus(); }});
					return false;
				}

				if(senha.val().length < 5)
				{
					$this.alert('Por favor, digite uma senha com pelo menos 5 letras ou numeros.', null, {clickOK: function() { senha.focus(); }});
					return false;
				}

				if(senha.val() != conf_senha.val())
				{
					$this.alert('A senha de confirmação não se corresponde a senha digitada.', null, {clickOK: function() { conf_senha.focus(); }});
					return false;
				}

				$this.sendRequest('../../CadastrarServlet?opcao=I',
					{
						nome: nome.val(), cpf: cpf.val(), email: email.val(), senha: senha.val()
					},
					function(data) {
						var result = trim(data);
						if(result == "true")
							location.href = "../restrito/index.jsp";
						else if(result == "cpf")
							$this.alert('Este cpf já esta sendo usado', null, {clickOK: function() { cpf.focus(); }});
						else if(result == "email")
							$this.alert('Este email já esta sendo usado', null, {clickOK: function() { email.focus(); }});
						else if(result != "processando")
							$this.alert('Ocorreu algum erro na hora de cadastrar.', null, {clickOK: function() { botaoVoltar.click(); }});
					}
				);
			});
		};
	});
</script>

<div id="CADASTRAR" class="conteudo" style="display: none;">
<form name="form" id="form" action="" method="post" onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" class="text"
				value="" /></td>
		</tr>
		<tr>
			<td>Cpf:</td>
			<td><input type="text" name="cpf" id="cpf" class="text"
				value="" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" id="email" class="text"
				value="" /></td>
		</tr>
		<tr>
			<td>Senha:</td>
			<td><input type="password" name="senha" id="senha" class="text"
				value="" /></td>
		</tr>
		<tr>
			<td>Conf. Senha:</td>
			<td><input type="password" name="conf_senha" id="conf_senha" class="text"
				value="" /></td>
		</tr>
	</tbody>
</table>
<p style="text-align: right;">
<button type="submit" class="ui-state-default ui-corner-all"
	name="cadastrar" id="cadastrar">Cadastrar</button>
<button type="button" class="ui-state-default ui-corner-all"
	name="voltar" id="voltar">voltar</button>
</p>
</form>
</div>