<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ page import="com.epam.smyrnov.service.OrderService" %>
<%--<c:set var="ordersOfUser" value="${applicationScope.OrderService.getOrdersByUserId(sessionScope.user.id)}" scope="page" />--%>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf"%>
    <title>Your account</title>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf"%>
Your orders:<br>
<c:forEach var="order" items="${requestScope.orders}">
    ${order}<br>
</c:forEach>
</body>
</html>
