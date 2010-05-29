<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../AreaTrabalhoServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});		
		
			$this.find('button#incluir').click(function (e) {
				
				var campo_no_areaTrabalho = $this.find('[id="no_areaTrabalho"]');
				var campo_ds_areaTrabalho = $this.find('[id="ds_areaTrabalho"]');
				
				var no_areaTrabalho = campo_no_areaTrabalho.val();
				var ds_areaTrabalho = campo_ds_areaTrabalho.val();

				if(no_areaTrabalho.length == 0)
				{
					$this.alert('Por favor, preencha o campo nome.', null, {clickOK: function() { campo_no_areaTrabalho.focus(); }});
					return false;
				}
				
				if(ds_areaTrabalho.length == 0)
				{
					$this.alert('Por favor, preencha o campo Descri&ccedil;&atilde;o.', null, {clickOK: function() { campo_ds_areaTrabalho.focus(); }});
					return false;
				}

				$this.sendRequest('../../AreaTrabalhoServlet?opcao=I',
						{no_areaTrabalho: no_areaTrabalho, ds_areaTrabalho: ds_areaTrabalho},
						function() { botaoVoltar.click(); }
					);
									
			});
		};
	});
</script>

<div id="title">Incluir Area de trabalho</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_areaTrabalho"
				id="no_areaTrabalho" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_areaTrabalho" name="ds_areaTrabalho" cols="50" rows="10"></textarea></td>
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