<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Корзина</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/bucketservletadd">
<tаble>
      <tr>
         <td><B>Order Id</B></td>
         <td><input type=textbox name="id" size="25" value=" "></td>
      </tr>
</table>
    <input type=submit value="Make the order">
</form>

</div>
</body>
</html>