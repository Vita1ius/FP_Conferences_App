<%--
  Created by IntelliJ IDEA.
  User: BELIZO
  Date: 28.08.2022
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<h2>Exception occurred while processing the request</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
</body>
</html>