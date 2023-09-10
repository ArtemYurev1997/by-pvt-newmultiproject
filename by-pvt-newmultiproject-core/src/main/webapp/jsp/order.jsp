<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<b>Добавление заказа</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletadd">
    <tаble>
        <tr>
            <td><B>Id</B></td>
            <td><input type=textbox name="id" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>User Id</B></td>
            <td><input type=textbox name="userId" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Costs</B></td>
            <td><input type=textbox name="costs" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Status</B></td>
            <td><input type=textbox name="status" size="25" value=" "></td>
        </tr>
    </tаble>
    <input type=submit value="Add">

</form>
</body>
</html>