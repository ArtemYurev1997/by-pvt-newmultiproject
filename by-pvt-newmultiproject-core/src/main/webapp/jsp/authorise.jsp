<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Авторизация клиента</b>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/authorise">
    <tаble>
            <tr>
                <td><B>Login</B></td>
                <td><input type=textbox name="login" size="25" value=""></td>
            </tr>
            <tr>
                <td><B>Password</B></td>
                <td><input type=textbox name="password" size="25" value=""></td>
            </tr>
    </tаble>
        <input type=submit value="Enter">
<br>
<div>
</body>
</html>