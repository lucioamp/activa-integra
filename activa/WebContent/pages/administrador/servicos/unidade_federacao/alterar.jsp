<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<% Uf uf = (Uf)request.getAttribute("uf"); %>
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
						
			$this.find('button#alterar').click(function() {
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
				var municipio = null;
				for (var i in object['municipios'])
				{
					municipio = object['municipios'][i];

					if(municipio.virtual == true)
						municipios += 'nome:'+municipio.nome+', virtual: true&';
					else if(municipio.deletado == true)
						municipios += 'id: '+municipio.id+', nome:'+municipio.nome+', deletar: true&';
					else if(municipio.alterado == true)
						municipios += 'id: '+municipio.id+', nome:'+municipio.nome+', alterar: true&';
				}

				$this.sendRequest('../../UfServlet?opcao=A',
					{pkEstado: <%= uf.getPkEstado() %>, estado: nome.val(), sigla: sigla.val(), municipios: municipios},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirMunicipio').click(function() {
				$mainPage.newPage('servicos/unidade_federacao/municipio/incluir.jsp');
			});

			function adicionarMunicipio(id, nome) {
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html(nome);
				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
							$mainPage.newPage('../../UfServlet?opcao=M', {id: id, nome: nome, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['municipios'])
										{
											if(object['municipios'][i].id == id)
												object['municipios'][i].deletado = true;
										}
										tr.clear();
									}
								});
							}
						}
					}
				});

				object['municipios'].push({id: id, nome: nome, virtual: false, deletado: false, alterado: false, trReferencia: tr});
				
				$this.find('table#tabela_municipios tbody').append(tr.append(tdNome));
			};

			$this.find('input#nome').val("<%= uf.getEstado() %>");
			$this.find('input#sigla').val("<%= uf.getSigla() %>");
			<%
				@SuppressWarnings ("unchecked")
				Collection<Municipio> colMunicipio = (Collection<Municipio>)request.getAttribute("colMunicipio");
				for(Municipio municipio:colMunicipio)
					out.print("adicionarMunicipio("+municipio.getPkMunicipio()+", '"+municipio.getMunicipio()+"'.clearNull());");
			%>
		}
	});
</script>

<div id="title">Alterar Unidade de Federa&ccedil;&atilde;o</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>sigla:</td>
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
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>