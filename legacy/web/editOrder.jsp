<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ page import="com.epam.smyrnov.entity.order.Status" %>
<%@ page import="com.epam.smyrnov.entity.order.DeliveryType" %>
<%@ page import="com.epam.smyrnov.entity.order.PaymentType" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title><fmt:message key="edit.order"/> ${order.id}</title>
    <script>
        $(document).on("click", "#submitItem", function () {
            $.get("editOrderAjax", {itemId: $("#itemId").val(), quantity: $("#quantity").val()}, function (map) {
                var out = '<td colspan=\"2\"><div style="text-align: center;"><h5>' + map + '</h5></div></td>';
                $("#existingItems").html(out);
            });
            return false;
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <p><fmt:message key="existing.order"/></p>
    <table class="table table-bordered">
        <tr>
            <td>Id</td>
            <td>${order.id}</td>
        </tr>
        <tr>
            <td><fmt:message key="name"/></td>
            <td>${order.user.firstName} ${order.user.lastName}</td>
        </tr>
        <tr>
            <td><fmt:message key="status"/></td>
            <td>${order.status.value()}</td>
        </tr>
        <tr>
            <td><fmt:message key="del.type"/></td>
            <td>${order.deliveryType.value()}</td>
        </tr>
        <tr>
            <td><fmt:message key="pay.type"/></td>
            <td>${order.paymentType.value()}</td>
        </tr>
        <c:forEach var="item" items="${order.itemsAndQuantities}">
            <tr>
                <td>${item.key.name}</td>
                <td>${item.value}</td>
            </tr>
        </c:forEach>
    </table>
    <p><fmt:message key="will.become"/></p>
    <form action="saveOrder" method="post">
        <input name="orderId" value="${order.id}" type="hidden">
        <input name="userId" value="${order.user.id}" type="hidden">
        <table class="table table-bordered">
            <tr>
                <td>
                    <p class="font-weight-bold"><label for="statusId"><fmt:message key="status"/></label></p>
                </td>
                <td>
                    <select class="custom-select" id="statusId" name="statusId">
                        <option value="none"><fmt:message key="remain"/></option>
                        <c:forEach items="${Status.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="deliveryTypeId"><fmt:message key="del.type"/></label></p>
                </td>
                <td>
                    <select class="custom-select" id="deliveryTypeId" name="deliveryTypeId">
                        <option value="none"><fmt:message key="remain"/></option>
                        <c:forEach items="${DeliveryType.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="paymentTypeId"><fmt:message key="pay.type"/></label></p>
                </td>
                <td>
                    <select class="custom-select" id="paymentTypeId" name="paymentTypeId">
                        <option value="none"><fmt:message key="remain"/></option>
                        <c:forEach items="${PaymentType.values()}" var="type">
                            <option value="${type.ordinal()}">${type.value()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <p class="font-weight-bold"><label for="itemId"><fmt:message key="item.id"/></label></p>
                </td>
                <td><input class="form-control" id="itemId" name="itemId" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="quantity"><fmt:message key="quantity"/></label></p></td>
                <td><input class="form-control" id="quantity" name="quantity" type="number"></td>
            </tr>
            <tr>
                <td colspan="2"><a class="btn btn-primary btn-lg btn-block" id="submitItem" href="#"><fmt:message key="add.item"/></a></td>
            </tr>
            <tr>
                <td colspan="2"><input class="btn btn-primary btn-lg btn-block" type="submit" value="Save"></td>
            </tr>
            <tr id="existingItems"></tr>
        </table>
    </form>
    <table class="table">
        <tr>
            <td><p class="font-weight-bold"><fmt:message key="name"/></p></td>
            <td><p class="font-weight-bold">Id</p></td>
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
