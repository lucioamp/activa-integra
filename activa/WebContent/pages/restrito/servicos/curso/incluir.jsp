<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			object['aulas'] = new Array();

			$this.find('a#inclusaoAula').click(function(e) {
				e.preventDefault();

				$this.newPage('servicos/curso/aula/incluir.jsp');
			});
			
			$this.find('button#incluir').click(function (e) {
				var nome = $this.find('input#no_curso');
				var desc = $this.find('textarea#ds_servico');

				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				var aulas = "";
				/*for (var i in object['aulas'])
				{
					aulas += "assunto: " + object['aulas'][i].assunto + "#";
					aulas += "desc: " + object['aulas'][i].desc + "#";
					aulas += "peso: " + object['aulas'][i].peso + "#";
					aulas += "arquivo: " + object['aulas'][i].arquivo;
					aulas += "&";
				}*/

				$this.sendRequest('../../MembroCursoServlet?opcao=I',
					{nome: nome.val(), desc: desc.val(), aulas: aulas},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Curso incluido com sucesso.', null, {clickOK: function() {
								nome.val('');
								desc.val('');
								delete object['aulas'];
								object['aulas'] = new Array();
								$this.find('table#aulas tbody').empty();
							}});
						}else
							$this.alert('Erro ao tentar incluir o curso.');
					}
				);
			});
		};
	});
</script>

<div id="title">Incluir Curso</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_curso" id="no_curso" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o da Ementa:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"></textarea></td>
		</tr>
		<!--<tr>
			<td colspan="2">
			<table id="aulas" class="tabelaResult" border="1">
				<caption>Aulas<a href="#" id="inclusaoAula"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th>Assunto</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr> -->
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			</td>
		</tr>
	</tfoot>
</table>