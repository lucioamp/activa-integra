<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="nucleo.usuario.UsuarioPersist"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>OpenID V�lido!</p>

<%
	int idUsuario;
	String identificador;
	UsuarioPersist usuPersist; 
		
	usuPersist = new UsuarioPersist();
	
	identificador = request.getAttribute("identifier").toString();
	
	idUsuario = usuPersist.BuscaOpenID(identificador);

	if ( idUsuario == -1 )
	{
		String mensagem;

		mensagem = request.getParameter("erro");

		if ( mensagem == null )
			mensagem = "Usu�rio n�o cadastrado";
%>

	<p align=center>
		<font color="#FF0000"><b><%= mensagem %></b></font><br/>	
	</p>
	<form action="/IesAvance/FinalizaLoginOPenID" method="post">
		<p>Se voc� j� for um usu�rio cadastrado...</p>	  
		<fieldset>
			<legend>Associar a um Usu�rio AvaNCE</legend>
			<p class="loginTxt">
			<input type="hidden" name="operacao" value="associar"/>
			<input type="hidden" name="identifier" value="<%= identificador %>"/>
			<label for="cpf">CPF:</label> <input type="text" name="cpf" id="cpf"/> <br/>
			<label for="senha">Senha:</label> <input type="password" name="senha" id="senha"/>
			</p>
			<p class="loginTxt">
			<input type="submit" value="Associar"/>
			</p>
		</fieldset>
	</form>
	<p>Se ainda n�o for usu�rio do sistema</p>
	<form action="/IesAvance/FinalizaLoginOPenID" method="post">
		<fieldset>
			<legend>Cadastrar usu�rio OpenID </legend>
			<p class="loginTxt">
			<input type="hidden" name="operacao" value="cadastrar"/>
			<input type="hidden" name="identifier" value="<%= identificador %>"/>
			<label for="cpf">CPF:</label> 
			<input type="text" name="cpf" id="cpf"/> <br/>
			<input type="hidden" name="senha" value=""/>
			<input type="hidden" name="confirma" value=""/>
		    <label for="nomeUsuario">Nome:</label> 
		    <input type="text" name="nomeUsuario" id="nomeUsuario" size="60" maxlength="50"> <br/>
		    <label for="email">Email:</label>
		    <input type="text" name="email" id="email" size="60" maxlength="50"> <br/>
		    <label for="telefone">Telefone:</label>
		    <input type="text" name="telefone" id="telefone" size="9" maxlength="8"> <br/>
			</p>
			<p class="loginTxt">
			<input type="submit" value="Cadastrar"/>
			</p>
		</fieldset>
	</form>
		
<%
	}
	else
	{
		%>
		<form name="redirec" action="/IesAvance/FinalizaLoginOPenID" method="post">
			<input type="hidden" name="identifier" value="<%= identificador %>"/>
			<input type="hidden" name="idusuario" value="<%= idUsuario %>"/>
		</form>
		<script>
		document.redirec.submit();
		</script>
		<%
	}
%>
</body>
</html>