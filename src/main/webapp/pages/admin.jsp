<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="model.productModel"%>
<%@ page import="model.OrderModel"%>
<%@page import="Dao.OrderDao"%>


<%
ProductDao d = new ProductDao();
List<productModel> products = d.getAllProducts();
%>
<%
OrderDao orderDao = new OrderDao();
List<OrderModel> orders = orderDao.getAllOrders();
request.setAttribute("orders", orders);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../CSS/Admin.css">
<title>Admin Page</title>
<body>
	<div class="sidebar">
		<ul class="nav-container">
			<!-- Sidebar links -->
			<li><a href="#" class="active"> <img
					src="../Image/Logo.png" alt="" id="logo_dronemandu">
			</a></li>
			<div class="nav-container-link">
				<li>
					<div class="hover">
						<a href="#" class="nav-link-button"> <img
							src="../Image/Icons (6).png" alt=""> <span class="Nav_link">Dashboard</span>
						</a>
					</div>
				</li>
			</div>



			<div class="nav-container-link">
				<li>
					<div class="hover">
						<a href="#" class="nav-link-button"> <img
							src="../Image/icon_product.png" alt=""> <span
							class="Nav_link">Product</span>
						</a>
					</div>
				</li>
			</div>
			<div class="nav-container-link">
				<li>
					<div class="hover">
						<a href="#" class="nav-link-button"> <img
							src="../Image/ICON_order.png" alt=""> <span
							class="Nav_link">Orders</span>
						</a>
					</div>
				</li>
			</div>

			<div class="nav-container-link logout-link">
				<form action="${pageContext.request.contextPath}/LogoutServlet"
					method="post">
					<button type="submit" class="nav-link-button"
						style="background-color: transparent; border: none; padding: 0; cursor: pointer;">
						<img src="../Image/Logout_logo_sidebar.png" alt=""> <span
							style="color: white; font-size: 16px; font-weight: bold; margin-left: 5px;">Log
							out</span>
					</button>
				</form>
			</div>
		</ul>
	</div>

	<!-- Sections -->
	<section id="dashbord-section">
		<div id="dashbord_header">
			<h2>Dashboard</h2>

			<div class="container_Body">
				<div id="statistics">
					<div class="statistics_section">
						<div id="stat-container">
							<div class="container">
								<h1 class="product">Total Orders</h1>
								<h2 class="num_product">9</h2>
							</div>
							<div id="logo_stats">
								<img src="../Image/Click & Collect.png" alt="">

							</div>

						</div>

					</div>





					<div class="statistics_section">
						<div id="stat-container">
							<div class="container">
								<h1 class="product">Total Product</h1>
								<h2>21</h2>
							</div>



							<div id="logo_stats">
								<img src="../Image/Drone Bottom View (1).png" alt="">

							</div>

						</div>

					</div>


					<div class="statistics_section">
						<div id="stat-container">
							<div class="container">
								<h1 class="product">Total Customer</h1>
								<h2>15</h2>
							</div>



							<div id="logo_stats">
								<img src="../Image/Vector (2).png" alt="">

							</div>

						</div>

					</div>

				</div>
				<div id="image_container" style="margin-top: 100px;">
					<img src="../Image/image 2.png" width="100%" alt="">
				</div>

			</div>
		</div>


	</section>

	<section id="product-section">
		<div id="add-product-section">
			<h2>Product</h2>
			<div class="container_btn">

				<button class="add-product-btn" onclick="openPopup()">Add
					Product</button>
			</div>
		</div>
		<table>
			<thead>

				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Product Name</th>
					<th>Description</th>
					<th>Stock</th>
					<th>Price</th>

					<th>Actions</th>






				</tr>
			</thead>
			<tbody>
				<%
				for (productModel p : products) {
				%>
				<tr>

					<td><img src="<%=p.getImageUrl()%>" alt="Product Image"
						style="height: 60px; width: 60px;"></td>
					<td><%=p.getProductName()%></td>
					<td><%=p.getProductDesc()%></td>

					<td><%=p.getStock()%></td>

					<td><strong><%=p.getMrp()%></strong></td>



					<td>
						<form
							action="${pageContext.request.contextPath}/DeleteProductServlet"
							method="post">
							<input type="hidden" name="productId"
								value="<%=p.getProductId()%>">
							<button type="submit">Delete</button>
						</form>

						<button type="button"
							onclick="openUpdatePopup('<%=p.getProductId()%>', '<%=p.getProductName()%>', '<%=p.getProductDesc()%>', '<%=p.getCategory()%>', '<%=p.getMrp()%>', '<%=p.getDiscountPrice()%>', '<%=p.getStock()%>', '<%=p.getImageUrl()%>')">Update</button>


					</td>
				</tr>
				<%
				}
				%>
			</tbody>


			<div id="popup" class="popup">
				<div class="popup-content">
					<span class="close" onclick="closePopup()">&times;</span>
					<h2>Add Product</h2>
					<form action="${pageContext.request.contextPath}/AddProductServlet"
						method="post" class="productForm" id="productForm">
						<div class="input-box">
							<input type="text" class="add-product_input" name="productName"
								placeholder="Enter product name" required>
						</div>
						<div class="input-box">
							<input type="text" class="add-product_input" name="productDesc"
								placeholder="Enter product Description" required>
						</div>
						<div class="input-box">
							<select class="add-product_input" name="productCategory" required>
								<option value="">Select product brand</option>
								<option value="DJI">DJI</option>
								<option value="Parrot">Parrot</option>
								<option value="Yuneec">Yuneec</option>
								<option value="Autel Robotics">Autel Robotics</option>
								<option value="Holy Stone">Holy Stone</option>
							</select>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input" name="mrp"
								placeholder="Enter product mrp" required>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input" name="discount"
								placeholder="Enter discount amount" required>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input"
								name="productStock" placeholder="Enter total stock available"
								required>
						</div>
						<div class="input-box">
							<input type="text" class="add-product_input"
								name="productImageUrl" placeholder="Enter URL of image" required>
						</div>
						<div class="button">
							<button type="submit" class="add-product-button">Add
								Product</button>
							<button type="button" class="cancel-button"
								onclick="closePopup()">Cancel</button>
						</div>
					</form>
				</div>
			</div>

			<!-- 
