<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 08.04.2014
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/a.css"/>
    <title>Authorization</title>

</head>
<body>


<p style="color: darkgrey; font-size: 20px">
    Newbie here? <a href="/registration/create">Register</a>
</p>

<form action="/login" method="post">

    <div class="login">
        <h1>Login</h1>


        <input type="text" name="login" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <input type="submit" class="btn btn-primary btn-block btn-large" value="Login">Let me in.</button>

    </div>
</form>


<td colspan="2" height="30">
    <p align="center" style="color: red">${message}</p>
</td>
</body>
</html>
