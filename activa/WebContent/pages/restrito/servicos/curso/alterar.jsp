<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Curso"%>
<%@page import="modelo.Aula"%>
<%@page import="util.Ferramenta"%>
<% Curso curso = (Curso)request.getAttribute("curso"); %>

<%@page import="modelo.ArquivoAula"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, tab) {

			var object = $this.getObject();
			object['aulas'] = new Array();

			$this.find('a#inclusaoAula').click(function(e) {
				e.preventDefault();

				$this.newPage('servicos/curso/aula/incluir.jsp');
			});

			var id = '<%= curso.getPkServico() %>';

			var nome = $this.find('input#no_curso').val('<%= curso.getNome() %>');
			var desc = $this.find('textarea#ds_servico');
			$this.find('div#title').html('Alteração de <%= curso.getNome() %>');

			object['onLoad'] = function()
			{
				$this.hide().fadeIn(2000);
			};
			
			$this.find('button#alterar').click(function (e) {
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
				var aula = null;
				for (var i in object['aulas'])
				{
					aula = object['aulas'][i];
					
					aulas += "id: " + aula.id + "#";
					aulas += "assunto: " + aula.assunto + "#";
					aulas += "desc: " + aula.desc + "#";
					aulas += "peso: " + aula.peso + "#";
					aulas += "data: " + aula.data + "#";
					aulas += "arquivo: " + aula.arquivo+"#";
					if(aula.virtual == true)
						aulas += 'virtual: true';
					else if(aula.deletado == true)
						aulas += 'deletar: true';
					else if(aula.alterado == true)
						aulas += 'alterar: true';
					aulas += "&";
				}

				$this.sendRequest('../../MembroCursoServlet?opcao=A',
					{id: id, nome: nome.val(), desc: desc.val(), aulas: aulas},
					function(data) {
						var result = trim(data);

						if(result == "true")
							$this.alert('Curso alterado com sucesso.', null, {clickOK: function(e) {
								tab.tabs('load', object['id']);
							}});
						else
							$this.alert('Erro ao tentar alterar o curso.');
					}
				);
			});

			var adicionarAula = function(id, assunto, desc, peso, data)
			{
				var tr = $('<tr></tr>');

				var aula = {id: id, assunto: assunto, desc: desc, arquivos: new Array(), peso: peso, data: data, virtual: false, deletado: false, alterado: false, trReferencia: tr};

				var tdAssunto = $('<td></td>').html($('<a href="#"></a>').append(assunto).click(function(e) {
					e.preventDefault();
					object['_aula'] = aula;
					$this.newPage('servicos/curso/aula/detalhe.jsp', null);
				}));

				var tdData = $('<td></td>').html(data);

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								object['_aula'] = aula;
								$this.newPage('servicos/curso/aula/detalhe.jsp', null);
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								object['_aula'] = aula;
								$this.newPage('servicos/curso/aula/alterar.jsp', null);
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										aula.deletado = true;
										tr.clear();
									}
								});
							}
						}
					}
				});
				object['aulas'].push(aula);

				$this.find('table#aulas tbody').append(tr.append(tdAssunto).append(tdData));

				return aula;
			};

			var adicionarArquivo = function(id, nome, aula)
			{
				aula.arquivos.push({id: id, nome: nome});
			};

			var aula = null;
			<%
			@SuppressWarnings ("unchecked")
			Collection<Aula> aulas = (Collection<Aula>)request.getAttribute("aulas");
			for(Aula aula:aulas)
			{
				out.print("aula = adicionarAula("+aula.getPkAula()+", '"+aula.getAssunto()+"'.clearNull(), '"+Ferramenta.clearScape(aula.getAula())+"'.clearNull(), "+aula.getPeso()+", '"+Ferramenta.formatarData(aula.getData(), true)+"');");
				
				Collection<ArquivoAula> arquivosAula = ArquivoAula.consultarPorAula(aula);
				for(ArquivoAula arquivoAula:arquivosAula)
				{
					out.print("adicionarArquivo("+arquivoAula.getPkArquivoAula()+", '"+arquivoAula.getNome()+"'.clearNull(), aula);");
				}			
			}
			%>
		};
	});
</script>

<div id="title"></div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_curso" id="no_curso" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o da Ementa:</td>
			<td><textarea name="ds_servico" id="ds_servico" cols="50" rows="10"><%= curso.getDescricao() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="aulas" class="tabelaResult" border="1">
				<caption>Aulas<a href="#" id="inclusaoAula"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
				<thead>
					<tr>
						<th>Assunto</th><th>Data</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="alterar" id="alterar">Alterar</button>
			</td>
		</tr>
	</tfoot>
</table>