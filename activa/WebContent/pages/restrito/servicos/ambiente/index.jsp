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
<%@page import="modelo.Tarefa"%><script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			
			object['tags'] = new Array();
			object['metas'] = new Array();
			
			var nome = $('input#no_ambiente');
			var desc = $('textarea#ds_ambiente');
			
			$this.find('#incluirTag').click(function() {
				$this.newPage('servicos/tags/incluir.jsp');
			});
			
			$this.find('#incluirMeta').click(function() {
				$this.newPage('servicos/ambiente/meta/incluir.jsp');
			});
			
			$this.find('button#alterar').click(function() {
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

				var tags = "";
				var tag = null;
				for (var i in object['tags'])
				{
					tag = object['tags'][i];

					tags += 'id: '+tag.id+'# nome:'+tag.nome+' # desc:'+tag.desc+'# ';
					if(tag.virtual == true)
						tags += 'virtual: true&';
					else if(tag.deletado == true)
						tags += 'deletar: true&';
					else if(tag.alterado == true)
						tags += 'alterar: true&';
				}

				var metas = "";
				var meta = null;

				var metaTags = "";
				var metaTag = null;

				var etapas = "";
				var etapa = null;

				var tarefas = "";
				var tarefa = null;
				for (var i in object['metas'])
				{
					meta = object['metas'][i];

					metas += 'id: '+meta.getId()+'# desc:'+meta.getDescricao()+'# duracao: '+meta.getDuracao()+'# ';
					if(meta.getVirtual() == true)
						metas += 'virtual: true&';
					else if(meta.getDeletado() == true)
						metas += 'deletar: true&';
					else if(meta.getAlterado() == true)
						metas += 'alterar: true&';

					for (var iit in meta.getTags())
					{
						metaTag = meta.getTags()[iit];
						metaTags += 'metaid: '+meta.getId()+'# id: '+metaTag.id+'# nome:'+metaTag.nome+'# desc: '+metaTag.desc+'# ';
						if(metaTag.virtual == true)
							metaTags += 'virtual: true&';
						else if(metaTag.deletado == true)
							metaTags += 'deletar: true&';
						else if(metaTag.alterado == true)
							metaTags += 'alterar: true&';							
					}

					for (var ii in meta.getEtapas())
					{
						etapa = meta.getEtapas()[ii];
						etapas += 'metaid: '+meta.getId()+'# id: '+etapa.id+'# nome:'+etapa.nome+'# desc: '+etapa.desc+'# data: '+etapa.data+'# ';
						if(etapa.virtual == true)
							etapas += 'virtual: true&';
						else if(etapa.deletado == true)
							etapas += 'deletar: true&';
						else if(etapa.alterado == true)
							etapas += 'alterar: true&';

						for (var it in etapa.tarefas)
						{
							tarefa = etapa.tarefas[it];
							tarefas += 'etapaid: '+etapa.id+'# id: '+tarefa.id+'# nome:'+tarefa.nome+'# desc: '+tarefa.desc+'# data: '+tarefa.data+'# ';
							if(tarefa.virtual == true)
								tarefas += 'virtual: true&';
							else if(tarefa.deletado == true)
								tarefas += 'deletar: true&';
							else if(tarefa.alterado == true)
								tarefas += 'alterar: true&';							
						}
					}
				}
				
				$this.sendRequest('../../MembroAmbienteServlet?opcao=A', {
					nome: nome.val(), desc: desc.val(), tags: tags, metas: metas, metaTags: metaTags,
					etapas: etapas, tarefas: tarefas
				}, function(data) {
					var result = trim(data);

					if(result == "true")
					{
						$this.alert('Ambiente alterado com sucesso.', null, {clickOK: function() {
							document.title = 'Desktop ['+nome.val()+']';
							object['reloadNoQuestion'] = true;
							object['buttonReload'].click();							
						}});						
					}else
					{
						$this.alert('Erro ao tentar alterar o ambiente.');
					}
				});
				
				//$('body').css('background-image', 'url(../../images/desktop_background/'+$this.find('input#imageFile').val()+')')
				//	/*.css('background-repeat', 'no-repeat')*/.css('background-position', 'top right');
			});

			var adicionarTag = function(id, nome, desc) {
				var tr = $('<tr></tr>');
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(nome).click(function(e) {
					e.preventDefault();
					$this.newPage('../../MembroTagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaDetalhe"});
				}));

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../MembroTagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaDetalhe"});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../MembroTagServlet?opcao=D', {id: id, nome: nome, desc: desc, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										for (var i in object['tags'])
										{
											if(object['tags'][i].id == id)
												object['tags'][i].deletado = true;
										}
										tr.clear();
									}
								});
							}
						}
					}
				});
				
				object['tags'].push({id: id, nome: nome, desc: desc, virtual: false, deletado: false, alterado: false, trReferencia: tr});

				$this.find('table#tabela_tags tbody').append(tr.append(tdNome));
			};
			
			var adicionarMeta = function(id, desc, duracao) {				
				var tr = $('<tr></tr>');

				var meta = new Meta();

				meta.setId(id);
				meta.setDescricao(desc);
				meta.setDuracao(duracao);
				meta.setTrReferencia(tr);
				
				var tdNome = $('<td></td>').html($('<a href="#"></a>').html(desc).click(function(e) {
					e.preventDefault();
					object['meta'] = meta;
					$this.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaDetalhe"});
				}));

				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								object['meta'] = meta;
								$this.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaDetalhe"});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								object['meta'] = meta;
								$this.newPage('../../MembroAmbienteServlet?opcao=M', {id: id, tipo: "paginaAlterar"});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										meta.setDeletado(true);
										tr.clear();
									}
								});
							}
						}
					}
				});
				
				object['metas'].push(meta);

				$this.find('table#tabela_metas tbody').append(tr.append(tdNome));

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
			%>

			nome.val('<%= ambiente.getNome()%>'.clearNull());
			desc.val('<%= Ferramenta.clearScape(ambiente.getDescricao())%>'.clearNull());

			var buttonEnviarArquivo = $this.find('button#enviar_arquivo');
			var inputArquivoImagem = $this.find('input#arquivo_imagem');
			var interval = null;
			<%			
			if(ambiente.getImagem() == null || ambiente.getImagem().length() > 5)
			{
				out.print("$this.find('img.membroFoto').attr('src', '../../arquivo/fotoambiente/"+ambiente.getImagem()+"?'+new Date().getTime());");
			}
			%>

			var buttonRemoverImagemFundo = $this.find('button#remover_imagem').click(function(e) {
				$this.alert('Tem certeza que deseja remover a imagem de fundo?', null, {dialog: DIALOG_CONFIRM, clickOK: function(e) {
					$this.sendRequest('../../MembroAmbienteServlet?opcao=1', function(data) {
						var result = data.trim();

						if(result == "true")
						{
							$this.alert('Imagem de fundo removida com sucesso.', null, {clickOK: function() {
								$this.find('img.membroFoto').attr('src', '../../images/naoDefinido.jpg?'+new Date().getTime());
								$('body').css('background-image', 'url(\"?'+new Date().getTime()+'\")');					
							}});						
						}else
						{
							$this.alert('Erro ao tentar alterar remover a imagem de fundo.');
						}
					});
				}});
			});

			var telaLoading = null;
			var enviaArquivo = new AjaxUpload('#upload_button', {
				action: '../../MembroAmbienteServlet?opcao=0',
				name: 'file1',
				autoSubmit: true,
				onChange : function(file){
					inputArquivoImagem.val(file);
				},
				onSubmit: function(file, ext) {
					if (ext && /^(jpg)$/.test(ext)){
						telaLoading = $this.sendRequest().telaLoading;
						this.disable();
						/*var iniTexto = "Enviando Imagem";
						buttonEnviarArquivo.html(iniTexto);
						interval = window.setInterval(function(){
							var text = buttonEnviarArquivo.html();
							if (text.length < 20){
								buttonEnviarArquivo.html(text + '.');					
							} else {
								buttonEnviarArquivo.html(iniTexto);
							}
						}, 200);*/
					} else {
						inputArquivoImagem.val("");
						$this.alert('Só pode enviar imagens no formato (*.jpg).');
						return false;				
					}
				},
				onComplete: function(file, response) {
					telaLoading.clear();
					this.enable();
					/*window.clearInterval(interval);
					inputArquivoImagem.val("");				
					buttonEnviarArquivo.html("Enviar Arquivo");*/
					<%
					String nomeArquivo = Ferramenta.md5(Long.toString(ambiente.getPkAmbiente()))+".jpg";
					out.print("$this.find('img.membroFoto').attr('src', '../../arquivo/fotoambiente/"+nomeArquivo+"?'+new Date().getTime());");
					out.print("$('body').css('background-image', 'url(\"../../arquivo/fotoambiente/"+nomeArquivo+"?'+new Date().getTime()+'\")');");
					%>
				}
			});
		};
	});
