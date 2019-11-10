<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="WEB-INF/jspf/style.jspf"%>
    <title>Items</title>
    <c:set var="items" value="${applicationScope.ItemService.allItems}" scope="page"/>
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
    <l:adminElement element="${pageScope.items}"/>

</main>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
