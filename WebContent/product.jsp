<%@page import="entity.ProductDetail"%>
<%@page import="entity.Product"%>
<%
	session.setAttribute("view", "/product");
	Product selectedProduct = (Product) session.getAttribute("selectedProduct");
	ProductDetail selectedProductDetail = (ProductDetail) session.getAttribute("selectedProductDetail");
	String name = (String) request.getSession().getAttribute("login");
	String deleteProduct = (String) request.getAttribute("deleteProduct");
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
					<li><img src="img/demo/<%=img%>" alt="" title="" /></li>
					<%
						}
					%>
				</ul>
				<ul class="amazingslider-thumbnails" style="display: none;">
					<%
						for (String img : selectedProductDetail.getAllImages()) {
					%>
					<li><img src="img/demo/<%=img%>" alt="" title="" /></li>
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
			<h3>Status</h3>
			<c:if test="${selectedProductDetail.getQuantity() > 0}"><p>In Stock</p></c:if>
			<%	
					if (name != null && name.equals("admin")) { %>
						<h3>Quantity</h3>
						<p>${selectedProductDetail.quantity }</p>
				<% 	} %>
			<c:if test="${selectedProductDetail.getQuantity() < 1}"><p>Out of Stock</p></c:if>
			
			<p style="text-align: left; margin-right: 16px">
				<c:if test="${selectedProductDetail.getQuantity() > 0}"><a href="<c:url value='addToCart?${selectedProduct.getProductId()}'/>" class="button">Add to cart</a></c:if>	
				<%	
					if (name != null && name.equals("admin")) { %>
						<a href="editProduct.jsp" class="button">Edit Product</a>
						<a href="deleteProduct" class="button">Delete Product</a>
				<% 	} %>
				<c:if test="${!empty deleteProduct}"><font color=red>Sorry, This product can't delete!</font></c:if>
				
			</p>
		</div>
	</div>
	<div style="clear: both; height: 40px"></div>
</div>