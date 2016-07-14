<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<link href="<c:url value="/static/resources/css/app.css"/>" rel="stylesheet"/>	
	<title>Todos os convidados</title>
</head>
<body>
<c:url var="firstUrl" value="/mostrarConvidados/1" />
<c:url var="lastUrl" value="/mostrarConvidados/${totalPages}" />
<c:url var="prevUrl" value="/mostrarConvidados/${currentIndex-1}" />
<c:url var="nextUrl" value="/mostrarConvidados/${currentIndex+1}" />

    <ul class="pagination">
    	<c:set var="usuario" value="${usuario}"/>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}?usuario=${usuario}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}?usuario=${usuario}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/mostrarConvidados/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}?usuario=${usuario}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}?usuario=${usuario}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}?usuario=${usuario}">&gt;</a></li>
                <li><a href="${lastUrl}?usuario=${usuario}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
      <c:choose>
            <c:when test="${convidados.size()==0}">
                <em>Nenhum Convidado cadastrado</em>
            </c:when>
            <c:otherwise>
                <table class="table table-hover">
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
                                <security:authorize access="hasRole('ROLE_ADMIN')">
	                                <spring:url var = "atualizar" value="/atualizarConvidado/${convidado.codigo}?usuario=${usuario}"/>     
	                                <td><a href="${atualizar}">Alterar</a></td>
	                                <spring:url var = "excluir" value="/excluirConvidado/${convidado.codigo}?usuario=${usuario}"/>     
	                                <td><a href="${excluir}">Excluir</a></td>
	                            </security:authorize>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <div>
            <spring:url var = "index" value="/index"/>        	
        	<button class="btn btn-secondary"><a href="${index}?usuario=${usuario}">Página Inicial</a></button>
        </div>        

</body>
</html>