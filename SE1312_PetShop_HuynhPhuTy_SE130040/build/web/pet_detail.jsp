<%-- 
    Document   : pet_detail
    Created on : Jul 5, 2019, 2:46:17 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PET DETAILS</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 20px;
                text-align: center; 
                background-color: rgb(227, 240, 244);
                zoom: 180%;
            }
            a{
                text-decoration: none;
                font-size: 16px;
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
        <c:if var="checkAccount" test="${not empty sessionScope.ACCOUNT and sessionScope.ACCOUNT.getRole() eq 'user' and not empty requestScope.PET}">
            <div style="width: 100%; text-align: center">
                <label class="shopping-title"><b>PET INFORMATION</b></label><br/>
            </div>
            <font color="red">
            ${requestScope.UPDATE_FAILED}
            </font>
            <font color="green">
            ${requestScope.UPDATE_SUCCESS}
            </font>
            <div class="infor-form">
                <form action="MainController" method="POST">
                    Pet ID<br/>
                    <input type="text" name="petId" value="${requestScope.PET.getId()}" readonly/><br/>
                    Pet Name<br/>
                    <input type="text" name="name" value="${requestScope.PET.getName()}" required/><br/>
                    Age<br/>
                    <input type="number" name="age" min="0" max="10" value="${requestScope.PET.getAge()}" required/><br/>
                    Type<br/>
                    <select name="type">
                        <option value="Dog" <c:if test="${requestScope.PET.getType() eq 'Dog'}">selected</c:if>>Dog</option>
                        <option value="Cat" <c:if test="${requestScope.PET.getType() eq 'Cat'}">selected</c:if>>Cat</option>
                        </select>
                        <input class="btn_addcart" type="submit" name="action" value="Update Pet"/>
                        <input class="btn_addcart" type="submit" name="action" value="Delete Pet"/>
                    </form>
                    <a href="ViewAccountInfoController">Back</a>
                </div>
        </c:if>
        <c:if test="${!checkAccount}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </body>
</html>
