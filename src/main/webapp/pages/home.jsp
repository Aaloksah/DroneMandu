<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="view.droneStringView"%>
<%@page import="Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="model.productModel"%>

<!DOCTYPE html>
<html>
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
<style>
/* Style the custom select dropdown */
/* Style the custom select dropdown */
/* Style the container to center the dropdown */
.brand-options {
    display: flex;
    justify-content: center;
}

/* Style the custom select dropdown */
.custom-select {
    width: auto;
    max-width: 200px;
    padding: 0.375rem 2.25rem 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
ansition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

/* Style the dropdown arrow */
.custom-select::after {
    position: absolute;
    top: 50%;
    right: 0.75rem;
    transform: translateY(-50%);
    width: 0;
    height: 0;
    content: "";
    border-top: 0.3rem solid;
    border-right: 0.3rem solid transparent;
    border-bottom: 0;
    border-left: 0.3rem solid transparent;
}

/* Style the dropdown options */
.custom-select option {
    background-color: #fff;
    color: #000;
}

/* Style the dropdown when it's open */
.custom-select:focus {
    outline: none;
    border-color: #80bdff;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.Home {
        overflow-x: hidden;
    }

    @keyframes slider {
        0% {
            left: 0;
        }

        20% {
            left: 0;
        }

        25% {
            left: -100%;
        }

        45% {
            left: -100%;
        }

        50% {
            left: -200%;
        }

        70% {
            left: -200%;
        }

        75% {
            left: -300%;
        }

    }

    #slider {
        position: relative;
        overflow: hidden;
        width: 500%;
        margin: 0;
        left: 0;
        animation: 20s slider infinite;
    }

    #slider figure img {
        float: left;
        width: 20%;

    }</style>

<title>Home Page</title>
</head>
<%
String search = request.getParameter("search");
String brand = request.getParameter("brand");
List<productModel> products; // Empty List
ProductDao dao = new ProductDao(); //New Object

if (search != null && !search.isEmpty()) {
	products = dao.getProductsBySearch(search);
} else if (brand != null && !brand.isEmpty()) {
	products = dao.getProductsByCategory(brand);
} else {
	products = dao.getAllProducts();
}
%>

<body>
	<%@ include file="header.jsp"%>

	<section class="Home">
		<div id="slider">
			<figure>
				<img src="../Image/slide1.png" alt="">
				<img src="../Image/slide2.png" alt="" class="imgleft">
				<img src="../Image/slide3.png" alt="" class="imgleft">
				<img src="../Image/slide2.png" alt="" class="imgleft">
			</figure>

		</div>
		<div class="down">
			<i class="fa-solid fa-chevron-down"></i>
		</div>

		<div class="container" id="allProducts">
			<%
			if (search != null && !search.isEmpty()) {
			%>
			<h3 class="product_category">
				Showing results for
				<%=search%></h3>
			<%
			} else {
			%>
			<h3 class="product_category">Products</h3>
			<%
			}
			%>

			<div class="brand-options">
    <select id="brandSelect" value="<%=brand%>" class="custom-select">
        <option value="">All</option>
        <option value="Dji">Dji</option>
        <option value="Parrot">Parrot</option>
        <option value="Apple">Yuneec</option>
        <option value="Mi">Autel Robotics</option>
        <option value="Fifa">Holy Stone</option>
    </select>
</div>


			<div class="product-collection">
				<%
				for (productModel p : products) {
				%>
				<div class="product">
					<div class="p-img">
						<a href="product_info.html"><img src="<%=p.getImageUrl()%>"
							width="70%" alt=""></a>
					</div>

					<div class="p-text">
						<h3 class="product-title product-text"><%=p.getProductName()%></h3>
					</div>

					<p class="price product-text"><%=p.getProductDesc()%></p>
					<p class="price product-text"><span class="old-price"><%=p.getMrp()%></span>&nbsp;<%=p.getDiscountPrice()%></p>

					<form id="productForm" method="post"
						action="${pageContext.request.contextPath}/ProductDetailsServlet">
						<input type="hidden" name="productId"
							value="<%=p.getProductId()%>">
						<button class="buynowbutton"
							onclick="submitForm('<%=p.getProductId()%>')">See
							details</button>
					</form>
				</div>
				<%
				}
				%>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</section>

	<script>
    // Event listener for select element change
    document.getElementById('brandSelect').addEventListener('change', function() {
        var selectedOption = this.value;
        if (selectedOption === 'All') {
            selectedOption = ''; // Set brand parameter to empty string
        }
        var url = new URL(window.location.href);
        url.searchParams.set('brand', selectedOption);
        window.history.pushState({}, '', url);
        window.location.reload(); // Refresh the page
    });
</script>

</body>
</html>