<%-- 
    Document   : index
    Created on : Jul 3, 2019, 9:02:49 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pet Care System</title>
    </head>
    <body>
        <c:if test="${sessionScope.ACCOUNT.getRole() eq 'admin'}">
            <c:redirect url="admin.jsp"/>
        </c:if>
        <%@include file="petshop_header.jsp" %>
        <%@include file="petshop_body.jsp" %>
        <%@include file="petshop_footer.jsp" %>
    </body>
</html>
