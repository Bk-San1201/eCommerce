<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/column-layout.css">
<style>
button {
	font-family: "QuickSandBook";
	border-radius: 4px;
}
</style>
</head>
<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%
	Set<Product> resultSearch = (Set<Product>) session.getAttribute("resultSearch");
%>
<body>
	<div id="container">
		<h1>Search Result</h1>
		<div class="column-layout"
			style="font-weight: 900; color: red; text-align: center">
			<div class="first-column">Name</div>
			<div class="second-column">Category</div>
			<div class="third-column">Price</div>
			<div class="fourth-column">Description</div>
			<div class="fifth-column">Image</div>
			<div class="sixth-column">Last update</div>
			<div class="seventh-column">Options</div>
		</div>
		<c:forEach var="product" items="${resultSearch}">
			<div class="column-layout">
				<div class="first-column">${product.getName()}</div>
				<div class="second-column">${product.getCategory().getName()}</div>
				<div class="third-column">${product.getPrice()}</div>
				<div class="fourth-column">${product.getDescription()}</div>
				<div class="fifth-column">
					<img src="${initParam.imgProductPath}${product.getImage()}"
						width=100% alt="">
				</div>
				<div class="sixth-column">${product.getLastUpdate()}</div>
				<c:choose>
					<c:when test="${login == 'admin'}">
						<div class="seventh-column">
							<div style="padding: 10px 0px 10px 0px">
								<b><a href="<c:url value='product?${product.productId}'/>">Detail</a></b>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="seventh-column">
							<div style="padding: 10px 0px 10px 0px">
								<b><a href="<c:url value='addToCart?${product.productId}&search=Ok'/>">Add to Cart</a></b>
								<div style="padding: 10px 0px 10px 0px">
								<b>
								<br><a href="<c:url value='product?${product.productId}'/>">Detail</a></b>
								</div>
							</div>
	
						</div>
					</c:otherwise>
				</c:choose>				
			</div>
		</c:forEach>
	</div>
</body>