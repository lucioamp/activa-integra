<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<% Instituicao instituicao = (Instituicao)request.getAttribute("instituicao"); %>
<%@page import="modelo.Instituicao"%>
<%@page import="modelo.ContatoInstituicao"%>
<%@page import="modelo.Uf"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#nome').html("<%= instituicao.getNome() %>".clearNull());
			$this.find('select#tp_logradouro').selectOptions("<%= instituicao.getTipoLogradouro() %>".clearNull());
			$this.find('label#no_logradouro').html("<%= instituicao.getLogradouro()%>".clearNull());
			$this.find('label#nu_logradouro').html("<%= instituicao.getNumero()%>".clearNull());
			$this.find('label#nu_cep').html("<%= instituicao.getCep()%>".clearNull());
			$this.find('label#uf').html("<%= instituicao.getBairro().getMunicipio().getUf().getEstado() %>".clearNull());
			$this.find('label#municipio').html("<%= instituicao.getBairro().getMunicipio().getMunicipio() %>".clearNull());
			$this.find('label#bairro').html("<%= instituicao.getBairro().getBairro() %>".clearNull());


			var adicionarContato = function(tipo, desc)
			{
				$this.find('table#tabela_contatos tbody').append('<tr><td>'+tipo+'</td><td>'+desc+'</td></tr>');
			}
			
			<%
			@SuppressWarnings ("unchecked")
			Collection<ContatoInstituicao> contatos = (Collection<ContatoInstituicao>)request.getAttribute("contatos");
			for(ContatoInstituicao contato:contatos)
				out.print("adicionarContato('"+contato.getTipoContato().getTipoContato()+"'.clearNull(), '"+contato.getContato()+"'.clearNull());");
			%>
		}
	});
</script>

<div id="title">Detalhe de Institui&ccedil;&atilde;o</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="nome"></label></td>
		</tr>
		<tr>
			<td>Tipo de Logradouro:</td>
			<td><select name="tp_logradouro" id="tp_logradouro"
				disabled="disabled">
				<option value=""></option>
				<option value="Av">Avenida</option>
				<option value="Rua">Rua</option>
				<option value="Est">Estrada</option>
				<option value="Prç">Praça</option>
			</select></td>
		</tr>
		<tr>
			<td>Logradouro:</td>
			<td><label id="no_logradouro"></label></td>
		</tr>
		<tr>
			<td>N&uacute;mero:</td>
			<td><label id="nu_logradouro"></label></td>
		</tr>
		<tr>
			<td>Complemento:</td>
			<td><textarea id="ds_complemento" name="ds_complemento"
				disabled="disabled"><%= instituicao.getComplemento()%></textarea></td>
		</tr>
		<tr>
			<td>Cep:</td>
			<td><label id="nu_cep"></label></td>
		</tr>
		<tr>
			<td>UF:</td>
			<td><label id="uf"></label></td>
		</tr>
		<tr>
			<td>Munic&iacute;pio:</td>
			<td><label id="municipio"></label></td>
		</tr>
		<tr>
			<td>Bairro:</td>
			<td><label id="bairro"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_contatos" class="tabelaResult">
				<caption>Contatos</caption>
				<thead>
					<tr>
						<th>Tipo</th>
						<th>Descrição</th>
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
				name="voltar" id="voltar">Voltar</button>
			</td>
		</tr>
	</tfoot>
</table>