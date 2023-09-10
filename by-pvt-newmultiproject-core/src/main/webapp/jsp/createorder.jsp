<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Новый заказ успешно создан!</b>
<br>
</br>
<c:out value = "${newOrder}" />

<br>
</br>
<b>Оформить заказ</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletcheckout">
    <tаble>
        <tr>
            <td><B>Id Order</B></td>
            <td><input type=textbox name="orderId" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Count</B></td>
            <td><input type=textbox name="count" size="25" value=" "></td>
        </tr>
    </tаble>
        <input type=submit value="Checkout">
</form>

</div>
</body>
</html>