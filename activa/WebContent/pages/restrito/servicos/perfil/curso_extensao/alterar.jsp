<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="modelo.CursoExtensao"%>
<% CursoExtensao cursoExtensao = (CursoExtensao)request.getAttribute("cursoExtensao"); %>
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
			
			$this.find('button#alterar').click(function() {
				var no_cursoExtensao = $this.find('#no_cursoExtensao');
				var ds_cursoExtensao = $this.find('#ds_cursoExtensao');
				
				if(no_cursoExtensao.val().length == 0)
				{
					$this.alert('Por favor, preencha o campo nome.', null, {clickOK: function() { no_cursoExtensao.focus(); }});
					return false;
				}
				
				if(ds_cursoExtensao.val().length == 0)
				{
					$this.alert('Por favor, preencha o campo Descri&ccedil;&atilde;o.', null, {clickOK: function() { ds_cursoExtensao.focus(); }});
					return false;
				}


				$this.sendRequest('../../CursoExtensaoServlet?opcao=A',
					{pkCursoExtensao: <%= cursoExtensao.getPkCursoExtensao() %>, no_cursoExtensao: no_cursoExtensao.val(), ds_cursoExtensao: ds_cursoExtensao.val()},
					function() { botaoVoltar.click(); }
				);
			});

			$this.find('input#no_cursoExtensao').val("<%= cursoExtensao.getNome() %>".clearNull());
			$this.find('textarea#ds_cursoExtensao').val("<%= cursoExtensao.getDescricao() %>".clearNull());			
		}
	});
</script>

<div id="title">Alterar Curso de Extens&atilde;o</div>
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
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>