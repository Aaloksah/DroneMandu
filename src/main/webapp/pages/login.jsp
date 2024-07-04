<%@page import="view.droneStringView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(droneStringView.MESSAGE_ERROR);
String successMsg = (String) request.getAttribute(droneStringView.MESSAGE_SUCCESS);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="../CSS/Login,Register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-image: url('bcImage3-01.jpeg.jpg');
            background-size: cover;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            max-width: 950px;
            background-color: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-sizing: border-box;
            margin: 20px;
        }

        .image {
            padding: 10px;
            text-align: center;
            margin-right: 30px;
        }

        .form-container {
            padding: 10px;
        }

        .title {
            font-size: 25px;
            font-weight: 600;
            margin-bottom: 20px;
        }

        .user-details {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin-bottom: 20px;
            width: 100%;
        }





        .logininput input {
            padding-left: 30px;
            width: 100%;
            height: 45px;
            font-size: 16px;

        }



        .sign-up-text a {
            color: #7a29e2;
            text-decoration: none;
        }

        .sign-up-text a:hover {
            text-decoration: underline;
        }

        .forgot-password {
            text-align: center;
            margin-top: 10px;
            font-size: 14px;
        }

        .forgot-password a {
            color: #7a29e2;
            text-decoration: none;
        }

        .forgot-password a:hover {
            text-decoration: underline;
        }

        .button {
            width: 100%;
        }
    </style>
</head>

<body>
    <%
	if (errMsg != null) {
	%>
	<p class="error-msg">
		<%
		out.println(errMsg);
		%>
	</p>
	<%
	}
	%>
    <div class="container">
        <div class="image">
            <img src="../Image/Add a heading (1) (2).png" alt="Login Image">
        </div>
        <div class="form-container">
            <div class="title">Login</div>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <div class="user-details">
                    <div class="logininput">
                        <i class="fas fa-envelope"></i>
                        <input type="text" name="userName" placeholder="Enter your username" required>
                    </div>
                    <div class="logininput">
                        <i class="fas fa-lock"></i>
                        <input type="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="forgot-password">
                        <a href="#">Forgot password?</a>
                    </div>
                    <div class="button input-box">
                        <input type="submit" value="Submit">
                    </div>
                    <div class="text sign-up-text">
                        Don't have an account? <a href="register.jsp">Register now</a>
                    </div>
                </div>
                </form>
        </div>
    </div>
</body>

</html>