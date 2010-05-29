<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['municipios'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../UfServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					delete object['municipios'];
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#nome');
				var sigla = $this.find('#sigla');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(sigla.val().length == 0)
				{
					$this.alert('Por favor, digite uma sigla.', null, {clickOK: function() { sigla.focus(); }});
					return false;
				}

				var municipios = "";
				for (var i in object['municipios'])
					municipios += object['municipios'][i].nome + ":";

				$this.sendRequest('../../UfServlet?opcao=I',
					{estado: nome.val(), sigla: sigla.val(), municipios: municipios},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirMunicipio').click(function() {
				$mainPage.newPage('servicos/unidade_federacao/municipio/incluir.jsp');
			});			
		}
	});
</script>

<div id="title">Incluir Unidade de Federa&ccedil;&atilde;o</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Sigla:</td>
			<td><input type="text" name="sigla" id="sigla" maxlength="2" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_municipios" class="tabelaResult">
				<caption>Munic&iacute;pios <a href="#"
					id="incluirMunicipio" title="Incluir"><img
					src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
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