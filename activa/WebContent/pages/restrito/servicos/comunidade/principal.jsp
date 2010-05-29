<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var adicionarComunidade = function(id, nome, img, owner)
			{
				var tbody = $this.find('table#tabelaComunidade tbody');
							
				var comunidade = $('<td></td>').append('<div style="text-align: center;">'+nome+'</div>').prepend(
					$('<a></a>').attr('href', '#').append('<img src="../../images/servicos/semFoto.gif" class="membroFoto" />')
						.click(function() {
							if(owner == true)
								tab.addTab(id, 'servicos/comunidade/alterar.jsp', 'Alterar Comunidade');
							else
								tab.addTab(id, 'servicos/comunidade/detalhe.jsp', 'Detalhe da Comunidade');
							return false;
						})
				);
				
				var tr = tbody.find('tr');
				
				if(tr.html() == null)
					tbody.append($('<tr></tr>').append(comunidade));
				else
				{
					tbody.find('tr:eq('+(tr.length-1)+')').each(function() {
						if($(this).find('td').length == 4)
							tbody.append($('<tr></tr>').append(comunidade));
						else
							$(this).append(comunidade);
					});
				}
			}
			
			adicionarComunidade(1, 'Comunidade 1', '', true);
			adicionarComunidade(2, 'Comunidade 2', '');
			adicionarComunidade(3, 'Comunidade 3', '');
		};
	});
</script>

<div id="page_1">
<form name="form" id="form" action="" method="post"
	onsubmit="return false;">
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nomeComunidade" id="nomeComunidade" /></td>
			<td><a href="#" onclick="" title="Consultar"><img
				src="../../images/icons/b_search.png" /></a></td>
		</tr>
	</tbody>
</table>
</form>
<fieldset class="blue">
<table id="tabelaComunidade" class="tabelaFoto">
	<tbody style="text-align: center;">
	</tbody>
</table>
</fieldset>
</div>