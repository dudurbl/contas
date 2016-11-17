<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem</title>
    </head>
    <body>
        <a href="novo">Nova atividade</a> <br>
        <hr>
        <table border="1">
            <tr>
                <th id="codigo" >Código</th>
                <th id="descricao">Descrição</th>
                <th id="valor">Valor</th>
                <th id="datavencimento">Data de vencimento</th>
                <th id="datapagamento">Data de Pagamento</th>
                <th>Ações</th>
            <tr>    
            <c:forEach var="e" items="${lista}" >
                <tr>
                    <td>${e.idconta}</td>
                    <td>${e.descricao}</td>
                    <td>R$ ${e.valor}</td>
                    <td>${e.data_vencimento}</td>
                    <c:choose>
                        <c:when test="${empty e.data_pagamento}">
                         <td>Há pagar</td>   
                        </c:when>
                        <c:otherwise>
                        <td>${e.data_vencimento}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${empty e.data_pagamento}">
                        <a href="editar?codigo=${e.idconta}">Editar</a> - 
                        <a href="pagar?codigo=${e.idconta}">Pagar</a> -
                        </c:if>
                        <a href="remover?codigo=${e.idconta}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
