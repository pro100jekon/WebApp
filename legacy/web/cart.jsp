<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ page import="com.epam.smyrnov.entity.order.DeliveryType" %>
<%@ page import="com.epam.smyrnov.entity.order.PaymentType" %>
<html>
<head>
    <title><fmt:message key="cart"/></title>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <table class="table">
        <form action="updateOrder" method="post">
            <c:set var="counter" value="0"/>
            <c:forEach var="item" items="${sessionScope.cart.itemInCart}">
                <tr>
                    <td>
                        <p class="card-text">
                            <a href="itemPage?id=${item.key.id}">${item.key.name}</a>
                        </p>
                    </td>
                    <td>
                        <input name="quantity${counter}" class="form-control" value="${item.value}" type="number">
                    </td>
                    <td>
                        <p class="font-weight-bold">
                                ${item.key.price * item.value} UAH
                        </p>
                    </td>
                    <td>
                        <a href="deleteItemFromCart?id=${item.key.id}"><fmt:message key="delete"/></a>
                    </td>
                </tr>
                <c:set value="${counter + 1}" var="counter"/>
            </c:forEach>
            <tr>
                <td colspan="4"><input class="btn btn-primary btn-lg btn-block" type="submit" value="Update"></td>
            </tr>
        </form>

        <c:choose>
            <c:when test="${user == null or cart.isEmpty() or !user.verified}">
                <tr>
                    <td colspan="4"><input class="btn btn-primary btn-lg btn-block disabled" type="submit"
                                           value="Register order"></td>
                </tr>
            </c:when>
            <c:otherwise>
                <form action="registerOrder" method="post">
                    <tr>
                        <td>
                            <label for="deliveryType"><fmt:message key="del.type"/></label>
                        </td>
                        <td colspan="4">
                            <select id="deliveryType" name="deliveryType" class="form-control">
                                <c:forEach var="deliveryType" items="${DeliveryType.values()}">
                                    <option value="${deliveryType.ordinal()}">${deliveryType.value()}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="paymentType"><fmt:message key="pay.type"/></label>
                        </td>
                        <td colspan="4">
                            <select name="paymentType" id="paymentType" class="form-control">
                                <c:forEach var="paymentType" items="${PaymentType.values()}">
                                    <option value="${paymentType.ordinal()}">${paymentType.value()}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Save the order">
                        </td>
                    </tr>
                </form>
            </c:otherwise>
        </c:choose>
    </table>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
