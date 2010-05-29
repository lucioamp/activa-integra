<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
		
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
		};
	});
</script>
<div id="title">Detalhe Comunidade</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td rowspan="6" valign="top" style="width: 25%;">
			<fieldset class="blue" style="height: 315px;"><a href="#"><img
				src="../../images/servicos/semFoto.gif" class="membroFoto" /></a></fieldset>
			</td>
			<td style="width: 25%;">Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><label id="desc"></label></td>
		</tr>
		<tr>
			<td>Data de cria&ccedil;&atilde;o:</td>
			<td><label id="dt_criacao"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags</caption>
				<tbody>
					<tr>
						<td><a href="#" onclick="mostrarTag(1);" title="Consultar">Tag
						1</a></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_artefatos" class="tabelaResult">
				<caption>Artefatos</caption>
				<tbody>
					<tr>
						<td>xxxxxx</td>
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
					name="textMensagem" id="textMensagem" cols="40"></textarea> <br>
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