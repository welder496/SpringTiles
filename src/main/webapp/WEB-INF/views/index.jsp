<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<h2>Spring Basic Application</h2>
<div>
<button>
 <spring:url var = "mostrartodos" value="/mostrartodos/1"/>
 <a href="${mostrartodos}">Todos os cadastrados</a>
</button>
<button>
 <spring:url var = "cadastrar" value="/cadastrar"/>
 <a href="${cadastrar}">Cadastrar convidados</a>
</button>
</div>
</body>
</html>
