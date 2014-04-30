<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/>

</head>

<body>
<div class="container">

    ${userLogin}
    <c:choose>
        <c:when test="${empty userLogin}"><a href="/login">Login</a></c:when>
        <c:otherwise> | <a href="/login">Logout</a></c:otherwise>
    </c:choose>
    <h2>User Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.firstname} ${user.secondname}"/></b></td>
        </tr>
        <tr>
            <th>login</th>
            <td><c:out value="${user.login}"/></td>
        </tr>

        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/registration/edit/${user.id}.html">Edit User</a><br/>
                <a href="${pageContext.request.contextPath}/registration/delete/${user.id}.html">Delete Profile</a><br/>
            <td>

        </tr>
    </table>

    <h2>Tasks</h2>
    <a href="${pageContext.request.contextPath}/task/create">Add task</a>
    <c:forEach var="task" items="${user.tasks}">
        <table class="table">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
                        <dt>id</dt>
                        <dd><a href="${pageContext.request.contextPath}/task/${task.id}"/>${task.id}</dd>
                        </a>
                        <dt>Date</dt>
                        <dd><c:out value="${task.date.year+1900} "/>|<c:out value="${task.date.month}"/>|<c:out
                                value="${task.date.day}"/></dd>
                        <dt>text</dt>
                        <dd><c:out value="${task.text}"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${task.status}"/></dd>
                        <dt>Performer</dt>
                        <dd><c:out value="${task.user.login}"/></dd>
                    </dl>
                </td>


            </tr>
        </table>

        <a href="${pageContext.request.contextPath}/task/tasks">Tasks</a>
    </c:forEach>


</div>

</body>

</html>
