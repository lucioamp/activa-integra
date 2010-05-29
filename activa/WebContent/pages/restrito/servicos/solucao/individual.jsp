<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
		
			adicionarSolucao = function(id, nome)
			{
				var tbody = $this.find('table#solucoes tbody');
				
				var detalheLink = $('<a href="#"></a>').html(nome);
				
				var tdEditar = $('<td></td>');
				var tdExcluir = $('<td></td>');
				criarBotaoEditar(tdEditar);
				criarBotaoExcluir(tdExcluir);
				
				tbody.append($('<tr></tr>')
					.append($('<td></td>').html(detalheLink))
					.append(tdEditar)
					.append(tdExcluir)
				);
			}
			
			adicionarSolucao(1, 'solucao 1');
			adicionarSolucao(2, 'solucao 2');
		};
	});
</script>

<div id="page_1">
<p>
<button type="button" class="ui-state-default ui-corner-all"
	name="gerar" id="gerar">Gerar</button>
</p>
<table id="solucoes" class="tabelaResult">
	<tbody>
	</tbody>
</table>
</div>