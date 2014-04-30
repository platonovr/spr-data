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

<div style="background: #e0eeee; width: 350; height: 240; margin: auto; margin-top: 150; padding: 20; padding-top: 0;
 border: black; border-style: solid; border-width: 1px;">

    <div align="center"><p style="font-family: Arial; font-size: 20">Registration</p></div>

    <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/registration/create.html">

        <table width="100%" style="margin-top: 30">
            <tr>
                <td>Логин:</td>
                <td align="right"><form:input type="text" path="login"/></td>
                <td><form:errors path="login" cssstyle="color: red;"></form:errors></td>

            </tr>
            <tr>
                <td>Пароль:</td>
                <td align="right"><form:input type="password" path="password"/></td>
                <td><form:errors path="password" cssstyle="color: red;"></form:errors></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <td align="right"><form:input type="text" path="firstname"/></td>
                <td><form:errors path="firstname" cssstyle="color: red;"></form:errors></td>
            </tr>
            <tr>
                <td>Фамилия:</td>
                <td align="right"><form:input type="text" path="secondname"/></td>
                <td><form:errors path="secondname" cssstyle="color: red;"></form:errors></td>
            </tr>
            <tr>
                <td colspan="2" height="15">
                    <p style="color: darkgrey; font-size: 12px">
                        Вы уже зарегестриованы? <a href="/login">Войти</a>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="30">
                    <p align="center" style="color: red">${message}</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p align="center"><input type="submit" value="registration" style="width: 200"></p>
                </td>
            </tr>
        </table>


    </form:form>

</div>

</body>
</html>