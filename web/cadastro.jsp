<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de contas</title>
    </head>
    <body>
        <h1>Cadastro de contas</h1>
        <form method="POST" action="salvar">
            Código: <input type="text" name="codigo"><br>
            Descrição: <input type="text" name="descricao" ><br>
            Data de vencimento <input type="text" name="data_vencimento"><br>
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
            <c:forEach var="f" items="${lista}" >
                <tr>
                    <td>${f.codigo}</td>
                    <td>${f.descricao}</td>
                    <td>${f.valor}%</td>
                    <td>${f.dataVencimento}</td>
                    <td>
                        <a href="editar?codigo=${f.codigo}">Editar</a> - 
                        <a href="editar?codigo=${f.codigo}">Pagar</a> - 
                        <a href="remover?codigo=${f.codigo}">Excluir</a>
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
            <c:forEach var="f" items="${lista}" >
                <tr>
                    <td>${pg.codigo}</td>
                    <td>${pg.descricao}</td>
                    <td>${pg.valor}%</td>
                    <td>${pg.dataVencimento}</td>
                    <td>${pg.dataPagamento}</td>
                    <td>
                        <a href="remover?codigo=${pg.codigo}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <a href="listar">Voltar</a> <br>
    </body>
</html>
