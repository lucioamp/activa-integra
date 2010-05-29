<%@page import="java.util.Collection"%>
<%@page import="modelo.Favorito"%>
<%@page import="modelo.PastasFavorito"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();

			var table = $this.find("table");
			var tbody = table.find('tbody');

			var adicionarPasta = function(id, nome, parent_id, append)
			{
				var _tr = tbody.find('tr[id="node-'+id+'"]');
				if(_tr.html() != null)
					return true;

				var option = table.createOptionTreeTable({id: id, nome: nome, parent_id: parent_id, isFile: false, append: append}, function(id, name, parent, isFile) {
					if(isFile == false)
						$this.sendRequest("../../MembroFavoritoServlet?opcao=P", {pkPasta: id, pkParent: parent, tipo: "alterar"});
					else
						$this.sendRequest("../../MembroFavoritoServlet?opcao=F", {pkFavorito: id, pkPasta: parent, tipo: "alterarPasta"});
				});

				var tr = option.getTr();
				var tdDetalhe = option.getTd();
				var span = option.getSpan();

				tr.append($('<td></td>').html('&nbsp;'));

				var removerPasta = function(_id)
				{
					tbody.find('tr.child-of-node-'+_id).each(function() {
						var _parentId = $(this).attr('id').split('-')[1];
						removerPasta(_parentId);
					}).remove();
				};
				
				tdDetalhe.contextMenu({
					buttons: {
						Nova_pasta: {
							onClick: function() {
								$this.sendRequest('../../MembroFavoritoServlet?opcao=P', {nome: "Nova Pasta", parent_id: id, tipo: "incluir"},
									function(data) {
										var result = trim(data);
										if(result == "false")
											$this.alert('Erro ao tentar criar uma nova pasta.');
										else
										{
											adicionarPasta(parseInt(result), "Nova Pasta", id);
											tr.removeClass('initialized');
											table._treeTable();
										}
									}
								);
							}
						},
						Novo_favorito: {
							onClick: function() {
								$this.sendRequest('../../MembroFavoritoServlet?opcao=F', {nome: "Novo Favorito", url: "http://", parent_id: id, tipo: "incluir"},
									function(data) {
										var result = trim(data);
										if(result == "false")
											$this.alert('Erro ao tentar criar uma nova pasta.');
										else
										{
											adicionarFavorito(parseInt(result), "Novo Favorito", "http://", id);
											tr.removeClass('initialized');
											table._treeTable();
										}
									}
								);					
							}
						},
						Renomear: {
							icon: "edit",
							onClick: function() {
								var oldValor = span.html();
								var input = $('<input type="text" size="10"/>').val(span.html()).blur(function() {
									var valor = $(this).val();
									$(this).remove();
									span.html(valor);
									if(oldValor == valor)
										return true;
			
									$this.sendRequest('../../MembroFavoritoServlet?opcao=P', {pkPasta: id, nome: valor, pkParent: (parent_id == null ? 0 : parent_id), tipo: "alterar"},
										function(data) {
											var result = trim(data);
											if(result == "false")
											{
												$this.alert('Erro ao tentar alterar o nome da pasta.');
												span.html(oldValor);
											}
										}
									);
								});
								
								span.html(input);
								setTimeout(function() { input.focus(); }, 500);
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								desktop.alert("Tem certeza que deseja remover a pasta '"+nome+"' e todo o seu conteúdo?", null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.sendRequest('../../MembroFavoritoServlet?opcao=P', {pkPasta: id, tipo: "excluir"},
											function(data) {
												var result = trim(data);
												if(result == "false")
													$this.alert('Erro ao tentar remover a pasta.');
												else
												{
													removerPasta(id);
													tr.clear();	
												}
											}
										);						
									}
								});
							}
						}
					}
				});
			};
			
			var adicionarFavorito = function(id, nome, url, parent_id, append)
			{
				var option = table.createOptionTreeTable({id: id, nome: nome, parent_id: parent_id, isFile: true, append: append});

				var tr = option.getTr();
				var tdDetalhe = option.getTd();
				var span = option.getSpan();

				var tdUrl = tr.find('td:second');

				span.dblclick(function(e) {
					e.preventDefault();
					if(tdUrl.html().indexOf("http://www", 0) == -1 && tdUrl.html().substr_count('.') < 2)
					{
						$this.alert('URL invalida!');
						return true;
					}
					$(this).openWindow(nome, {title: nome, width: '700', height: '350', file: 'servicos/iframe.jsp?url='+tdUrl.html()}, true); e.preventDefault();
				});
				
				tdDetalhe.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								if(tdUrl.html().indexOf("http://www", 0) == -1 && tdUrl.html().substr_count('.') < 2)
								{
									$this.alert('URL invalida!');
									return true;
								}
								$(this).openWindow(nome, {title: nome, width: '700', height: '350', file: 'servicos/iframe.jsp?url='+tdUrl.html()}, true);
							}
						},
						Renomear: {
							icon: "edit",
							onClick: function() {
								var oldValor = span.html();
								var input = $('<input type="text" size="10"/>').val(span.html()).blur(function() {
									var valor = $(this).val();
									$(this).remove();
									span.html(valor);
									if(oldValor == valor)
										return true;
			
									$this.sendRequest('../../MembroFavoritoServlet?opcao=F', {pkFavorito: id, nome: valor, tipo: "alterarNome"},
										function(data) {
											var result = trim(data);
											if(result == "false")
											{
												$this.alert('Erro ao tentar alterar o nome do favorito.');
												span.html(oldValor);
											}
										}
									);
								});
								
								span.html(input);
								setTimeout(function() { input.focus(); }, 500);
							}
						},
						Editar_url: {
							icon: "edit",
							onClick: function() {
								var url = tr.find('td').eq(1);

								var oldValor = url.html();
								var input = $('<input type="text" size="10"/>').val(url.html()).blur(function() {
									var valor = $(this).val();
									$(this).remove();
									url.html(valor);
									if(oldValor == valor)
										return true;

									if(valor.indexOf("http://www", 0) == -1 && valor.substr_count('.') < 2)
									{
										$this.alert('URL invalida! A url deve começar com http://www.');
										url.html(oldValor);
										return true;
									}
			
									$this.sendRequest('../../MembroFavoritoServlet?opcao=F', {pkFavorito: id, url: valor, tipo: "alterarUrl"},
										function(data) {
											var result = trim(data);
											if(result == "false")
											{
												$this.alert('Erro ao tentar alterar a url do favorito.');
												url.html(oldValor);
											}
										}
									);
								});
								
								url.html(input);
								setTimeout(function() { input.focus(); }, 500);
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								desktop.alert("Tem certeza que deseja remover este favorito?", null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.sendRequest('../../MembroFavoritoServlet?opcao=F', {pkFavorito: id, tipo: "excluir"},
											function(data) {
												var result = trim(data);
												if(result == "false")
													$this.alert('Erro ao tentar remover o favorito.');
												else
													tr.clear();
											}
										);						
									}
								});
							}
						}
					}
				});

				var tdUrl = $('<td></td>').html(url);
				tr.append(tdUrl);
			};
			
			<%
		
			@SuppressWarnings ("unchecked")
			Collection<PastasFavorito> pastas = (Collection<PastasFavorito>)request.getAttribute("pastas");
			
			@SuppressWarnings ("unchecked")
			Collection<Favorito> favoritos = (Collection<Favorito>)request.getAttribute("favoritos");
			
			for(PastasFavorito pasta:pastas)
				out.print("adicionarPasta("+pasta.getPkPasta()+", '"+pasta.getNome()+"'.clearNull(), "+pasta.getPasta().getPkPasta()+");");	
		
			for(Favorito favorito:favoritos)
				out.print("adicionarFavorito("+favorito.getPkFavorito()+", '"+favorito.getNomeLink()+"'.clearNull(), '"+favorito.getUrl()+"'.clearNull(), "+favorito.getPasta().getPkPasta()+");");
			%>
			
			table._treeTable("Disco Local", {
				buttons: {
				Nova_pasta: {
					onClick: function() {
						$this.sendRequest('../../MembroFavoritoServlet?opcao=P', {nome: "Nova Pasta", tipo: "incluir"},
							function(data) {
								var result = trim(data);
								if(result == "false")
									$this.alert('Erro ao tentar criar uma nova pasta.');
								else
								{
									adicionarPasta(parseInt(result), "Nova Pasta", 0, true);
									table._treeTable();
								}
							}
						);
					}
				},
				Novo_favorito: {
					onClick: function() {
						$this.sendRequest('../../MembroFavoritoServlet?opcao=F', {nome: "Novo Favorito", url: "http://", tipo: "incluir"},
							function(data) {
								var result = trim(data);
								if(result == "false")
									$this.alert('Erro ao tentar criar uma nova pasta.');
								else
								{
									adicionarFavorito(parseInt(result), "Novo Favorito", "http://", 0, true);
									table._treeTable();
								}
							}
						);					
					}
				}
			}
		});
		};
	});
</script>

<table class="tabelaResult">
	<thead>
		<tr>
			<th style="width: 50%;">Descrição</th>
			<th>URL</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>