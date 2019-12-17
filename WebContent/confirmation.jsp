<%-- 
    Document   : confirmation
    Created on : Dec 15, 2019, 12:09:14 AM
    Author     : Zayt
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
    </head>
    <body>
        <div id="container">
            <div class="one">
                <div class="heading_bg">
                    <h2>Confirmation</h2>
                </div>
                <p id="confirmationText">
                    <strong>Succeed</strong>
                    <br>
                    Confirmation Number: 
                    <strong>${orderRecord.confirmationNumber}</strong>
                    <br>
                    Thanks for your purchase!
                </p>
            </div>
            <div class="two-third">
                <div class="heading_bg">
                    <h3>Order Summary</h3>
                </div>
                <table>
                    <th>Product </th>
                    <th></th>
                    <th>Price</th>
                        <c:forEach var="orderedProduct" items="${orderedProducts}"
                                   varStatus="iter">
                        <tr>
                            <td>
                                ${products[iter.index].name}
                            </td>
                            <td>
                                ${orderedProduct.quantity}
                            </td>
                            <td>
                                ${products[iter.index].price
                                                           * orderedProduct.quantity}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="2"><strong>Surcharge :</strong></td>
                        <td>
                            ${initParam.deliveryFee}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><strong>Total :</strong></td>
                        <td>
                            ${orderRecord.amount}</td>
                    </tr>
                    <tr>
                        <td colspan="3"><strong>Date Process :</strong>
                                ${orderRecord.dateCreated}
                    </tr>
                </table>
            </div>
            <div class="sidebar_right">
                <div class="heading_bg">
                    <h3>Delivery Address </h3>
                </div>
                <table>
                    <tr>
                        <td colspan="3">
                            ${customer.name}
                            <br>
                            ${customer.address}
                            <br>
                            City: ${customer.cityRegion}
                            <br>
                            <hr>
                            <strong>Email :</strong>
                            ${customer.email}
                            <br>
                            <strong>Phone :</strong>
                            ${customer.phone}
                        </td>
                    </tr>
                </table>
            </div>
            <div style="clear:both; height: 40px"></div>
        </div>
    </body>
</html>
