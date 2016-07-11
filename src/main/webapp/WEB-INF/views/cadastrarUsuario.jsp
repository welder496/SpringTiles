<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<link href="<c:url value="/static/resources/css/app.css"/>" rel="stylesheet"/>	
	<title>Cadastrar Usuários</title>
</head>
<body>
<div id="mainWrapper">
	<div class="container">
  		<spring:url var = "action" value="/cadastrarUsuario"/>
  		<h3> Cadastrar Usuários</h3>
  		<form:form action="${action}?usuario=${usr}" modelAttribute="usuario" method="POST">
			<div class="row">
				<fieldset class="form-group col-sm-4">
  					<form:label path="nome">Nome</form:label>
  					<form:input type="text" path="nome" class="form-control"/>
  					<form:errors path="nome" cssStyle="color:red;"/>
  				</fieldset>
  			</div>
  			<div class="row">
  				<fieldset class="form-group col-sm-4">
  					<form:label path="username">Usuário</form:label>
  					<form:input type="text" path="username" class="form-control"/>
  					<form:errors path="username" cssStyle="color:red;"/>
  				</fieldset>
  			</div>
  			<div class="row">	
  				<fieldset class="form-group col-sm-4">
  					<form:label path="password">Senha</form:label>
  					<form:input type="password" path="password" class="form-control"/>
  					<form:errors path="password" cssStyle="color:red;"/>
  				</fieldset>
  			</div>
  			<div class="row">
  				<fieldset class="form-group col-sm-4">
  					<form:label path="email">E-mail</form:label>
  					<form:input type="text" path="email" class="form-control"/>
   					<form:errors path="email" cssStyle="color:red;"/>
  				</fieldset>
  			</div>
  			<div class="row">
  				<fieldset class="form-group col-sm-4">
  					<form:label path="regra.codigo">Regra</form:label>
 					<form:select path="regra.codigo" class="form-control">
  						<form:options items="${regras}"/>
  					</form:select>
  					<form:errors path="regra" cssStyle="color:red;"/>
  				</fieldset>
  			</div>	
  			<div class="row">
  				<fieldset class="form-group col-sm-4">
  					<form:checkbox path="enabled" value="enabled"/>Habilitado
  				</fieldset>
  			</div>
			<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
			<input type="submit" value="Enviar" class="btn btn-secondary"/>
  		</form:form>
	</div> 
</div> 
</body>
</html>