<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Idioma"%>
<%@page import="modelo.MembroIdioma"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {

			var	idioma = $this.find('select#idioma');

			$this.find('a#incluir').click(function() {
				
				if(idioma.val() == 0)
				{
					$this.alert('Por favor, selecione um idioma.', null, {clickOK: function() { idioma.focus(); }});
					return false;
				}

				if($this.find('tr#[id="'+idioma.val()+'"]').html() != null)
				{
					$this.alert('Este idioma ja foi selecionado.', null, {clickOK: function() { idioma.focus(); }});
					return false;
				}

				var tr = $('<tr></tr>').attr('id', idioma.val()).hide();
				var tdExcluir = $('<td></td>');
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
						$this.sendRequest('../../MembroIdiomaServlet?opcao=E',
								{pkIdioma: idioma.val()},
								function() {
									tr.clear();
								}
							);
						}
					});
				});

				$this.sendRequest('../../MembroIdiomaServlet?opcao=I',
					{pkIdioma: idioma.val()},
					function() {
						$this.find('table#tabelaIdiomas tbody').append(tr
							.append($('<td></td>').html(idioma.selectedTexts().toString()))
							.append(tdExcluir).fadeIn(500)
						);
					}
				);
			});

			adicionarMembroIdioma = function(id, nome) {
				var tr = $('<tr></tr>').attr('id', id);
				var tdExcluir = $('<td></td>');
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
						$this.sendRequest('../../MembroIdiomaServlet?opcao=E',
								{pkIdioma: id},
								function() {
									tr.clear();
								}
							);
						}
					});
				});
				
				$this.find('table#tabelaIdiomas tbody').append(tr
					.append($('<td></td>').html(nome))
					.append(tdExcluir)
				);
			}

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<Idioma> idiomas = (Collection<Idioma>)request.getAttribute("idiomas");
			for(Idioma idioma:idiomas)
				out.print("idioma.addOption('"+idioma.getPkIdioma()+"'.clearNull(), '"+idioma.getNome()+"'.clearNull());");
			
			@SuppressWarnings ("unchecked")
			Collection<MembroIdioma> membroIdiomas = (Collection<MembroIdioma>)request.getAttribute("membroIdiomas");
			for(MembroIdioma idioma:membroIdiomas)
				out.print("adicionarMembroIdioma('"+idioma.getIdioma().getPkIdioma()+"'.clearNull(), '"+idioma.getIdioma().getNome()+"'.clearNull());");
			%>

			idioma.selectOptions("0");
		};
	});

</script>

<div id="title">Alterar Idioma</div>
<table>
	<tbody>
		<tr>
			<td>Idiomas</td>
		</tr>
		<tr>
			<td><select name="idioma" id="idioma">
				<option value="0"></option>
			</select></td>
			<td><a href="#" id="incluir" title="Incluir"><img
				src="../../images/icons/add.gif"></a></td>
		</tr>
	</tbody>
</table>
<br>
<table id="tabelaIdiomas">
	<caption style="width: 100%; text-align: left;">Idiomas do
	Membro</caption>
	<tbody></tbody>
</table>