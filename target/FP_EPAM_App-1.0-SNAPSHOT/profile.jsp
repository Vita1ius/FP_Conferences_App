<%@ page import="java.sql.*" %>
<%@ page import="com.example.fp_epam_app.DAO.mapper.EventDAO" %>
<%@ page import="com.example.fp_epam_app.DAO.entity.Event" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 16.07.2022
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" var="lang"/>
<%
    String login = (String) session.getAttribute("login");
    Statement statement = null;
    ResultSet resultSet1 = null;

    Connection connection = DBConnection.getInstance().getConnection();
    try {
        if (connection != null) {
            statement=connection.createStatement();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="assets/css/navi.css">
    <link rel="stylesheet" href="assets/css/scrollstyle.css">
    <link rel="stylesheet" href="assets/css/btnbadge.css">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #252636;
        }

        /* Create two columns/boxes that floats next to each other */
        nav {
            float: left;
            width: 20%;

            background: #313348;
            padding: 20px;
            margin-left: 25px;
            margin-top: 20px;
            color: white;
        }

        /* Style the list inside the menu */
        nav ul {
            list-style-type: none;
            padding: 0;
        }

        article {
            float: left;
            padding: 20px;
            width: 75%;
            margin-left: 25px;
            margin-top: 20px;
            background-color: #313348;
            height: 600px; /* only for demonstration, should be removed */
            color: white;
            overflow: auto;
        }

        /* Clear floats after the columns */
        section:after {
            content: "";
            display: table;
            clear: both;

        }


        /* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
        @media (max-width: 600px) {
            nav, article {
                width: 100%;
                height: auto;
            }
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            width:40px;
            height:40px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 10px;
        }
        .button2 {background-color: #f44336;} /* Red */

        #sidbutton {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 32px;
            width: 100%;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }

    </style>
</head>
<body>
<div class="navbar">
    <%@ include file="header.jsp"%>
</div>
<section>
    <nav>
        <%if(loginSession != null && roleSession.equalsIgnoreCase("moderator")) {%>
        <center><i class="fa fa-user-secret" style="font-size:100px;color:#FE9800"></i></center>
        <%}else if(loginSession != null && roleSession.equalsIgnoreCase("speaker")) {%>
        <center><i class="fa fa-user" style="font-size:100px;color:#FE9800"></i></center>
        <%}else if(loginSession != null && roleSession.equalsIgnoreCase("user")) {%>
        <center><i class="fa fa-user-circle-o" style="font-size:100px;color:#FE9800"></i></center>
        <%}%>
        <center><p>@<%=login%></p>
            <%
                try {
                    resultSet1 = statement.executeQuery("SELECT * FROM conferences.user where login = '"+login+"';");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                while(resultSet1.next()){%>
            <p><i class="fa fa-address-card"style="font-size: 20px;margin-right: 10px;" ></i> <%=resultSet1.getString("name")%></p>
            <p><i class="fa fa-envelope" style="font-size: 20px;margin-right: 10px;"></i> <%=resultSet1.getString("email")%></p>
            <p><i class="fa fa-money" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="account.account" bundle="${lang}"/> <%=resultSet1.getString("account")%></p>
            <form action="account" method="post">
                <input type="number" style="font-size:15px; width: 80px" name="top-up_amount">
                <input type="submit" value="<fmt:message key="account.deposit" bundle="${lang}"/>">
            </form>
            <%}%>
        </center>
        <br>
        <%if(loginSession != null && roleSession.equalsIgnoreCase("moderator")) {%>
            <a href="addEvent.jsp" id="sidbutton"><i class="fa fa fa-pencil" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="add_new_event" bundle="${lang}"/></a><br><br>
            <a href="users.jsp" id="sidbutton"><i class="fa fa-users" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="users" bundle="${lang}"/></a><br><br>
        <%}%>
        <a href="event.jsp" id="sidbutton"><i class="fa fa-th-list" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="new_event" bundle="${lang}"/></a><br><br>
        <a href="pastEvent" id="sidbutton"><i class="fa fa-th-list" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="past_event" bundle="${lang}"/></a><br><br>
    </nav>
    <article>
        <div class="w3-container">
            <%if (loginSession != null){
                    if (roleSession.equalsIgnoreCase("speaker")){%>
            <h2>All Events</h2>
            <br>
            <table class="w3-table w3-bordered" id="tableSort">
                <thead>
                <tr style="color:#FE9800;">
                    <th datatype="integer"><fmt:message key="new_event.id" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="new_event.name" bundle="${lang}"/></th>
                    <th datatype="date"><fmt:message key="new_event.date" bundle="${lang}"/></th>
                    <th datatype="integer"><fmt:message key="new_event.participants" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="new_event.place" bundle="${lang}"/></th>
                    <th datatype="double"><fmt:message key="new_event.amount" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <%ArrayList<Event> Eventlist = (ArrayList<Event>) EventDAO.getInstance().getEventsByLogin(loginSession);%>
                <% for (Event event: Eventlist) {%>
                <tr>
                    <td><%=event.getId()%></td>
                    <td><%=event.getName()%></td>
                    <td><%=event.getTimestamp()%></td>
                    <td><%=event.getNumber_of_participants()%></td>
                    <td><%=event.getPlace()%></td>
                    <td><%=event.getAmount()%></td>
                </tr>
                <% } %>
                <script src="src/main/webapp/tablesort.js"></script>
                </tbody>
            </table>
            <script src="${pageContext.request.contextPath}/assets/js/tablesort.js"></script>
            <%}
            }%>
        </div>
    </article>
</section>


</body>
</html>
