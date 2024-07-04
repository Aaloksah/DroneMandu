<%@page import="view.droneStringView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Dao.UserDao"%>
<%@page import="model.droneModel"%>
<%@ page import="Dao.CartDao"%>
<%@ page import="model.CartItem"%>
<%@ page import="java.util.List"%>
<%@ page import="view.droneStringView"%>
<%@ page import="model.productModel"%>

<%
// Create a new instance of UserDao
UserDao userDao = new UserDao();

// Retrieve user name from session
String userName = (String) session.getAttribute(droneStringView.userName);
droneModel currentUser = userDao.returnUser(userName);

// Initialize variables for cookies
String cookieUserName = null;
String cookieSessionId = null;
Cookie[] Cookies = request.getCookies();
if (Cookies != null) {
	for (Cookie cookie : Cookies) {
		if (cookie.getName().equals(droneStringView.USER)) {
	cookieUserName = cookie.getValue();
		}
		if (cookie.getName().equals(droneStringView.JSESSIONID)) {
	cookieSessionId = cookie.getValue();
		}
	}
}

// Retrieve cart items from the database
CartDao cartDao = new CartDao();
List<CartItem> cartItems = cartDao.getAllCartItems();

// Set cart items as attribute in request object
request.setAttribute("cartItems", cartItems);
%>
<link rel="stylesheet" href="../CSS/home.css">
<!-- And so on for other attributes -->

<style>

/*  for pop cart */
.icon {
	cursor: pointer;
}

/* Existing styles */
.header1 {
	margin-right: -700px;
	width: 1000px;
	height: 750px;
	padding: 20px;
	background-color: white;
	overflow-y: auto;
	display: flex;
	flex-direction: column;
	justify-content: center;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 24px; /* Adding border radius */
}

.heading h1 {
	color: #8a2be2;
	font-size: 24px;
	letter-spacing: 2px;
	letter-spacing: 1px;
	margin: 0;
	padding: 20px 0;
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
	font-weight: bold;
}

.second {
	position: relative;
	width: 55vw;
	height: 100vh;
	background: white;
	margin-top: 10px;
	border-radius: 10px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
	align-self: center;
}

.third {
	position: absolute;
	bottom: 0;
	right: 0;
	width: 25vw;
	height: 40vh;
	padding: 10px;
	display: flex;
	flex-direction: column;
	align-self: flex-end;
	margin-right: 37px;
	background: white;
	border-radius: 10px;
}

.item1 {
	font-weight: bold;
	display: flex;
	justify-content: space-between;
	border-bottom: 1px solid #e0e0e0;
	padding: 10px;
	font-size: 16px;
}

.button1 {
	text-align: center;
	margin-top: 10px;
}

.button1 button {
	padding: 10px;
	font-size: 16px;
	font-weight: bold;
	background-color: rgb(139, 53, 200);
	color: white;
	border-radius: 10px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.button1 button:hover {
	background-color: rgb(98, 10, 160);
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.2);
}

.link1 {
	text-decoration: underline;
	color: #8a2be2;
	cursor: pointer;
	transition: text-decoration 0.3s, color 0.3s;
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
}

.link1:hover {
	text-decoration: none;
	color: rgb(92, 4, 154);
}

