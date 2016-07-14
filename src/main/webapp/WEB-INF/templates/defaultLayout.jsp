<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title" /></title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/bootstrap.min.css"/>"/>
	<link href="<c:url value="/static/resources/css/app.css"/>" rel="stylesheet"/>		
</head>
  
<body>
  <div id="mainWrapper">
	  <div class="container">
	  	<div class="navbar-header">
	  		<div class="container">
	  			<tiles:insertAttribute name="header"/>
	  		</div>	
	  	</div>
	  	<div class="row">
	  		<div class="container">
  				<div class="col-md-2"><tiles:insertAttribute name="menu"/></div>
  				<div class="col-md-10"><tiles:insertAttribute name="body"/></div>
	  		</div>
	  	</div>
	  	<div class="navbar navbar-default navbar-fixed-bottom">
	  		<div class="container">
	  			<tiles:insertAttribute name="footer"/>
	  		</div>
	  	</div>
	  </div>
  </div>	  
</body>
</html>