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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
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
            <center><h2><b>EPAM #STANDWITHUKRAINE</b></h2></center><br>

            <center><fmt:message key="about" bundle="${lang}"/></center><br>
            <center><a href="https://www.instagram.com/epam_ukraine/"><i class="fa-brands fa-instagram" style="font-size: 20px;margin-right: 10px;"></i></a>
                <a href="https://t.me/epamukraine"><i class="fa-brands fa-telegram" style="font-size: 20px;margin-right: 10px;"></i></a>
                <a href="https://www.youtube.com/c/epamuacareer/featured"><i class="fa-brands fa-youtube" style="font-size: 20px;margin-right: 10px;"></i></a>
                <a href="https://twitter.com/epam_ukraine"><i class="fa-brands fa-twitter" style="font-size: 20px;margin-right: 10px;"></i></a>
                <a href="https://www.linkedin.com/company/epam-systems/"><i class="fa-brands fa-linkedin-in" style="font-size: 20px;margin-right: 10px;"></i></a>
            </center>
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