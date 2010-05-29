<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<script type="text/javascript">
	$(document).ready(function () {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var id = object['_aula'].id+"";
			
			var peso = $this.find('input#nu_peso').onlyNumber().val(object['_aula'].peso);
			var assunto = $this.find('input#ds_assunto').val(object['_aula'].assunto);
			var desc = $this.find('textarea#ds_aula').val(object['_aula'].desc);
			var data = $this.find('input#dt_aula').datepick().setMask('99/99/9999').val(object['_aula'].data);
					
			$this.find('button#alterar').click(function(e){
	
				if(assunto.val().length == 0)
				{
					$this.alert('Por favor, digite um assunto.', null, {clickOK: function() { assunto.focus(); }});
					return false;
				}
	
				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				if(data.val().length == 0)
				{
					$this.alert('Por favor, digite uma data.', null, {clickOK: function() { data.focus(); }});
					return false;
				}

				if(peso.val().length == 0)
				{
					$this.alert('Por favor, digite um peso.', null, {clickOK: function() { peso.focus(); }});
					return false;
				}

				var _data = data;
				$this.sendRequest('../../MembroCursoServlet?opcao=D', {id: id, assunto: assunto.val(), desc: desc.val(), peso: peso.val(), data: data.val(), tipo: 'alterar'}, function(data) {
					object['_aula'].assunto = assunto.val();
					object['_aula'].desc = desc.val();
					object['_aula'].peso = peso.val();
					object['_aula'].data = _data.val();
					object['_aula'].alterado = true;
					object['_aula'].trReferencia.find('td:first').find('a').html(object['_aula'].assunto);
					object['_aula'].trReferencia.find('td:last').html(object['_aula'].data);
					$this.alert('Alterado com acesso.', null, {clickOK: function() { botaoVoltar.click(); }});

					object['_aula'] = null;
				});

				$this.alert('Erro ao tentar alterar a tag.');
			});

			if(object['_aula'].virtual == true)
				$this.find('tr#arquivo_aula').hide();
			else
			{
				var adicionarArquivo = function(id, nome, newArquivo)
				{					
					var tr = $('<tr></tr>');
					var tdArquivo = $('<td></td>').append('<a href="../../arquivo/aula/'+nome+'" target="_blank">'+nome.split('/')[1]+'</a>');
					tr.contextMenu({
						buttons: {
							Remover: {
								icon: "delete",
								onClick: function() {
									$this.alert('Tem certeza que deseja excluir?', null, {
										dialog: DIALOG_CONFIRM,
										clickOK: function() {
											$this.sendRequest('../../MembroCursoServlet?opcao=3', {codigoArquivo: id}, function(data) {
												var result = data.trim();
												
												if(result == "true")
													$this.alert('Arquivo excluido com sucesso.', null, {clickOK: function() { tr.clear(); }});
												else
													$this.alert('Erro ao tentar excluir o arquivo.');
											});
										}
									});
								}
							}
						}
					});			
					
					$this.find('table#arquivos tbody').append(tr.append(tdArquivo));
				};

				for (var i in object['_aula'].arquivos)
				{
					var arquivo = object['_aula'].arquivos[i];
					adicionarArquivo(arquivo.id, arquivo.nome);
				}
				
				$this.find('tr#arquivo_aula').show();
				var buttonEnviarArquivo = $this.find('button#enviar_arquivo');
				var inputArquivoImagem = $this.find('input#arquivo_imagem');
				var interval = null;

				$this.find('div#upload_button').attr('id', 'upload_button'+id);

				var telaLoading = null;
				var enviaArquivo = new AjaxUpload('#upload_button'+id, {
					action: '../../MembroCursoServlet?opcao=0&tipo=enviar&codigoAula='+id,
					name: 'file1',
					autoSubmit: true,
					onChange : function(file){
						inputArquivoImagem.val(file);
					},
					onSubmit: function(file, ext) {
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
					},
					onComplete: function(file, response) {
						telaLoading.clear();
						this.enable();
						/*window.clearInterval(interval);
						inputArquivoImagem.val("");				
						buttonEnviarArquivo.html("Enviar Arquivo");
						*/
						var result = response.trim();
						if(result != "false")
						{
							result = result.split(':');
							adicionarArquivo(result[0].trim(), result[1].trim(), true);
						}
					}
				});
			}
		};
	});
</script>
<div id="title">Alterar Aula</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Assunto:</td>
			<td><input type="text" name="ds_assunto" id="ds_assunto" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_aula" name="ds_aula" cols="50" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><input type="text" name="dt_aula" id="dt_aula" /></td>
		</tr>
		<tr>
			<td>Peso:</td>
			<td><input type="text" name="nu_peso" id="nu_peso" /></td>
		</tr>
		<tr id="arquivo_aula">
			<td>Arquivo da Aula:</td>
			<td>
				<div id="upload_button">
					<input type="text" name="arquivo_imagem" id="arquivo_imagem" style="display: inline;"/>
					<button type="button" class="ui-state-default ui-corner-all" name="enviar_arquivo" id="enviar_arquivo">Enviar Arquivo</button>
				</div>
			</td>
		</tr>
		<tr id="arquivo_aula">
			<td colspan="2">
			<table id="arquivos" class="tabelaResult" border="1">
				<caption>Arquivos</caption>
				<thead>
					<tr>
						<th>Arquivo</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>