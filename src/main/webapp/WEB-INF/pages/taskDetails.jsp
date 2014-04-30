<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="en">


<body>
<div class="container">


    <h2>Task Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Started at:</th>
            <td><b><c:out value="${task.date}"/></b></td>
        </tr>
        <tr>
            <th>Status</th>
            <td><c:out value=" ${task.status}"></c:out></td>
        </tr>
        <tr>
            <th>About:</th>
            <td><c:out value="${task.text}"/></td>
        </tr>

        <tr>
            <a href="${pageContext.request.contextPath}/task/edit/${task.id}.html">Edit task</a><br/>
            <a href="${pageContext.request.contextPath}/task/delete/${task.id}.html">Delete task</a><br/>
            <a href="${pageContext.request.contextPath}/task/create.html">Create another</a>
        </tr>
    </table>

    <h2>Activities</h2>

    <c:forEach var="activity" items="${task.taskActivities}">
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
                        <dt>id</dt>
                        <dd><c:out value="${activity.id}"/></dd>
                        <dt>Time</dt>
                        <dd><c:out value="${activity.comment}"/></dd>
                        <dt>text</dt>
                        <dd><c:out value="${activity.task}"/></dd>

                    </dl>
                </td>


            </tr>
        </table>
    </c:forEach>


</div>

</body>

</html>
