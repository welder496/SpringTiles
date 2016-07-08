<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<link href="<c:url value="/static/resources/css/app.css"/>" rel="stylesheet"/>	
	<title>Mostrar Usuários</title>
</head>
<body>
<c:url var="firstUrl" value="/mostrarUsuarios/1" />
<c:url var="lastUrl" value="/mostrarUsuarios/${totalPages}" />
<c:url var="prevUrl" value="/mostrarUsuarios/${currentIndex-1}" />
<c:url var="nextUrl" value="/mostrarUsuarios/${currentIndex+1}" />

<div id="mainWrapper">
  <div class="container">
    <ul class="pagination">
    	<c:set var="usr" value="${usr}"/>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}?usuario=${usr}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}?usuario=${usr}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/mostrarUsuarios/${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}?usuario=${usr}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}?usuario=${usr}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}?usuario=${usr}">&gt;</a></li>
                <li><a href="${lastUrl}?usuario=${usr}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
      <c:choose>
            <c:when test="${usuarios.size()==0}">
                <em>Nenhum Usuário cadastrado</em>
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
                        <c:forEach items="${usuarios}" var="usuario">
                            <tr>
                                <td>${usuario.codigo}</td>
                                <td>${usuario.nome}</td>
                                <security:authorize access="hasRole('ROLE_ADMIN')">
	                                <spring:url var = "atualizar" value="/atualizarUsuario/${usuario.codigo}?usuario=${usr}"/>     
	                                <td><a href="${atualizar}">Alterar</a></td>
	                                <spring:url var = "excluir" value="/excluirUsuario/${usuario.codigo}?usuario=${usr}"/>     
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
        	<button class="btn btn-secondary"><a href="${index}?usuario=${usr}">Página Inicial</a></button>
        </div>        
  </div>
</div>  
</body>
</html>