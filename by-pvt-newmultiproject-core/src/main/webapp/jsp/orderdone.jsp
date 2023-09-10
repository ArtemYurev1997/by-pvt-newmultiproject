<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>




<html>
<body>
<div>
<b>Заказ сформирован!</b>
<br>
</br>
<c:out value = "${orderCheckout}" />

<br>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/authorise">

    <input type=submit value="Comeback">
</form>

</div>
</body>
</html>