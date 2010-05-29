<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Ambiente"%>
<%@page import="modelo.AmbienteTag"%>
<%@page import="modelo.Meta"%>
<%@page import="modelo.Usuario"%>
<%@page import="util.Ferramenta"%>

<%@page import="java.io.File"%>
<%@page import="modelo.Tag"%>
<%@page import="modelo.MetaTag"%>
<%@page import="modelo.Etapa"%>
<%@page import="modelo.Tarefa"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			object['tags'] = new Array();
			object['metas'] = new Array();
			
			adicionarTag = function(id, nome, desc) {
				var tr = $('<tr></tr>');
				var nomeLink = $('<a href="#"></a>').html(nome).click(function(e) {
					e.preventDefault();
					$this.newPage('../../MembroTagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaDetalhe"});
				});
				
				$this.find('table#tabela_tags tbody').append(tr
					.append($('<td></td>').html(nomeLink))
				);
			};
			
			adicionarMeta = function(id, desc, duracao) {
				var tr = $('<tr></tr>');
				var meta = new Meta();

				meta.setId(id);
				meta.setDescricao(desc);
				meta.setDuracao(duracao);
				meta.setTrReferencia(tr);
				
				var nomeLink = $('<a href="#"></a>').html(desc).click(function(e) {
					e.preventDefault();
					object['meta'] = meta;
					$this.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaDetalhe"});
				});
				
				$this.find('table#tabela_metas tbody').append(tr
					.append($('<td></td>').html(nomeLink))
				);

				object['metas'].push(meta);

				return meta;
			};
			
			var adicionarMetaTag = function(id, nome, desc, meta) {
				var tag = {id: id, nome: nome, desc: desc, virtual: false, deletado: false, alterado: false, trReferencia: null};
				meta.adicionarTag(tag);

				return tag;
			};

			var adicionarEtapa = function(id, nome, desc, data, meta) {
				var etapa = {id: id, nome: nome, desc: desc, data: data, virtual: false, deletado: false, alterado: false, trReferencia: null, tarefas: new Array()};

				meta.adicionarEtapa(etapa);

				return etapa;
			};

			var adicionarTarefa = function(id, nome, desc, data, etapa) {
				var tarefa = {
					id: id, nome: nome, desc: desc, data: data,
					virtual: false, deletado: false, alterado: false, trReferencia: null
				};

				etapa.tarefas.push(tarefa);
			};

			_meta = null;
			_etapa = null;
			<%
			Ambiente ambiente = (Ambiente)request.getAttribute("ambiente");			
			
			@SuppressWarnings ("unchecked")
			Collection<AmbienteTag> tags = (Collection<AmbienteTag>)request.getAttribute("tags");
			for(AmbienteTag tag:tags)
				out.print("adicionarTag("+tag.getTag().getPkTag()+", '"+tag.getTag().getNome()+"'.clearNull(), '"+Ferramenta.clearScape(tag.getTag().getDescricao())+"'.clearNull());");
			
			@SuppressWarnings ("unchecked")
			Collection<Meta> metas = (Collection<Meta>)request.getAttribute("metas");
			for(Meta meta:metas)
			{
				out.print("_meta = adicionarMeta("+meta.getPkMeta()+", '"+Ferramenta.clearScape(meta.getDescricao())+"'.clearNull(), '"+meta.getDuracao()+"'.clearNull());");
			
				try {
					Collection<MetaTag> metaTags = MetaTag.consultarPorMeta(meta);
					for(MetaTag tag:metaTags)
						out.print("adicionarMetaTag("+tag.getTag().getPkTag()+", '"+tag.getTag().getNome()+"'.clearNull(), '"+Ferramenta.clearScape(tag.getTag().getDescricao())+"'.clearNull(), _meta);");
				
					Collection<Etapa> etapas = Etapa.consultarPorMeta(meta);
					for(Etapa etapa:etapas)
					{
						out.print("_etapa = adicionarEtapa("+etapa.getPkEtapa()+", '"+etapa.getNome()+"'.clearNull(), '"+Ferramenta.clearScape(etapa.getDescricao())+"'.clearNull(), '"+Ferramenta.formatarData(etapa.getData(), true)+"'.clearNull(), _meta);");
						
						Collection<Tarefa> tarefas = Tarefa.consultarPorVisao(etapa);
						for(Tarefa tarefa:tarefas)
							out.print("adicionarTarefa("+tarefa.getPkTarefa()+", '"+tarefa.getNome()+"'.clearNull(), '"+Ferramenta.clearScape(tarefa.getDescricao())+"'.clearNull(), '"+Ferramenta.formatarData(tarefa.getData(), true)+"'.clearNull(), _etapa);");
					}
				} catch (Exception e) {
				}
			}

			if(ambiente.getImagem() == null || ambiente.getImagem().length() > 5)
			{
				out.print("$this.find('img.membroFoto').attr('src', '../../arquivo/fotoambiente/"+ambiente.getImagem()+"?'+new Date().getTime());");
			}
		%>

			$('label#no_ambiente').html('<%= ambiente.getNome()%>'.clearNull());
			
		};
	});
</script>
<div id="title">Perfil do Ambiente</div>
<form enctype="multipart/form-data" action="" method="post">
<table>
	<tbody>
		<tr>
			<td rowspan="5" valign="top" style="width: 25%;">
			<fieldset class="blue"><a href="#"><img
				src="../../images/naoDefinido.jpg" class="membroFoto"></a>
			</fieldset>
			</td>
			<td>Nome:</td>
			<td><label id="no_ambiente"></label></td>
		</tr>
		<tr>
			<td valign="top">Descri&ccedil;&atilde;o do Ambiente:</td>
			<td><textarea name="ds_ambiente" id="ds_ambiente" disabled="disabled"><%= ambiente.getDescricao()%></textarea></td>
		</tr>
		<tr>
			<td colspan="3">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags</caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Nome</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table id="tabela_metas" class="tabelaResult">
				<caption>Metas</caption>
				<thead>
					<tr>
						<th><img src="../../images/icons/b_search.png" />Descri&ccedil;&atilde;o</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</form>