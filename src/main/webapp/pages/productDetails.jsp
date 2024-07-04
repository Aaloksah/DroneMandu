<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Dao.ProductDao"%>
<%@page import="model.productModel"%>


<%
int productId = Integer.parseInt(request.getParameter("productId"));
ProductDao dao = new ProductDao();
productModel product = dao.getProductById(productId);
%>

<%@page import="view.droneStringView"%>
<%
String userSession = (String) session.getAttribute(droneStringView.userName);

String cookieUsername = null;
String cookieSessionID = null;
Cookie[] cookies = request.getCookies();
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals(droneStringView.USER))
	cookieUsername = cookie.getValue();
		if (cookie.getName().equals(droneStringView.JSESSIONID))
	cookieSessionID = cookie.getValue();
	}
}
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<title>Product Details</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Poppins", sans-serif;
}
/* -------------------------------------------------------------- Css for product info page ------------------------------------------------------------------------------- */

/* For main product */
.current-product {
	width: 70%;
}

.current-product {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	margin-top: 50px;
	margin-left: 60px;
	height: 1100px;
}

.current-product-details {
	display: flex;
	justify-content: flex-start;
	align-items: center;
	margin-left: 0px;
}

.product_img {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 400px;
	border: 2px solid rgba(0, 0, 0, 0.253);
	padding: 25px;
	margin-top: -8px;
	cursor: pointer;
}

.product_details {
	margin-left: 30px;
}

.product_details ul li {
	list-style: none;
	display: inline-block;
}

.product_price {
	padding: 10px 0px 0px 0px;
	line-height: 12px;
}

.product_features {
	line-height: 40px;
}

.product_detailed_info {
	display: flex;
	width: 800px;
}

/* Important feature of products */
.product_features {
	width: 600px;
}

/* For other suggested and reated products */
.related-products {
	width: 20%;
	height: auto;
	position: absolute;
	top: 75px;
	right: 70px;
	border: 2px solid rgba(128, 128, 128, 0.578);
	margin-top: 10px;
}

/* For individual product of related products section */
.related-product {
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	margin: 20px 10px;
}

/* Main specs section */
.product-major-specs {
	margin-top: 40px;
	text-align: left;
}

.product-major-specs table {
	margin-top: 10px;
	width: 600px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 6px;
}

/* Detailed specs of product */
.product-all-specs ul {
	margin-left: 15px;
	margin-top: 20px;
}

/* Product image pop when hover */
.img_onhover {
	transition: all .2s ease-in-out;
}

.img_onhover:hover {
	transform: scale(1.1);
}

/* Name of suggested products */
.related-product h4 {
	color: black;
}

/* Price of suggested products */
.related-product p {
	color: #7d2ae8;
}
</style>

