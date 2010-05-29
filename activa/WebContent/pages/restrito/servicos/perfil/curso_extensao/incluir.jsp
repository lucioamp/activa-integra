<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../CursoExtensaoServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});		
		
			$this.find('button#incluir').click(function (e) {
				
				var campo_no_cursoExtensao = $this.find('[id="no_cursoExtensao"]');
				var campo_ds_cursoExtensao = $this.find('[id="ds_cursoExtensao"]');
				
				var no_cursoExtensao = campo_no_cursoExtensao.val();
				var ds_cursoExtensao = campo_ds_cursoExtensao.val();

				if(no_cursoExtensao.length == 0)
				{
					$this.alert('Por favor, preencha o campo nome.', null, {clickOK: function() { campo_no_cursoExtensao.focus(); }});
					return false;
				}
				
				if(ds_cursoExtensao.length == 0)
				{
					$this.alert('Por favor, preencha o campo Descri&ccedil;&atilde;o.', null, {clickOK: function() { campo_ds_cursoExtensao.focus(); }});
					return false;
				}

				$this.sendRequest('../../CursoExtensaoServlet?opcao=I',
						{no_cursoExtensao: no_cursoExtensao, ds_cursoExtensao: ds_cursoExtensao},
						function() { botaoVoltar.click(); }
					);
									
			});
		};
	});
</script>

<div id="title">Incluir Curso de Extens&atilde;o</div>
<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="no_cursoExtensao"
				id="no_cursoExtensao" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_cursoExtensao" name="ds_cursoExtensao" cols="50" rows="10"></textarea></td>
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