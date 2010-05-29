<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ACTIVA</title>
<link href="css/principal.css" rel="stylesheet" type="text/css" />
<link href="css/janela.css" rel="stylesheet" type="text/css" />
<link href="css/table.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/plugins/jquery-ui-1.7.1.custom.min.js"></script>
<script type="text/javascript" src="js/functions.js"></script>
<script type="text/javascript" src="js/globalEvent.js"></script>
<script type="text/javascript">
			$(document).ready(function () {
				var $this = $('body');
				$this.find('input#btn_enviar').click(function(e)
				{
					e.preventDefault();
					var usuario = $this.find('#box_login');
					var senha = $this.find('#box_senha');
					
					if(usuario.val().length == 0)
						$this.alert('Por favor, digite o usu&aacute;rio.', 'Login', {clickOK: function() { usuario.focus(); }});
					else if(senha.val().length == 0)
						$this.alert('Por favor, digite a senha.', 'Login', {clickOK: function() { senha.focus(); }});
					else
					{
						$.post('LoginServlet?opcao=3', {usuario: usuario.val(), senha: senha.val()}, function(data) {
							var result = trim(data);
							if(result == "processando")
								return;
							else if(result == "administrador")
								location.href = "pages/administrador/index.jsp";
							else if(result == "membro")
								location.href = "pages/restrito/index.jsp";
							else if(result != "")
								$(this).alert(result);
							else
								$this.find("form#form").submit(function() { return true; }).submit();
						});
					}
				});

				$this.find('input#btn_criar').click(function(e)
				{
					request.html($('body'), null, 'pages/cadastrar.jsp', false, function() {
						var cadastro = $('#CADASTRAR');
						cadastro.find('button#voltar').remove();
						cadastro.find('button#cadastrar').html('Criar');
						cadastro.showDialog({
							minimize: false,
							maximize: false,
							modal: true,
							showCloseButton: false,
							title: 'Criar Conta',
							width: '290',
							draggable: false,
							reload: false
						});
					});
				});
			});
		</script>
</head>

<body>




<div id="banner">
<div id="area_log">
  <div id="titulo_log">Meu Activa
  <form id="form_log" name="form_log" method="post" action="" onsubmit="return false;">
    <label>
      <input name="login" id="box_login" type="text" value="LOGIN" size="50" maxlength="100" />
      <input name="senha" id="box_senha" type="password" value="SENHA" size="50" maxlength="100" />
      <input type="image" src="images/principal/btn.png" id="btn_enviar" "name="entrar"  value="Entrar" />
      <input type="image" src="images/principal/btn_criar.png" id="btn_criar" name="criar conta" value="Criar Conta" />
    </label>
  </form>
 </div><div class="link" style="top:5px"><a href="../index_activa.html">esqueci meu login ou senha</a></div>
</div>



</div>
<div id="linha1">
<div id="linha1_box1">
  <div><img src="images/principal/titulo_noticias.jpg" alt="Notícias" name="noticias" width="280" height="19" id="noticias" /></div>
Noticias</div>

<div id="linha1_box2">
  <div><img src="images/principal/titulo_tags.jpg" alt="Notícias" name="tags" width="280" height="19" id="tags" /></div>
Tags</div>

<div id="linha1_box3">
  <div><img src="images/principal/titulo_twitter.jpg"  alt="Notícias" name="twitter" width="280" height="19" id="twitter" /></div>
Twitter</div>
</div>


<div id="rodape"><img src="images/principal/bg_rodape.jpg" width="1920" height="56" border="0" usemap="#redes_sociais" />
  <map name="redes_sociais" id="redes_sociais">
    <area shape="rect" coords="804,3,829,29" href="http://www.youtube.com" target="_blank" alt="youtube" />
    <area shape="rect" coords="852,3,878,29" href="http://www.delicious.com" target="_blank" alt="delicious" />
    <area shape="rect" coords="1096,3,1124,32" href="http://www.orkut.com" target="_blank" alt="orkut" />
    <area shape="rect" coords="1045,2,1074,31" href="http://www.twitter.com" target="_blank" alt="twitter" />
    <area shape="rect" coords="995,2,1021,28" href="http://www.netvibes.com" target="_blank" alt="netvibes" />
    <area shape="rect" coords="946,2,973,30" href="http://www.feed.com" target="_blank" alt="feeds"/>
    <area shape="rect" coords="900,3,928,31" href="http://www.facebook.com" target="_blank" alt="facebook" />
</map>
</div>

</body>
</html>
