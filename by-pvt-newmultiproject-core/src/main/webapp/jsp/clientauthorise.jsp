<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.core.api.dto.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Добро пожаловать</b>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/authorise">

    <input type=submit value="Authorise">
</form>
<br>

<c:out value= "${clientResponse}" />

</div>
</body>
</html>