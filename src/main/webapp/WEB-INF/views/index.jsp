<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<link href="<c:url value="/static/resources/css/app.css"/>" rel="stylesheet"/>		
	<title>Todos os convidados</title>
</head>
<body>
<div id="mainWrapper">
	<div class="container">
        <h3>Bem vindo: ${usuario}</h3>
 		<button class="btn btn-secondary">
 			<spring:url var = "mostrarConvidados" value="/mostrartodos/1?usuario=${usuario}"/>
 			<a href="${mostrarConvidados}">Todos os cadastrados</a>
		</button>
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<button class="btn btn-secondary">
 			<spring:url var = "cadastrarConvidado" value="/cadastrar/?usuario=${usuario}"/>
 			<a href="${cadastrarConvidado}">Cadastrar convidados</a>
		</button>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<button class="btn btn-secondary">
			<spring:url var = "cadastrarUsuario" value="/cadastrarUsuario/?usuario=${usuario}"/>
			<a href="${cadastrarUsuario}">Cadastrar usuário</a>
		</button>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<button class="btn btn-secondary">
			<spring:url var = "mostrarUsuarios" value="/mostrarUsuarios/1?usuario=${usuario}"/>
			<a href="${mostrarUsuarios}">Mostrar usuários</a>
		</button>
		</security:authorize>
		<button class="btn btn-secondary">
 			<spring:url var = "logout" value="/logout"/>
 			<a href="${logout}">Sair</a>
		</button>
	</div>
</div>
</body>
</html>
