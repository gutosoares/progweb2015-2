<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Adivinhe o número!</h3>
        <hr/>
        <h1>:)</h1>
        <p>
            <b>Parabéns</b>,
            você adivinhou o número em 
            <%= session.getAttribute(servlets.Tentar.ID_NUM_TENTATIVAS) %> tentativa(s)!
            <br>
            Seu menor numero de tentativas para descobrir o numero secreto foi:
            <%= session.getAttribute(servlets.Tentar.ID_NUM_MENOR_TENTATIVA) %> tentativa(s)!
                    
        </p>
        <a href="/Adivinhando/Sair">Sair</a>
    </body>
</html>
