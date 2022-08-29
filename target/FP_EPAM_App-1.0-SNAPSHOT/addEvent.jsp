<%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 23.07.2022
  Time: 16:57
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
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/slider.css">
    <link rel="stylesheet" href="assets/css/scrollstyle.css">
</head>
<style>


    .navbar {
        width: 100%;
        background-color: #313348;
        overflow: auto;
    }

    .navbar a {
        float: left;
        padding: 12px;
        color: white;
        text-decoration: none;
        font-size: 17px;
    }

    .navbar a:hover {
        background-color:#FE9800;
    }

    .active {
        background-color: #FE9800;
    }

    @media screen and (max-width: 500px) {
        .navbar a {
            float: none;
            display: block;
        }
    }
</style>
<body>
<div class="navbar">
    <%@ include file="header.jsp"%>
</div>

<div class="card" style="margin-top:20px;">
    <center><h2><b><fmt:message key="addEvent.register_event" bundle="${lang}"/></b></h2></center>
    <form action="InsertEvent" method="post" class="w3-container">
        <fmt:message key="addEvent.title" bundle="${lang}"/><input class="w3-input" type="text" name="name" required><br/>
        <fmt:message key="addEvent.date" bundle="${lang}"/><input class="w3-input" type="date" name="date" required><br>
        <fmt:message key="addEvent.time" bundle="${lang}"/><input class="w3-input" type="time" name="time" required><br>
        <fmt:message key="addEvent.place" bundle="${lang}"/><input class="w3-input" type="text" name="place" required><br>
        <fmt:message key="addEvent.amount" bundle="${lang}"/><input class="w3-input" type="number" name="amount" required><br>
        <input type="submit" class="button" value="<fmt:message key="addEvent.submit_event" bundle="${lang}"/>"/>

    </form>
</div>
</body>
</html>