</script>

<div id="title">Perfil do Ambiente</div>
<form enctype="multipart/form-data" action="" method="post">
<table>
	<tbody>
		<tr>
			<td rowspan="6" valign="top" style="width: 25%;">
			<fieldset class="blue"><a href="#"><img
				src="../../images/naoDefinido.jpg" class="membroFoto"></a>
			<p style="text-align: center;">
			<button type="button" name="alterar" id="alterar"
				class="ui-state-default ui-corner-all">Alterar</button>
			</p>
			</fieldset>
			</td>
			<td>Nome:</td>
			<td><input type="text" name="no_ambiente" id="no_ambiente" /></td>
		</tr>
		<tr>
			<td valign="top">Descri&ccedil;&atilde;o do Ambiente:</td>
			<td><textarea name="ds_ambiente" id="ds_ambiente"></textarea></td>
		</tr>
		<tr>
			<td>Imagem de Fundo</td>
			<td>
				<div id="upload_button">
					<input type="text" name="arquivo_imagem" id="arquivo_imagem" style="display: inline;"/>
					<button type="button" class="ui-state-default ui-corner-all" name="enviar_arquivo" id="enviar_arquivo">Enviar Imagem</button>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: right;">
					<button type="button" class="ui-state-default ui-corner-all" name="remover_imagem" id="remover_imagem">Remover Imagem de Fundo</button>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table id="tabela_tags" class="tabelaResult">
				<caption>Tags <a href="#" name="incluirTag"
					id="incluirTag" title="Incluir"><img src="../../images/icons/add.gif"></a><br />
				<input type="text" id="pesquisarTag" name="pesquisarTag" /><a
					href="#"><img src="../../images/icons/b_search.png" /></a></caption>
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
				<caption>Metas <a href="#" name="incluirMeta"
					id="incluirMeta" title="Incluir Metas"><img
					src="../../images/icons/add.gif"></a></caption>
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