<%@ page import="java.util.*"%>
<%@ page import="by.pvt.newmultiproject.api.dto.*"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html>
<body>
<div>
<b>Список всех клиентов</b>
<br>
<%
List clients = (List)request.getAttribute("clients");
        if(clients != null) {
        for(Object client: clients) {
        ClientResponse client1=(ClientResponse) client;
            out.println(client1.toString());
            }
        }
%>
</div>
</body>
</html>