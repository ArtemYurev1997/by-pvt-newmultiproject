<%@ page import="java.util.*"%>
<%@ page import="by.pvt.multiproject.api.dto.*"%>
<%@ page import="by.pvt.multiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Заказы пользователя</b>
<br>
<li>
<c:forEach var="orders" items="${order}">
<c:out value="${orders}" />
</li>
</c:forEach>


</div>
</body>
</html>