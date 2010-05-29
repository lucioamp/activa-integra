<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Bairro"%>
<%@page import="modelo.Instituicao"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Administrador"%>
<% Usuario usuario = (Usuario)session.getAttribute("membro");%>
<% Membro membro = (Membro)request.getAttribute("membro");%>

<%@page import="modelo.Membro"%>
<%@page import="util.Ferramenta"%>
<%@page import="modelo.FormacaoAcademica"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			object['contatos'] = new Array();
			
			var id = <%= usuario.getPkUsuario() %>;

			var dt_nasc = $this.find('input#dt_nasc').datepick().setMask('99/99/9999');
			var nu_cep = $this.find('input#nu_cep').setMask('99999-999');
			var nu_cpf = $this.find('input#nu_cpf').setMask('999.999.999-99');
			var nu_logradouro = $this.find('input#nu_logradouro').setMask('9999999999');

			var nome = $this.find('input#no_usuario');
			var apelido = $this.find('input#no_apelido');
			var email = $this.find('input#email');
			var pw_senha = $this.find('input#pw_senha');
			var ds_perguntaChave = $this.find('input#ds_perguntaChave');
			var ds_respostaChave = $this.find('input#ds_respostaChave');
			var tp_logradouro = $this.find('select#tp_logradouro');
			var no_logradouro = $this.find('input#no_logradouro');
			var ds_complemento = $this.find('input#ds_complemento');
			var administrador = $this.find("input#administrador").attr("disabled", "disabled");
			
			var instituicao = $this.find('select#instituicao');	
			var uf = $this.find('select#uf');
			var municipio = $this.find('select#municipio');
			var bairro = $this.find('select#bairro');
			var formacaoAcademica = $this.find('select#formacaoAcademica');
			$this.find('button#alterar').click(function() {
				
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
					$this.alert('Por favor, selecione uma instituição.', null, {clickOK: function() { instituicao.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				if(municipio.val() == 0)
				{
					$this.alert('Por favor, selecione um município.', null, {clickOK: function() { municipio.focus(); }});
					return false;
				}

				if(bairro.val() == 0)
				{
					$this.alert('Por favor, selecione um bairro.', null, {clickOK: function() { bairro.focus(); }});
					return false;
				}

				if(formacaoAcademica.val() == 0)
				{
					$this.alert('Por favor, selecione uma formação.', null, {clickOK: function() { formacaoAcademica.focus(); }});
					return false;
				}

				var contatos = "";
				var contato = null;
				for (var i in object['contatos'])
				{
					contato = object['contatos'][i];

					contatos += 'id: '+contato.id+'# tipo:'+contato.tipo+' # desc:'+contato.desc+'# ';
					if(contato.virtual == true)
						contatos += 'virtual: true&';
					else if(contato.deletado == true)
						contatos += 'deletar: true&';
					else if(contato.alterado == true)
						contatos += 'alterar: true&';
				}

				$this.sendRequest('../../MembroServlet?opcao=A',
					{
						nome: nome.val(), apelido: apelido.val(), email: email.val(), dataNasc: dt_nasc.val(), senha: pw_senha.val(),
						tipoLogradouro: tp_logradouro.val(), logradouro: no_logradouro.val(), numero: nu_logradouro.val(),
						perguntaChave: ds_perguntaChave.val(), respostaChave: ds_respostaChave.val(), complemento: ds_complemento.val(),
						cep: nu_cep.val(), cpf: nu_cpf.val(), contatos: contatos, pkInstituicao: instituicao.val(), pkBairro: bairro.val(),
						formacaoAcademica: formacaoAcademica.val()
					},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Dados alterados com sucesso.');
							menuOptionConteudo.find('div#nomeMembro').html(nome.val());
						}else							
							$this.alert('Erro ao tentar alterar os dados.');
					}
				);
			});
		
			uf.change(function() {
				var pkUf = $(this).val();

				municipio.empty().addOption("0", "").selectOptions("0");
				bairro.empty().addOption("0", "").selectOptions("0");
				$this.find('tr#tr_bairro').hide();
				if(pkUf > 0)
				{
					$this.find('tr#tr_municipio').show();
					$this.sendRequest('../../MembroServlet?opcao=M', {pkEstado: pkUf},	function(data) {
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
					$this.sendRequest('../../MembroServlet?opcao=B', {pkMunicipio: pkMunicipio}, function(data) {
						bairro.append(data);
					});
				}else
					$this.find('tr#tr_bairro').hide();
			});

			adicionarContato = function(id, tipo, tipo_nome, desc) {
				var tr = $('<tr></tr>');
				var tdTipo = $('<td></td>').html(tipo_nome);
				
				var tdDesc = $('<td></td>').html(desc);
				var tdEditar = $('<td></td>');
				var tdExcluir = $('<td></td>');

				criarBotaoEditar(tdEditar).click(function() { $this.newPage('../../MembroContatoServlet?opcao=D', {id: id, tipo_contato: tipo, desc: desc, tipo: "paginaAlterar"});  });
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
							for (var i in object['contatos'])
							{
								if(object['contatos'][i].id == id)
									object['contatos'][i].deletado = true;
							}
							tr.clear();
						}
					});
				});
				
				object['contatos'].push({id: id, tipo: tipo, desc: desc, virtual: false, deletado: false, alterado: false, trReferencia: tr});

				$this.find('table#tabela_contatos tbody').append(tr.append(tdTipo).append(tdDesc).append(tdEditar).append(tdExcluir));
			};
			
			<%
			Collection<FormacaoAcademica> colFA = FormacaoAcademica.consultarTodos();
			for(FormacaoAcademica formacaoAcademica:colFA)
				out.print("formacaoAcademica.addOption('"+formacaoAcademica.getPkFormacaoAcademica()+"'.clearNull(), '"+formacaoAcademica.getNome()+"'.clearNull());");
			
			Collection<Uf> colUf = Uf.consultarTodos();
			for(Uf uf:colUf)
				out.print("uf.addOption('"+uf.getPkEstado()+"'.clearNull(), '"+uf.getEstado()+"'.clearNull());");

			Collection<Municipio> colMun = Municipio.consultarPorUf(usuario.getBairro().getMunicipio().getUf());
			for(Municipio mun:colMun)
				out.print("municipio.addOption('"+mun.getPkMunicipio()+"'.clearNull(), '"+mun.getMunicipio()+"'.clearNull());");
			
			Collection<Bairro> colBar = Bairro.consultarPorMunicipio(usuario.getBairro().getMunicipio());
			for(Bairro bar:colBar)
				out.print("bairro.addOption('"+bar.getPkBairro()+"'.clearNull(), '"+bar.getBairro()+"'.clearNull());");
			
			Collection<Instituicao> instituicoes = (Collection<Instituicao>)Instituicao.consultarTodos();
			for(Instituicao inst:instituicoes)
				out.print("instituicao.addOption('"+inst.getPkInstituicao()+"'.clearNull(), '"+inst.getNome()+"'.clearNull());");
			
			//Ferramenta.formatarCpf("123.456.789-01");
			%>

			dt_nasc.val("<%= Ferramenta.formatarData(usuario.getDataNasc(), true) %>".clearNull());
			nu_cep.val("<%= usuario.getCep() %>".clearNull());
			nu_cpf.val("<%= Ferramenta.formatarCpf(usuario.getCpf()) %>".clearNull());
			nu_logradouro.val("<%= usuario.getNumero() %>".clearNull());

			nome.val("<%= usuario.getNome() %>".clearNull());
			apelido.val("<%= usuario.getApelido() %>".clearNull());
			email.val("<%= usuario.getEmail() %>".clearNull());
			pw_senha.val("<%= usuario.getSenha() %>".clearNull());
			ds_perguntaChave.val("<%= usuario.getPerguntaChave() %>".clearNull());
			ds_respostaChave.val("<%= usuario.getRespostaChave() %>".clearNull());
			tp_logradouro.val("<%= usuario.getTipoLogradouro() %>".clearNull());
			no_logradouro.val("<%= usuario.getLogradouro() %>".clearNull());
			ds_complemento.val("<%= usuario.getComplemento() %>".clearNull());
			
			instituicao.selectOptions("<%= usuario.getInstituicao().getPkInstituicao() %>".clearNull());
			uf.selectOptions("<%= usuario.getBairro().getMunicipio().getUf().getPkEstado() %>".clearNull());
			municipio.selectOptions("<%= usuario.getBairro().getMunicipio().getPkMunicipio() %>".clearNull());
			bairro.selectOptions("<%= usuario.getBairro().getPkBairro() %>".clearNull());

			formacaoAcademica.selectOptions("<%= membro.getFormacaoAcademica().getPkFormacaoAcademica() %>".clearNull());

			var buttonEnviarArquivo = $this.find('button#enviar_arquivo');
			var inputArquivoImagem = $this.find('input#arquivo_imagem');
			var interval = null;
			var telaLoading = null;
			var enviaArquivo = new AjaxUpload('#membro_upload_button', {
				action: '../../MembroServlet?opcao=I&tipo=enviar',
				name: 'file1',
				autoSubmit: true,
				onChange : function(file){
					inputArquivoImagem.val(file);
				},
				onSubmit: function(file, ext) {
					if (ext && /^(jpg)$/.test(ext)){
						telaLoading = $this.sendRequest().telaLoading;
						this.disable();
						/*var iniTexto = "Enviando arquivo";
						buttonEnviarArquivo.html(iniTexto);
						interval = window.setInterval(function(){
							var text = buttonEnviarArquivo.html();
							if (text.length < 20){
								buttonEnviarArquivo.html(text + '.');					
							} else {
								buttonEnviarArquivo.html(iniTexto);
							}
						}, 200);*/
					} else {
						inputArquivoImagem.val("");
						$this.alert('Só pode enviar imagens no formato (*.jpg).');
						return false;				
					}
				},
				onComplete: function(file, response) {
					telaLoading.clear();
					this.enable();
					/*window.clearInterval(interval);
					inputArquivoImagem.val("");				
					buttonEnviarArquivo.html("Enviar Arquivo");*/
					<%
					String nomeArquivo = Ferramenta.md5(usuario.getNome()+usuario.getPkUsuario())+".jpg";
					out.print("menuOptionConteudo.find('img.membroFoto').attr('src', '../../arquivo/fotomembro/"+nomeArquivo+"?'+new Date().getTime());");
					%>
				}
			});
		}
	});
</script>

<div id="title">Alterar dados do Usu&aacute;rio</div>
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
			<td>Instituição:</td>
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
				<option value="Prç">Praça</option>
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
		<tr id="tr_municipio">
			<td>Munic&iacute;pio:</td>
			<td><select name="municipio" id="municipio">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr id="tr_bairro">
			<td>Bairro:</td>
			<td><select name="bairro" id="bairro">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Formação Acadêmica:</td>
			<td><select name="formacaoAcademica" id="formacaoAcademica">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td>Foto:</td>
			<td>
				<div id="membro_upload_button">
					<input type="text" name="arquivo_imagem" id="arquivo_imagem" style="display: inline;"/>
					<button type="button" class="ui-state-default ui-corner-all" name="enviar_arquivo" id="enviar_arquivo">Enviar Arquivo</button>
				</div>
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
				name="alterar" id="alterar">Alterar</button>
			</td>
		</tr>
	</tfoot>
</table>