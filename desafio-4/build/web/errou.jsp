<%-- 
    Document   : errou
    Created on : Feb 22, 2016, 9:23:15 AM
    Author     : Augusto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Adivinhe o número!</h1>
        <hr/>
        <h2>:(</h2>
        <p><%= request.getAttribute(servlets.Tentar.ID_MSG_ERRO)%></p>
        <a href="Tentar.html">Tentar novamente</a> |
        <a href="/Adivinhando/Sair">Desistir</a>
    </body>
</html>
