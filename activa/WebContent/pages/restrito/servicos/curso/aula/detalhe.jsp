<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(document).ready(function () {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();

			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#nu_peso').onlyNumber().html(object['_aula'].peso);
			$this.find('label#ds_assunto').html(object['_aula'].assunto);
			$this.find('textarea#ds_aula').html(object['_aula'].desc);
			$this.find('label#dt_aula').html(object['_aula'].data);

			$this.find('div#title').html('Detalhe de '+object['_aula'].assunto);

			var adicionarArquivo = function(id, nome, newArquivo)
			{
				if(newArquivo == true)
					nome = hex_md5(id)+"/"+nome;
				
				var tr = $('<tr></tr>');
				var tdArquivo = $('<td></td>').append('<a href="../../arquivo/aula/'+nome+'" target="_blank">'+nome.split('/')[1]+'</a>');
	
				$this.find('table#arquivos tbody').append(tr.append(tdArquivo));
			};

			for (var i in object['_aula'].arquivos)
			{
				var arquivo = object['_aula'].arquivos[i];
				adicionarArquivo(arquivo.id, arquivo.nome);
			}

			object['_aula'] = null;
		};
	});
</script>
<div id="title">Detalhe da Aula 1</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 15%;">Assunto:</td>
			<td><label id="ds_assunto"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_aula" name="ds_aula" readonly="readonly"></textarea></td>
		</tr>
		<tr>
			<td>Data:</td>
			<td><label id="dt_aula"></label></td>
		</tr>
		<tr>
			<td>Peso:</td>
			<td><label id="nu_peso"></label></td>
		</tr>
		<tr id="arquivo_aula">
			<td colspan="2">
			<table id="arquivos" class="tabelaResult" border="1">
				<caption>Arquivos</caption>
				<thead>
					<tr>
						<th>Arquivo</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
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
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>