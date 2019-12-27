<%-- 
    Document   : service_book
    Created on : Jul 13, 2019, 8:37:13 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .login-form{
            text-align: center;
            width: 100%;
            background-image: url(img/login-bg.jpg);
            background-repeat: repeat;
            background-attachment: fixed;
            height: auto; 
            font-size: 30px; 
            padding-top: 100px;
            padding-bottom: 30px;
        }
        .abc{
            margin-top: 50px;
            padding-top: 5px;
            border-radius: 5px;
            padding-left: 10px;
            padding-right: 2px;
            margin-left: 50px;
            background-color:#c99404;
            max-width: 300px;
        }
        .register-subtitle{
            color:white; 
            font-size: 16px;
            font-weight: bold;

        }
        .service_card{
            font-size: 18px;
            text-align: center;
            width: 300px;
            height: auto;
            background-color: #c99404;
            margin: 10px;
            display: inline-block;
            padding: 5px;
            border-radius: 5px;
            color:white;
        }
        .btn_login{
            border-color: transparent;
            margin-top: 20px;
            border-radius: 5px;
            font-size: 20px;
            width: 250px;
        }
    </style>
    <body>
        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'admin'}">
            <c:redirect url="admin.jsp"/>
        </c:if>
        <c:if test="${sessionScope.ACCOUNT == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <div>
            <%@include file="petshop_header.jsp" %>
        </div>
        <div class="login-form">
            <div>
                <font color="red">
                ${requestScope.INVALID_DATE}
                ${requestScope.MESS}
                </font>
                <form class="form-inline abc" action="SearchServiceController" method="POST">
                    <label class="register-subtitle" >SEARCH DATE TO BOOK SERVICE</label>
                    <input class="form-control form-control-sm mr-3 w-75" 
                           type="date" 
                           placeholder="Search date"
                           aria-label="Search" 
                           required
                           name="date" value="${param.date}"/>
                    <button style="border: none; background-color: transparent; height: auto; width: auto">
                        <i class="fas fa-search" style="color: white" aria-hidden="true"></i>
                    </button>
                </form>
            </div>
            <div>
                <c:forEach items="${requestScope.SERVICE_LIST}" var="dto">
                    <div class="service_card">
                        Service: ${dto.getName()}<br/>
                        Price: ${dto.getPrice()}<br/>
                        <form action="MainController" method="POST">
                            <c:if test="${dto.isStatus() eq 'true'}" var="checkSlot">
                                Slot: 
                                <select name="slot">
                                    <c:forEach items="${dto.getListSlot()}" var="slotDto">
                                        <c:if test="${slotDto.isStatus() eq 'true'}">
                                            <option value="${slotDto.getSlot()}">${slotDto.getSlot()}</option>
                                        </c:if>
                                    </c:forEach>
                                </select><br/>
                                <c:if test="${not empty requestScope.LIST_PET }" var="checkPet">
                                    Choose your pet:
                                    <select name="petId">
                                        <c:forEach items="${requestScope.LIST_PET}" var="petDto">
                                            <option value="${petDto.getId()}">
                                                ${petDto.getName()}
                                            </option>
                                        </c:forEach>
                                    </select><br/>
                                    <input type="hidden" name="date" value="${param.date}"/>
                                    <input type="hidden" name="serId" value="${dto.getId()}"/>
                                    <button class="btn_login" name="action" value="Add to service cart">Add to cart</button>
                                </c:if>
                            </form>
                            <c:if test="${!checkPet}">
                                Please add pet before shopping
                            </c:if>
                        </c:if>
                        <c:if test="${!checkSlot}">
                            OUT OF STOCK
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div >
            <%@include file="petshop_footer.jsp" %>
        </div>
    </body>
</html>
