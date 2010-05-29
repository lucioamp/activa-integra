<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			
			var adicionarArtefato = function(id, nome, isOwner)
			{
				var tbody = $this.find('table#tabelaArtefatos tbody');
				
				var tr = $('<tr></tr>');
				
				var buttonDetalhe = $('<button type="button"></button>').attr('class', 'button_1').html(nome).click(function(){
						tab.addTab(id+"_detalhe", 'servicos/artefato/detalhe.jsp', 'Detalhe de '+nome);
				});
				
				tr.append($('<td></td>').append(buttonDetalhe));
				
				if(isOwner == true)
				{
					var buttonAlterar = $('<button></button>').attr('type', 'button').attr('class', 'button_2').html('Alterar').click(function(){
						tab.addTab(id+"_alterar", 'servicos/artefato/alterar.jsp', 'Alterar '+nome);
					});
					var buttonExcluir = $('<button></button>').attr('type', 'button').attr('class', 'button_2').html('Excluir').click(function(){
						$this.alert('Tem certeza que deseja excluir este artefato?', null, {
							dialog: DIALOG_CONFIRM,
							clickOK: function() {
								tr.remove();
								tab.removeTab(id, true);
								$this.alert('O artefato foi excluida com sucesso.', null);
							}
						});
					});
					
					tr.append($('<td></td>').append(buttonAlterar)).append($('<td></td>').append(buttonExcluir));
				}
				
				tbody.append(tr);
			}
			
			adicionarArtefato(1, 'artefato 1', false);
			adicionarArtefato(1, 'artefato 2', true);
			adicionarArtefato(1, 'artefato 3', false);
		};
	});
</script>

<div id="tabs-1">
<form name="form" id="form" action="" method="post"
	onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nomeArtefato" id="nomeArtefato" /></td>
			<td><a href="#" onclick="" title="Consultar"><img
				src="../../images/icons/b_search.png" /></a></td>
		</tr>
	</tbody>
</table>
</form>
<fieldset class="blue">
<table id="tabelaArtefatos" class="tabelaresult2">
	<tbody>
	</tbody>
</table>
</fieldset>
</div>