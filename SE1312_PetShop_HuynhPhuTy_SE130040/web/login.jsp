<%-- 
    Document   : login
    Created on : Jul 3, 2019, 8:50:14 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN PAGE</title>
        <style>
            .login-form{
                max-width: 100%; 
                background-image: url(img/login-bg.jpg);
                background-repeat: repeat;
                background-attachment: fixed;
                height: 650px; 
                font-size: 30px; 
                text-align: center; 
            }
            .login-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 50px;
            }
            .btn_login{
                margin-top: 20px;
                border-radius: 5px;
                font-size: 20px;
                width: 100px;
            }


        </style>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT != null}" var="checkAccount">
            <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}" var="user">
                <c:redirect url="index.jsp"/>
            </c:if>
            <c:if test="${!user}">
                <c:redirect url="admin.jsp"/>
            </c:if>
        </c:if>
        <%@include file="petshop_header.jsp" %>
        <div class="login-form">
            <div>
                <br/>
                <label class="login-title"><b>LOGIN ACCOUNT</b></label>
                <form action="MainController" method="POST">
                    <font color="red">
                    ${requestScope.INVALID}
                    </font>
                    <br/>
                    <label style="color:#f2b500">Username</label><br/>
                    <input type="text" name="username" value="${param.username}"/>
                    <br/>
                    <label style="color:#f2b500">Password</label><br/>
                    <input type="password" name="password"/>
                    <br/>
                    <button class="btn_login" name="action" value="Login"><b>LOGIN</b></button>
                </form>
                <div style="margin-top: 10px; font-size: 18px;">
                    <a href="register.jsp?role=user">Register account</a>
                    <a href="#">| Forget password ?</a>
                </div>
            </div>
        </div>  
        <%@include file="petshop_footer.jsp" %>
    </body>
</html>
