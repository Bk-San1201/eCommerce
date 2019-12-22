<head>
<link rel="stylesheet" type="text/css" media="screen" href="css/column-layout.css">
<style>
button{
	font-family: "QuickSandBook";
	border-radius: 4px;
}
</style>
</head>
<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<body>
<div id="container">
	<h1>Search Result</h1>
	<div class="column-layout" style="font-weight: 900; color: red; text-align: center">
		<div class="first-column">
			Name
		</div>
		<div class="second-column">
			Category
		</div>
		<div class="third-column">
			Price
		</div>
		<div class="fourth-column">
			Description
		</div>
		<div class="fifth-column">
			Image
		</div>
		<div class="sixth-column">
			Last update
		</div>
		<div class="seventh-column">
			Options
		</div>
	</div>
	<c:forEach var="product" items="${resultSearch}">
		<div class="column-layout">
			<div class="first-column">${product.getName()}</div>
			<div class="second-column">${product.getCategory()}</div>
			<div class="third-column">${product.getPrice()}</div>
			<div class="fourth-column">${product.getDescription()}</div>
			<div class="fifth-column">
				<img src="${initParam.imgProductPath}${product.getImage()}"
					width=100% alt="">
			</div>
			<div class="sixth-column">${product.getLastUpdate()}</div>
			<div class="seventh-column">
				<div style="padding: 10px 0px 10px 0px">
				<form action="editProduct" method="post">
					<button class="button-white" style="color:black" type="submit">
						Edit
					</button>
				</form>
				</div>
				<div style="padding: 10px 0px 10px 0px">
				<form action="addProduct" method="post">
					<button class="button-white" style="color: red" type="submit">
						Delete
					</button>
				</form>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
</body>