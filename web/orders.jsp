<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="WEB-INF/jspf/style.jspf"%>
    <title>Orders</title>
    <c:set var="orders" value="${applicationScope.OrderService.allOrders}" scope="page"/>
</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf"%>
<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">Album example</h1>
            <p class="lead text-muted">Here are all orders.</p>
            <p>
                <a href="#" class="btn btn-primary my-2">Main call to action</a>
                <a href="#" class="btn btn-secondary my-2">Secondary action</a>
            </p>
        </div>
    </section>

<%--    <div class="album py-5 bg-light">--%>
<%--        <div class="container">--%>
<%--            <div class="row">--%>
<%--                <c:forEach var="i" items="${pageScope.orders}">--%>
<%--                    <div class="col-md-4">--%>
<%--                        <div class="card mb-4 box-shadow">--%>
<%--                            <div class="card-body">--%>
<%--                                <p class="card-text">--%>
<%--                                    Order id: ${i.id}<br>--%>
<%--                                    User: ${i.user.firstName} ${i.user.lastName}<br>--%>
<%--                                    Email: ${i.user.email}<br>--%>
<%--                                    Payment type: ${i.paymentType.value()}<br>--%>
<%--                                    Delivery type: ${i.deliveryType.value()}<br>--%>
<%--                                    Items:<br>--%>
<%--                                    <c:forEach var="item" items="${i.itemsAndQuantities}">--%>
<%--                                        ${item.key.name}<br>--%>
<%--                                    </c:forEach>--%>
<%--                                </p>--%>
<%--                                <div class="d-flex justify-content-between align-items-center">--%>
<%--                                    <small class="text-capitalize"><a href="orderEdit?orderId=${i.id}">Edit</a></small>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <l:adminElement element="${pageScope.orders}"/>

</main>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