update 
 -->
			<div id="update-popup" class="popup">
				<div class="popup-content">
					<span class="close" onclick="closeUpdatePopup()">&times;</span>
					<h2>Update Product</h2>
					<form
						action="${pageContext.request.contextPath}/UpdateProductServlet"
						method="post" class="productForm" id="updateProductForm">
						<input type="hidden" id="updateProductId" name="productId">
						<div class="input-box">
							<input type="text" class="add-product_input" name="productName"
								id="updateProductName" placeholder="Enter product name" required>
						</div>
						<div class="input-box">
							<input type="text" class="add-product_input" name="productDesc"
								id="updateProductDesc" placeholder="Enter product Description"
								required>
						</div>
						<div class="input-box">
							<select class="add-product_input" name="productCategory"
								id="updateProductCategory" required>
								<option value="">Select product brand</option>
								<option value="DJI">DJI</option>
								<option value="Parrot">Parrot</option>
								<option value="Yuneec">Yuneec</option>
								<option value="Autel Robotics">Autel Robotics</option>
								<option value="Holy Stone">Holy Stone</option>
							</select>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input" name="mrp"
								id="updateProductMrp" placeholder="Enter product mrp" required>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input" name="discount"
								id="updateProductDiscount" placeholder="Enter discount amount"
								required>
						</div>
						<div class="input-box">
							<input type="number" class="add-product_input"
								name="productStock" id="updateProductStock"
								placeholder="Enter total stock available" required>
						</div>
						<div class="input-box">
							<input type="text" class="add-product_input"
								name="productImageUrl" id="updateProductImageUrl"
								placeholder="Enter URL of image" required>
						</div>
						<div class="button">
							<button type="submit" class="add-product-button">Update
								Product</button>
							<button type="button" class="cancel-button"
								onclick="closeUpdatePopup()">Cancel</button>
						</div>
					</form>
				</div>
			</div>





			</tbody>
		</table>

	</section>

	<section id="order-section">
		<h2>Order</h2>
		<table id="order-table">
			<thead>
				<tr>
					<th>Order Id</th>
					<th>User Id</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Total Amount</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (OrderModel order : orders) {
				%>
				<tr>
					<td><%=order.getOrderId()%></td>
					<td><%=order.getUserId()%></td>
					<td><%=order.getOrderDate()%></td>
					<td>
						<p class="status <%=order.getStatus().toLowerCase()%>"><%=order.getStatus()%></p>
					</td>
					<td><strong>$<%=order.getTotalAmount()%></strong></td>
					<%
					if ("Pending".equals(order.getStatus())) {
					%>
					<td>
						<div class="container_delevered_btn">
							<form
								action="${pageContext.request.contextPath}/OrderDetailsServlet"
								method="post">
								<input type="hidden" name="orderId"
									value="<%=order.getOrderId()%>">
								<button class="delevered-product-btn" type="submit">Mark
									as Delivered</button>
							</form>
						</div>
					</td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</section>

	<div id="popup" class="popup">
		<div class="popup-content">
			<span class="close" onclick="closePopup()">&times;</span>
			<h2>Add Product</h2>
			<form class="productForm" id="productForm">
				<div class="input-box">
					<input type="text" class="add-product_input" name="productName"
						placeholder="Enter product name" required>
				</div>


				<div class="input-box">
					<input type="text" class="add-product_input" name="productCategory"
						placeholder="Enter product category" required>
				</div>

				<div class="input-box">
					<input type="number" class="add-product_input" name="productStock"
						placeholder="Enter product stock" required>
				</div>
				<div class="input-box">
					<input type="text" class="add-product_input" name="productName"
						placeholder="Enter Url of image" required>
				</div>
				<div class="add_product_container"></div>
			</form>
		</div>

	</div>
	<!-- JavaScript to make sidebar functional -->
	<script>
        function openPopup() {
            document.getElementById("popup").style.display = "block";
        }

        function closePopup() {
            document.getElementById("popup").style.display = "none";
        }

       // Function to set display property of sections
    function setSectionDisplay(sectionId, displayValue) {
        document.getElementById(sectionId).style.display = displayValue;
    }

    // Function to show dashboard section by default
    function showDashboard() {
        setSectionDisplay('dashbord-section', 'block');
        setSectionDisplay('product-section', 'none');
        setSectionDisplay('order-section', 'none');
    }

    // Call the function to show dashboard section by default
    showDashboard();

    // Get all section elements
    const sections = document.querySelectorAll('section');

    // Get all sidebar links
    const sidebarLinks = document.querySelectorAll('.nav-container-link');

    // Loop through sidebar links
    sidebarLinks.forEach((link, index) => {
        // Add click event listener to each link
        link.addEventListener('click', () => {
            // Remove 'active' class from all links
            sidebarLinks.forEach((l) => l.classList.remove('active'));
            // Add 'active' class to the clicked link
            link.classList.add('active');
            // Hide all sections
            sections.forEach((section) => section.style.display = 'none');
            // Show the corresponding section based on the index
            sections[index].style.display = 'block';
        });
    });
    
    
    
 // Function to open the update popup
    function openUpdatePopup(productId, productName, productDesc, productCategory, productMrp, productDiscount, productStock, productImageUrl) {
        document.getElementById('updateProductId').value = productId;
        document.getElementById('updateProductName').value = productName;
        document.getElementById('updateProductDesc').value = productDesc;
        document.getElementById('updateProductCategory').value = productCategory;
        document.getElementById('updateProductMrp').value = productMrp;
        document.getElementById('updateProductDiscount').value = productDiscount;
        document.getElementById('updateProductStock').value = productStock;
        document.getElementById('updateProductImageUrl').value = productImageUrl;
        document.getElementById('update-popup').style.display = 'block';
    }

    // Function to close the update popup
    function closeUpdatePopup() {
        document.getElementById('update-popup').style.display = 'none';
    }

    </script>

</body>

</html>


