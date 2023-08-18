<%@ page import="java.util.*"%>
<%@ page import="by.pvt.multiproject.api.dto.*"%>
<%@ page import="by.pvt.multiproject.core.domain.*"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<b>Регистрирация клиента</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/servlet">
    <tаble>
        <tr>
            <td><B>Name</B></td>
            <td><input type=textbox name="name" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Surname</B></td>
            <td><input type=textbox name="surname" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Login</B></td>
            <td><input type=textbox name="login" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Password</B></td>
            <td><input type=textbox name="password" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Role</B></td>
            <td><input type=textbox name="role" size="25" value=" "></td>
        </tr>
    </tаble>
    <input type=submit value="Add">
</form>
</body>
</html>