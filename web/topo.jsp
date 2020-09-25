<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ARQUITETURA MVC </title> 
        <link href="assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">
            .espaco{
                margin: 0 0 20px 0;
            }

            .erro{
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container"><!-- Bloco principal -->
            <div class="row"><!-- Cria uma linha no grid -->
                <div class="col-md-8 mb-3" style="margin:auto;"><!-- Criando a barra superior (Menu) -->
                    <!-- Cria barra de navegação -->
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <a class="navbar-brand" href="#">Cadastro de Alunos (MVC)</a>
                        <!-- Cria os links no menu -->
                        <div class="collapse navbar-collapse">
                            <!-- Cria a barra de navegação para os links -->
                            <div class="navbar-nav">
                                <a class="nav-item nav-link" href="view_novo.jsp">Novo</a>
                                <a class="nav-item nav-link"href="#">Pesquisa</a>
                                <a class="nav-item nav-link"href="AlunosController">Listar Todos</a>
                            </div>
                        </div>
                    </nav>