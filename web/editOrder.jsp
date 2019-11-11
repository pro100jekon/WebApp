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
            return false;
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <p>Existing order</p>
    <table class="table table-bordered">
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
        <p>Will become</p>
    <table class="table table-bordered">
        <form action="saveOrder" method="post">
            <input name="orderId" value="${order.id}" type="hidden">

            <input name="userId" value="${order.user.id}" type="hidden">

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="statusId">Status</label></p>
                </td>
                <td>
                    <select class="custom-select" id="statusId" name="statusId">
                        <option value="none">Remain the same</option>
                        <c:forEach items="${Status.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="deliveryTypeId">Delivery type</label></p>

                </td>
                <td>
                    <select class="custom-select" id="deliveryTypeId" name="deliveryTypeId">
                        <option value="none">Remain the same</option>
                        <c:forEach items="${DeliveryType.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="paymentTypeId">Payment type</label></p>
                </td>
                <td>
                    <select class="custom-select" id="paymentTypeId"name="paymentTypeId">
                        <option value="none">Remain the same</option>
                        <c:forEach items="${PaymentType.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="itemId">Item id</label></p>
                </td>
                <td><input class="form-control" id="itemId" name="itemId" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="quantity">Quantity</label></p></td>
                <td><input class="form-control" id="quantity" name="quantity" type="number"></td>
            </tr>
            <tr>
                <td colspan="2"><a class="btn btn-primary btn-lg btn-block" id="submitItem" href="#">Add item into order</a></td>
            </tr>
            <tr>
                <td colspan="2"><input class="btn btn-primary btn-lg btn-block" type="submit" value="Save"></td>
            </tr>
            <tr id="existingItems"></tr>
        </form>
    </table>
    <table class="table">
        <tr>
            <td><p class="font-weight-bold">Name</p></td>
            <td><p class="font-weight-bold">ID</p></td>
        </tr>
    </table>
    <div class="overflow-auto" style="height: 200px">
        <table class="table">
            <c:forEach items="${applicationScope.ItemService.allItems}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.id}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
