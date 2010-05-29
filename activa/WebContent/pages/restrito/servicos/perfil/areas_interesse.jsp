<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.AreaInteresse"%>
<%@page import="modelo.MembroAreaInteresse"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {

			var	interesse = $this.find('select#interesse');

			$this.find('a#incluir').click(function() {
				
				if(interesse.val() == 0)
				{
					$this.alert('Por favor, selecione uma área.', null, {clickOK: function() { interesse.focus(); }});
					return false;
				}

				if($this.find('tr#[id="'+interesse.val()+'"]').html() != null)
				{
					$this.alert('Esta área ja foi selecionado.', null, {clickOK: function() { interesse.focus(); }});
					return false;
				}

				var tr = $('<tr></tr>').attr('id', interesse.val()).hide();
				var tdExcluir = $('<td></td>');
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
						$this.sendRequest('../../MembroAreaInteresseServlet?opcao=E',
								{pkAreaInteresse: interesse.val()},
								function() {
									tr.clear();
								}
							);
						}
					});
				});

				$this.sendRequest('../../MembroAreaInteresseServlet?opcao=I',
					{pkAreaInteresse: interesse.val()},
					function() {
						$this.find('table#tabelaConteudo tbody').append(tr
							.append($('<td></td>').html(interesse.selectedTexts().toString()))
							.append(tdExcluir).fadeIn(500)
						);
					}
				);
			});

			adicionarAreaInteresse = function(id, nome) {
				var tr = $('<tr></tr>').attr('id', id);
				var tdExcluir = $('<td></td>');
				criarBotaoExcluir(tdExcluir).click(function() {
					$this.alert('Tem certeza que deseja excluir?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
						$this.sendRequest('../../MembroAreaInteresseServlet?opcao=E',
								{pkAreaInteresse: id},
								function() {
									tr.clear();
								}
							);
						}
					});
				});
				
				$this.find('table#tabelaConteudo tbody').append(tr
					.append($('<td></td>').html(nome))
					.append(tdExcluir)
				);
			}

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<AreaInteresse> areas = (Collection<AreaInteresse>)request.getAttribute("areas");
			for(AreaInteresse area:areas)
				out.print("interesse.addOption('"+area.getPkAreaInteresse()+"'.clearNull(), '"+area.getNome()+"'.clearNull());");
			
			@SuppressWarnings ("unchecked")
			Collection<MembroAreaInteresse> membroInteresses = (Collection<MembroAreaInteresse>)request.getAttribute("membroInteresses");
			for(MembroAreaInteresse interesse:membroInteresses)
				out.print("adicionarAreaInteresse('"+interesse.getAreaInteresse().getPkAreaInteresse()+"'.clearNull(), '"+interesse.getAreaInteresse().getNome()+"'.clearNull());");
			%>

			interesse.selectOptions("0");
		};
	});

</script>

<div id="title">Alterar &Aacute;reas de Interesse</div>
<table>
	<tbody>
		<tr>
			<td>&Aacute;reas</td>
		</tr>
		<tr>
			<td><select name="interesse" id="interesse"></select></td>
			<td><a href="#" id="incluir" title="Incluir"><img
				src="../../images/icons/add.gif"></a></td>
		</tr>
	</tbody>
</table>
<br>
<table id="tabelaConteudo">
	<caption style="width: 100%; text-align: left;">&Aacute;reas
	do Membro</caption>
	<tbody></tbody>
</table>