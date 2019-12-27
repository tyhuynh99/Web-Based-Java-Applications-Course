<%-- 
    Document   : shopping
    Created on : Jul 7, 2019, 8:45:31 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SHOPPING</title>
        <style>
            .btn_addcart{
                margin-top: 10px;
                border-radius: 5px;
                padding: 5px;
                background-color: #f2b500; 
                color: white;
            }

            .shopping_form{
                background-image: url(img/shopping-bg.jpg);
                background-attachment: fixed;
                max-height: 3000px;
                height: auto;
            }
            .container_acc{
                width: 100%;
                text-align: center;
                padding-bottom: 50px;
            }
            .shopping-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 50px;
                text-align: center;
                width: 100%;
            }
            .shopping-subtitle{
                color: white;
                font-size: 18px;
            }
            .card:hover .card-body{
                background-color: #a0a0a0;
            }
            .mess{
                font-size: 20px;
                text-align: center;
                background-color: #F2B500;
                padding: 10px;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@include file="petshop_header.jsp" %>
        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}" var="checkRole"> 
            <div class="shopping_form">
                <div style="text-align: center; padding-top: 100px;">
                    <label class="shopping-title"><b>ACCESSORIES</b></label><br/>
                    <label class="shopping-subtitle">choose the best accessories for your pets</label>
                    <form action="MainController" method="POST">
                        <button class="btn_addcart" name="action" value="View Your Shopping Cart"><i class="fas fa-shopping-cart" style="color: black"></i> View Your Shopping Cart </button>
                    </form>
                </div>
                <br/>
                <c:if test="${requestScope.ADD_SUCCESSFUL != null or requestScope.INSERT_INVOICE_SUCCESS!= null}">
                    <div class="mess">
                        ${requestScope.ADD_SUCCESSFUL}<!--Thêm accessory vào giỏ hàng thành công-->
                        ${requestScope.INSERT_INVOICE_SUCCESS}
                    </div>
                </c:if>
                <c:if test="${not empty requestScope.LIST}" var="checkList">

                    <div class="container_acc">
                        <div class="row">
                            <c:forEach items="${requestScope.LIST}" var="dto">
                                <%@include file="petshop_accessory_card.jsp" %>
                            </c:forEach>
                        </div>
                    </div>

                </c:if>
                <!--Trường hợp không accessory nào còn hàng-->
                <c:if test="${!checkList}">
                    <label class="shopping-title" ><b>Sorry, no thing match</b></label><br/>
                </c:if>
            </c:if>
            <c:if test="${!checkRole}">
                <c:redirect url="login.jsp"/>
            </c:if>
        </div>
        <%@include file="petshop_footer.jsp" %>
    </body>
</html>
