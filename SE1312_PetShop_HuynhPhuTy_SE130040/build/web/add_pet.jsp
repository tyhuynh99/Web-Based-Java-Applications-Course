<%-- 
    Document   : add_pet
    Created on : Jul 6, 2019, 2:16:52 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD PET</title>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT != null and sessionScope.ACCOUNT.getRole() eq 'admin'}" var="checkAccount">
            <a href="admin.jsp">Back to Home</a>
            <h1>ADD PET</h1>
            <form action="MainController" method="POST">
                Username
                <br/>
                <input type="text" name="username" value="${param.username}" required/>
                <br/>
                Password
                <br/>
                <input type="password" name="password" required/>
                <input type="submit" name="action" value="Check username" style="margin-left: 10px;"/>
                <br/>
                <font color="red">
                ${requestScope.INVALID}
                </font>
            </form>
            <c:if test="${not empty requestScope.SHOW}">
                <h2>Pet information</h2>
                <form action="MainController" method="POST">
                    Owner: <b>${param.username}</b> <br/>
                    <input type="hidden" name="username" value="${param.username}"/>
                    Pet ID<br/>
                    <input type="text" name="id" value="${param.id}" required/>
                    <br/>
                    Pet Name<br/>
                    <input type="text" name="name" value="${param.name}" required/>
                    <br/>
                    Age<br/>
                    <input type="number" name="age" min="1" max="10" value="${param.age}" required>
                    <br/>
                    Type<br/>
                    <select name="type">
                        <option <c:if test="${param.type eq 'Dog'}">selected</c:if> >Dog</option>
                        <option <c:if test="${param.type eq 'Cat'}">selected</c:if> >Cat</option>
                        </select>
                        <input type="submit" name="action" value="Add pet"/>
                    </form>
                    <font color="green">
                ${requestScope.SUCCESS}
                </font>
                <font color="red">
                ${requestScope.FAILED}
                </font>
            </c:if>
        </c:if>
        <c:if test="${!checkAccount}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </body>
</html>
