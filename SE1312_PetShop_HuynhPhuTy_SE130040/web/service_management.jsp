<%-- 
    Document   : service_management
    Created on : Jul 13, 2019, 5:01:55 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 16px;
                text-align: left;
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
        <h1>SERVICE MANAGEMENT</h1>
        <label>
            ${requestScope.MESS}<br/>
        </label>
        <table>
            <thead>
                <tr>
                    <th>
                        <c:if test="${not empty requestScope.SER_LIST}" var="checkList">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Slot</th>
                                        <th>a</th>
                                        <th>a</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.SER_LIST}" var="dto" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${dto.getId()}</td>
                                            <td>${dto.getName()}</td>
                                            <td>${dto.getPrice()}</td>
                                            <td>${dto.getSlot()}</td>
                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.getId()}"/>
                                                    <button name="action" value="Edit Service">
                                                        Edit
                                                    </button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.getId()}"/>
                                                    <button name="action" value="Delete Service">
                                                        Delete
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

                    </th>
                    <th style="display: <c:if test="${param.action != 'Edit Service' and param.action != 'Update Service'}">none</c:if>">
                            <form action="MainController"  method="POST">
                                <label>Service ID</label><br/>
                                <input type="text" name="up_id" value="${requestScope.DTO.getId()}" readonly/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getId()}</label>
                            <br/>
                            <label>Service Name</label><br/>
                            <input type="text" name="up_name" value="${requestScope.DTO.getName()}" required/>
                            <br/>
                            <label>Price</label><br/>
                            <input type="text" name="up_price" value="${requestScope.DTO.getPrice()}" required/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getPrice()}</label>
                            <br/>
                            <label>Slot</label>
                            <br/>
                            <input type="text" name="up_quantity" value="${requestScope.DTO.getSlot()}" required/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getQuantity()}</label>
                            <br/>
                            <button name="action" value="Update Service"> Update </button>
                        </form>
                    </th>
                </tr>
            </thead>
        </table>
        <c:if test="${!checkList}">
            <label>No service in stock</label>
        </c:if>
        <form action="MainController" method="POST">
            <label>Service ID</label><br/>
            <input type="text" name="insert_id" value="${param.insert_id}" required/>
            <label class="err-mess">${requestScope.INVALID.getId()}</label>
            <br/>
            <label>Service Name</label><br/>
            <input type="text" name="name" value="${param.name}" required/>
            <br/>
            <label>Price</label><br/>
            <input type="text" name="price" value="${param.price}" required/>
            <label class="err-mess">${requestScope.INVALID.getPrice()}</label>
            <br/>
            <label>Slot</label>
            <br/>
            <input type="text" name="quantity" value="${param.quantity}" required/>
            <label class="err-mess">${requestScope.INVALID.getQuantity()}</label>
            <br/>
            <button name="action" value="Insert Service"> Insert </button>
        </form>
    </body>
</html>
