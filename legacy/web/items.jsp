<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <%@include file="WEB-INF/jspf/style.jspf"%>
    <title><fmt:message key="items"/></title>
    <c:set var="items" value="${applicationScope.ItemService.allItems}" scope="page"/>
</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf"%>
<main role="main">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading"><fmt:message key="all.items"/></h1>
            <p>
                <a href="addItem.jsp" class="btn btn-primary my-2"><fmt:message key="add.item"/></a>
                <a href="deleteItem.jsp" class="btn btn-primary my-2"><fmt:message key="delete.item"/></a>
            </p>
        </div>
    </section>
    <l:adminElement element="${pageScope.items}"/>
</main>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
