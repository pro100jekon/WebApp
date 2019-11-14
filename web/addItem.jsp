<%@ include file="WEB-INF/jspf/taglib.jspf"%>
<html>
<head>
    <title><fmt:message key="add.item"/></title>
    <%@include file="WEB-INF/jspf/style.jspf"%>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <form action="saveItem" method="post">
        <table class="table table-bordered">
            <tr>
                <td><p class="font-weight-bold"><label for="category"><fmt:message key="category"/></label></p></td>
                <td><input class="form-control" id="category" name="category" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="name"><fmt:message key="name"/></label></p></td>
                <td><input class="form-control" id="name" name="name" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold">Size</p></td>
                <td>
                    <div class="form-row">
                        <div class="col-2"><input class="form-control" id="size1" name="size1" size="2" type="number" required></div>
                        x
                        <div class="col-2"><input class="form-control" id="size2" name="size2" size="2" type="number" required></div>
                        x
                        <div class="col-2"><input class="form-control" id="size3" name="size3" size="2" type="number" required></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="weight"><fmt:message key="weight"/></label></p></td>
                <td><input class="form-control" id="weight" name="weight" type="number" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="color"><fmt:message key="color"/></label></p></td>
                <td><input class="form-control" id="color" name="color" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="price"><fmt:message key="price"/></label></p></td>
                <td><input class="form-control" id="price" name="price" type="number" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="date"><fmt:message key="date"/></label></p></td>
                <td><input class="form-control" id="date" type="date" name="date" required></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="imgPaths"><fmt:message key="img"/></label></p></td>
                <td><input class="form-control" id="imgPaths" name="imgPaths" required></td>
            </tr>
        </table>
        <input class="btn btn-primary btn-lg btn-block" type="submit" value="Save">
    </form>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
