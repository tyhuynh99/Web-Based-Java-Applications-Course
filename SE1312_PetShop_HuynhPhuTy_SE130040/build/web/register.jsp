<%-- 
    Document   : login
    Created on : Jul 3, 2019, 8:50:14 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN PAGE</title>
        <style>
            .register-form{
                max-width: 100%; 
                background-image: url(img/register-bg.jpg);
                background-attachment: fixed;
                height: 1200px; 
                font-size: 30px; 
                text-align: center; 
            }
            .register-title{
                color:#f2b500; 
                font-size: 50px;
                margin-top: 50px;
            }
            .register-subtitle{
                color:#f2b500; 
                font-size: 20px;
                font-weight: bold;
            }
            input{
                border-radius: 5px;
                border-color: transparent;
                font-size: 24px;
            }
            .btn_login{
                margin-top: 20px;
                border-radius: 5px;
                font-size: 20px;
                width: 250px;
            }

        </style>
    </head>
    <body>

        <%@include file="petshop_header.jsp" %>
        <div class="register-form">
            <div>
                <br/>
                <label class="register-title"><b>REGISTER ACCOUNT</b></label><br/>
                <label class="register-subtitle">*: You must enter information into these fields</label>
                <form action="MainController" method="POST">
                    <font color="red">
                    ${requestScope.RegisterFailed}
                    </font><br/>
                    <label class="register-subtitle" style="color: red; background-color: orange">
                        ${requestScope.INVALID.getErrUsername()}
                    </label>
                    <br/>
                    <label class="register-subtitle">Username*</label> 
                    <br/>
                    <input type="text" name="username" value="${param.username}" required/>
                    <br/>
                    <label class="register-subtitle">Password*</label>
                    <br/>
                    <input type="password" name="password" required/>
                    <br/>
                    <label class="register-subtitle">Confirm Password*</label>
                    <br/>
                    <input type="password" name="confirm" required/><br/>
                    <label class="register-subtitle" style="color: red; background-color: orange">
                        ${requestScope.INVALID.getErrConfirm()}
                    </label>
                    <br/>
                    <label class="register-subtitle">Fullname*</label>
                    <br/>
                    <input type="text" name="fullname" value="${param.fullname}" required/>
                    <br/>
                    <label class="register-subtitle" style="color: red; background-color: orange">
                        ${requestScope.INVALID.getErrDOB()}
                    </label><br/>
                    <label class="register-subtitle">Birthdate* (dd/MM/yyyy)</label>
                    <br/>

                    <input type="text" name="dob" value="${param.dob}" required/>
                    <br/>
                    <label class="register-subtitle">Email*</label>
                    <br/>
                    <input type="email" name="email" value="${param.email}" required/>
                    <br/>
                    <input type="hidden" name="role" value="${param.insert_role}"/>
                    <button class="btn_login"  name="action" value="Register" ><b>Register Account</b></button>
                </form>

            </div>
        </div>  
        <%@include file="petshop_footer.jsp" %>
    </body>
</html>


