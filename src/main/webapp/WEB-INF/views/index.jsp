<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<h2>Spring Basic Application</h2>
		<button class="btn btn-secondary">
 			<spring:url var = "mostrartodos" value="/mostrartodos/1"/>
 			<a href="${mostrartodos}">Todos os cadastrados</a>
		</button>
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<button class="btn btn-secondary">
 			<spring:url var = "cadastrar" value="/cadastrar"/>
 			<a href="${cadastrar}">Cadastrar convidados</a>
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
