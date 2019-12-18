
<script src="js/jquery.tools.min.js"></script>
<script>
	$(function() {
		$("#prod_nav ul").tabs("#panes > div", {
			effect : 'fade',
			fadeOutSpeed : 400
		});
	});
</script>
<script>
	$(document).ready(function() {
		$(".pane-list li").click(function() {
			window.location = $(this).find("a").attr("href");
			return false;
		});
	});
</script>

    </script>

<c:set var='view' value='/index' scope='session' />

<div id="container">



<h2>Best Seller</h2>
<div id="prod_wrapper">
	<div id="panes">
		<c:forEach var="product" items="${newProducts}">
			<div>
				<img src="${initParam.imgProductPath}${product.getImage()}"
					width="379" height="225" alt="">
				<h5>${product.getName()}</h5>
				<p>${product.getDescriptionDetail()}</p>
				<p style="text-align: right; margin-right: 16px">
					<a href="<c:url
value='product?${product.productId}'/>"
						class="button">More Info</a> <a
						href="<c:url value='addToCart?${product.productId}'/>"
						class="button">Buy Now</a>
				</p>
			</div>
		</c:forEach>
	</div>
	<!-- END tab panes -->
	<br clear="all">
	<!-- navigator -->
	<div id="prod_nav">
		<ul>
			<c:set var="countProduct" scope="request" value="${1}" />
			<c:forEach var="product" items="${newProducts}">
				<li><a href="#${countProduct}"> <img
						src="${initParam.imgProductPath}${product.getImage()}" width="75"
						height="75" alt=""> <strong>${product.getName()}</strong> $
						${product.getPrice()}
				</a></li>
				<c:set var="countProduct" scope="request" value="${countProduct+1}" />
			</c:forEach>
		</ul>
	</div>
	<!-- close navigator -->
</div>
<div style="clear: both"></div>
<c:forEach var="category" items="${categories}">
	<c:if test="${category.categoryId == 4}">
		<div class="one-fourth last">
			<div class="heading_bg">
				<h2>${category.name}</h2>
			</div>
			<a href="<c:url value='category?${category.categoryId}'/>"> <img
				src="${initParam.imgProductPath}${category.getImage()}" width="217"
				height="145" alt="">
			</a>
		</div>
	</c:if>
	<c:if test="${category.categoryId < 4}">
		<div class="one-fourth">
			<div class="heading_bg">
				<h2>${category.name}</h2>
			</div>
			<a href="<c:url value='category?${category.categoryId}'/>"><img
				src="${initParam.imgProductPath}${category.getImage()}" width="217"
				height="145" alt=""> </a>
		</div>
	</c:if>
</c:forEach>
<div style="clear: both; height: 40px"></div>
</div>