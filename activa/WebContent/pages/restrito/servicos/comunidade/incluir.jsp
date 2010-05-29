<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			object['tabs'] = new Array();
			object['artefatos'] = new Array();
			
			$this.find('#incluirComunidade').click(function() {
				$this.alert('A comunidade foi incluida com sucesso.');
			});
		
			$this.find('#incluirArtefato').click(function() {
				$this.newPage('servicos/comunidade/artefato/incluir.jsp', null);
			});
						
			$this.find('#incluirTags').click(function() {
				$this.newPage('servicos/tags/incluir.jsp', null);
			});
			
			$this.find('input#imageFile').filestyle({ 
				image: "images/choose-file.gif",
				imageheight : 22,
				imagewidth : 82,
				width : 124
			});
		}
	});

</script>

<div id="page_1">

<div id="title">Incluir Comunidade</div>
<table>
	<tbody>
		<tr>
			<td rowspan="6" valign="top" style="width: 25%;">
			<fieldset class="blue" style="height: 315px;"><a href="#"><img
				src="../../images/servicos/semFoto.gif" class="membroFoto" /></a>
			<p style="text-align: center;">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluirComunidade" id="incluirComunidade">Incluir</button>
			</p>
			</fieldset>
			</td>
			<td style="width: 40%;">Nome:</td>
			<td><input type="text" id="nome" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><input type="text" id="desc" /></td>
		</tr>
		<!--<tr>
			<td>Data de cria&ccedil;&atilde;o:</td>
			<td><input type="text" id="dt_criacao" /></td>
		</tr>-->
		<tr>
			<td>Foto da comunidade</td>
			<td><input type="file" name="imageFile" id="imageFile" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags<a href="#" name="incluirTags"
					id="incluirTags" title="Incluir"><img
					src="../../images/icons/add.gif"></a><br />
				<input type="text" id="pesquisarTag" name="pesquisarTag" /><a
					href="#"><img src="../../images/icons/b_search.png" /></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
						<th>Alterar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_artefatos" class="tabelaResult">
				<caption>Artefatos<a href="#" name="incluirArtefato"
					id="incluirArtefato" title="Incluir"><img
					src="../../images/icons/add.gif"></a><br />
				<input type="text" id="pesquisarArtefato" name="pesquisarArtefato" /><a
					href="#"><img src="../../images/icons/b_search.png" /></a></caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
						<th>Alterar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td colspan="2">
				<table id="tabela_post" class="tabelaResult">
					<caption>Mensagens</caption>
					<tbody></tbody>
				</table>
			</td>
		</tr>-->
	</tbody>
</table>
</div>