<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Meta"%>
<%@page import="modelo.CategoriaForum"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var tab = $this.parents('#tabs');
			var object = $this.getObject();

			var meta = $this.find('select#meta');
			var etapa = $this.find('select#etapa');
			$this.find('button#incluir').click(function() {
				var nome = $this.find('input#no_catForum');
				var desc = $this.find('textarea#ds_catForum');

				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(desc.val().length == 0)
				{
					$this.alert('Por favor, digite uma descrição.', null, {clickOK: function() { desc.focus(); }});
					return false;
				}

				if(meta.val() == 0)
				{
					$this.alert('Por favor, selecione uma meta.', null, {clickOK: function() { meta.focus(); }});
					return false;
				}

				if(etapa.val() == 0)
				{
					$this.alert('Por favor, selecione uma etapa.', null, {clickOK: function() { etapa.focus(); }});
					return false;
				}

				$this.sendRequest('../../MembroForumServlet?opcao=I',
					{nome: nome.val(), desc: desc.val(), pkEtapa: etapa.val(), tipo: "incluirCategoria"},
					function(data) {
						var result = trim(data);

						if(result == "true")
						{
							$this.alert('Categoria incluida com sucesso.', null, {clickOK: function() {
								nome.val('');
								desc.val('');
								meta.selectOptions('0');
								etapa.selectOptions('0');
							}});
						}else
							$this.alert('Erro ao tentar incluir a categoria.');
					}
				);
			});

			trEtapa = $this.find('tr#etapa');
			meta.change(function() {
				var pkMeta = $(this).val();
				if(pkMeta == 0)
				{
					trEtapa.hide();
					etapa.empty().addOption("0", "");
				}else
				{
					trEtapa.show();
					$this.sendRequest('../../MembroForumServlet?opcao=C&tipo=consultarEtapa', {pkMeta: pkMeta},	function(data) {
						var result = trim(data);
						etapa.empty().addOption("0", "").append(result);
					});
				}
			});

			<%
			@SuppressWarnings ("unchecked")
			Collection<Meta> metas = (Collection<Meta>)request.getAttribute("metas");
			for(Meta meta:metas)
				out.print("meta.addOption('"+meta.getPkMeta()+"', '"+meta.getDescricao()+"');");
			%>

			meta.selectOptions("0");
		};
	});
</script>

<div id="title">Incluir Categoria</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td style="width: 30%;">Nome:</td>
			<td><input type="text" name="no_catForum" id="no_catForum" /></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o:</td>
			<td><textarea id="ds_catForum" name="ds_catForum" cols="50" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>Meta:</td>
			<td><select name="meta" id="meta">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr id="etapa" style="display: none;">
			<td>Etapa:</td>
			<td><select name="etapa" id="etapa">
				<option value="0"></option>
			</select></td>
		</tr>
	</tbody>
	<tfoot style="text-align: center;">
		<tr>
			<td colspan="3">
			<button type="button" class="ui-state-default ui-corner-all"
				name="incluir" id="incluir">Incluir</button>
			</td>
		</tr>
	</tfoot>
</table>