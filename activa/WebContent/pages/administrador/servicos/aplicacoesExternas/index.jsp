<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.integra.AplicacaoExterna"%>
<%@page import="modelo.integra.Recurso"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'aplicacoesExternas';

			adicionarAplicacao = function(id, nome, url, arrRecurso, arrPath)
			{
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).empty().load("../../AplicacaoExternaServlet?opcao=E", {idAplicacao: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});

				var linkDetalhe = $('<a></a>').attr('href', '#').append('Mostrar/Esconder Detalhes').click(function(e) {
					$('tr.' + id + ':visible').slideUp('slow');
					$('tr.' + id + ':hidden').slideDown('slow');
				});
				
				var leftColumn = '<b>Status:</b> Aplicação Carregada';
				leftColumn += '<br><b>Recursos:</b>';
				for (var i = 0; i < arrRecurso.length; i++) {
					leftColumn += '<br>' + arrRecurso[i] + ' (' + arrPath[i] + ')';    
			    }

				$this.find('table tbody')
				.append(tr
					.append($('<td width=470></td>').html('<b>' + nome + '</b>&nbsp;(' + url + ')'))
					.append($('<td align=right></td>').append(linkDetalhe))
				)
				.append($('<tr class=' + id + '></tr>')
					.append($('<td colspan=2></td>').html(leftColumn))
				);

				$('tr.' + id).hide();
			};

			var tbody = $this.find('table tbody');
			tbody.find('tr').remove();

			<%			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<AplicacaoExterna> colAplicacao = (Collection<AplicacaoExterna>)request.getAttribute("colAplicacao");
			if (colAplicacao != null) {
				for (AplicacaoExterna aplicacaoExterna:colAplicacao) {
					ArrayList<Recurso> recursoList = (ArrayList<Recurso>) aplicacaoExterna.getRecursoList();

					out.print("var arrRecurso = new Array(" + recursoList.size() + ");");
					out.print("var arrPath = new Array(" + recursoList.size() + ");");
							
					for (int i = 0; i < recursoList.size(); i++) {
						out.print("arrRecurso[" + i + "] = '" + recursoList.get(i).getNome().replaceAll("'", "") + "';");
						out.print("arrPath[" + i + "] = '" + recursoList.get(i).getPath() + "';");
					}
					
					out.print("adicionarAplicacao("+aplicacaoExterna.getIdAplicacao()+", '"+aplicacaoExterna.getNome()+"'.clearNull(), '"+aplicacaoExterna.getUrl()+"'.clearNull(), arrRecurso, arrPath);");
				}
			}
			%>
						
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		};
	});
</script>

<table class="tabelaResult" border="0">
	<caption>Aplicações Externas <a href="#" id="incluir" title="Incluir"><img
		src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome<br></th>
			<th></th>
		</tr>
	</thead>
	<tbody></tbody>
</table>
