<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.AreaInteresse"%>
<%@page import="modelo.AreaInteresseTag"%>
<%@page import="modelo.Tag"%>
<% AreaInteresse area = (AreaInteresse)request.getAttribute("area"); %>
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
						
			$this.find('button#alterar').click(function() {
				var nome = $this.find('#nome');
				var desc = $this.find('#desc');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				var tags = "";
				var tag = null;
				for (var i in object['tags'])
				{
					tag = object['tags'][i];

					tags += 'id: '+tag.id+'# nome:'+tag.nome+' # desc:'+tag.desc+'# ';
					if(tag.virtual == true)
						tags += 'virtual: true&';
					else if(tag.deletado == true)
						tags += 'deletar: true&';
					else if(tag.alterado == true)
						tags += 'alterar: true&';
				}
				
				$this.sendRequest('../../AreaInteresseServlet?opcao=A',
					{pkAreaInteresse: <%= area.getPkAreaInteresse() %>, nome: nome.val(), descricao: desc.val(), tags: tags},
					function() { botaoVoltar.click(); }
				);
			});
		
			$this.find('#incluirTag').click(function() {
				$mainPage.newPage('servicos/tags/incluir.jsp', null);
			});

			adicionarTag = function(id, nome, desc) {
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(nome).click(function(e) {
					e.preventDefault();
					$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaDetalhe"});
				}));
				
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaDetalhe"});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$mainPage.newPage('../../TagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['tags'])
										{
											if(object['tags'][i].id == id)
												object['tags'][i].deletado = true;
										}
										tr.clear();
									}
								});
							}
						}
					}
				});
				
				object['tags'].push({id: id, nome: nome, desc: desc, virtual: false, deletado: false, alterado: false, trReferencia: tr});

				$this.find('table#tabela_tags tbody').append(tr.append(tdNome));
			};

			$this.find('input#nome').val("<%= area.getNome() %>".clearNull());

			<%
			@SuppressWarnings ("unchecked")
			Collection<AreaInteresseTag> colTag = (Collection<AreaInteresseTag>)request.getAttribute("colTag");
			for(AreaInteresseTag area_tag:colTag)
				out.print("adicionarTag('"+area_tag.getTag().getPkTag()+"', '"+area_tag.getTag().getNome()+"'.clearNull(), '"+area_tag.getTag().getDescricao()+"'.clearNull());");
			%>
		}
	});
</script>

<div id="title">Alterar &Aacute;rea de Interesse</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="desc" name="desc"><%= area.getDescricao() %></textarea></td>
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
				name="alterar" id="alterar">Alterar</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>