<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%
	session.setAttribute("view", "/category");
	
	Category category = (Category) session.getAttribute("selectedCategory");
%>
<div id="container">
	<div class="one">
		<div class="heading_bg">
			<h2><%=category.getName()%></h2>
		</div>
		<div id="portfolio">
			<div class="portfolio-container" id="columns">
				<ul>
					<%
						List<Product> categoryProducts = (List<Product>) session.getAttribute("categoryProducts");
						for (Product p : categoryProducts) {
					%>
					<li class="one-third">
						<p>
							<a title="<%=p.getName()%>" href="img/demo/<%=p.getImage()%>"
								class="portfolio-item-preview" datarel="prettyPhoto"> <img
								src="img/demo/<%=p.getImage()%>" alt="" width="210" height="145"
								class="portfolio-img pretty-box">
							</a>
						</p>
						<h4>
							<a href="#"><%=p.getName()%></a>
						</h4>
						<p><%=p.getDescription()%></p>
						<p style="text-align: left">
							<a href="product?<%=p.getProductId()%>"
								class="button_small white">See Details &raquo;</a>
						</p>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div style="clear: both; height: 40px"></div>
	</div>
