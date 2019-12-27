<%-- 
    Document   : accessory_manage
    Created on : Jul 13, 2019, 2:07:06 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCESSORY MANAGEMENT</title>
    </head>
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
    <body>
        <c:if test="${sessionScope.ACCOUNT == null}" var="checkAccount">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}" var="checkRole">
            <c:redirect url="index.jsp"/>
        </c:if>
        <div style="width: 100%; text-align: center">
            <label class="shopping-title"><b>ACCESSORY MANAGEMENT</b></label><br/>
        </div>
        <label>
            ${requestScope.MESS}<br/>
        </label>
        <a href="admin.jsp" >Back to home</a>
        <table>
            <thead>
                <tr>
                    <th><c:if test="${not empty requestScope.ACC_LIST}" var="checkList">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Description</th>
                                        <th>Quantity</th>
                                        <th></th>
                                        <th></th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.ACC_LIST}" var="dto" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${dto.getId()}</td>
                                            <td>${dto.getName()}</td>
                                            <td>${dto.getPrice()}</td>
                                            <td>${dto.getDes()}</td>
                                            <td>${dto.getQuantity()}</td>
                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.getId()}"/>
                                                    <button name="action" value="Edit Accessory">
                                                        Edit
                                                    </button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="id" value="${dto.getId()}"/>
                                                    <button name="action" value="Delete Accessory">
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
                    <th style="display: <c:if test="${param.action != 'Edit Accessory' and param.action != 'Update Accessory'}">none</c:if>" >
                            <form action="MainController" method="POST">
                                <label>Accessory ID</label><br/>
                                <input type="text" name="up_id" value="${requestScope.DTO.getId()}" readonly/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getId()}</label>
                            <br/>
                            <label>Accessory Name</label><br/>
                            <input type="text" name="up_name" value="${requestScope.DTO.getName()}" required/>
                            <br/>
                            <label>Price</label><br/>
                            <input type="text" name="up_price" value="${requestScope.DTO.getPrice()}" required/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getPrice()}</label>
                            <br/>
                            <label>Description</label><br/>
                            <input type="text" name="up_des" value="${requestScope.DTO.getDes()}" required/><br/>
                            <label>Quantity</label>
                            <br/>
                            <input type="text" name="up_quantity" value="${requestScope.DTO.getQuantity()}" required/>
                            <label class="err-mess">${requestScope.INVALID_UPDATE.getQuantity()}</label>
                            <br/>
                            <button name="action" value="Update Accessory"> Update </button>
                        </form>
                    </th>
                </tr>
            </thead>
        </table>


        <c:if test="${!checkList}">
            <label>No accessory in stock</label>
        </c:if>
        <form action="MainController" method="POST">
            <label>Accessory ID</label><br/>
            <input type="text" name="insert_id" value="${param.insert_id}" required/>
            <label class="err-mess">${requestScope.INVALID.getId()}</label>
            <br/>
            <label>Accessory Name</label><br/>
            <input type="text" name="name" value="${param.name}" required/>
            <br/>
            <label>Price</label><br/>
            <input type="text" name="price" value="${param.price}" required/>
            <label class="err-mess">${requestScope.INVALID.getPrice()}</label>
            <br/>
            <label>Description</label><br/>
            <input type="text" name="des" value="${param.des}" required/><br/>
            <label>Quantity</label>
            <br/>
            <input type="text" name="quantity" value="${param.quantity}" required/>
            <label class="err-mess">${requestScope.INVALID.getQuantity()}</label>
            <br/>
            <button name="action" value="Insert Accessory"> Insert </button>
        </form>
    </body>
</html>
