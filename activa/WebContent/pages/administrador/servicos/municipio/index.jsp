<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Municipio"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'municipio';
			
			adicionarMunicipio = function(id, nome, estado)
			{	
				var nomeLink = $('<a href="#"></a>').html(nome);

				var exibirDetalhe = function() {
					$this.newPage('../../MunicipioServlet?opcao=D', {pkMunicipio: id});
				};
				
				var tr = $('<tr></tr>');
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
								$this.newPage('../../MunicipioServlet?opcao=D', {pkMunicipio: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../MunicipioServlet?opcao=E", {pkMunicipio: id}, function() {
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
					.append($('<td></td>').html(nomeLink).click(exibirDetalhe))
				);
			}
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<Municipio> colMun = (Collection<Municipio>)request.getAttribute("colMun");
			for(Municipio mun:colMun)
				out.print("adicionarMunicipio("+mun.getPkMunicipio()+", '"+mun.getMunicipio()+"'.clearNull(), '"+mun.getUf().getEstado()+"'.clearNull());");	
		%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('../../MunicipioServlet?opcao=D', {inclusao: true});
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Munic&iacute;pios <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Estado</th>
			<th><img src="../../images/icons/b_search.png" />Município</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>