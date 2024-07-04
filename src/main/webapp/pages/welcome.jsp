<%@page import="view.droneStringView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String contextPath = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* Resetting default margin and padding */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    /* Styling body */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        text-align: center;
    }

    /* Styling welcome container */
    .welcome-container {
        max-width: 600px;
        margin: 50px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    /* Styling headings */
    h1, h3 {
        color: #7d2ae8;
        margin-bottom: 15px;
    }

    /* Styling paragraph */
    p {
        color: #333;
        margin-bottom: 20px;
    }

    /* Styling links */
    .back-btn {
        display: inline-block;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
        margin-right: 10px;
        transition: background-color 0.3s;
    }

    .back-btn:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
<%
    String userSession = (String) session.getAttribute(droneStringView.userName);
    
    String cookieUsername  = null;
    String cookieSessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie: cookies){
            if(cookie.getName().equals(droneStringView.USER)) cookieUsername = cookie.getValue();
            if(cookie.getName().equals(droneStringView.JSESSIONID)) cookieSessionID = cookie.getValue();
        }
    }
%>
<div class="welcome-container">
    <h1>Welcome to  DRONEMANDU!</h1>
    <h3>Cookie session Id is <%=cookieSessionID %></h3>
    <p>Session username: <%=userSession %></p>
  
        <a href="<%=contextPath%>/pages/login.jsp"> <button class ="login-button">Continue to login page</button></a>
    
        <a href="<%=contextPath%>/pages/register.jsp"> <button class ="home-button">Continue to register page</button></a>

</div>
</body>
</html>
