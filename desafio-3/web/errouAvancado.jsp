<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Adivinhando</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h3>? Adivinhe o n�mero ?</h3>
            <hr/>
            <h1>:(</h1>
            <p><%= request.getAttribute(servlets.Tentar.ID_MSG_ERRO) %></p>
            <a href="tentarAvancado.html">Tentar novamente</a> | <a href="/desafio-3/Sair">Desistir</a>
        </div>
        <script src="js/jquery-1.12.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>