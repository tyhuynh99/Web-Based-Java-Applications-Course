<%-- 
    Document   : cart_detail
    Created on : Jul 8, 2019, 9:44:30 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CART DETAIL</title>
        <style>
            body{
                text-align: center;

            }
            .cartdetail_form{
                background-image: url(img/shopping-bg.jpg);
                background-attachment: fixed;
                max-height: 5000px;
                height: auto;
                text-align: center;
                padding-bottom: 50px;
            }
            .shopping-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 150px;
                margin-bottom: 20px;
            }
            .cart_detail_card{
                border-radius: 10px;
                padding: 10px;
                margin: 10px 10px 10px 50px;
                width: 600px;
                height: auto;
                max-height: 800px;
                /*                border-radius: 10px;*/
                background-color: white;
                border-bottom: 2px;
            }
            .card_form{
                margin-left: 40px;
                border-radius: 10px;
                padding: 10px 5px;
                padding-bottom: 20px;
                text-align: center;
                width: auto;
                max-width:  700px;
                height: auto;
                background-color: orange;
            }
            .btn_card{
                border-radius: 10px;
                background-color: #FFA500;
                color: white;
                padding: 5px 10px;
                border-color:#e0930d;
            }
            table{
                width: 100%;
                border:none;
                border-color: transparent;
            }
            .card_subtitle{
                font-size: 20px;
                color: white; 
            }
            .info_form{
                margin-top: 20px;
                width: 100%;
                height: auto;
                background-color:rgb(214, 195, 53,0.8);
            }
        </style>
    </head>
    <body>
        <%@include file="petshop_header.jsp" %>


        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}" var="checkRole">

            <div class="cartdetail_form">
                <c:if test="${sessionScope.CART.getCountAccCart()>0 or sessionScope.CART.getCountSerCart() > 0}" var="checkQuantity">
                    <label class="shopping-title"><b>SHOPPING CART DETAILS</b></label><br/>
                    <label class="card_subtitle">
                        Please check all your carts. If you want to change, please press "Update" or "Delete" before check out
                    </label>
                    <c:if test="${dto.getPetId() eq requestScope.PET_ID_ERROR and dto.getAccId() eq requestScope.ACC_ID_ERROR}">
                        <font color="red">
                        ${requestScope.UPDATE_FAILED}
                        </font>
                    </c:if>
                    <br/>
                    <c:if test="${requestScope.ACC_CART_LIST != null or requestScope.SER_CART_LIST}">

                        <table border="1">
                            <thead>
                                <tr>
                                    <th>
                                        <c:if test="${not empty requestScope.ACC_CART_LIST}">
                                            <div class="card_form">
                                                <label class="card_subtitle"><b>Accessories</b></label><br/>
                                                <label><b>Total: </b>${requestScope.TOTAL_ACC}</label>
                                                <c:forEach items="${requestScope.ACC_CART_LIST}" var="dto" varStatus="counter">
                                                    <div class="cart_detail_card">
                                                        <c:if test="${requestScope.LIST_KEY_NOT_ENOUGH.contains(dto.getAccId())}">
                                                            <font color="red">
                                                            Sorry, this accessory is not enough
                                                            </font>
                                                        </c:if>
                                                        <form action="MainController" method="POST">
                                                            <b>Accessory Name: </b>${dto.getAccName()}
                                                            <input type="hidden" name="accId" value="${dto.getAccId()}"/>
                                                            <br/>
                                                            <b>Pet Name: </b>${dto.getPetName()}
                                                            <input type="hidden" name="petId" value="${dto.getPetId()}"/>
                                                            <br/>
                                                            <b>Quantity: </b>
                                                            <input name="quantity" type="number" min="1" value="${dto.getQuantity()}" required/>
                                                            <br/>
                                                            <c:if test="${not empty requestScope.OUT_OF_STOCK_RANGE and dto.getPetId() eq requestScope.PET_ID_ERROR and dto.getAccId() eq requestScope.ACC_ID_ERROR or dto.getQuantity() > dto.getAccQuantity()}" var="checkAccQuantity">
                                                                <font color="red">
                                                                <c:set var="accept" value="false"/>
                                                                Quantity maximum available: ${dto.getAccQuantity()}
                                                                </font>
                                                                <br/>
                                                            </c:if>
                                                            <b>Price per unit: </b>${dto.getAccPrice()}<br/>
                                                            <b>Total: </b><font color="green">${dto.getAccPrice()*dto.getQuantity()}</font>
                                                            <br/>
                                                            <button class="btn_card" name="action" value="Update Accessory Cart">Update</button>
                                                            <button class="btn_card" name="action" value="Delete Accessory Cart">Delete</button>
                                                        </form>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </th>
                                    <th>
                                        <c:if test="${not empty requestScope.SER_CART_LIST}">
                                            <div class="card_form">
                                                <label class="card_subtitle"><b>Services</b></label>
                                                <br/>
                                                ${requestScope.SER_MESS}<br/>
                                                <label><b>Total: </b>${requestScope.TOTAL_SER}</label>
                                                <c:forEach items="${requestScope.SER_CART_LIST}" var="dto" varStatus="counter">
                                                    <div class="cart_detail_card">
                                                        <form action="MainController" method="POST">
                                                            <b>Service Name: </b>${dto.getSerName()}
                                                            <input type="hidden" name="serId" value="${dto.getServiceId()}"/>
                                                            <br/>
                                                            <b>Pet Name: </b>${dto.getPetName()}
                                                            <input type="hidden" name="petId" value="${dto.getPetId()}"/>
                                                            <br/>
                                                            <b>Slot: </b>
                                                            <select name="slot">
                                                                <c:forEach items="${requestScope.AVAILABLE_SLOT_LIST.get(dto.getServiceId())}" var="slotList">
                                                                    <c:if test="${slotList.isStatus() eq 'true'}">
                                                                        <option 
                                                                            value="${slotList.getSlot()}"
                                                                            <c:if test="${slotList.getSlot() eq dto.getSlot()}">
                                                                                selected
                                                                            </c:if>>
                                                                            ${slotList.getSlot()}
                                                                        </option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                            <br/>
                                                            <b>Date: </b>${dto.getDateBook()}
                                                            <input type="hidden" name="date" value="${dto.getDateBook()}"/>
                                                            <br/>
                                                            <b>Total: </b>${dto.getSerPrice()}<br/>
                                                            <br/>
                                                            <button class="btn_card" name="action" value="Update Service Cart">Update</button>
                                                            <button class="btn_card" name="action" value="Delete Service Cart">Delete</button>
                                                        </form>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </th>
                                </tr>
                            </thead>
                        </table>
                    </c:if>
                    <div class="info_form">
                        <form action="MainController" method="POST">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <!--Thông tin khách hàng--> 
                                        <th class="customer_info">
                                            <label class="card_subtitle"><b>Customer Information</b></label><br/>
                                            <label><b>Username:</b> ${requestScope.ACCOUNT.getUsername()}</label><br/>
                                            <label><b>Fullname:</b> ${requestScope.ACCOUNT.getFullname()}</label><br/>
                                            <label><b>Email:</b> ${requestScope.ACCOUNT.getEmail()}</label><br/>
                                            <label><b>Address: </b></label> <input type="text" name="addr" value="${param.addr}" required/><br/>
                                            <label><b>Phone number: </b></label> <input type="text" name="phone" value="${param.phone}" required/><br/>
                                        </th>
                                        <!--Cách thanh toán-->
                                        <th class="payment_info">
                                            <label><b>Payment method</b></label>
                                            <select name="payment">
                                                <c:forEach items="${requestScope.PAY_LIST}" var="payDto">
                                                    <option value="${payDto.getId()}" <c:if test="${param.payment eq payDto.getId()}"> selected </c:if>>${payDto.getMethod()}</option>
                                                </c:forEach>
                                            </select><br/>

                                            <button class="btn_card" style="width: 200px;"  name="action" value="Add to invoice" <c:if test="${accept eq 'false'}">disabled</c:if>>
                                                    <b>Check Out</b>
                                                </button>
                                                <br/>
                                                <a href="ShoppingController" style="font-size: 20px; color: white">Back to Shopping</a>
                                            </th>
                                        </tr>
                                    </thead>
                                </table>
                            </form>
                        </div>
                </c:if>
                <c:if test="${!checkQuantity}">
                    <label class="shopping-title"><b>Shopping cart does not have anything</b></label><br/>
                    <a href="ShoppingController" style="font-size: 20px;">Back to Shopping</a>
                </c:if>

            </c:if>
        </div>
        <c:if test="${!checkRole}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <%@include file="petshop_footer.jsp" %>
    </body>
</html>
