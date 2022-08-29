<%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 04.08.2022
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" var="lang"/>
<%String id = request.getParameter("id");%>
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
            <center><h2><b><fmt:message key="change_report" bundle="${lang}"/></b></h2></center>
            <form action="ChangeReport?id=<%=request.getParameter("id")%>&report=<%=request.getParameter("report")%>" method="post" class="w3-container">
                <textarea id="subject" name="changeReport" placeholder="Write something.." style="height:100px; width:100%;resize: vertical;" required></textarea>
                <br/>
                <input type="submit" class="button" value="<fmt:message key="submit" bundle="${lang}"/>"/>
            </form>
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

