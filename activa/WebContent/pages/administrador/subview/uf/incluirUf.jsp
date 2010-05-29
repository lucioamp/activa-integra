<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Incluir Uf
<br />
<br />
<form action="UfServlet?opcao=I" method="post">Estado: <input
	type="text" name="estado" id="estado" maxlength="60" /><br>
Sigla: <input type="text" name="sigla" id="sigla" maxlength="2" /><br>
<input type="submit" name="enviar" value="Incluir"></form>
</body>
</html>