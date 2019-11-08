<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ page import="com.epam.smyrnov.entity.order.Status" %>
<%@ page import="com.epam.smyrnov.entity.order.DeliveryType" %>
<%@ page import="com.epam.smyrnov.entity.order.PaymentType" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title>Edit order ${order.id}</title>
    <script>
        $(document).on("click", "#submitItem", function () {
            $.get("editOrderAjax", {itemId: $("#itemId").val(), quantity: $("#quantity").val()}, function (map) {
                $("#existingItems").html(map);
            });
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
${error}
<table border="2">
    <tr>
        <td>Id</td>
        <td>${order.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${order.user.firstName} ${order.user.lastName}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${order.status.value()}</td>
    </tr>
    <tr>
        <td>Delivery type</td>
        <td>${order.deliveryType.value()}</td>
    </tr>
    <tr>
        <td>Payment type</td>
        <td>${order.paymentType.value()}</td>
    </tr>
    <c:forEach var="item" items="${order.itemsAndQuantities}">
        <tr>
            <td>${item.key.name}</td>
            <td>${item.value}</td>
        </tr>
    </c:forEach>
</table>
<table border="2">
    <form action="saveOrder" method="post">
        <%--        <tr><td>Id</td><td>${order.id}</td></tr>--%>

        <input name="orderId" value="${order.id}" type="hidden">

        <%--        <tr><td>Name:</td><td>${order.user.firstName} ${order.user.lastName}</td></tr>--%>

        <input name="userId" value="${order.user.id}" type="hidden">

        <tr>
            <td>
                Status
            </td>
            <td>
                <select name="statusId">
                    <c:forEach items="${Status.values()}" var="type">
                        <option value="${type.ordinal()}">${type.value()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Delivery type</td>
            <td>
                <select name="deliveryTypeId">
                    <c:forEach items="${DeliveryType.values()}" var="type">
                        <option value="${type.ordinal()}">${type.value()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Payment type</td>
            <td>
                <select name="paymentTypeId">
                    <c:forEach items="${PaymentType.values()}" var="type">
                        <option value="${type.ordinal()}">${type.value()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Item id:</td>
            <td><input id="itemId" name="itemId"></td>
        </tr>
        <tr>
            <td>Quantity:</td>
            <td><input id="quantity" name="quantity"></td>
        </tr>
        <tr>
            <td></td>
            <td><a id="submitItem" href="#">Add item into order</a></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </form>
</table>
<div id="existingItems"></div>
<table border="2">
    <tr>
        <td>Name</td>
        <td>ID</td>
    </tr>
    <c:forEach items="${applicationScope.ItemService.allItems}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.id}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
