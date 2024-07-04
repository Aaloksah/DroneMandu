<%@page import="view.droneStringView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../CSS/Login,Register.css">
<title>User Registration</title>
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
	max-width: 1050px;
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
}

.Signupinput {
	margin-bottom: 15px;
}

.Signupinput span.details {
	display: block;
	font-weight: 500;
	margin-bottom: 5px;
}

.user-details .Signupinput input {
	width: 100%;
	height: 45px;
	font-size: 16px;
	font-weight: 500;
	border: none;
	outline: none;
	border-bottom: 2px solid rgba(0, 0, 0, 0.2);
	transition: all 0.3s ease;
	box-sizing: border-box;
	padding: 5px;
}

.button {
	margin-top: 20px;
}

.button input {
	width: 100%;
	border-radius: 5px;
	border: none;
	color: #ffffff;
	font-size: 18px;
	font-weight: 500;
	letter-spacing: 1.5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	background-color: #7a29e2;
}

.sign-up-text a {
	color: #7a29e2;
	text-decoration: none;
}

.sign-up-text a:hover {
	text-decoration: underline;
}
</style>
</head>

<body>
	<div class="container">
		<div class="image">
			<img src="../Image/Add a heading (1) (2).png" alt="Registration Image">
		</div>
		<div class="form-container">
			<div class="title">Registration</div>
			<form action="${pageContext.request.contextPath}/registerServlet"
				method="post">
				<div class="user-details">
					<div class="Signupinput">
						<span class="details">Full Name</span> <input type="text"
							name="fullName" placeholder="Enter your name" required>
					</div>
					<div class="Signupinput">
						<span class="details">username</span> <input type="text"
							name="userName" placeholder="Enter your username" required>
					</div>
					<div class="Signupinput">
						<span class="details">Email</span> <input type="email"
							name="email" placeholder="Enter your email" required>
					</div>
					<div class="Signupinput">
						<span class="details">Phone Number</span> <input type="text"
							name="phoneNumber" placeholder="Enter your number" required>
					</div>
					<div class="Signupinput"
						style="margin-bottom: 15px; border-bottom: 2px solid #ccc;">

						<span class="details">Gender</span> <select name="gender" required
							style="padding: 8px 70px 5px 0px; border: none;">

							<option value="" disabled selected>Select your gender</option>

							<option value="male">Male</option>

							<option value="female">Female</option>

							<option value="other">Other</option>

						</select>

					</div>


					<div class="Signupinput">
						<span class="details">Province</span> <input type="text"
							name="province" placeholder="Enter your Province" required>
					</div>
					<div class="Signupinput">
						<span class="details">City</span> <input type="text" name="city"
							placeholder="Enter your City" required>
					</div>
					<div class="Signupinput">
						<span class="details">Street</span> <input type="text"
							name="street" placeholder="Enter your street" required>
					</div>
					<div class="Signupinput">
						<span class="details">Password</span> <input type="password"
							name="password" placeholder="Enter your password" required>
					</div>
					<div class="Signupinput">
						<span class="details">Confirm Password</span> <input
							type="password" name="retypePassword"
							placeholder="Confirm your password" required>
					</div>
				</div>
				<div class="button">
					<input type="submit" value="Register">
				</div>
				<div class="text sign-up-text">
					Already have an account?<a href="login.jsp">Login now</a>
				</div>
			</form>
		</div>
		<%
		String errMsg = (String) request.getAttribute(droneStringView.MESSAGE_ERROR);
		String successMsg = (String) request.getAttribute(droneStringView.MESSAGE_SUCCESS);
		if (errMsg != null) {
			// print
		%>
		<h4 class="error-msg">
			<%
			out.println(errMsg);
			%>
		</h4>
		<%
		}
		if (successMsg != null) { // print
		%>
		<h4 class="success-msg">
			<%
			out.println(successMsg);
			%>
		</h4>
		<%
		}
		%>
	</div>
</body>

</html>