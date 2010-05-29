<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['tags'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../AreaInteresseServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#nome');
				var desc = $this.find('#desc');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				var tags = "";
				for (var i in object['tags'])
				{
					tags += "nome: " + object['tags'][i].nome + "#";
					tags += "desc: " + object['tags'][i].desc;
					tags +="&";
				}

				$this.sendRequest('../../AreaInteresseServlet?opcao=I',
					{nome: nome.val(), tags: tags, descricao: desc.val()},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirTag').click(function() {
				$mainPage.newPage('servicos/tags/incluir.jsp', null);
			});
		}
	});
</script>

<div id="title">Incluir &Aacute;rea de Interesse</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="desc" name="desc"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags<a href="#" id="incluirTag" title="Incluir"><img
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