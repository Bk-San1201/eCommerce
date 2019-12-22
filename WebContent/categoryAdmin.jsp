<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<%
	List<Category> categories = (List<Category>) request.getServletContext().getAttribute("categories");
	List<Integer> quantity = (List<Integer>) request.getAttribute("quantity");
	List<Integer> numtype = (List<Integer>) request.getAttribute("numtype");
	
%>
<div id="container">
	<div class="heading_bg">
		<h2>Category Management</h2>
	</div>
	<table border="0" style="font-size: 13px">
		<th>Image Category</th>
		<th>Category Name</th>
		<th>Number of Product Types</th>
		<th>Quantity</th>
		<th>Remove</th>
		<c:forEach var="category" items="${categories }" varStatus="loop">
			<tr>
				<td><img
					src="${initParam.imgProductPath}${category.getImage()}" width="100"
					height="50"></td>
				<td><a href="<c:url value='category?${category.categoryId}'/>">${category.name }
				</a></td>
				<td>${numtype.get(loop.index)}</td>
				<td>${quantity.get(loop.index)}</td>
				<td>
					<form action="<c:url value='deleteCategory'/>" method="get">
						<input type="hidden" name="categoryId"
							value="${category.categoryId}" />
						<fieldset>
							<input name="Mysubmitted" id="Mysubmitted" value="Remove"
								class="button white" type="submit" style="font-size: 13px;">
						</fieldset>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${!empty notDelete}"><font color="red">Can't Delete This Category</font></c:if>
	<b>ADD CATEGORY: </b>
	<form action="<c:url value='addCategory'/>" method="post">
		<fieldset>
			<label>Category Name<span class="required">*</span></label> <input
				type="text" name="category" id="myPhone" required
				class="text requiredField subject" style="width: 50%">
		</fieldset>
		<div>
			<label for="name_first">Image for Category: </label> <input
				type="file" name="image" size="500" />
		</div>
		<fieldset>
			<input name="Mysubmitted" id="Mysubmitted" value="Add Category"
				class="button white" type="submit" style="font-size: 13px;">
		</fieldset>
	</form>
</div>

<div style="clear: both; height: 40px"></div>
</div>