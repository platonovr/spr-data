<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 23.04.2014
  Time: 1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Delete Task page</title>
</head>
<body>
<h1>Delete Task page</h1>

<c:choose>
    <c:when test="${empty userLogin}">
        <form:form method="POST" modelAttribute="user"
                   action="${pageContext.request.contextPath}/registration/user/${user.id}">
            You unathorized!
        </form:form>
    </c:when>
    <c:otherwise>

        <form:form method="POST" commandName="task"
                   action="${pageContext.request.contextPath}/task/delete/${task.id}.html">

            <td>task id:</td>
            <td><c:out value="${task.id}"/></td>

            Do you want to delete this?
            <input type="submit" value="delete"/>
        </form:form>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/">Home page</a>

</body>
</html>
