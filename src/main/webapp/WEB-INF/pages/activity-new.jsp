<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Регистрация</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>

<c:choose>
    <c:when test="${empty userLogin}">
        <form:form method="POST" modelAttribute="activityForm"
                   action="${pageContext.request.contextPath}/activity/create.html">
            <h2 style="color: ghostwhite"> Вы не авторизованы! </h2>
        </form:form>
    </c:when>
    <c:otherwise>
        <form:form method="POST" commandName="activityForm"
                   action="${pageContext.request.contextPath}/activity/create.html">
            <h2>Затраченное время</h2>
            <form:input path="time" placeholder="6 hours"/>
            <form:errors path="time" cssstyle="color: red;"></form:errors>
            <h2>Комменатрий к выполненной работе</h2>
            <form:textarea path="comment"/>
            <form:errors path="comment" cssstyle="color: red;"></form:errors>

            <h2>Задача</h2>
            <form:select path="task" items="${taskList}"></form:select>
            <form:errors path="task" cssstyle="color: red;"></form:errors>


            <input type="submit" value="acitivity"/></td>


            <i>${message1}</i>

        </form:form>

    </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
