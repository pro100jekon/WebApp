<%@include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title>Edit Item #${item.id}</title>
    <script>
        $(document).ready(function () {
            $('a[id^="i"]').on('click', function () {
                var id = this.id;
                $.get("editItemAjax", {ordinal: $("#input" + id).val()}, function (text) {
                    var out = '<td colspan=\"2\"><div style=\"text-align: center;\"><p>'+ text + '</p></div></td>';
                    $("#alert").html(text);
                    $("#" + id).text("Removed");
                });
            });
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <p>Existing item</p>
    <table class="table table-bordered">
        <tr>
            <td>Id</td>
            <td>${item.id}</td>
        </tr>
        <tr>
            <td>Category</td>
            <td>${item.category}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${item.name}</td>
        </tr>
        <tr>
            <td>Size</td>
            <td>${item.size}</td>
        </tr>
        <tr>
            <td>Weight</td>
            <td>${item.weight}</td>
        </tr>
        <tr>
            <td>Color</td>
            <td>${item.color}</td>
        </tr>
        <tr>
            <td>Price</td>
            <td>${item.price}</td>
        </tr>
        <tr>
            <td>Date of addition</td>
            <td>${item.date}</td>
        </tr>
        <c:set var="counter" value="0"/>
        <c:forEach var="i" items="${item.imageURLs}">
            <tr>
                <td><a class="btn btn-primary btn-lg btn-block" href="#" id="i${counter}">Delete</a></td>
                <td id="tdi${counter}">${i}</td>
                <input id="inputi${counter}" type="hidden" value="${counter}"/>
            </tr>
            <c:set var="counter" value="${counter + 1}"/>
        </c:forEach>
        <tr id="alert"></tr>
    </table>
    <p>Will become</p>
    <form action="saveItem" method="post">
        <table class="table table-bordered">
            <tr>
                <td><p class="font-weight-bold">Id</p></td>
                <td>${item.id}</td>
                <input id="itemId" name="itemIdentifier" type="hidden" value="${item.id}">
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="category">Category</label></p></td>
                <td><input class="form-control" id="category" name="category"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="name">Name</label></p></td>
                <td><input class="form-control" id="name" name="name"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold">Size</p></td>
                <td>
                    <div class="form-row">
                        <div class="col-2"><input class="form-control" id="size1" name="size1" size="2" type="number"></div>
                        x
                        <div class="col-2"><input class="form-control" id="size2" name="size2" size="2" type="number"></div>
                        x
                        <div class="col-2"><input class="form-control" id="size3" name="size3" size="2" type="number"></div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="weight">Weight</label></p></td>
                <td><input class="form-control" id="weight" name="weight" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="color">Color</label></p></td>
                <td><input class="form-control" id="color" name="color"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="price">Price</label></p></td>
                <td><input class="form-control" id="price" name="price" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="date">Date</label></p></td>
                <td><input class="form-control" id="date" type="date" name="date"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="imgPaths">IMG (delimiter is ;)</label></p></td>
                <td><input class="form-control" id="imgPaths" name="imgPaths"></td>
            </tr>
        </table>
        <input class="btn btn-primary btn-lg btn-block" type="submit" value="Save">
    </form>
</div>
</body>
</html>
