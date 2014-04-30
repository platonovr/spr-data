<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Действия</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
</head>
<body>
<ul class="nav nav-pills">
    <li><a href="${pageContext.request.contextPath}/">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/task/tasks">Задачи</a></li>
    <li class="active"><a href="${pageContext.request.contextPath}/activity/activities">Действия</a></li>
</ul>
<h1>Список действий</h1>

<p>Здесь вы можете посмотреть, кто что делал</p>
<table class="table">
    <thead>
    <tr>
        <th style="display: none" width="10%">id</th>
        <th width="15%">затраченное время</th>
        <th width="10%"> проделанная работа</th>
        <th width="10%">название задачи</th>
        <th width="10%">исполнитель задачи</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="activity" items="${activities}">
        <tr>
            <td style="display:none"><a
                    href="${pageContext.request.contextPath}/activity/${activity.id}"/>${activity.id}</td>
            <td>${activity.time} </td>
            <td><a href="${pageContext.request.contextPath}/activity/${activity.id}"/>${activity.comment}</td>
            <td>${activity.task.id}:${activity.task.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/registration/users/${activity.task.user.id}"/>${activity.task.user.login}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/activity/create.html">Create</a> <br/>
                <a href="${pageContext.request.contextPath}/activity/edit/${activity.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/activity/delete/${activity.id}.html">Delete</a><br/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>