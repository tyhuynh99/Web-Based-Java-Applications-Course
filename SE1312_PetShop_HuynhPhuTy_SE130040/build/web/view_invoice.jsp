<%-- 
    Document   : view_invoice.jsp
    Created on : Jul 14, 2019, 6:14:26 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <c:if  test="${sessionScope.ACCOUNT.getRole() != 'admin'}" >
            <c:redirect url="login.jsp"/>
        </c:if>
        <label class="shopping-title"><b>SEARCH INVOICE</b></label>
        <br/><a href="admin.jsp" >Back to home</a>
        <div>
            <form action="MainController" method="POST">
                ${requestScope.MESS}<br/>
                <label>Input username:</label>
                <input type="text" name="username" value="${param.username}"/>
                <button name="action" value="Search Invoice by username">Search</button>
            </form>
        </div>
        <div>
            <form action="MainController" method="POST">
                <label>Input date (yyyy-MM-dd):</label>
                <input type="text" name="date_search" value="${param.date_search}"/>
                <button name="action" value="Search Invoice by username">Search</button>
            </form>
        </div>
        <hr/>
        <c:if test="${not empty requestScope.INVOICE}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Order ID</th>
                        <th>Username</th>
                        <th>Date</th>
                        <th>Payment ID</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.INVOICE}" var="invoiceDto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${invoiceDto.getId()}</td>
                            <td>${invoiceDto.getUsername()}</td>
                            <td>${invoiceDto.getDateBook()}</td>
                            <td>${invoiceDto.getPaymentId()}</td>
                            <td>${invoiceDto.getPhoneNo()}</td>
                            <td>${invoiceDto.getAddr()}</td>
                            <td>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="orderId" value="${invoiceDto.getId()}"/>
                                    <input type="hidden" name="username" value="${param.username}"/>
                                    <input type="hidden" name="date_search" value="${param.date_search}"/>
                                    <button name="action" value="Show Invoice Detail">View Details</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <hr/>
        <c:if test="${not empty requestScope.ACC_LIST}">
            <label class="register-subtitle">Accessory Invoice List</label>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Accessory Id</th>
                        <th>Pet Id</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.ACC_LIST}" var="accDto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${accDto.getAccId()}</td>
                            <td>${accDto.getPetId()}</td>
                            <td>${accDto.getQuantity()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty requestScope.SER_LIST}">
            <label class="register-subtitle">Service Invoice List</label>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Service Id</th>
                        <th>Pet Id</th>
                        <th>Slot</th>
                        <th>Date Book</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.SER_LIST}" var="accDto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${accDto.getServiceId()}</td>
                            <td>${accDto.getPetId()}</td>
                            <td>${accDto.getSlot()}</td>
                            <td>${accDto.getDateBook()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