.wrapper1 {
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn1 {
	background-color: white;
	border: 1px solid gray;
	border-radius: 5px;
	font-size: 16px;
	width: 24px;
	height: 24px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn1:hover {
	background-color: #8a2be2;
}

.display1 {
	padding: 0 10px;
	font-size: 16px;
	text-align: center;
}

.second {
	font-weight: bold;
}

.second table {
	width: 100%;
	border-collapse: collapse;
}

.second th, .second td {
	border-bottom: 1px solid #e0e0e0;
	padding: 16px;
}

.second th:first-child, .second td:first-child {
	text-align: left;
}

.second th:last-child, .second td:last-child {
	text-align: right;
}

.second tr {
	height: 50px;
}

::-webkit-scrollbar {
	width: 8px;
	height: 8px;
}

::-webkit-scrollbar-track {
	background: #f8f0fa;
	border-radius: 4px;
}

::-webkit-scrollbar-thumb {
	background: lightgray;
	border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
	background: rgb(98, 10, 160);
}
</style>

<header>
	<nav>
		<div class="nav-left">
			<img src="../Image/Logo.png" alt="" class="logoimg">
			<ul class="nav-menu">
				<li>DJI</li>
				<li>AIRDATA</li>
				<li>SKYDIO</li>
				<li>FLYABILITY</li>
				<li class="new">AIR 3<span>New</span></li>
			</ul>
		</div>
		<div class="nav-right">
			<div class="search-bar">
				<form id="searchForm" action="/search" method="GET">
					<a id="searchLink" href="#"></a>
					<!-- Font Awesome search icon -->
					<input type="text" id="searchInput" name="q"
						placeholder="Search For Products, brands and more">
				</form>
			</div>
			<div>
				<a href="#" onclick="openPopup()"><i class="fa-regular fa-user"></i></a>
				<p>Profile</p>
			</div>
			<div>
				<a href="#" onclick="togglePopup()"><i
					class="fa-regular fa-heart"></i></a>
				<p>Wishlist</p>
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/LogoutServlet"><i
					class="fa-solid fa-right-from-bracket"></i></a>
				<p>Out</p>
			</div>
		</div>
	</nav>
</header>


<div id="profilePopup" class="popup" style="display: none;">
	<div class="main-frame">
		<span class="close" onclick="closePopup()"
			style="font-weight: bold; font-size: 40px; position: absolute; top: 10px; right: 10px;">&times;</span>
		<div class="basic-info-frame">
			<h2>Basic Information</h2>
			<div class="update-profile">
				<h3 class="update-profile">Update your profile picture and
					basic information.</h3>
			</div>
		</div>

		<div class="third-frame">
			<div class="profile-container">
				<div class="profile-picture"></div>
				<input type="file" id="file-input" style="display: none;">
				<button class="change-photo"
					onclick="document.getElementById('file-input').click()">Change
					Photo</button>
			</div>

			<form action="${pageContext.request.contextPath}/UpdateUserServlet"
				method="post">

				<div class="form-group">
					<label for="full-name">Full Name</label> <input type="text"
						id="full-name" name="fullName" placeholder="Enter your full name"
						value="<%=currentUser.getFullName()%>">

				</div>
				<input type="hidden" name="userName" value="<%=userName%>">
				<div class="form-group">
					<label for="email">Email</label> <input type="email" id="email"
						name="email" placeholder="Enter your email address"
						value="<%=currentUser.getEmail()%>">
				</div>
				<div class="form-group">
					<label for="gender">Gender</label> <select id="gender"
						name="gender">
						<option value="Male"
							<%=currentUser.getGender().equals("Male") ? "selected" : ""%>>Male</option>
						<option value="Female"
							<%=currentUser.getGender().equals("Female") ? "selected" : ""%>>Female</option>
						<option value="Others"
							<%=currentUser.getGender().equals("Others") ? "selected" : ""%>>Others</option>
					</select>
				</div>

				<div class="form-group">
					<label for="province">Province</label> <input type="text"
						id="province" name="province" placeholder="Enter your province"
						value="<%=currentUser.getProvince()%>">
				</div>
				<div class="form-group">
					<label for="phone">Phone Number</label> <input type="tel"
						id="phone" name="phone" placeholder="Enter your phone number"
						value="<%=currentUser.getPhone()%>">
				</div>
				<div class="form-group">
					<label for="city">City</label> <input type="text" id="city"
						name="city" placeholder="Enter your city"
						value="<%=currentUser.getCity()%>">
				</div>
				<div class="form-group">
					<label for="street">Street</label> <input type="text" id="street"
						name="street" placeholder="Enter your street"
						value="<%=currentUser.getProvince()%>">
				</div>
				<div class="save-button">
					<input type="submit" value="Save Changes">
				</div>
			</form>



		</div>

	</div>

</div>

<div id="popup" class="popup" style="display: none;">

	<div class="header1">
		<div class="heading">
			<h1>Your Cart</h1>
		</div>
		<div class="second">
			<table>
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (CartItem item : (List<CartItem>) request.getAttribute("cartItems")) {
						productModel Product = CartDao.getproductnamefromId(item.getProductId());
					%>
					<tr>
						<td><%=Product.getProductName()%></td>
						<td><%=Product.getMrp()%></td>
						<td><%=item.getQuantity()%></td>
						<td><%=Product.getMrp() * item.getQuantity()%></td>
					</tr>
					<%
					}
					%>

				</tbody>
				</tbody>
				</tbody>
			</table>

			<div class="third">
				<!-- Display subtotal, sales tax, and grand total -->
				<div class="item1">
					<span>Subtotal:400000</span> <span>${cartSubtotal}</span>
				</div>
				<div class="item1">
					<span>Sales Tax:Null </span> <span>${salesTax}</span>
				</div>
				<div class="item1">
					<span>Add Coupon Code:Null</span> <a href="#" class="link1">Add
						Coupon</a>
				</div>
				<div class="item1">
					<span>Grand Total:400000</span> <span>${grandTotal}</span>
				</div>
				<div class="button1">
					<button>Check Out</button>
				</div>
				<div class="button1">
					<button onclick="togglePopup()">Close</button>
				</div>
			</div>

			<div class="third">
				<!-- Display subtotal, sales tax, and grand total -->
				<div class="item1">
					<span>Subtotal:</span> <span>${cartSubtotal}</span>
				</div>
				<div class="item1">
					<span>Sales Tax:</span> <span>${salesTax}</span>
				</div>
				<div class="item1">
					<span>Add Coupon Code:</span> <a href="#" class="link1">Add
						Coupon</a>
				</div>
				<div class="item1">
					<span>Grand Total:</span> <span>${grandTotal}</span>
				</div>
				<form id="checkoutForm"
					action="${pageContext.request.contextPath}/MoveToOrderServlet"
					method="POST">
					<div class="button1">
						<button type="submit">Check Out</button>
					</div>
				</form>
				<div class="button1">
					<button onclick="togglePopup()">Close Cart</button>
				</div>
			</div>

		</div>
	</div>
</div>




<script>
	function openPopup() {
		document.getElementById("profilePopup").style.display = "block";
	}

	function closePopup() {
		document.getElementById("profilePopup").style.display = "none";
	}

	// Function to toggle the popup
	function togglePopup() {
		var popup = document.getElementById("popup");
		if (popup.style.display === "none" || popup.style.display === "") {
			popup.style.display = "block";
		} else {
			popup.style.display = "none";
		}
	}

	function updateSearchParam(searchText) {
		var url = new URL(window.location.href);
		url.searchParams.set("search", searchText);
		window.location.href = url.href;
	}

	document.getElementById("searchLink").addEventListener("click",
			function(event) {
				event.preventDefault(); // Prevent the link from navigating
				var searchText = document.getElementById("searchInput").value;
				updateSearchParam(searchText);
			});

	document.getElementById("searchInput")
			.addEventListener(
					"keydown",
					function(event) {
						if (event.key === "Enter") {
							event.preventDefault(); // Prevent the default form submission behavior
							var searchText = document
									.getElementById("searchInput").value;
							updateSearchParam(searchText);
						}
					});
</script>