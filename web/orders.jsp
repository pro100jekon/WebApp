<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<html>
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
            <h1 class="jumbotron-heading">Here are all orders</h1>
        </div>
    </section>
    <l:adminElement element="${pageScope.orders}"/>
</main>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
