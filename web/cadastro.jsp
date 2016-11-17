<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de contas</title>
    </head>
    <body>
        <h1>Cadastro de contas</h1>
        <form method="POST" action="salvar">
            <c:if test="%{#parameters.conta == null}">
                Código: <input type="text" name="codigo" disabled="true" value="${conta.idconta}"><br>
            </c:if>
            Descrição: <input type="text" name="descricao" value="${conta.descricao}"><br>
            Valor: <input type="text" name="valor" value="${conta.valor}"><br>
            Data de vencimento <input type="date" name="data_vencimento" value="${conta.data_vencimento}"><br>
            <button>Salvar</button>
        </form>
        <br/>
        <br/>
        <hr>
        <h1>Contas pendentes</h1>
        <table border="1">
            <tr>
                <th id="codigo" >Código</th>
                <th id="descricao">Descrição</th>
                <th id="Valor">Valor</th>
                <th id="dataVencimento">Data de Vencimento</th>
                <th>Ações</th>
            <tr>    
            <c:forEach var="pg" items="${listaPagar}" >
                <tr>
                    <td>${pg.idconta}</td>
                    <td>${pg.descricao}</td>
                    <td>R$ ${pg.valor}</td>
                    <td>${pg.data_vencimento}</td>
                    <td>
                        <a href="editar?codigo=${pg.idconta}">Editar</a> - 
                        <a href="pagar?codigo=${pg.idconta}">Pagar</a> - 
                        <a href="remover?codigo=${pg.idconta}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <hr>
        <h1>Contas Pagas</h1>
        <table border="1">
            <tr>
                <th id="codigo" >Código</th>
                <th id="descricao">Descrição</th>
                <th id="Valor">Valor</th>
                <th id="dataVencimento">Data de Vencimento</th>
                <th id="dataVencimento">Data de Pagamento</th>
                <th>Ações</th>
            <tr>    
            <c:forEach var="pg" items="${listaPago}" >
                <tr>
                    <td>${pg.idconta}</td>
                    <td>${pg.descricao}</td>
                    <td>R$ ${pg.valor}</td>
                    <td>${pg.data_vencimento}</td>
                    <td>${pg.data_pagamento}</td>
                    <td>
                        <a href="remover?codigo=${pg.idconta}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <a href="listar">Voltar</a> <br>
    </body>
</html>
