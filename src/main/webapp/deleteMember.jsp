<%@ page import="com.example.fp_epam_app.DAO.mapper.ParticipantDAO" %>
<%@ page import="com.example.fp_epam_app.DAO.mapper.UserDAO" %>
<%@ page import="com.example.fp_epam_app.DAO.mapper.EventDAO" %><%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 24.07.2022
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String login = request.getParameter("user_login");
    String id = request.getParameter("id");
    String amount = request.getParameter("amount");
    ParticipantDAO.getInstance().DeleteOrder(login,id);
    UserDAO.getInstance().cashBack(login,amount);
    EventDAO.getInstance().updateParticipate(-1,id);
    response.sendRedirect("event.jsp");

%>
