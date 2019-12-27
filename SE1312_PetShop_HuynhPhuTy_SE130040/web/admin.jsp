<%-- 
    Document   : admin
    Created on : Jul 3, 2019, 9:54:38 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN PAGE</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 20px;
                text-align: center;
                background-color: rgb(227, 240, 244);
                zoom: 180%;
            }
            a{
                text-decoration: none;
                font-size: 16px;
            }
            .shopping-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 50px;
                width: 100%;
            }
            .btn_addcart{
                border-radius: 5px;
                padding: 5px;
                background-color: #f2b500; 
                color: black;
            }
        </style>
    </head>
    <body>
        <c:if var="checkAccount" test="${sessionScope.ACCOUNT == null}" >
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${!checkAccount}">
            <c:if test="${sessionScope.ACCOUNT.getRole() eq 'admin'}" var="admin">
                <div style="width: 100%; text-align: center">
                    <label class="shopping-title"><b>ADMIN PAGE</b></label><br/>
                </div>
                <h3>Hello, ${sessionScope.ACCOUNT.getFullname()}</h3>
                <form action="MainController" method="POST">
                    <input class="btn_addcart" type="submit" name="action" value="Logout"/>
                    <input class="btn_addcart" type="submit" name="action" value="View Account"/>
                    <input class="btn_addcart" type="submit" name="action" value="Manage Account"/>
                    <input class="btn_addcart" type="submit" name="action" value="Manage Accessory"/>
                    <input class="btn_addcart" type="submit" name="action" value="Manage Service"/>
                </form>
                <a href="register.jsp?insert_role=admin">Register Admin</a>
                |
                <a href="add_pet.jsp">Add Pet</a>
                |
                <a href="view_invoice.jsp">View Invoice</a>
            </c:if>
            <c:if test="${!admin}">
                <c:redirect url="index.jsp"/>
            </c:if>
        </c:if>
    </body>
</html>
