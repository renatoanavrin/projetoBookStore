<head>
    <meta charset = "UTF-8">
    <title>Cadastro de Livros</title>

    <!--  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-3.1.1.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>-->


    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>-->


    <script src="js/jquery-3.1.1.js"></script>
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
                    <h1>Consulta de Bebidas</h1>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            consultar();
        });

        function  editarBebida(codigo) {

            window.location.href = 'cadastro.jsp?codigo=' + codigo + '&acao=EDITAR';
        }

        function consultar() {

            acao = 'consultar';
            consulta = $('#consulta').val();
            dadosConsulta = $('#dadosconsulta').val();
            precoinicial = $('#precoinicial').val();
            precofinal = $('#precofinal').val();

            request = $.ajax({

                url: '/cadastrobebida/consultabebida',
                type: 'post',
                data: {
                    'acao': acao,
                    'consulta': consulta,
                    'dadosconsulta': dadosConsulta,
                    'precoinicial':precoinicial,
                    'precofinal':precofinal
                },

                beforeSend: function (msg) {
                    $('#tabela').html('');
                    $('#loading').show();

                },
                success: function (msg) {
                    
                    $('#loading').hide();

                    // some user Feedback
                }
            });
            request.done(function (response, textStatus, jqXHR) {
                
                console.log(response.mensagem);
                //resp = $.parseJSON(response);
                $('#tabela').html(response.mensagem);

            });
            request.fail(function (jqXHR, textStatus, errorThrown) {

                console.log(errorThrown);
            });




        }
    </script>
    <div class="container">
        <div class="row">
            <div class="col-md-2 col-lg-2">
                <select class="form-control" id="consulta" >
                    <option value="categoria" selected="'.$nome.'">Categoria</option>
                    <option value="fornecedor" selected="'.$codigo .'">Fornecedor</option>
                </select>
            </div>
            <div class="col-md-10 col-lg-10"  >
                <input type="text" class="form-control " id="dadosconsulta" value=''>

            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-lg-2">

            </div>
            <div class="col-md-10 col-lg-10"  style="margin-top: 20px">
                <button type="button" class="btn btn-default" onclick="consultar()">Consultar <span class="glyphicon glyphicon-search" aria-hidden="true"></button>
                <button type="button" class="btn btn-default" onclick="window.location.href = 'cadastro.jsp'">Novo <span class="glyphicon glyphicon-list-alt" aria-hidden="true"> </button>            
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                
                
                <label>Preço</label>
                <br>
                <input type="text" class="form-control " id="precoinicial" value='' placeholder="Mínimo" style="width: 48%;float: left">
                <input type="text" class="form-control " id="precofinal" value='' placeholder="Máximo" style="width: 48%;float: right">
            </div>
            <div class="col-md-10" >
                <div id="loading" style="margin: auto;width: 270px;margin-top: 100px">
                    <!--<img src="../img/ajax-loader.gif" style="margin: auto">-->
                </div>
                <div id="tabela">

                </div>
            </div>
        </div>



    </div>

</body>
