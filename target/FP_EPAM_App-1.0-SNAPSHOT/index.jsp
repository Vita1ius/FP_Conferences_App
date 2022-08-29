<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>
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
    <link rel="stylesheet" href="assets/css/scrollstyle.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/tesseract.css">
</head>
<style>
    p.a {
        font: 80px arial, sans-serif;
        font-weight: bold;
        text-align:center;
        color:#FE9800;
    }
</style>
<body style="background-color:#252636 ;color:white;">
<div class="navbar">
    <%@ include file="header.jsp"%>
</div>

<div class="block">
    <div class="shape">
        <div class="cube outer">
            <div class="side left"></div>
            <div class="side right"></div>
            <div class="side top"></div>
            <div class="side bottom"></div>
            <div class="side front"></div>
            <div class="side back"></div>


            <div class="cube">
                <div class="side left"></div>
                <div class="side right"></div>
                <div class="side top"></div>
                <div class="side bottom"></div>
                <div class="side front"></div>
                <div class="side back"></div>
            </div>

        </div>
    </div>
</div>

<ex:MyTag/>
</body>
</html>

