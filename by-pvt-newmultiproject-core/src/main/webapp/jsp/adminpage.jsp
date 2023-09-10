<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page import="by.pvt.newmultiproject.core.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
Добро пожаловать,  <c:out value = "${user.fullname}" />
</div>
<br>
<b>Добавление товара</b>
</br>

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
</br>
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
</br>
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
</br>
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

<form name="Form"
      method="get"
      action="http://localhost:8080/hello/clientgetall">
    <input type=submit value="Show clients">
</form>

<br>
<b>Удаление пользователя</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/clientdelete">
      <tаble>
              <tr>
                  <td><B>Id User</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Delete user">
</form>



<br>
<b>Получение пользователя по id</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/clientid">
      <tаble>
              <tr>
                  <td><B>Id User</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Enter">
</form>


<br>
<b>Обновление пользователя</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/clientupdate">
       <tаble>
               <tr>
                    <td><B>Id</B></td>
                    <td><input type=textbox name="id" size="20" value=" "></td>
               </tr>
               <tr>
                    <td><B>Name</B></td>
                    <td><input type=textbox name="name" size="20" value=" "></td>
               </tr>
               <tr>
                    <td><B>Surname</B></td>
                    <td><input type=textbox name="surname" size="20" value=" "></td>
               </tr>
               <tr>
                    <td><B>Login</B></td>
                    <td><input type=textbox name="login" size="20" value=" "></td>
               </tr>
               <tr>
                    <td><B>Password</B></td>
                    <td><input type=textbox name="password" size="20" value=" "></td>
               </tr>
               <tr>
                    <td><B>Role</B></td>
                    <td><input type=textbox name="role" size="20" value=" "></td>
               </tr>
          </tаble>
    <input type=submit value="Update user">
</form>

<br>
<b>Добавление заказа</b>
</br>
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

<form name="Form"
      method="get"
      action="http://localhost:8080/hello/orderservletadd">
    <input type=submit value="All Orders">
</form>

<br>
<b>Удаление заказа</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletdelete">
      <tаble>
              <tr>
                  <td><B>Id Order</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Delete order">
</form>

<br>
<b>Получение заказа по id</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletfindbyid">
      <tаble>
              <tr>
                  <td><B>Id Order</B></td>
                  <td><input type=textbox name="id" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Enter">
</form>

<br>
<b>Обновление заказа</b>
<br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletupdate">
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
    <input type=submit value="Update order">
</form>


<br>
<b>Получение информации из корзины по Id заказа</b>
</br>
<form name="Form"
      method="post"
      action="http://localhost:8080/hello/orderservletfindbyorderid">
      <tаble>
              <tr>
                  <td><B>Id Order</B></td>
                  <td><input type=textbox name="orderId" size="25" value=" "></td>
              </tr>
      </tаble>
    <input type=submit value="Enter">
</form>

<br>
</br>
<form name="Form"
      method="get"
      action="http://localhost:8080/hello/logout">
    <input type=submit value="LogOut">
</form>
</body>
</html>