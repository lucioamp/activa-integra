<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(document).ready(function () {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			var arquivoAula = $this.find('input#arquivoAula').filestyle({ 
				image: "../../images/choose-file.gif",
				imageheight : 22,
				imagewidth : 82,
				width : 157
			});

			var peso = $this.find('input#nu_peso').onlyNumber();
			var data = $this.find('input#dt_aula').datepick().setMask('99/99/9999');
			$this.find('button#incluir').click(function(e){
				var assunto = $this.find('input#ds_assunto');
				var desc = $this.find('textarea#ds_aula');
	
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

				var id = assunto.val()+rand(1, 999);

				var tr = $('<tr></tr>');

				var aula = {id: id, assunto: assunto.val(), desc: desc.val(), arquivos: arquivoAula.val(), peso: peso.val(), data: data.val(), virtual: true, trReferencia: tr};
				
				var tdAssunto = $('<td></td>').html($('<a href="#"></a>').append(assunto.val()).click(function(e) {
					e.preventDefault();
					object['_aula'] = aula;
					$mainPage.newPage('servicos/curso/aula/detalhe.jsp', null);
				}));

				var tdData = $('<td></td>').html(data.val());

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								object['_aula'] = aula;
								$mainPage.newPage('servicos/curso/aula/detalhe.jsp', null);
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								object['_aula'] = aula;
								$mainPage.newPage('servicos/curso/aula/alterar.jsp', null);
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										delete aula;
										tr.clear();
									}
								});
							}
						}
					}
				});

				$oldPage.find('table#aulas tbody').append(tr.append(tdAssunto).append(tdData));

				object['aulas'].push(aula);

				$this.alert('A aula foi incluida com sucesso.', null, {clickOK: function() {
					botaoVoltar.click();
				}});
			});
		};
	});
</script>
<div id="title">Incluir Aula</div>
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
	</tbody>
	<tfoot>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>