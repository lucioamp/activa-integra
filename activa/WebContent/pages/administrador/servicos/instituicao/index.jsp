<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Instituicao"%>

<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'instituicao';
			
			adicionarInstituicao = function(id, nome, cep)
			{
				var nomeLink = $('<a href="#"></a>').html(nome);
				
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../InstituicaoServlet?opcao=D', {pkInstituicao: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
							$this.newPage('../../InstituicaoServlet?opcao=D', {pkInstituicao: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../InstituicaoServlet?opcao=E", {pkInstituicao: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});

				
				$this.find('table tbody').append(tr
					.append($('<td></td>').html(nomeLink).click(function(e) {
						$this.newPage('../../InstituicaoServlet?opcao=D', {pkInstituicao: id});
					}))
					.append('<td>'+cep+'</td>')
				);
			}			
			
			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<Instituicao> instituicoes = (Collection<Instituicao>)request.getAttribute("instituicoes");
			for(Instituicao instituicao:instituicoes)
				out.print("adicionarInstituicao("+instituicao.getPkInstituicao()+", '"+instituicao.getNome()+"'.clearNull(), '"+instituicao.getCep()+"'.clearNull());");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult">
	<caption>Institui&ccedil;&otilde;es <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
			<th>Cep</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>