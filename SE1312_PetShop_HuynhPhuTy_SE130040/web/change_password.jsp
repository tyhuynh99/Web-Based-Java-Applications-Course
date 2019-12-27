<%-- 
    Document   : change_password
    Created on : Jul 4, 2019, 11:23:36 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHANGE PASSWORD</title>
        <style>
            a{
                text-decoration: none;
                font-size: 16px;
            }
            body{
                margin: 0;
                padding: 0;
                font-size: 20px;
                text-align: center; 
                background-color: rgb(227, 240, 244);
                zoom: 180%;
            }
            .shopping-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 50px;
                width: 100%;
            }
            .infor-form{
                margin-left: 20px;
                text-align: left;
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
        <c:if test="${not empty sessionScope.ACCOUNT}" var="checkAccount">
            <div style="width: 100%; text-align: center">
                <label class="shopping-title"><b>CHANGE PASSWORD</b></label><br/>
            </div>
            <font color="green">
            *: Must be fill in these fields
            </font>
            <form action="MainController" method="POST">
                Input password*<br/>
                <input type="password" name="password" value="${param.password}" required />
                <br/>Input new password*<br/>
                <input type="password" name="newPassword"  required />
                <br/>Confirm new password*<br/>
                <input type="password" name="confirm"  required />
                <br/>
                <input class="btn_addcart" type="submit" name="action" value="Change password" style="margin-top: 10px;"/>
            </form>
            <a href="ViewAccountInfoController">Back</a><br/>
            <font color="red">
            ${requestScope.INVALID}
            </font>
        </c:if>
        <c:if test="${!checkAccount}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </body>
</html>