<body>
<%@ include file="header.jsp"%>
	<!-- This is for Product details -->

	<div class="current-product">
		<a href="home.jsp"><button onclick="increaseNumber()" class="buynowbutton"
			style="color: rgb(255, 255, 255); width: 100px; cursor: pointer; background-color: #7d2ae8;">Back
			to Home</button> </a>
		<div class="current-product-details">
			<!-- Image of products -->
			<div class="product_img">
				<img src="<%=product.getImageUrl()%>" width="350px" alt="Cam Image"
					onmouseover="setNewImage();" onmouseout="setOldImage();">
			</div>

			<div class="product_details">
				<!-- Name of products -->
				<h2><%=product.getProductName()%></h2>
				<br>
				<hr style="opacity: 50%;">
				<!-- Price and discount percentage of products -->
				<br>
				<p class="product_price" style="font-size: 18px;">
					<span class="old-price">Rs.<%=product.getMrp()%></span>&nbsp; Rs.<%=product.getDiscountPrice()%>
					<span class="offer">&nbsp; &nbsp;Discount<br></span> <br>
					<span style="color: rgba(0, 0, 0, 0.658); font-size: 14px;">Only
						<%=product.getStock()%> left in stock - order soon
					</span>
				</p>
				<br> <br> <br>
				<!-- Buy and Add to card button -->
				<form action="${pageContext.request.contextPath}/AddToCart"
					method="post">
					<input type="number" name="quantity"
						<%if (product.getStock() <= 0) {%> value="0" <%} else {%>
						value="1" <%}%> min="0" max="<%=product.getStock()%>"> <input
						type="hidden" name="userName" value="<%=userSession%>"> <input
						type="hidden" name="price" value="<%=product.getDiscountPrice()%>">
					<input type="hidden" name="productId" value="<%=productId%>">

					<%
					if (product.getStock() <= 0) {
					%>
					<ul>
						<li><button class="buynowbutton"
								style="color: grey; width: 200px; cursor: not-allowed; background-color: #7d2ae8; opacity: 0.5;"
								disabled>Out of Stock</button></li>
					</ul>
					<%
					} else {
					%>
					<ul>
						<li><button type="submit" class="buynowbutton"
								style="color: rgb(255, 255, 255); width: 200px; cursor: pointer; background-color: #7d2ae8; opacity: 1;">Add
								to Cart</button></li>
					</ul>
					<%
					}
					%>
				</form>


				<br>
				<hr style="opacity: 50%;">
				<br>
				<!-- Product main features -->
				<ul class="product_features">
					<li>• <%=product.getProductDesc()%></li>
					<li>• Exceptional 15-stop dynamic range captures nuanced</li>
					<li>• High-fidelity 14-bit uncompressed RAW format</li>
					<li>• Wide ISO range from 50 to 102,400</li>
					<li>• Rapid-fire shooting capabilities: Up to 10fps</li>
				</ul>
			</div>

		</div>
		<!-- Product detailed featuers -->
		<div class="product-specs">
			<div class="product-major-specs">
				<h3>
					Product details of
					<%=product.getProductName()%>
				</h3>
				<table>
					<tr>
						<td>High-resolution imaging</td>
						<td>150,000 pixel metering sensor</td>
					</tr>
					<tr>
						<td>Dual Pixel RAW</td>
						<td>GPS</td>
					</tr>
					<tr>
						<td>Dual Pixel CMOS AF</td>
						<td>Wi-Fi / NFC</td>
					</tr>
					<tr>
						<td>7 frame per second</td>
						<td>EOS Movie 4K + Full HD</td>
					</tr>
				</table>

			</div>
			<br>
			<hr style="opacity: 50%;">
			<div class="product-all-specs">
				<ul>
					<li style="font-weight: bolder;">Image Sensor</li>
					<li>Type</li>
					<li>Effective Pixels</li>
					<li>Approx. 30.4 megapixels</li>
					<li>Total Pixels</li>
					<li>Low-Pass Filter</li>
					<li>Built-in/Fixed</li>
					<li>Sensor Cleaning</li>
					<li style="font-weight: bolder;">Image</li>
					<li>Type</li>
					<li>DIGIC 6+</li>
					<li style="font-weight: bolder;">Lens</li>
					<li>Lens Mount</li>
					<li>EF (excludes EF-S / EF-M lenses)</li>
					<li>Focal length</li>
					<li>Equivalent to 1.0x the focal length of the lens</li>


				</ul>
			</div>
		</div>

	</div>

	<!-- This is for Related Product Section -->

	<div class="related-products">
		<div class="related-product">
			<div>
				<img src="../Image/Screenshot 2024-04-19 091649.png"
					class="img_onhover" width="150px" alt="Camera">
				<!-- Name and price of suggested product -->
				<h4>SkyVista Pro</h4>
				<p>Rs. 200,000</p>
			</div>
		</div>
		<div class="related-product">
			<div>
				<img src="../Image/Screenshot 2024-04-19 091606.png"
					class="img_onhover" width="150px" alt="Camera">
				<!-- Name and price of suggested product -->
				<h4>AeroSwift X</h4>
				<p>Rs. 150,000</p>
			</div>
		</div>
		<div class="related-product">
			<div>
				<img src="../Image/Screenshot 2024-04-19 091631.png"
					class="img_onhover" width="150px" alt="Camera">
				<!-- Name and price of suggested product -->
				<h4>HorizonWing Elite</h4>
				<p>Rs.250,000</p>
			</div>
		</div>
		<div class="related-product">
			<div>
				<img src="../Image/Screenshot 2024-04-19 091631.png"
					class="img_onhover" width="150px" alt="Camera">
				<!-- Name and price of suggested product -->
				<h4>NimbusEye HD</h4>
				<p>Rs. 80,000</p>
			</div>
		</div>
		<div class="related-product">
			<div>
				<img src="../Image/Screenshot 2024-04-19 091631.png"
					class="img_onhover" width="150px" alt="Camera">
				<!-- Name and price of suggested product -->
				<h4>SkyProwler Evo</h4>
				<p>Rs. 130,000</p>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp"%>
</body>

</html>