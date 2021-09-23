<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<html>
<head>
    <title><fmt:message key="delete.item"/></title>
    <%@include file="WEB-INF/jspf/style.jspf"%>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <table class="table">
        <c:forEach var="item" items="${applicationScope.ItemService.allItems}">
            <tr>
                <td>
                    ${item.id}
                </td>
                <td>
                    ${item.name}
                </td>
                <td>
                    <a href="deleteItem?id=${item.id}"><fmt:message key="delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>