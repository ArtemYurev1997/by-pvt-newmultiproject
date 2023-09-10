<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Добро пожаловать,  <c:out value = "${user.fullname}" /></b>
<br>
<form name="Form"
      method="get"
      action="http://localhost:8080/hello/products">
    <input type=submit value="Show all products">
</form>

<b>Создание нового заказа</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletcreateorder">
    <tаble>
        <tr>
            <td><B>Product Id</B></td>
            <td><input type=textbox name="productId" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Session Id</B></td>
            <td><input type=textbox name="sessionId" size="25" value=" "></td>
        </tr>
    </tаble>
        <input type=submit value="Create new order">
</form>

<form name="Form"
      method="get"
      action="http://localhost:8080/hello/logout">
    <input type=submit value="LogOut">
</form>

</div>
</body>
</html>