<%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 14.07.2022
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" var="lang"/>

<!DOCTYPE html>
<html>
<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="assets/css/navi.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/bganim.css">
</head>

<body>
<div class="area" >
    <ul class="circles">
        <div class="navbar">
            <%@ include file="header.jsp"%>
        </div>

        <div class="card" >
            <center><h2><b><fmt:message key="singup" bundle="${lang}"/></b></h2></center>
            <form  action="register" method="post" class="w3-container">
                <fmt:message key="register.name" bundle="${lang}"/><input class="w3-input" type="text" name="name" required /><br/>

                <fmt:message key="register.email" bundle="${lang}"/><input class="w3-input" type="email" name="email" required /><br>

                <fmt:message key="register.username" bundle="${lang}"/><input class="w3-input" type="text" name="login" required /><br>

                <fmt:message key="register.password" bundle="${lang}"/><input class="w3-input" type="password" name="password" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\S])[A-Za-z0-9\S]{8,20}$" required /><br>

                <input type="submit" class="button" value="<fmt:message key="register.register" bundle="${lang}"/>"/>
            </form>
            <p style="text-align: center;"><fmt:message key="already_have_an_account" bundle="${lang}"/> <a href="login.jsp" style="color:#FE9800;"><fmt:message key="sign.in" bundle="${lang}"/></a></p>
        </div>

        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div >
</body>
</html>
