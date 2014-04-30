<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
    <meta http-equiv=" Content-Type
    " content="text/html; charset=UTF-8"/>
    <title>Home page</title>
</head>
<body>
<ul class="nav nav-pills">
    <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/task/tasks">Задачи</a></li>
    <li><a href="${pageContext.request.contextPath}/activity/activities">Действия</a></li>
</ul>

<h1>Home page</h1>
${userLogin}
<c:choose>
    <c:when test="${empty userLogin}">

        <ul class="nav nav-pills">
            <li class="active">
                <a href="/login">Войти</a>
            </li>
        </ul>
    </c:when>

    <c:otherwise> |
        <ul class="nav nav-pills nav-stacked">
            <li>
                <a href="/login">Выйти</a>
            </li>
        </ul>
    </c:otherwise>
</c:choose>


<div class="container">


    <ul class="nav nav-pills nav-stacked">

        <li><a href="${pageContext.request.contextPath}/registration/list.html">Список пользователей</a></li>
        <li><a href="${pageContext.request.contextPath}/task/tasks">Задачи</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/task/create">Добавить задачу</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/activity/activities">Список действий</a></li>
        <li><a href="${pageContext.request.contextPath}/activity/create">Добавить действие по задаче</a></li>

    </ul>


</div>
</body>


</html>