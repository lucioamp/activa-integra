<%--
  ~ Copyright 2006-2007 Sxip Identity Corporation
  --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>OpenID HTML FORM Redirection</title>
</head>
<body onload="document.forms['openid-form-redirection'].submit();">
 <br/>
    <form name="openid-form-redirection" 
          action='<c:out value="${message.OPEndpoint}"/>'
          method="post" accept-charset="utf-8">
        <c:forEach var="parameter" items="${message.parameterMap}">
        <input type="hidden" name='<c:out value="${parameter.key}"/>' 
                             value='<c:out value="${parameter.value}"/>'/>
        </c:forEach>
        <p>Redirecionando para o seu provedor de OpenID...</p>
        <p>Se não abrir em alguns segundos, selecione o botão...</p>
        <button type="submit">Continue...</button>
    </form>
</body>
</html>
