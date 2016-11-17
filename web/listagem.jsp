<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Ações</th>
            <tr>    
            <c:forEach var="e" items="${lista}" >
                <tr>
                    <td>${e.idconta}</td>
                    <td>${e.descricao}</td>
                    <td>${e.valor}%</td>
                    <td>${e.data_vencimento}</td>
                    <td>
                        <a href="editar?codigo=${e.idconta}">Editar</a> - 
                        <a href="remover?codigo=${e.idconta}">Excluir</a> - 
                        <a href="concluir?codigo=${e.idconta}">Concluir</a> - 
                        <a href="notas?codigo=${e.idconta}">Notas</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
