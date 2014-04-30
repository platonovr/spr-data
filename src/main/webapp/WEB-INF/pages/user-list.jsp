<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>

    <title>List of users</title>

</head>
<body>
${userLogin}
<c:choose>
    <c:when test="${empty userLogin}"><a href="/login">Login</a></c:when>
    <c:otherwise> | <a href="/login">Logout</a></c:otherwise></c:choose>
<h1>Список пользователей</h1>

<p>Админка</p>
<table class="table">
    <thead>
    <tr>
        <th style="display: none" width="10%">id</th>
        <th width="15%">логин</th>
        <th width="15%">имя пользователя</th>
        <th width="10%">фамилия</th>
        <th width="10%">возможные действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr class="success">

            <td style="display: none"><a
                    href="${pageContext.request.contextPath}/registration/users/${user.id}"/>${user.id}</td>
            <td><a href="${pageContext.request.contextPath}/registration/users/${user.id}"/>${user.login}</td>
            <td>${user.firstname}</td>
            <td>${user.secondname}</td>
            <td>
                <a href="${pageContext.request.contextPath}/registration/edit/${user.id}.html">Редактировать</a><br/>
                <a href="${pageContext.request.contextPath}/registration/delete/${user.id}.html">Удалить</a><br/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/">Home page</a></p>

</body>
</html>