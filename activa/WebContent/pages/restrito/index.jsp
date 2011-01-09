<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Collection"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Ambiente"%>
<%@page import="modelo.MicroBlog"%>
<%@page import="util.Ferramenta"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="java.io.File"%>
<%@page import="modelo.Membro"%><html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<title>Desktop</title>
<link rel="stylesheet" type="text/css" href="../../css/designer.css">
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.corner.js"></script>
<script type="text/javascript" src="../../js/plugins/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.selectboxes.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.filestyle.js"></script>
<script type="text/javascript"
	src="../../js/plugins/ui.datepicker-pt-BR.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.meio.mask.min.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.datepick.min.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.datepick-pt-BR.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.treeTable.min.js"></script>
<script type="text/javascript"
	src="../../js/plugins/jquery.contextMenu.js"></script>
<script type="text/javascript"
	src="../../js/plugins/ajaxupload.js"></script>
<script type="text/javascript"
	src="../../js/plugins/md5.js"></script>
<script type="text/javascript" src="../../js/functions.js"></script>
<script type="text/javascript" src="../../js/globalEvent.js"></script>
<script type="text/javascript" src="../../js/objetos.js"></script>
<script type="text/javascript" src="../../js/events/login.js"></script>
<script type="text/javascript" src="../../js/events/menu_restrito.js"></script>
<script type="text/javascript" src="../../js/events/desktop_restrito.js"></script>
<script type="text/javascript" src="../../js/classes/String.js"></script>
<script type="text/javascript" src="../../js/events/notificacao_automatica.js"></script>
<script type="text/javascript">
			$(document).ready(function () {
				globalEvent('basico');

				$('button#limparNotificacao').click(function() {
					notificacaoDialog.fadeOut(2000);
				});
				
			<%
				if(request.getAttribute("msg") != null)
					out.print("$(this).alert('"+request.getAttribute("msg")+"');");
			
				if(session.getAttribute("membro") == null)
					out.print("loginEvent.showDialog();");
				else
				{
					Usuario usuario = (Usuario)session.getAttribute("membro");
					
					out.print("menuOptionConteudo.find('div#nomeMembro').html('"+usuario.getNome()+"');");
					
					out.print("globalEvent('restrita');");
					
					if(session.getAttribute("ambiente") == null)
						out.print("showTelaAmbiente();");
					else
					{
						Ambiente ambiente = (Ambiente)session.getAttribute("ambiente");
						
						out.print("document.title = 'ACTIVA - Ambiente: ["+ambiente.getNome()+"]';");
						
						File arq = new File(ambiente.getEnderecoArquivo()+"/fotomembro/"+(usuario.getImagem() != null && usuario.getImagem().length() < 8 ? "b" : usuario.getImagem()));
						if(arq.exists())
							out.print("menuOptionConteudo.find('img.membroFoto').attr('src', '../../arquivo/fotomembro/"+usuario.getImagem()+"');");
						
						arq = new File(ambiente.getEnderecoArquivo()+"/fotoambiente/"+(ambiente.getImagem() != null && ambiente.getImagem().length() < 8 ? "b" : ambiente.getImagem()));
						if(arq.exists())
						{
							out.print("$('body').css('background-image', 'url(\"../../arquivo/fotoambiente/"+ambiente.getImagem()+"\")');");
						}
												
						
						Collection<MicroBlog> microBlogs = (Collection<MicroBlog>)usuario.getMicroBlogs();
						if(microBlogs.size() == 0)
							out.print("$('input#microBlog').val('Meu microblog.');");
						else
						{
							for(MicroBlog microBlog:microBlogs)
							{
								if(microBlog.selecionado())
									out.print("$('input#microBlog').val('"+microBlog.getDescricao()+"');");	
							}
						}
						
						if(!Membro.isProfessor(usuario))
							out.print("menuOptionConteudo.find('a[href=\"#NOVO_AMBIENTE\"]').hide()");
						
						// Notificações automáticas
						out.print("executaReverseAjax();");
					}
				}
			%>
			});

			
		</script>
</head>
<body>
<div id="desktop">

</div>
<div id="menuOption">
<div id="conteudo"><img src="../../images/user.gif"
	id="miniUserImg" alt="" />
<div id="nomeMembro"></div>
<div id="servicos">
<ul>
	<li></li>
</ul>
</div>
<div id="sub_servicos">
<div id="perfil_menu" style="display: none;">
<ul>
	<li></li>
</ul>
</div>
</div>

<div id="perfil">
	<img src="../../images/naoDefinido.jpg" alt="" class="membroFoto" />
	<input type="text" name="microBlog" id="microBlog" value="" />
	<a href="#PERFIL_USUARIO" style="">Perfil do Usuário <span style="position: absolute; left: 136px;">&#187;</span></a>
	<hr />
	<a href="#PERFIL_AMBIENTE">Perfil do Ambiente</a>
	<a href="#NOVO_AMBIENTE" target="_blank">Novo Ambiente</a>
	<a href="#TROCAR_AMBIENTE">Trocar Ambiente</a>
	<!--<hr />
	<a href="#PAINEL_CONTROLE">Painel de Controle</a>-->
	<hr />
<a href="#SAIR">Sair</a></div>
</div>
</div>
<div id="menuBar"><a href="#" id="botaoIniciar"><img
	src="../../images/button_start.gif" alt="Iniciar" /></a>
<div>
<table id="minimizedWindows" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td></td>
		</tr>
	</tbody>
</table>
</div>
</div>

<div id="notificacao" style="display: none;">
	<button type="button" class="ui-state-default ui-corner-all"
				name="limparNotificacao" id="limparNotificacao">Limpar Aviso</button>
	<br/><br/>
	<div id="notificacaoConteudo"></div>
</div>

</body>
</html>