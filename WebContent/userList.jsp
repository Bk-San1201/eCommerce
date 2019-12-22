<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<%
	List<Customer> customers= (List<Customer>) session
			.getAttribute("customers");
%>

<c:set var='view' value='/viewCart' scope='session' />
<div id="container">
	<div class="heading_bg">
		<h2>User List</h2>
	</div>
	<table border="0">
		<th>User Name</th>
		<th>Full Name</th>
		<th>Email</th>
		<th>Phone Number</th>
		<th>Address</th>
		<c:forEach var="customer" items="${customers }"
			varStatus="iter">
			<tr>
				<td><a href="<c:url value='orderDetail?${customerOrder.orderId}'/>">${customer.username }</a></td>
				<td>${customer.name }</td>
				<td>${customer.email }</td>
				<td>${customer.phone}</td>
				<td>${customer.address }, ${customer.cityRegion }</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div style="clear: both; height: 40px"></div>
</div>