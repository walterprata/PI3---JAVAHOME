<%-- 
    Document   : CadastrarJogo
    Created on : 18/04/2020, 15:34:15
    Author     : Vitoria Cristina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <!-- Eu tenho uma pasta de customização dessa tela, mas não colocarei 
        pois preciso ver com o restante do grupo-->
        
        <!-- Bootstrap core CSS 
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:100,200,300,400,500,600,700,800,900" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="vendor/devicons/css/devicons.min.css" rel="stylesheet">
        <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
        --->
       
    <link href="topodapagina.css" rel="stylesheet">
    <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/CadastrarJogo" method="POST" >
        


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Jogo</title>
    </head>
    <body id="page-top">

        <%@include  file="header.jsp" %>

        <div align ="center">
            <br>
            <br>
            <h1>Cadastrar Jogo</h1>


            <div class="col-md-6 mb-3">


                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label>Código</label>
                        <input name="codigojogo" type="text" class="form-control" id="primeironome" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Código obrigatório
                        </div>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label>Nome</label>
                        <input name="nome" type="text" class="form-control" id="primeironome" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Nome obrigatório
                        </div>
                    </div>
                    <div class="col-md-12 mb-3">
                        <label>Descrição</label>
                        <input name="descricao" type="text" class="form-control" id="primeironome" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Descrição necessária
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="categoria">Categoria</label>
                        <select name ="categoriapet" class="custom-select d-block w-100" id="categoria" required>
                            <option value="">Selecionar...</option>
                            <option>Jogo</option>
                            <option>Brinde</option>
                            <option>Console</option>
                            <option>Outros</option>
                        </select>

                        <div class="invalid-feedback">
                            Favor selecionar a categoria.
                        </div>
                    </div>


                    <div class="col-md-4 mb-3">
                        <label for="valor">Valor</label>
                        <input name="valor" type="number" class="form-control" id="valor" pattern = "^\d+\.\d{2}" placeholder="R$00,00" required>
                        <div class="invalid-feedback">
                            Favor inserir o Valor.
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="qtdestoque">Quantidade</label>
                        <input name="qtdestoque" type="number" class="form-control" id="qtdestoque" placeholder="1" required>
                        <div class="invalid-feedback">
                            Favor inserir a quantidade.
                        </div>
                    </div>

                </div>

                <hr class="mb-4">
                <button class="btn btn-secondary btn-lg btn-block" type="submit">Gravar</button>
                </form>
            </div>
        </div>

        <footer class="my-5 pt-5 text-muted text-center text-small">
            <p class="mb-1">&copy; JavaHome </p>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="#">Privacidade</a></li>
                <li class="list-inline-item"><a href="#">Termos</a></li>
                <li class="list-inline-item"><a href="https://www.google.com">Suporte</a></li>
            </ul>
        </footer>
        </div>


        </div>        
    </body>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        }
        )();


    </script>
    <script>


        var mensagem = "${mensagem}"
        var codigo = "${codigobarras}"


        if (mensagem == "Cadastrado com sucesso!") {
            alert(mensagem)


        } else if (mensagem == "Já Cadastrado") {
            alert("O Jogo com código " + codigo + " já se encontra cadastrado")

        }
    </script>
</html>