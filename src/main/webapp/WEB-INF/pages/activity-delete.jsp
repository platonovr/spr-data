<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 29.04.2014
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Activity delete</title>
</head>
<body>
<c:choose>
    <c:when test="${empty userLogin}">
        <form:form method="POST" modelAttribute="user"
                   action="${pageContext.request.contextPath}/registration/user/${user.id}">
            You unathorized!
        </form:form>
    </c:when>
    <c:otherwise>
        <form:form method="POST" commandName="activity"
                   action="${pageContext.request.contextPath}/activity/delete/${activity.id}.html">

            <td>actiivity id:</td>
            <td><c:out value="${activity.id}"/></td>

            Do you want to delete this?
            <input type="submit" value="delete"/>
        </form:form>
    </c:otherwise>
</c:choose>

</body>
</html>
