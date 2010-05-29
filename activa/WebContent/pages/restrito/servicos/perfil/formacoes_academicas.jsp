<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var formacoes = new Array();
			
			$this.find('a#incluir').click(function(e) {
				incluirFormacao($this.find('select#formacao').val());
			});
			
			inserirFormacao = function(id, nome)
			{
				formacoes[id] = new Array();
				formacoes[id]['nome'] = nome;
				
				$this.find('select#formacao').append('<option value="'+id+'">'+nome+'</option>');
			}

			incluirFormacao = function(id)
			{
				var tabelaConteudo = $this.find('#tabelaConteudo tbody');
				if(!tabelaConteudo.find('#'+id).html())
					tabelaConteudo.append('<tr id="'+id+'"><td>'+formacoes[id]['nome']+'</td><td><a href="#" title="Excluir" onclick="excluirFormacao('+id+')"><img src="../../images/icons/delete.gif" /></a></td></tr>');
				else
				{
					$this.alert('Este interesse j&aacute; esta incluso.', null, {
						clickOK: function() {
						}
					});
				}
			}
			
			excluirFormacao = function(id)
			{
			}
			
			inserirFormacao(1, 'Bacharelado');
			inserirFormacao(2, 'Mestrado');
			inserirFormacao(3, 'Doutorado');
		};
	});
</script>

<div id="title">Alterar Forma&ccedil;&atilde;es Academicas</div>
<table>
	<tbody>
		<tr>
			<td>Forma&ccedil;&atilde;es</td>
		</tr>
		<tr>
			<td><select name="formacao" id="formacao"></select></td>
			<td><a href="#" id="incluir" title="Incluir"><img
				src="../../images/icons/add.gif"></a></td>
		</tr>
	</tbody>
</table>
<br>
<table id="tabelaConteudo">
	<caption style="width: 100%; text-align: left;">Forma&ccedil;&atilde;es
	do Membro</caption>
	<tbody></tbody>
</table>