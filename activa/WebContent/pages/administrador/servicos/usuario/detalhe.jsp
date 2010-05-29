<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<% Usuario usuario = (Usuario)request.getAttribute("usuario"); %>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.ContatoUsuario"%>
<%@page import="modelo.Uf"%>

<%@page import="modelo.Administrador"%>
<%@page import="util.Ferramenta"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this, $oldPage, $mainPage) {
			var object = $mainPage.getObject();
			var botaoVoltar = $this.find('button#voltar').click(function() { $this.remove(); $oldPage.fadeIn(600); });

			$this.find('label#no_usuario').html("<%= usuario.getNome() %>".clearNull());
			$this.find('label#no_apelido').html("<%= usuario.getApelido() %>".clearNull());
			$this.find('label#nu_cpf').html("<%= Ferramenta.formatarCpf(usuario.getCpf()) %>".clearNull());
			$this.find('label#dt_nasc').html("<%= usuario.getDataNasc() %>".clearNull());
			$this.find('label#no_email').html("<%= usuario.getEmail() %>".clearNull());
			$this.find('label#ds_perguntaChave').html("<%= usuario.getPerguntaChave() %>".clearNull());
			$this.find('label#ds_respostaChave').html("<%= usuario.getRespostaChave() %>".clearNull());
			$this.find('label#no_logradouro').html("<%= usuario.getLogradouro() %>".clearNull());
			$this.find('label#nu_logradouro').html("<%= usuario.getNumero() %>".clearNull());
			$this.find('label#ds_complemento').html("<%= usuario.getComplemento() %>".clearNull());
			$this.find('label#nu_cep').html("<%= usuario.getCep() %>".clearNull());
			$this.find('select#st_usuario').selectOptions("<%= usuario.getStatus() %>".clearNull());
			$this.find('label#instituicao').html("<%= usuario.getInstituicao().getNome() %>".clearNull());
			$this.find('input#administrador').attr('checked', '<%= (Administrador.isAdministrador(usuario) == true ? "checked" : "") %>');
			$this.find('select#tp_logradouro').val("<%= usuario.getTipoLogradouro() %>".clearNull());
			$this.find('label#uf').html("<%= usuario.getBairro().getMunicipio().getUf().getEstado() %>".clearNull());
			$this.find('label#municipio').html("<%= usuario.getBairro().getMunicipio().getMunicipio() %>".clearNull());
			$this.find('label#bairro').html("<%= usuario.getBairro().getBairro() %>".clearNull());

			var adicionarContato = function(tipo, desc)
			{
				$this.find('table#tabela_contatos tbody').append('<tr><td>'+tipo+'</td><td>'+desc+'</td></tr>');
			}

			<%
			@SuppressWarnings ("unchecked")
			Collection<ContatoUsuario> contatos = (Collection<ContatoUsuario>)request.getAttribute("contatos");
			for(ContatoUsuario contato:contatos)
				out.print("adicionarContato('"+contato.getTipoContato().getTipoContato()+"'.clearNull(), '"+contato.getContato()+"'.clearNull());");
			%>
		};
	});
</script>

<div id="title">Consultar Usu&aacute;rio</div>
<table style="width: 100%;">
	<tbody>
		<tr>
			<td>Nome:</td>
			<td><label id="no_usuario"></label></td>
		</tr>
		<tr>
			<td>Apelido:</td>
			<td><label id="no_apelido"></label></td>
		</tr>
		<tr>
			<td>CPF:</td>
			<td><label id="nu_cpf"></label></td>
		</tr>
		<tr>
			<td>Data de Nascimento:</td>
			<td><label id="dt_nasc"></label></td>
		</tr>
		<tr>
			<td>Pergunta Chave:</td>
			<td><label id="ds_perguntaChave"></label></td>
		</tr>
		<tr>
			<td>Resposta Chave:</td>
			<td><label id="ds_respostaChave"></label></td>
		</tr>
		<tr>
			<td>Instituição:</td>
			<td><label id=instituicao></label></td>
		</tr>
		<tr>
			<td>Tipo de Logradouro:</td>
			<td><select name="tp_logradouro" id="tp_logradouro" disabled="disabled">
				<option value=""></option>
				<option value="Av">Avenida</option>
				<option value="Rua">Rua</option>
				<option value="Est">Estrada</option>
				<option value="Prç">Praça</option>
			</select></td>
		</tr>
		<tr>
			<td>Nome do Logradouro:</td>
			<td><label id="no_logradouro"></label></td>
		</tr>
		<tr>
			<td>N&uacute;mero do Logradouro:</td>
			<td><label id="nu_logradouro"></label></td>
		</tr>
		<tr>
			<td>Descri&ccedil;&atilde;o do Complemento:</td>
			<td><label id="ds_complemento"></label></td>
		</tr>
		<tr>
			<td>N&uacute;mero do Cep:</td>
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
			<td>Status:</td>
			<td><select name="st_usuario" id="st_usuario" disabled="disabled">
				<option value="A">Ativo</option>
				<option value="D">Desativado</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="checkbox" value="1"
				name="administrador" id="administrador" disabled="disabled"
				style="display: inline;"> Administrador</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_contatos" class="tabelaResult">
				<caption>contatos<a href="#" id="incluirContato"
					title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
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