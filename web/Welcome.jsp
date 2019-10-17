<%--
  Created by IntelliJ IDEA.
  User: Fawad
  Date: 15/10/2019
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%
    String regNoS = null;
    String regNoC = null;

    if (session.getAttribute("userreg") == null) {
        response.sendRedirect("index.jsp");
    } else
        regNoS = (String) session.getAttribute("userreg");
    String userName = null;
    String sessionID = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userreg"))
                regNoC = cookie.getValue();
        }
    }
%>
<h3>Hi <%=regNoC %>, Login successful. your Session Name is <%=regNoS%>
</h3>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
