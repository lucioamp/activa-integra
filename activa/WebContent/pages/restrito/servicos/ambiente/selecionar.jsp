<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.MembroAmbiente"%>
<%@page import="modelo.Usuario"%>
<%@page import="java.io.File"%>
<%@page import="modelo.Ambiente"%>
<% Ambiente _ambiente = (Ambiente)session.getAttribute("ambiente"); %>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			var ambiente = $this.find('select#ambiente');

			$this.find('button#sair').click(function() {
				menuOptionConteudo.find('div#perfil a[href="#SAIR"]').click();
			});

			var form = $this.find('form div#ambientes');
			var adicionarAmbiente = function(id, nome, imagem)
			{
				var src = "../../images/naoDefinido.jpg";
				if(imagem.length >= 8)
					src = "../../arquivo/fotoambiente/"+imagem;
				
				var link = $('<a href="#"></a>').html('<img src="'+src+'" alt="" />');
				var divImagem = $('<div></div>').html(link);
				var divCentral = $('<div></div>').addClass('ambiente').html(divImagem)
				.append('<div style="text-align: center;">'+nome+'</div>');
				link.click(function() {
					$this.sendRequest('../../LoginServlet?opcao=4', {pkAmbiente: id}, function(data) {
						var result = trim(data);
						if(result == "true")
						{
							location.href = "index.jsp";
							/*$this.fadeOut(500, function() {
								$(this).dialog('close').dialog('destroy').remove();
								desktop.show();
								$('#menuBar').show();
							});*/
						}
					});
					
					$this.fadeOut(500, function() {
						$(this).dialog('close').dialog('destroy').remove();
						document.title = 'Desktop ['+ambiente.selectedTexts()+']';
						desktop.show();
						$('#menuBar').show();
					});
				});
				
				form.append(divCentral);
			};
			
			$this.find('#selecionar').click(function() {
				var ambiente = $this.find('#ambiente');
				
				if(ambiente.val() == 0)
					$this.alert('Por favor, selecione um ambiente.', null, {clickOK: function() { ambiente.focus(); }});
				else
				{
					$this.sendRequest('../../LoginServlet?opcao=4', {pkAmbiente: ambiente.val()}, function(data) {
						var result = trim(data);
						if(result == "true")
						{
							location.href = "index.jsp";
							/*$this.fadeOut(500, function() {
								$(this).dialog('close').dialog('destroy').remove();
								desktop.show();
								$('#menuBar').show();
							});*/
						}
					});
					
					$this.fadeOut(500, function() {
						$(this).dialog('close').dialog('destroy').remove();
						document.title = 'Desktop ['+ambiente.selectedTexts()+']';
						desktop.show();
						$('#menuBar').show();
					});
				}
			});
			
			$this.find('#gerenciar').click(function() {
				$this.dialog('option', 'hide', 'explode').dialog('close').dialog('destroy').remove();
				showTelaNovoAmbiente();
			});

			<%			
			Usuario usuario = (Usuario)session.getAttribute("membro");
			
			Collection<MembroAmbiente> ambientes = MembroAmbiente.consultarPorMembro(usuario);
			for(MembroAmbiente ambiente:ambientes)
				out.print("adicionarAmbiente('"+ambiente.getAmbiente().getPkAmbiente()+"'.clearNull(), '"+ambiente.getAmbiente().getNome()+"'.clearNull(), '"+ambiente.getAmbiente().getImagem()+"');");
			
			//for(MembroAmbiente ambiente:ambientes)
				//out.print("ambiente.addOption('"+ambiente.getAmbiente().getPkAmbiente()+"'.clearNull(), '"+ambiente.getAmbiente().getNome()+"'.clearNull());");
			%>

			ambiente.selectOptions("0");
		};
	});
</script>

<div id="selecionar_ambiente" class="conteudo" style="display: none;">
	<form name="form" id="form" action="" method="post" onsubmit="return false;">
		<div style="display: block;">
			<button type="submit" class="ui-state-default ui-corner-all" name="gerenciar"
				id="gerenciar">Gerenciar</button>
			<button type="submit" class="ui-state-default ui-corner-all"
				name="sair" id="sair">Sair</button>
		</div>
		<div id="ambientes" style="text-align: center; margin: 0px 40px; overflow:auto; width: 510px; max-height: 450;"></div>
	</form>
</div>