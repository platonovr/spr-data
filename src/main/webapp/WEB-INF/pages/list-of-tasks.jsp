<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Список задач</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>
</head>
<body>
<ul class="nav nav-pills">
    <li><a href="${pageContext.request.contextPath}/">Home</a></li>
    <li class="active"><a href="${pageContext.request.contextPath}/task/tasks">Задачи</a></li>
    <li><a href="${pageContext.request.contextPath}/activity/activities">Действия</a></li>
</ul>
<h1>Список действий</h1>

<p>Here you can see the list of the tasks, edit them, remove or update.</p>
<table class="table">
    <thead>
    <tr>
        <th style="display: none" width="10%">id</th>
        <th width="15%">date</th>
        <th width="10%">text</th>
        <th width="10%">performer</th>
        <th width="10%">actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td style="display:none"><a href="${pageContext.request.contextPath}/task/${task.id}"/>${task.id}</td>
            <td>${task.date.year+1900} - ${task.date.date} - ${task.date.day} </td>
            <td><a href="${pageContext.request.contextPath}/task/${task.id}"/>${task.text}</td>
            <td>${task.user.login}</td>
            <td>
                <a href="${pageContext.request.contextPath}/task/create.html">Create</a> <br/>
                <a href="${pageContext.request.contextPath}/task/edit/${task.id}.html">Edit</a><br/>
                <a href="${pageContext.request.contextPath}/task/delete/${task.id}.html">Delete</a><br/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>