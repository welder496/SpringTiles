<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<title>Todos os convidados</title>
</head>
<body>
<c:url var="firstUrl" value="/mostrartodos/1" />
<c:url var="lastUrl" value="/mostrartodos/${convidadosServ.totalPages}" />
<c:url var="prevUrl" value="/mostrartodos/${currentIndex-1}" />
<c:url var="nextUrl" value="/mostrartodos/${currentIndex+1}" />

<div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/mostrartodos/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == convidadosServ.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
      <c:choose>
            <c:when test="${convidados.size()==0}">
                <em>Nenhum convidado cadastrado</em>
            </c:when>
            <c:otherwise>
                <table id="membersTable">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nome</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${convidados}" var="convidado">
                            <tr>
                                <td>${convidado.codigo}</td>
                                <td>${convidado.nome}</td>
                                <spring:url var = "atualizar" value="/atualizar/${convidado.codigo}"/>     
                                <td><a href="${atualizar}">Alterar</a></td>
                                <spring:url var = "excluir" value="/excluir/${convidado.codigo}"/>     
                                <td><a href="${excluir}">Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <div>
            <spring:url var = "index" value="/index"/>        	
        	<button><a href="${index}">Página Inicial</a></button>
        </div>        
</div>
</body>
</html>