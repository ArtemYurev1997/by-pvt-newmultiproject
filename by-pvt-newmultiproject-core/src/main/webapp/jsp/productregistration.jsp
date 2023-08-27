<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<b>Добавление товара</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/productadd">
    <tаble>
        <tr>
            <td><B>Name</B></td>
            <td><input type=textbox name="name" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Type</B></td>
            <td><input type=textbox name="type" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Code</B></td>
            <td><input type=textbox name="code" size="25" value=" "></td>
        </tr>
        <tr>
            <td><B>Price</B></td>
            <td><input type=textbox name="price" size="25" value=" "></td>
        </tr>
    </tаble>
    <input type=submit value="Add">
</form>


<form name="Form"
      method="get"
      action="http://localhost:8080/hello/productadd">
    <input type=submit value="Show all products">
</form>

<br>

<b>Удаление товара</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/productdelete">
      <tаble>
              <tr>
                  <td><B>Id Product</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Delete product">
</form>



<br>
<b>Получение товара по id</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/productid">
      <tаble>
              <tr>
                  <td><B>Id Product</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Enter">
</form>


<br>
<b>Обновление товара</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/productupdate">
       <tаble>
              <tr>
                   <td><B>Id</B></td>
                   <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
              <tr>
                  <td><B>Name</B></td>
                  <td><input type=textbox name="name" size="25" value=" "></td>
              </tr>
              <tr>
                  <td><B>Type</B></td>
                  <td><input type=textbox name="type" size="25" value=" "></td>
              </tr>
              <tr>
                  <td><B>Code</B></td>
                  <td><input type=textbox name="code" size="25" value=" "></td>
              </tr>
              <tr>
                  <td><B>Price</B></td>
                  <td><input type=textbox name="price" size="25" value=" "></td>
              </tr>
          </tаble>
    <input type=submit value="Update">
</form>
</body>
</html>