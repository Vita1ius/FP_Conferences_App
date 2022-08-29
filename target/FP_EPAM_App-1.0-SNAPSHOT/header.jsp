<%@ page import="com.example.fp_epam_app.DAO.mapper.UserDAO" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.fp_epam_app.DBConnection" %>
<%@ page import="com.example.fp_epam_app.DAO.entity.User" %>
<a class="active" href="index.jsp"><i class="fa fa-fw fa-home"></i><fmt:message key="header.main" bundle="${lang}"/></a>
<a href="about.jsp"><i class="fa fa-fw fa-envelope"></i> <fmt:message key="header.about" bundle="${lang}"/></a>
<%
    String loginSession = (String)session.getAttribute("login");
    String roleSession = (String) session.getAttribute("role");
    String nameSession = (String) session.getAttribute("name");
%>

<%if(loginSession == null) {%>
    <a  href="login.jsp" style="float: right;"><i class="fa fa-fw fa-user"></i><fmt:message key="header.login" bundle="${lang}"/></a>
<%}else{%>
    <a  href="logout" style="float: right;"><i class="fa fa-sign-out"></i><fmt:message key="header.logout" bundle="${lang}"/></a>
    <a  href="profile.jsp"><i class="fa fa-fw fa-user"></i><fmt:message key="header.profile" bundle="${lang}"/></a>
<%}%>
<a href="?locale=uk" style="float: right;"><fmt:message key="header.lang.uk" bundle="${lang}"/></a>
<a href="?locale=en" style="float: right;"><fmt:message key="header.lang.en" bundle="${lang}"/></a>

