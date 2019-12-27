<%-- 
    Document   : account_info
    Created on : Jul 4, 2019, 10:35:46 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCOUNT INFORMATION</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                font-size: 20px;
                text-align: center; 
                background-color: rgb(227, 240, 244);
                zoom: 150%;
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
        </style>
    </head>
    <body>
        <c:if test="${not empty sessionScope.ACCOUNT}" var="checkAccount">
            <div style="width: 100%; text-align: center">
                <label class="shopping-title"><b>ACCOUNT INFORMATION</b></label><br/>
            </div>
            <div class="infor-form" >
                <font color="green">
                ${requestScope.UPDATE_SUCCESS}
                </font>
                <font color="red">
                ${requestScope.UPDATE_FAILED}
                </font>
                <br/>
                <b>Username</b><br/>        
                <input type="text" name="username" value="${requestScope.DTO.getUsername()}" readonly/>
                <br/>
                <b>Password</b>
                <br/>
                <input type="password" name="password" value="${requestScope.DTO.getPassword()}" readonly/>
                <a href="change_password.jsp">Change password</a>
                <br/>
                <form action="MainController" method="POST">
                    <b>Fullname</b><br/>        
                    <input type="text" name="fullname" value="${requestScope.DTO.getFullname()}" required/>
                    <br/>
                    <b>Birthdate (dd/MM/yyyy)</b><br/>        
                    <input type="text" name="birthdate" value="${requestScope.DTO.getBirthdate()}" required/>
                    <font color="red">
                    ${requestScope.INVALID}
                    </font>
                    <br/>
                    <b>Email</b><br/>        
                    <input type="email" name="email" value="${requestScope.DTO.getEmail()}" required/>
                    <br/>
                    <input class="btn_addcart" type="submit" name="action" value="UpdateAccount"/>
                    <input class="btn_addcart" type="submit" name="action" value="Logout"/>
                </form>

                <a href="MainController">Back to Home</a>
            </div>
            <div style="text-align: left">

                <c:if test="${sessionScope.ACCOUNT.getRole() eq 'user'}">
                    <hr/>
                    <label class="shopping-title" ><b>YOUR PETS</b></label><br/>
                    <font color="red">
                    ${requestScope.DELETE_FAILED}
                    </font>
                    <font color="green">
                    ${requestScope.DELETE_SUCCESS}
                    </font>
                    <br/>
                    <c:if test="${not empty requestScope.LIST_PET}" var="checkListPet">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Pet ID</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Type</th>
                                    <th>Details</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.LIST_PET}" var="dto" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.getId()}</td>
                                        <td>${dto.getName()}</td>
                                        <td>${dto.getType()}</td>
                                        <td>${dto.getAge()}</td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="petId" value="${dto.getId()}"/>
                                                <input class="btn_addcart" type="submit" name="action" value="Pet details"/>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="petId" value="${dto.getId()}"/>
                                                <input class="btn_addcart" type="submit" name="action" value="Delete pet"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
                <c:if test="${!checkListPet}">
                    <font color="red" >
                    You do not have any pet<br/>
                    Please contact to admin to add pets
                    </font>
                </c:if>
                <hr/>
            </c:if>
        </c:if>
        <c:if test="${!checkAccount}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </body>
</html>
