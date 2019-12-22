<%@page import="entity.*"%>
<%@page import="java.util.List"%>
<%
	List<CustomerOrder> customerOrderHistories = (List<CustomerOrder>) session
			.getAttribute("customerOrderHistories");
%>

<c:set var='view' value='/viewCart' scope='session' />
<div id="container">
	<div class="heading_bg">
		<h2>Order History</h2>
	</div>
	<table border="0">
		<th>Confirm Number</th>
		<th>Name Customer</th>
		<th>Username</th>
		<th>Date</th>
		<th>Status</th>
		<c:forEach var="customerOrder" items="${customerOrderHistories}"
			varStatus="iter">
			<c:set var="customer" value="${customerOrder.getCustomer()}" />
			<tr>
				<td><a href="<c:url value='orderDetail?${customerOrder.orderId}'/>">${customerOrder.getConfirmationNumber() }</a></td>
				<td>${customer.name }</td>
				<td>${customer.username }</td>
				<td>${customerOrder.dateCreated }</td>
				<td>
					<c:if test="${customerOrder.status == 2 }">
						<p><b>Delivered</b></p>
					</c:if>
					<c:if test="${customerOrder.status == 0}">
						<p><b>Waiting to Delivery</b></p>						
					</c:if>	
					<c:if test="${customerOrder.status == 1}">
						<p><b>Shipping</b></p>						
					</c:if>	
					<c:if test="${customerOrder.status == 3}">
						<p><b>Cancel Order</b></p>						
					</c:if>	
					<c:if test="${customerOrder.status < 2}">
						<form action="<c:url value='updateStatusOrder'/>" method="post">
						<input type="hidden" name="orderId" value="${customerOrder.orderId}" /> 							
						<select name="status" id="status">
								<option value="">- Status -</option>
								<option value="0">Waiting to Delivery</option>
								<option value="1">Shipping</option>
								<option value="2">Delivered</option>
								<option value="3">Cancel Order</option>
								
							</select>
						<input type="submit" name="submit" value="update" />
						</form>						
					</c:if>	
						
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div style="clear: both; height: 40px"></div>
</div>