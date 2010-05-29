<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Uf"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Bairro"%>
<%@page import="modelo.Instituicao"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Administrador"%>
<%@page import="modelo.Membro"%>
<%@page import="modelo.MicroBlog"%>
<%@page import="modelo.MembroIdioma"%>
<%@page import="modelo.MembroAreaInteresse"%>
<%@page import="modelo.CursoExtensao"%>
<%@page import="util.Ferramenta"%>
<%@page import="modelo.FormacaoAcademica"%>
<% Usuario usuario = (Usuario)request.getAttribute("usuario");%>
<% Membro membro = (Membro)request.getAttribute("membro");%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this, tab) {
			var object = $this.getObject();
			
			<%
				for(MicroBlog microBlog:usuario.getMicroBlogs())
				{
					if(microBlog.selecionado())
					{
						out.print("$this.find('#meu_microBlog').html('"+microBlog.getDescricao()+"');");
						break;
					}
				}
			%>

			var imagem = "<%=usuario.getImagem()%>";
			if(imagem.length >= 8)
				$this.find('img.membroFoto').attr('src', "../../arquivo/fotomembro/"+imagem);
			
			$this.find('#no_usuario').html("<%= usuario.getNome() %>".clearNull());
			$this.find('#no_apelido').html("<%= usuario.getApelido() %>".clearNull());
			$this.find('#dt_nasc').html("<%= Ferramenta.formatarData(usuario.getDataNasc(), true) %>".clearNull());
			$this.find('#tp_logradouro').html("<%= usuario.getTipoLogradouro() %>".clearNull());
			$this.find('#no_logradouro').html("<%= usuario.getLogradouro() %>".clearNull());
			$this.find('#nu_logradouro').html("<%= usuario.getNumero() %>".clearNull());
			$this.find('#ds_complemento').html("<%= usuario.getComplemento() %>".clearNull());
			$this.find('#nu_cep').html("<%= usuario.getCep() %>".clearNull());
			$this.find('#formacao').html("<%= membro.getFormacaoAcademica().getNome() %>".clearNull());

			var tabelaIdioma = $this.find('#tabela_idioma');
			var tabelaInteresse = $this.find('#tabelaCursoExtensao');
			var tabelaCurso = $this.find('#tabelaAreaTrabalho');
			
			<%
				@SuppressWarnings ("unchecked")
				Collection<MembroIdioma> idiomas = (Collection<MembroIdioma>)request.getAttribute("idiomas");
				for(MembroIdioma idioma:idiomas)
					out.print("tabelaIdioma.append('<tr><td>"+idioma.getIdioma().getNome()+"</td></tr>');");
				
				@SuppressWarnings ("unchecked")
				Collection<MembroAreaInteresse> interesses = (Collection<MembroAreaInteresse>)request.getAttribute("interesses");
				for(MembroAreaInteresse interesse:interesses)
					out.print("tabelaInteresse.append('<tr><td>"+interesse.getAreaInteresse().getNome()+"</td></tr>');");
				
				@SuppressWarnings ("unchecked")
				Collection<CursoExtensao> cursos = (Collection<CursoExtensao>)request.getAttribute("cursos");
				for(CursoExtensao curso:cursos)
					out.print("tabelaCurso.append('<tr><td>"+curso.getNome()+"</td></tr>');");
			%>
		};
	});
</script>

<div id="title">Detalhe do Membro</div>
<table>
	<tbody>
		<tr>
			<td rowspan="13" valign="top" style="width: 25%;">
			<fieldset class="blue" style="height: 300px;"><a href="#"><img
				src="../../images/naoDefinido.jpg" class="membroFoto" /></a>
			<fieldset class="microBlogDetalhe"><label
				id="meu_microBlog" style="font-size: 10px"></label></fieldset>
			<!--<p><label>Nota:</label> <input type="text" name="nota" id="nota"
				size="1" maxlength="3"
				style="display: inline; -moz-border-radius: 0px;">
			<button type="button" name="avaliar" id="avaliar"
				class="ui-state-default ui-corner-all" style="text-align: right;">Avaliar</button>
			</p>
			Recomenda&ccedil;&atilde;o: <select name="recomendacao"
				id="recomendacao">
				<option id="0"></option>
				<option id="1">Rodrigo</option>
			</select>
			<button type="button" name="recomendar" id="recomendar"
				class="ui-state-default ui-corner-all">Recomendar</button>
			<p style="text-align: center; margin: 20px 0px;">
			<button type="button" name="convidar" id="convidar"
				class="ui-state-default ui-corner-all">Convidar</button>
			</p>-->
			</fieldset>
			</td>
			<td style="width: 40%;">Nome:</td>
			<td><label id="no_usuario"></label></td>
		</tr>
		<tr>
			<td>Apelido:</td>
			<td><label id="no_apelido"></label></td>
		</tr>
		<tr>
			<td>Data de Nascimento:</td>
			<td><label id="dt_nasc"></label></td>
		</tr>
		<tr>
			<td>Tipo do Logradouro:</td>
			<td><label id="tp_logradouro"></label></td>
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
			<td>Formação:</td>
			<td><label id="formacao"></label></td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabela_idioma" class="tabelaResult">
				<caption>Idiomas</caption>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabelaCursoExtensao" class="tabelaResult">
				<caption>Curso de Extens&atilde;o</caption>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table id="tabelaAreaTrabalho" class="tabelaResult">
				<caption>&Aacute;rea de Trabalho</caption>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>