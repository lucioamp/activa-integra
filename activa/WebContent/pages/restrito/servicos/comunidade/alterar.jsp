<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
		
			var button_enviaMensagem = $this.find('#enviaMensagem');
			var caixaMensagem = $this.find('#caixaDeMensagem');
			button_enviaMensagem.click(function(e){
				$(this).hide();
				caixaMensagem.show();
			});
			
			$this.find('#enviarMensagem').click(function(e){
				var textMensagem = caixaMensagem.find('#textMensagem');
				
				if(textMensagem.val().length == 0)
				{
					$this.alert('Digite uma mensagem para ser enviada.', null, {clickOK: function() { textMensagem.focus(); }});
					return false;
				}
				
				$this.alert('A mensagem foi enviada com sucesso.', null, {
					clickOK: function() {
						button_enviaMensagem.show();
						caixaMensagem.hide();
						
						$this.find('table#tabela_post tbody').append(
							$('<tr></tr>').append($('<td></td>').html('(00/00/0000) Raimundo')).append($('<td></td>').html(textMensagem.val()))
						);						
					}
				});
			});
			
			$this.find('#alterarComunidade').click(function(){
				$this.alert('A comunidade foi alterada com sucesso.');
			});
			
			$this.find('#excluirComunidade').click(function(){
				$this.alert('Tem certeza que deseja excluir esta comunidade?', null, {
					dialog: DIALOG_CONFIRM,
					clickOK: function() {
						var tab = $this.parents('#tabs'); tab.tabs('remove', tab.tabs('option', 'selected'));
						$this.alert('A comunidade foi excluida com sucesso.', null);
					}
				});
			});	
			
			$this.find('input#imageFile').filestyle({ 
				image: "images/choose-file.gif",
				imageheight : 22,
				imagewidth : 82,
				width : 124
			});
		};
	});
</script>

<div id="page_1">
<div id="title">Alterar Comunidade</div>
<table>
	<tbody>
		<tr>
			<td rowspan="6" valign="top" style="width: 25%;">
			<fieldset class="blue" style="height: 315px;"><a href="#"><img
				src="../../images/servicos/semFoto.gif" class="membroFoto"
				border="0" /></a>
			<p style="text-align: center;">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterarComunidade" id="alterarComunidade">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="excluirComunidade" id="excluirComunidade">Excluir</button>
			</p>
			</fieldset>
			</td>
			<td style="width: 25%;">Nome:</td>
			<td><input type="text" id="nome" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><input type="text" id="desc" /></td>
		</tr>
		<!--<tr>
			<td>Data de cria&ccedil;&atilde;o:</td>
			<td><input type="text" id="dt_criacao" /></td>
		</tr>-->
		<tr>
			<td>Foto da comunidade</td>
			<td><input type="file" name="imageFile" id="imageFile" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags<a href="#" onclick="incluirTag()"
					title="Incluir"><img src="../../images/icons/add.gif"></a><br />
				<input type="text" id="pesquisarTag" name="pesquisarTag" /> <a
					href="#"><img src="../../images/icons/b_search.png" /></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
						<th>Alterar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#" onclick="mostrarTag(1);" title="Consultar">Tag
						1</a></td>
						<td><a href="#" onclick="alterarTag(1);" title="Editar"><img
							src="../../images/icons/b_edit.png" /></a></td>
						<td><a href="#" onclick="excluirTag(1);" title="Excluir"><img
							src="../../images/icons/delete.gif" /></a></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_artefatos" class="tabelaResult">
				<caption>Artefatos<a href="#" id="inclusaoArtefato"
					title="Incluir"><img src="../../images/icons/add.gif"></a><br />
				<input type="text" id="pesquisarArtefato" name="pesquisarArtefato" /><a
					href="#"><img src="../../images/icons/b_search.png" /></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
						<th>Alterar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#" onclick="mostrarArtefatoDetalhe(1);"
							title="Consultar">Artefato 1</a></td>
						<td><a href="#" onclick="alterarArtefato2(1);" title="Editar"><img
							src="../../images/icons/b_edit.png" /></a></td>
						<td><a href="#" onclick="excluirArtefato2(1);"
							title="Excluir"><img src="../../images/icons/delete.gif" /></a></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_post" class="tabelaResult">
				<caption>Mensagens<br />
				<button type="button" class="ui-state-default ui-corner-all"
					name="enviaMensagem" id="enviaMensagem">Enviar Mensagem</button>
				<span style="display: none;" id="caixaDeMensagem"> <textarea
					name="textMensagem" id="textMensagem" cols="50" rows="10"></textarea> <br>
				<button type="button" class="ui-state-default ui-corner-all"
					name="enviarMensagem" id="enviarMensagem">Enviar</button>
				</span></caption>
				<tbody>
					<tr>
						<td>(10/03/2009) Priscila</td>
						<td>testando msg</td>
					</tr>
					<tr>
						<td>(05/04/2009) Renato</td>
						<td>minha msg</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</div>