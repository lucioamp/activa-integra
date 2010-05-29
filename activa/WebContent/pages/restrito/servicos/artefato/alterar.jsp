<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			$this.find('#alterarArtefato').click(function() {
				$this.alert('O artefato foi alterado com sucesso.');
			});
		
			$this.find('#incluirTag').click(function() {
				$this.newPage('servicos/tags/incluir.jsp', null);
			});
		}
	});
</script>
<div id="page_1">
<div id="title">Alterar Artefato</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome do Servico:</td>
			<td><input type="text" name="no_servico" id="no_servico" /></td>
		</tr>
		<tr>
			<td>Nome do Autor:</td>
			<td><input type="text" name="no_autor" id="no_autor" /></td>
		</tr>
		<tr>
			<td>Ano de Publica&ccedil;&atilde;o:</td>
			<td><input type="text" name="nu_anoPublicacao"
				id="nu_anoPublicacao" /></td>
		</tr>
		<tr>
			<td>Data de cria&ccedil;&atilde;o:</td>
			<td><input type="text" name="dt_criacao" id="dt_criacao" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags<a href="#" id="incluirTag" title="Incluir"><img
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
				<tbody>
					<tr>
						<td><a href="#" onclick="mostrarTag(1);" title="Consultar">Tag
						1</a></td>
						<td><a href="#" onclick="alterarTag(1);" title="Editar"><img
							src="../../images/icons/b_edit.png" /></a></td>
						<td><a href="#" onclick="excluirTag(1);" title="Excluir"><img
							src="../../images/icons/delete.gif" /></a></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td>
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">alterar</button>
			</td>
		</tr>
	</tfoot>
</table>
</div>