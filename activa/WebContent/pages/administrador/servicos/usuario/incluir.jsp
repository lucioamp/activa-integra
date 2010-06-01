<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Instituicao"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['contatos'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../UsuarioServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});

			var dt_nasc = $this.find('input#dt_nasc').datepick().setMask('99/99/9999');
			var nu_cep = $this.find('input#nu_cep').setMask('99999-999');
			var nu_cpf = $this.find('input#nu_cpf').setMask('999.999.999-99');
			var nu_logradouro = $this.find('input#nu_logradouro').setMask('9999999999');
			
			var instituicao = $this.find('select#instituicao');	
			var uf = $this.find('select#uf');
			var municipio = $this.find('select#municipio');
			var bairro = $this.find('select#bairro');			
			$this.find('button#incluir').click(function() {
				var nome = $this.find('input#no_usuario');
				var apelido = $this.find('input#no_apelido');
				var email = $this.find('input#email');
				var pw_senha = $this.find('input#pw_senha');
				var ds_perguntaChave = $this.find('input#ds_perguntaChave');
				var ds_respostaChave = $this.find('input#ds_respostaChave');
				var tp_logradouro = $this.find('select#tp_logradouro');
				var no_logradouro = $this.find('input#no_logradouro');
				var ds_complemento = $this.find('input#ds_complemento');
				var st_usuario = $this.find('select#st_usuario');
				var administrador = $this.find("input#administrador").is(':checked');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(nu_cpf.val().length == 0)
				{
					$this.alert('Por favor, digite um cpf.', null, {clickOK: function() { nu_cpf.focus(); }});
					return false;
				}

				if(nu_cpf.val().length < 14)
				{
					$this.alert('O cpf se encontra incompleto.', null, {clickOK: function() { nu_cpf.focus(); }});
					return false;
				}

				if(dt_nasc.val().length == 0)
				{
					$this.alert('Por favor, digite uma data de nascimento.', null, {clickOK: function() { dt_nasc.focus(); }});
					return false;
				}

				if(email.val().length == 0)
				{
					$this.alert('Por favor, digite um email.', null, {clickOK: function() { email.focus(); }});
					return false;
				}

				if(!email.isEmail())
				{
					$this.alert('Por favor, digite um email correto.', null, {clickOK: function() { email.focus(); }});
					return false;
				}

				if(pw_senha.val().length == 0)
				{
					$this.alert('Por favor, digite uma senha.', null, {clickOK: function() { pw_senha.focus(); }});
					return false;
				}

				if(instituicao.val() == 0)
				{
					$this.alert('Por favor, selecione uma institui��o.', null, {clickOK: function() { instituicao.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				if(municipio.val() == 0)
				{
					$this.alert('Por favor, selecione um munic�pio.', null, {clickOK: function() { municipio.focus(); }});
					return false;
				}

				if(bairro.val() == 0)
				{
					$this.alert('Por favor, selecione um bairro.', null, {clickOK: function() { bairro.focus(); }});
					return false;
				}

				var contatos = "";
				for (var i in object['contatos'])
				{
					contatos += "tipo: " + object['contatos'][i].tipo + "#";
					contatos += "desc: " + object['contatos'][i].desc;
					contatos += "&";
				}

				$this.sendRequest('../../UsuarioServlet?opcao=I',
					{
						nome: nome.val(), apelido: apelido.val(), email: email.val(), dataNasc: dt_nasc.val(), senha: pw_senha.val(),
						tipoLogradouro: tp_logradouro.val(), logradouro: no_logradouro.val(), numero: nu_logradouro.val(),
						perguntaChave: ds_perguntaChave.val(), respostaChave: ds_respostaChave.val(), complemento: ds_complemento.val(),
						cep: nu_cep.val(), cpf: nu_cpf.val(), contatos: contatos, pkInstituicao: instituicao.val(), pkBairro: bairro.val(), status: st_usuario.val(),
						imagem: "", isAdministrador: administrador
					},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirContato').click(function() {
				$mainPage.newPage('servicos/contato/incluir.jsp', null);
			});
			
			uf.change(function() {
				var pkUf = $(this).val();

				municipio.empty().addOption("0", "").selectOptions("0");
				bairro.empty().addOption("0", "").selectOptions("0");
				$this.find('tr#tr_bairro').hide();
				if(pkUf > 0)
				{
					$this.find('tr#tr_municipio').show();
					$this.sendRequest('../../BairroServlet?opcao=M', {pkEstado: pkUf},	function(data) {
						municipio.append(data);
					});
				}else
					$this.find('tr#tr_municipio').hide();
			});
			
			municipio.change(function() {
				var pkMunicipio = $(this).val();
				bairro.empty().addOption("0", "");
				if(pkMunicipio > 0)
				{
					$this.find('tr#tr_bairro').show();
					$this.sendRequest('../../BairroServlet?opcao=B', {pkMunicipio: pkMunicipio}, function(data) {
						bairro.append(data);
					});
				}else
					$this.find('tr#tr_bairro').hide();
			});

			<%
			Collection<Uf> colUf = Uf.consultarTodos();
			for(Uf uf:colUf)
				out.print("uf.addOption('"+uf.getPkEstado()+"', '"+uf.getEstado()+"');");

			Collection<Instituicao> instituicoes = (Collection<Instituicao>)Instituicao.consultarTodos();
			for(Instituicao inst:instituicoes)
				out.print("instituicao.addOption('"+inst.getPkInstituicao()+"', '"+inst.getNome()+"');");
			%>

			uf.selectOptions("0");
			instituicao.selectOptions("0");
		}
	});
</script>

<div id="title">Incluir Usu&aacute;rio</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 40%;">Nome:</td>
			<td><input type="text" name="no_usuario" id="no_usuario" /></td>
		</tr>
		<tr>
			<td>Apelido:</td>
			<td><input type="text" name="no_apelido" id="no_apelido" /></td>
		</tr>
		<tr>
			<td>Cpf:</td>
			<td><input type="text" name="nu_cpf" id="nu_cpf" /></td>
		</tr>
		<tr>
			<td>Data de Nascimento:</td>
			<td><input type="text" name="dt_nasc" id="dt_nasc" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" id="email" /></td>
		</tr>
		<tr>
			<td>Senha:</td>
			<td><input type="password" name="pw_senha" id="pw_senha" /></td>
		</tr>
		<tr>
			<td>Pergunta Chave:</td>
			<td><input type="text" name="ds_perguntaChave"
				id="ds_perguntaChave" /></td>
		</tr>
		<tr>
			<td>Resposta Chave:</td>
			<td><input type="text" name="ds_respostaChave"
				id="ds_respostaChave" /></td>
		</tr>
		<tr>
			<td>Institui��o:</td>
			<td><select name="instituicao" id="instituicao">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Tipo de Logradouro:</td>
			<td><select name="tp_logradouro" id="tp_logradouro">
				<option value=""></option>
				<option value="Av">Avenida</option>
				<option value="Rua">Rua</option>
				<option value="Est">Estrada</option>
				<option value="Pr�">Pra�a</option>
			</select></td>
		</tr>
		<tr>
			<td>Nome do Logradouro:</td>
			<td><input type="text" name="no_logradouro" id="no_logradouro" /></td>
		</tr>
		<tr>
			<td>N&uacute;mero do Logradouro:</td>
			<td><input type="text" name="nu_logradouro" id="nu_logradouro" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o do Complemento:</td>
			<td><input type="text" name="ds_complemento" id="ds_complemento" /></td>
		</tr>
		<tr>
			<td>N&uacute;mero do Cep:</td>
			<td><input type="text" name="nu_cep" id="nu_cep" /></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><select name="uf" id="uf">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr id="tr_municipio" style="display: none;">
			<td>Munic&iacute;pio:</td>
			<td><select name="municipio" id="municipio">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr id="tr_bairro" style="display: none;">
			<td>Bairro:</td>
			<td><select name="bairro" id="bairro">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Status:</td>
			<td><select name="st_usuario" id="st_usuario">
				<option value="A">Ativo</option>
				<option value="D">Desativado</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="checkbox" value="1"
				name="administrador" id="administrador" style="display: inline;">
			Administrador</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_contatos" class="tabelaResult">
				<caption>contatos<a href="#" id="incluirContato"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th>Tipo</th>
						<th>Descri��o</th>
						<th>Alterar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
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