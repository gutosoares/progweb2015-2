<%-- 
    Document   : imcResult
    Created on : 03/02/2016, 00:31:46
    Author     : Wisner Júnior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cálculo IMC - Resultado</title>
    </head>
    <body>
        <h1>Resultado</h1>
        <hr/>
        <h2>Seu IMC é <%= request.getAttribute(JavaServlets.calculoIMC.ID_IMC) %> .</h2>
        <br/>
        <h3><%= request.getAttribute(JavaServlets.calculoIMC.ID_MSG) %></h3>
        <hr/>
    </body>
</html>
