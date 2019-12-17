<%@page import="entity.ProductDetail"%>
<%@page import="entity.Product"%>
<%
	session.setAttribute("view", "/product ");
	Product selectedProduct = (Product) session.getAttribute("selectedProduct");
	ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");
%>
<div id="container">
	<div class="one">
		<div class="heading_bg">
			<h2>
				<%=selectedProduct.getName()%>
			</h2>
		</div>
	</div>
	<div class="one-half">
		<div id="amazingslider-wrapper-1"
			style="display: block; position: relative; max-width: 450px; margin: 0px auto 98px;">
			<div id="amazingslider-1"
				style="display: block; position: relative; margin: 0 auto;">
				<ul class="amazingslider-slides" style="display: none;">
					<%
						for (String img : selectedProductDetail.getAllImages()) {
					%>
					<li><img src="images/<%=img%>" alt="" title="" /></li>
					<%
						}
					%>
				</ul>
				<ul class="amazingslider-thumbnails" style="display: none;">
					<%
						for (String img : selectedProductDetail.getAllImages()) {
					%>
					<li><img src="images/<%=img%>" alt="" title="" /></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div class="one-half last">
		<ul id="tabify_menu" class="menu_tab" style="margin: 0;">
			<li><a href="#fane1">Product Details</a></li>
		</ul>
		<div id="fane1" class="tab_content">
			<h3>Technical Details</h3>
			<p>
				<%=selectedProductDetail.getInformation()%>
			</p>
			<h3>Accessories</h3>
			<p><%=selectedProductDetail.getAccessories()%></p>
			<h3>Warranty Strategy</h3>
			<p><%=selectedProductDetail.getGuaranty()%></p>
			<h3>Price</h3>
			<p><%=selectedProduct.getPrice()%>
				$
			</p>
			<p style="text-align: left; margin-right: 16px">
				<a href="<c:url value='addToCart?${selectedProduct.getProductId()}'/>" class="button">Add to cart</a>
				<%
					String name = (String) request.getSession().getAttribute("admin");
					if (name != null) { %>
						<a href="editProduct.jsp" class="button">Edit Product</a>
						<a href="deleteProduct" class="button">Delete Product</a>
				<% 	} %>	
				
			</p>
		</div>
	</div>
	<div style="clear: both; height: 40px"></div>
</div>