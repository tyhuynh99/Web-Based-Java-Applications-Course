<%-- 
    Document   : account_management
    Created on : Jul 4, 2019, 2:31:00 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCOUNT MANAGEMENT</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 20px;
                background-color: rgb(227, 240, 244);
            }
            a{
                text-decoration: none;

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
            .register-subtitle{
                color: green; 
                font-size: 20px;
                font-weight: bold;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT == null}" var="checkAccount">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}" var="checkRole">
            <c:redirect url="index.jsp"/>
        </c:if>
        <c:if test="${!checkRole}">
            <div style="width: 100%; text-align: center">
                <label class="shopping-title"><b>ACCOUNT MANAGEMENT</b></label><br/>
            </div>
            <!--User-->
            <label class="register-subtitle">
                USER ACCOUNTS
            </label>
            <h5>
                <font color="red">
                ${requestScope.DELETE_RESULT}
                </font>
            </h5>
            <c:if test="${not empty requestScope.LIST_USER}" var="checkListUser">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Birthdate</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST_USER}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td>${dto.birthdate}</td>
                                <td>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="username" value="${dto.username}"/>
                                        <input type="submit" name="action" value="Delete"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!checkListUser}">
                <font color="red">
                <h4>Sorry, we do not have any customer account</h4>
                </font>
            </c:if>
            <hr/>
            <!--Admin-->
            <label class="register-subtitle">
                ADMIN ACCOUNTS
            </label>
            <c:if test="${not empty requestScope.LIST_ADMIN}" var="checkListAdmin">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Birthdate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST_ADMIN}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td>${dto.birthdate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!checkListAdmin}">
                <font color="red">
                <h4>Sorry, we do not have any admin account</h4>
                </font>
            </c:if>
        </c:if>
        <a href="admin.jsp" >Back to home</a>
    </body>
</html>
