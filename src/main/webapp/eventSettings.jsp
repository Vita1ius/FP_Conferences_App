<%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 30.07.2022
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.fp_epam_app.DAO.entity.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.fp_epam_app.DAO.entity.Report" %>
<%@ page import="com.example.fp_epam_app.DAO.mapper.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" var="lang"/>
<%
    String login = (String) session.getAttribute("login");
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet1 = null;

    connection = DBConnection.getInstance().getConnection();
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
        #tableSort th {
            cursor: pointer;
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
        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 20px;
        }
        form label {
            display: block;
        }
        form p {
            margin: 0;
        }

        .full-width {
            grid-column: 1 / 3;
        }

        button,
        input,
        textarea {
            padding: 1em;
        }

        button {
            background-color:#5CB85C;
            width: 100%;
            border: 0;
            color:white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 1px 2px;
            cursor: pointer;
            border-radius: 3px;
        }
        button:hover, button:focus {
            background: #4da14d;
            outline: 0;
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
            <%}%>
        </center>
        <br>
        <%if(loginSession != null && roleSession.equalsIgnoreCase("moderator")) {%>
        <a href="addEvent.jsp" id="sidbutton"><i class="fa fa fa-pencil" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="add_new_event" bundle="${lang}"/></a><br><br>
        <%}%>
        <a href="event.jsp" id="sidbutton"><i class="fa fa-th-list" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="new_event" bundle="${lang}"/></a><br><br>
        <a href="pastEvent" id="sidbutton"><i class="fa fa-th-list" style="font-size: 20px;margin-right: 10px;"></i><fmt:message key="past_event" bundle="${lang}"/></a><br><br>
    </nav>
    <article>
        <div class="form">
            <center><h2><b><fmt:message key="change_event" bundle="${lang}"/></b></h2></center>
            <form action="updateEvent?id=<%=request.getParameter("id")%>" method="post">
                <p>
                    <label><fmt:message key="addEvent.title" bundle="${lang}"/></label>
                    <input  style="width: 100%" type="text" name="name" required>
                </p>
                <p>
                    <label ><fmt:message key="addEvent.date" bundle="${lang}"/></label>
                    <input style="width: 100%" type="date" name="date" required>
                </p>
                <p>
                    <label><fmt:message key="addEvent.time" bundle="${lang}"/></label>
                    <input style="width: 100%" type="time" name="time" required>
                </p>
                <p>
                    <label><fmt:message key="addEvent.place" bundle="${lang}"/></label>
                    <input style="width: 100%" type="text" name="place" required>
                </p>
                <p>
                    <label><fmt:message key="addEvent.amount" bundle="${lang}"/></label>
                    <input style="width: 100%" type="number" name="amount" required>
                </p>
                <p>
                    <br><button type="submit"><fmt:message key="addEvent.submit_event" bundle="${lang}"/></button>
                </p>
            </form>
        </div>
        <br>

        <center><h2><b><fmt:message key="confirm_report" bundle="${lang}"/></b></h2></center>
        <br>
        <div class="w3-container">
            <table class="w3-table w3-bordered" id="tableSort">
                <thead>
                <tr style="color:#FE9800;">
                    <th datatype="integer"><fmt:message key="new_event.id" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="report" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="speaker" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="status" bundle="${lang}"/></th>
                    <th datatype="text"><fmt:message key="new_event.action" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <%ArrayList<Report> ReportList = (ArrayList<Report>) ReportDAO.getInstance().listReports(request.getParameter("id"),"Пропонує спікер");%>
                <% for (Report reports : ReportList) {%>
                <tr>
                    <td><%=reports.getEvent_id()%></td>
                    <td><%=reports.getReport()%></td>
                    <td><%=reports.getSpeaker()%></td>
                    <td><%=reports.getStatus()%></td>
                    <td>
                        <a href="deleteReport?id=<%=reports.getEvent_id()%>&report=<%=reports.getReport()%>">delete</a>
                        <a href="updateReport?id=<%=reports.getEvent_id()%>&report=<%=reports.getReport()%>&speaker=<%=reports.getSpeaker()%>&status=Підтверджено">submit</a>
                    </td>
                </tr>
                <% } %>
                <%ArrayList<Report> ReportList1 = (ArrayList<Report>) ReportDAO.getInstance().listReports(request.getParameter("id"),"Очікує на підтвердження");%>
                <% for (Report reports : ReportList1) {%>
                <tr>
                    <td><%=reports.getEvent_id()%></td>
                    <td><%=reports.getReport()%></td>
                    <td><%=reports.getSpeaker()%></td>
                    <td><%=reports.getStatus()%></td>
                    <td>
                        <a href="updateReport?id=<%=reports.getEvent_id()%>&report=<%=reports.getReport()%>&speaker=%>&status=Пропонується">cancel</a>
                        <a href="updateReport?id=<%=reports.getEvent_id()%>&report=<%=reports.getReport()%>&speaker=<%=reports.getSpeaker()%>&status=Підтверджено">submit</a>
                    </td>
                </tr>
                <% } %>
                <script src="src/main/webapp/tablesort.js"></script>
                </tbody>
            </table>
            <script src="${pageContext.request.contextPath}/assets/js/tablesort.js"></script>
        </div>
    </article>
</section>
</body>
</html>

