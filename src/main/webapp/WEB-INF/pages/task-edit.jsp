<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 22.04.2014
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>New Task page</title>
</head>
<body>
${userLogin}
<c:choose>
    <c:when test="${empty userLogin}"><a href="/login">Log in</a></c:when>
    <c:otherwise> | <a href="/login">Log out</a></c:otherwise>
</c:choose>

<h1>Edit Task Page</h1>
${userLogin}
<c:choose>
    <c:when test="${empty userLogin}"><a href="/login">Log in</a></c:when>
    <c:otherwise> | <a href="/login">Log out</a></c:otherwise>
</c:choose>


<c:choose>
    <c:when test="${empty userLogin}">
        <form:form method="POST" modelAttribute="taskForm"
                   action="${pageContext.request.contextPath}/task/edit/${taskForm.id}">
            You unathorized!
        </form:form>
    </c:when>
    <c:otherwise>
        <form:form method="POST" commandName="taskForm"
                   action="${pageContext.request.contextPath}/task/edit/${taskForm.id}">
            <h2>Date of beginning</h2>
            <form:input path="date" placeholder="2014/12/12"/>
            <form:errors path="date" cssstyle="color: red;"></form:errors>
            <h2>Name of task</h2>
            <form:input path="name"/>
            <form:errors path="name" cssstyle="color: red;"></form:errors>
            <h2>Text about task</h2>
            <form:textarea path="text"/>
            <form:errors path="text" cssstyle="color: red;"></form:errors>
            <h2>Status of task</h2>
            <form:radiobuttons path="status"></form:radiobuttons>
            <form:errors path="status" cssstyle="color: red;"></form:errors>


            <h2>Visibility</h2>
            <form:radiobutton path="visibility" value="true" title="true"></form:radiobutton>
            <form:radiobutton path="visibility" value="false"></form:radiobutton>
            <form:errors path="visibility" cssstyle="color: red;"></form:errors>

            <h2>User</h2>
            <form:select path="user" items="${userList}"></form:select>
            <form:errors path="user" cssstyle="color: red;"></form:errors>


            <input type="submit" value="task"/></td>


            <i>${message1}</i>

        </form:form>

    </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
