<%-- 
    Document   : error
    Created on : Jul 3, 2019, 8:50:44 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE</title>
        <style>
            a{
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <h1>ERROR PAGE</h1>
        <font color="red">
        ${requestScope.ERROR}
        </font>
        <a href="index.jsp">Back to Home</a>
    </body>
</html>
