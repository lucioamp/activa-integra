<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			object['bairros'] = new Array();
			
			var botaoVoltar = $this.find('button#voltar').click(function() {
				$this.remove();
				$mainPage.getPage(1).empty().load('../../MunicipioServlet?opcao=C', function() {
					$mainPage.find('div#msgCarregando').hide();
					delete object['bairros'];
					modulo_ready($mainPage);
					$oldPage.fadeIn(600);
				});
			});
			
			$this.find('button#incluir').click(function() {
				var nome = $this.find('#nome');
				var uf = $this.find('#uf');
				
				if(nome.val().length == 0)
				{
					$this.alert('Por favor, digite um nome.', null, {clickOK: function() { nome.focus(); }});
					return false;
				}

				if(uf.val() == 0)
				{
					$this.alert('Por favor, selecione uma uf.', null, {clickOK: function() { uf.focus(); }});
					return false;
				}

				var bairros = "";
				for (var i in object['bairros'])
					bairros += "nome: " + object['bairros'][i].nome + "&";

				$this.sendRequest('../../MunicipioServlet?opcao=I',
					{municipio: nome.val(), uf: uf.val(), bairros: bairros},
					function() { botaoVoltar.click(); }
				);
			});

			adicionarUnidade = function(id, nome)
			{
				$this.find('select#uf').append('<option value="'+id+'">'+nome+'</option>');
			}

		<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
		@SuppressWarnings ("unchecked")
			Collection<Uf> colUf = (Collection<Uf>)request.getAttribute("colUf");
			for(Uf u:colUf)
				out.print("adicionarUnidade("+u.getPkEstado()+", '"+u.getEstado()+"');");	
		%>
			
			$this.find('#incluirBairro').click(function() {
				$mainPage.newPage('servicos/municipio/bairro/incluir.jsp', null);
			});
		}
	});
</script>

<div id="title">Incluir Munic&iacute;pio</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><input type="text" name="nome" id="nome" /></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><select name="uf" id="uf">
				<option value="0"></option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_bairros" class="tabelaResult">
				<caption>Bairros <a href="#" id="incluirBairro"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
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
				name="incluir" id="incluir">Incluir</button>
			<button type="button" class="ui-state-default ui-corner-all"
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>