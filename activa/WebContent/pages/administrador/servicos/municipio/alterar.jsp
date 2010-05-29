<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Bairro"%>
<% Municipio municipio = (Municipio)request.getAttribute("municipio"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['bairros'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../MunicipioServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					delete object['bairros'];
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
						
			$this.find('button#alterar').click(function() {
				var nome = $this.find('#nome');
				var uf = $this.find('#uf');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				var bairros = "";
				var bairro = null;
				for (var i in object['bairros'])
				{
					bairro = object['bairros'][i];

					if(bairro.virtual == true)
						bairros += 'nome:'+bairro.nome+', virtual: true&';
					else if(bairro.deletado == true)
						bairros += 'id: '+bairro.id+', nome:'+bairro.nome+', deletar: true&';
					else if(bairro.alterado == true)
						bairros += 'id: '+bairro.id+', nome:'+bairro.nome+', alterar: true&';
				}

				$this.sendRequest('../../MunicipioServlet?opcao=A',
					{pkMunicipio: <%= municipio.getPkMunicipio() %>, municipio: nome.val(), uf: uf.val(), bairros: bairros},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirBairro').click(function() {
				$mainPage.newPage('servicos/municipio/bairro/incluir.jsp');
			});

			adicionarBairro = function(id, nome) {
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html(nome);

				tr.contextMenu({
					buttons: {
						Editar: {
							icon: "edit",
							onClick: function() {
								$mainPage.newPage('../../MunicipioServlet?opcao=B', {id: id, nome: nome, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['bairros'])
										{
											if(object['bairros'][i].id == id)
												object['bairros'][i].deletado = true;
										}
										tr.clear();
									}
								});
							}
						}
					}
				});

				object['bairros'].push({id: id, nome: nome, virtual: false, deletado: false, alterado: false, trReferencia: tr});

				$this.find('table#tabela_bairros tbody').append(tr.append(tdNome).append(tdEditar).append(tdExcluir));
			};

			$this.find('input#nome').val("<%= municipio.getMunicipio() %>".clearNull());

			<%
				@SuppressWarnings ("unchecked")
				Collection<Uf> colUf = (Collection<Uf>)request.getAttribute("colUf");
				for(Uf uf:colUf)
					out.print("$this.find('select#uf').addOption("+uf.getPkEstado()+", '"+uf.getEstado()+"'.clearNull());");
			
				@SuppressWarnings ("unchecked")
				Collection<Bairro> colBairro = (Collection<Bairro>)request.getAttribute("colBairro");
				for(Bairro bairro:colBairro)
					out.print("adicionarBairro("+bairro.getPkBairro()+", '"+bairro.getBairro()+"'.clearNull());");
			%>
			
			$this.find('select#uf').selectOptions("<%= municipio.getUf().getPkEstado() %>".clearNull());
		}
	});
</script>

<div id="title">Alterar Munic&iacute;pio</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><select name="uf" id="uf">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_bairros" class="tabelaResult">
				<caption>Bairros <a href="#" id="incluirBairro"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
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