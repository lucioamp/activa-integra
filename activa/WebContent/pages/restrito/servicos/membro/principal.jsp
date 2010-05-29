<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Ambiente"%>
<%@page import="modelo.Membro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			
			var object = $this.getObject();
			object['cache'] = false;

			var divMembros = $this.find('div#membros');
			var adicionarMembro = function(id, nome, imagem)
			{
				var tbody = $this.find('table#tabelaMembros tbody');

				var src = "../../images/naoDefinido.jpg";
				if(imagem.length >= 8)
					src = "../../arquivo/fotomembro/"+imagem;

				var link = $('<a href="#"></a>').html('<img src="'+src+'" alt="" />');
				var divImagem = $('<div></div>').html(link);
				var divCentral = $('<div></div>').addClass('membro').html(divImagem)
				.append('<div style="text-align: center;">'+nome+'</div>');
				link.click(function() {
					tab.addTab(id, '../../MembroServlet?opcao=C&tipo=detalhe&pkMembro='+id, 'Detalhe de '+nome);
				});
				
				divMembros.append(divCentral);
			};

			<%
			Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");
			
			Collection<Membro> membros = (Collection<Membro>)ambiente.consultarMembros();
			for(Membro membro:membros)
				out.print("adicionarMembro("+membro.getPkUsuario()+", '"+membro.getNome()+"'.clearNull(), '"+membro.getImagem()+"');");
			%>
		};
	});
</script>
<div id="title">Membros</div>
<!--<table>
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nomeMembro" id="nomeMembro" /></td>
			<td><a href="#" onclick="" title="Consultar"><img
				src="../../images/icons/b_search.png" /></a></td>
		</tr>
	</tbody>
</table>
<br />-->
<fieldset class="blue">
	<div id="membros" style="text-align: center; margin: 0px 40px;"></div>
</fieldset>