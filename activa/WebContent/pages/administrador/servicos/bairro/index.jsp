<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Bairro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'bairro';
			
			adicionarBairro = function(id, nome, estado, municipio)
			{
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');

				var exibirDetalhe = function() {
					$this.newPage('../../BairroServlet?opcao=D', {pkBairro: id});
				};

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: exibirDetalhe
						},
						Editar: {
							icon: "edit",
							onClick: function() {
							$this.newPage('../../BairroServlet?opcao=D', {pkBairro: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../BairroServlet?opcao=E", {pkBairro: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});
				
				$this.find('table tbody').append(tr
					.append($('<td></td>').html($('<a href="#"></a>').html(estado)).click(exibirDetalhe))
					.append($('<td></td>').html($('<a href="#"></a>').html(municipio)).click(exibirDetalhe))
					.append($('<td></td>').html(nomeLink).click(exibirDetalhe))
				);
			}

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<Bairro> colBairro = (Collection<Bairro>)request.getAttribute("colBairro");
			for(Bairro bairro:colBairro)
				out.print("adicionarBairro("+bairro.getPkBairro()+", '"+bairro.getBairro()+"'.clearNull(), '"+bairro.getMunicipio().getUf().getEstado()+"'.clearNull(), '"+bairro.getMunicipio().getMunicipio()+"'.clearNull());");	
			%>
						
			$this.find('a#incluir').click(function() {
				$this.newPage('../../BairroServlet?opcao=D', {inclusao: true});
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Bairros <a href="#" id="incluir" title="Incluir"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Estado</th>
			<th><img src="../../images/icons/b_search.png" />Município</th>
			<th><img src="../../images/icons/b_search.png" />Bairro</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>