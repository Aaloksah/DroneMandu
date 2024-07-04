<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta name="author" content="Nimesh">
<title>About Us</title>
<!-- Linking Exteral css -->
<link rel="Icon" href="../Icons/Camera logo.png">
<!-- Internal CSS -->
<style>

/* -------------------------------------------------- About Us Page  ---------------------------------------------------------------------------- */

/* MAin div for about website description  */
.about_website {
	/* Center the div */
	margin: 0px auto 0 auto;
	width: 90%;
	text-align: center;
}

/* Main div for Feedback form */
.feedback {
	display: flex;
	justify-content: center;
	align-items: center;
}

/* Name of form */
.feedback_form {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	box-shadow: 8px 8px 15px 10px rgba(0, 0, 0, 0.064);
	margin-top: 10px;
	margin-left: 90px;
	width: 100%;
	height: auto;
	padding: 30px 50px;
}

/* Input boxex */
.feedback_form input, .feedback_form textarea {
	padding: 5px;
	margin: 7px 5px;
	border: 1px solid rgba(0, 0, 0, 0.139);
}

/* Text above input boxex */
.feedback_form label {
	color: rgb(0, 0, 0);
	font-weight: 20;
	margin: 8px;
}

/* Submit Button */
.submit_button {
	background-color: #7d2ae8;
	color: white;
	font-size: 15px;
	border: none;
	margin-top: 20px;
	width: 150px;
	height: 40px;
}
/* Internal Css for this page only */
a {
	color: black;
}

.footer {
	/* To move footer little below from form page */
	margin-top: 50px;
}
</style>
</head>

<body>
<%@ include file="header.jsp"%>
	<!-- This is for Description of Website -->
	<div class="about_website">
		<!-- Heading -->
		<h3 style="color: #7d2ae8">About Us</h3>
		<br>
		<!-- Information about website -->
		<p style="opacity: 90%; text-align: justify;">
			<span style="color: #7d2ae8; font-size: 20px; font-weight: bolder;">Drone
				Mandu</span> is an ecommerce website based in Kathmandu, Nepal that
			specializes in selling cameras and camera equipment. The website is
			designed to cater to the needs of photography enthusiasts and
			professionals who are looking to purchase high-quality cameras,
			lenses, tripods, lighting equipment, and other camera accessories. <br>
			<br> The website is built and managed by a team of four members
			who study at Islington College. They are passionate about coding and
			web-designing and have put in a lot of effort to ensure that the
			website is user-friendly, informative, and easy to navigate. <br>
			<br> Customers can browse through the website to find a wide
			range of products from popular brands such as Canon, Nikon, Sony, and
			more. The website also provides detailed product descriptions,
			specifications, and customer reviews to help users make informed
			decisions when purchasing their desired products. <br> <br>

			Hamro Kyamera offers a convenient and secure online shopping
			experience, with easy payment options and fast delivery services. The
			company's focus on customer satisfaction is evident through its
			responsive customer support team that is available to answer any
			queries or concerns that customers may have.
		</p>
	</div>


	<!-- This is feedback Form -->
	<div class="feedback">

		<form name="feedback_form" class="feedback_form">
			<h3 style="color: #7d2ae8;">Give us your valuable feedback :)</h3>
			<br> <label for="f_Name">First Name: </label> <input type="text"
				name="fname" placeholder="Your Lastname"> <label
				for="l_Name">Last Name: </label> <input type="text" name="lname"
				placeholder="Your Firstname"> <label for="Age">Age:
			</label> <input type="number" name="age" placeholder="Your age"> <label
				for="Email">Enter your Email: </label> <input type="email"
				name="email" placeholder="example@gmail.com"> <label
				for="feedback_message">Write your message here:</label>
			<textarea name="message" placeholder="Enter your message here.."
				rows="6" cols="120"></textarea>
			<!-- Submit Button -->
			<input onclick="FormValidation()" class="submit_button" type="button"
				value="Submit" style="cursor: pointer;">
		</form>
	</div>
<%@ include file="footer.jsp"%>
</body>

</html>