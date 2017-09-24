<%-- 
    Document   : cadastro
    Created on : 02/09/2017, 23:00:03
    Author     : renato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset = "UTF-8">
        <title>Cadastro de Livros</title>

        <!-- <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
         <script src="js/jquery-3.1.1.js" type="text/javascript"></script>
         <script src="js/bootstrap.min.js" type="text/javascript"></script>-->

        <script src="js/jquery-3.1.1.js"></script>
        <script src="js/mask/jQuery-Mask-Plugin-master/dist/jquery.mask.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <!--<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>



    </head>
    <body>
        <div class="container-fluid">
            <div class="row" >
                <div class="jumbotron col-lg-12 col-md-12 col-xs-12" style="height: 30px;background-color: hsl(0, 0%, 98%);    box-shadow: 0px 3px 5px grey;" >
                    <div style="width: 100%; margin: auto; margin-bottom: 50px;text-align: center">
                        <h1>Cadastro de Livros</h1>
                    </div>
                </div>
            </div>
        </div>

        <script>

            $(document).ready(function () {
                codigo = $('#codigo').val();
                carregarDados(codigo);
                $('#ano').mask('0000');
            });

            function salvarProduto() {
                alert($("form").serialize());
                if ($("#codigo").val().length > 0) {
                    acao = 'alterar';
                    codigo = $("#codigo").val();
                } else {
                    acao = 'salvar';
                    codigo = 0;
                }

                console.log($(this).serialize());
    //build an array of selected values
        var categorias = [];
                $('#categoria :selected').each(function (i, selected) {
                    categorias[i] = $(selected).val();
                });
                
                var subcategorias = [];
                $('#subcategoria :selected').each(function (i,selected){
                   subcategorias[i]  = $(selected).val();
                });

                
                request = $.ajax({

                    url: '/lojaLivros/cadastrolivro',
                    type: 'post',
                    data: $('form').serialize() + "&acao=" + acao +"&categoria=" + categorias + "&subcategoria=" +subcategorias

                            /* {
                             'acao': acao,
                             'titulo': $('#titulo').val(),
                             'fabricante': $('#fabricante').val(),
                             'datafabricacao': $('#datafabricacao').val(),
                             'datavalidade': $('#datavalidade').val(),
                             'fornecedor': $('#fornecedor').val(),
                             'categoria': $('#categoria').val(),
                             'teor': $('#teor').val(),
                             'codigo': codigo,
                             'preco': $('#preco').val(),
                             'estoqueminimo': $('#estoqueminimo').val(),
                             'estoquemaximo': $('#estoquemaximo').val()}*/
                });


                request.done(function (response, textStatus, jqXHR) {
                    console.log(response);

                    if (response.codigo > 0) {
                        $('#codigo').val(response.codigo);
                    }
                    alert(response.mensagem);
                });


                request.fail(function (jqXHR, textStatus, errorThrown) {

                    console.log(errorThrown);

                });

            }
            function excluir() {

                if (!confirm('Deseja realmente excluir a bebida?'))
                    return;

                request = $.ajax({

                    url: '/lojaLivros/cadastrobebida',
                    type: 'post',
                    data: {
                        'acao': 'excluir',
                        'codigo': $('#codigo').val()}
                });


                request.done(function (response, textStatus, jqXHR) {
                    console.log(response);

                    if (response.codigo > 0) {
                        $('#codigo').val(response.codigo);
                    }
                    alert(response.mensagem);
                    limpar();
                });


                request.fail(function (jqXHR, textStatus, errorThrown) {

                    console.log(errorThrown);

                });
            }

            function limpar() {
                //$('#idDoSeuComboBox').attr('selectedIndex')
                $('#codigo').val('');
                $('#nome').val('');
                $('#descricao').val('');
                $('#fabricante').val(1);
                $('#datafabricacao').val('');
                $('#datavalidade').val('');
                //$('#categoria').val('');
                $('#fornecedor').attr('selectedIndex');
                $('#categoria').attr('selectedIndex');
                $('#teor').val(0);
                $('#preco').val(0);
                $('#estoqueminimo'.val(0));
                $('#estoquemaximo').val(0);

            }

            function telaConsulta() {
                window.location.href = 'consulta.jsp';
            }

            function carregarDados(codigo) {

                acao = 'editar';
                request = $.ajax({

                    url: '/cadastrobebida/cadastroLivro',
                    type: 'post',
                    data: {
                        'acao': acao,
                        'codigo': codigo},

                    beforeSend: function (msg) {
                        $('#tabela').html('');

                        $('#loading').show();

                    },
                    success: function (msg) {
                        $('#loading').hide();
                        $('#dados').show();
                        // some user Feedback
                    }
                });

                request.done(function (response, textStatus, jqXHR) {

                    entidade = response;

                    console.log(response);

                    $('#nome').val(entidade.nome);
                    $('#fabricante').val(entidade.fabricante);
                    $('#datafabricacao').val(entidade.dataFabricacao);
                    $('#datavalidade').val(entidade.dataValidade);
                    $('#fornecedor').val(entidade.fornecedor);
                    $('#categoria').val(entidade.categoria);
                    $('#teor').val(entidade.teoralcoolico);
                    $('#preco').val(entidade.preco);
                    $('#estoqueminimo').val(entidade.estoqueminimo);
                    $('#estoquemaximo').val(entidade.estoquemaximo);

                });

                /*request.done(function (response, textStatus, jqXHR) {
                 resp = $.parseJSON(response);
                 entidade = resp.mensagem;
                 console.log(entidade.codigo);
                 
                 });*/
            }

            function limpar() {

                var elements = document.getElementsByTagName("input");
                for (var i = 0; i < elements.length; i++) {
                    if (elements[i].type == "text") {
                        elements[i].value = "";
                    } else if (elements[i].type == "radio") {
                        elements[i].checked = false;
                    } else if (elements[i].type == "checkbox") {
                        elements[i].checked = false;
                    } else if (elements[i].type == "select") {
                        elements[i].selectedIndex = 0;
                    }
                }

                $('#fabricante').val(1);
                $('#fornecedor').val(1);
                $('#categoria').val(1);
            }
        </script>
        <div class="container">

            <div class="row">
                <div  style=' margin: auto;border-style: solid;border-width: 1px;border-color: rgb(204, 204, 204); padding: 40px; border-radius: 5px;box-shadow: 0px 10px 10px grey;' class='col-md-12 col-lg-12'>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <ul class="nav nav-tabs">
                                <li class="nav-item">
                                    <a class="nav-link active" href="#">Principal</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Ingredientes</a>
                                </li>

                            </ul>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <form id='dados'>
                                <div class='form-group'>
                                    <div style='width: 15%;'>
                                        <label for='codigo'> Código</label>

                                        <%
                                            String codigo = request.getParameter("codigo");

                                            if (codigo == null) {
                                                codigo = "";
                                            }

                                        %>
                                        <input type='text' class='form-control ' id='codigo' readonly='readonly' value= <% out.print(codigo);%>  >
                                        <br>
                                    </div>
                                </div>
                                <div class='form-group'>
                                    <div style='width: 100%;float: left'>
                                        <label for='titulo'> Título</label>
                                        <input type='text' class='form-control ' id='titulo' name="titulo" value='' >
                                    </div>
                                    <!--<div style='width: 45%;float: right'>
                                        <label for='categoria' >Categoria</label>
                                        <select class='form-control' id='categoria' >
                                            <option value="1">Categoria1</option>
                                            <option value="2">Categoria2</option>
                                        </select>
                                    </div>-->
                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='categoria' >Categoria</label>
                                        <!--<select class='form-control' id='categoria' name="categoria">
                                            <option value="1">Categoria1</option>
                                            <option value="2">Categoria2</option>
                                        </select>-->
                                        <select  class='form-control' id="categoria" multiple="multiple" >
                                            <option value="1">Categoria1</option>
                                            <option value="2">Categoria2</option>
                                            <option value="3">Categoria3</option>
                                        </select>
                                    </div>
                                    <div style='width: 45%;float: right'>
                                        <label for='subcategoria' >SubCategoria</label>
                                        <!--<select class='form-control' id='subcategoria' name="subcategoria">
                                            <option value="1">SubCategoria1</option>
                                            <option value="2">SubCategoria2</option>
                                        <option value="3">SubCategoria3</option>
                                        </select>-->
                                        <select  class='form-control' id="subcategoria" multiple="multiple" >
                                            <option value="1">SubCategoria1</option>
                                            <option value="2">SubCategoria2</option>
                                        </select>
                                    </div>
                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='autor' >Autor</label>
                                        <select id='autor' class='form-control' name="autor">
                                            <option value="1">Autor1 </option>
                                            <option value="2">Autor2</option>
                                        </select>
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='editora' >Editora</label>
                                        <select id='editora' class='form-control' name="editora">
                                            <option value="1">Editora1</option>
                                            <option value="2">Editora2</option>
                                        </select>
                                    </div>
                                </div>


                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='edicao' >Edição</label>
                                        <input type='text' class='form-control' id='edicao' value='' name="edicao" >
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='ano' >Ano</label>
                                        <input type='text' class='form-control' name='ano' value='' >
                                    </div>

                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='isbn' >ISBN</label>
                                        <input type='text' class='form-control' id='isbn' value='' name="isbn">
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='numeropaginas' >Número de Páginas</label>
                                        <input type='text' class='form-control' id='numeropaginas' value='' name="numeropaginas">
                                    </div>

                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='altura' >Altura</label>
                                        <input type='text' class='form-control' id='altura' value='' name="altura" >
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='largura' >Largura</label>
                                        <input type='text' class='form-control' id='largura' value='' name="largura">
                                    </div>

                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='peso' >Peso</label>
                                        <input type='text' class='form-control' id='peso' value='' name="peso">
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='profundidade' >Profundidade</label>
                                        <input type='text' class='form-control' id='profundidade' value='' name="profundidade">
                                    </div>

                                </div>

                                <div class='form-group'>
                                    <div style='width: 45%;float: left'>
                                        <label for='precificacao' >Grupo Precificação</label>
                                        <select id='precificacao' class='form-control' name="precificacao">
                                            <option value="1">precificacao 1 </option>
                                            <option value="2">ptecificacao 2</option>
                                        </select>
                                    </div>

                                    <div style='width: 45%;float: right'>
                                        <label for='codigobarras' >Código de Barras</label>
                                        <input type='text' class='form-control' id='codigobarras' value='' name="codigobarras">
                                    </div>


                                </div>

                                <div class='form-group'>

                                    <div style='width: 100%;float: left'>
                                        <label for='sinopse' >Sinopse</label>
                                        <textarea rows="4" cols="50" class="form-control" id="sinopse" name="sinopse"></textarea>
                                    </div>


                                </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 col-lg-12" style="margin-top: 20px">
                            <div style='width:100%'>
                                <button type='button' class='btn btn-default pull-left' onclick='salvarProduto()'>Salvar  <span class='glyphicon glyphicon-floppy-save' aria-hidden='true'></span></button>
                                <button type='button' class='btn btn-default pull-left' onclick='telaConsulta()'>Consulta  <span class='glyphicon glyphicon-search' aria-hidden='true'></span></button>
                                <button type='button' class='btn btn-default pull-left' onclick='limpar()'>Novo <span class='glyphicon glyphicon-list-alt' aria-hidden='true'></span></button>
                                <button type='button' class='btn btn-default pull-left' onclick='excluir();
                                        limpar()'>Excluir <span class='glyphicon glyphicon-remove' aria-hidden='true'></span></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>

</body>
</html>