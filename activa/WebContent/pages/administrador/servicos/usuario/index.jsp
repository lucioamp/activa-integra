<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Membro"%>
<script type="text/javascript">
	$(function() {
		modulo_ready = function($this) {
			var object = $this.getObject();
			var servico = 'usuario';
			
			adicionarUsuario = function(id, nome, apelido, isProfessor, isAluno, desativado)
			{
				var tr = $('<tr></tr>');
				tr.contextMenu({
					buttons: {
						Abrir: {
							textBold: true,
							icon: "open",
							onClick: function() {
								$this.newPage('../../UsuarioServlet?opcao=D', {pkUsuario: id});
							}
						},
						Editar: {
							icon: "edit",
							onClick: function() {
								$this.newPage('../../UsuarioServlet?opcao=D', {pkUsuario: id, alteracao: true});
							}
						},
						Remover: {
							icon: "delete",
							onClick: function() {
								$this.alert('Tem certeza que deseja excluir?', null, {
									dialog: DIALOG_CONFIRM,
									clickOK: function() {
										$this.getPage(1).load("../../UsuarioServlet?opcao=E", {pkUsuario: id}, function() {
											modulo_ready($this);
										});
									}
								});
							}
						}
					}
				});

				var linkPermissao = $('<select></select>').addOption("A", "Aluno").addOption("P", "Professor").change(function(){
					$this.sendRequest("../../UsuarioServlet?opcao=P", {pkUsuario: id, permissao: $(this).val()});
				}).selectOptions((isProfessor ? "P" : "A"));

				var status = null;
				var linkStatus = $('<a href="#"></a>');
				
				if(desativado)
				{
					linkStatus.html("Ativar");
					linkStatus.css('color', 'blue');
					status = "A";
				}else
				{
					linkStatus.html("Desativar");
					linkStatus.css('color', 'red');
					status = "D";
				}

				linkStatus.click(function(e) {
					e.preventDefault();
					$this.alert('Deseja '+(desativado ? 'ativar' : 'desativar')+' o usu&aacute;rio selecionado?', null, {
						dialog: DIALOG_CONFIRM,
						clickOK: function() {
							$this.getPage(1).load("../../UsuarioServlet?opcao=S", {pkUsuario: id, status: status}, function() {
								modulo_ready($this);
							});
						}
					});
				});
							
				$this.find('table tbody').append(tr
					.append($('<td></td>').html($('<a href="#"></a>').html(nome).click(function(e) {
						$this.newPage('../../UsuarioServlet?opcao=D', {pkUsuario: id});
					})))
					.append('<td>'+apelido+'</td>')
					.append($('<td></td>').html(linkPermissao))
					.append($('<td></td>').html(linkStatus))
				);
			}

			<%
			if(request.getAttribute("erro") != null)
				out.print("$this.alert('"+request.getAttribute("erro")+"');");
		
			@SuppressWarnings ("unchecked")
			Collection<Usuario> usuarios = (Collection<Usuario>)request.getAttribute("usuarios");
			for(Usuario usuario:usuarios)
				out.print("adicionarUsuario("+usuario.getPkUsuario()+", '"+usuario.getNome()+"'.clearNull(), '"+usuario.getApelido()+"'.clearNull(), "+(Membro.isProfessor(usuario) ? "true" : "false")+", null, "+(usuario.getStatus().equals("D") ? "true" : "false")+");");	
			%>
			
			$this.find('a#incluir').click(function() {
				$this.newPage('servicos/'+servico+'/incluir.jsp', null);
			});
		}
	});
</script>

<table class="tabelaResult">
	<caption>Usu&aacute;rios <a href="#" id="incluir"
		title="Incluir"><img src="../../images/icons/add.gif"></a></caption>
	<thead>
		<tr>
			<th><img src="../../images/icons/b_search.png" />Nome</th>
			<th>Apelido</th>
			<th>Permissão</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>