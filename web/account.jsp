<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title><fmt:message key="account"/></title>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <p class="font-weight-bold"><h3>Your orders:</h3></p>
    <c:forEach var="order" items="${requestScope.orders}">
        <h4><fmt:message key="order"/> ${order.id}</h4>
        <table class="table">
            <tr>
                <td>
                    <fmt:message key="pay.type"/>
                </td>
                <td width="500">
                        ${order.paymentType.value()}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="del.type"/>
                </td>
                <td width="500">
                        ${order.deliveryType.value()}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="status"/>
                </td>
                <td width="500">
                    <p class="font-italic">
                            ${order.status.value()}
                    </p>
                </td>
            </tr>
            <c:forEach var="item" items="${order.itemsAndQuantities}">
                <tr>
                    <td>
                            ${item.key.name}
                    </td>
                    <td width="500">
                            ${item.value} <fmt:message key="pcs"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
