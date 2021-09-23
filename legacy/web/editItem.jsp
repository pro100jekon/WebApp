<%@include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title><fmt:message key="edit.item"/> ${item.id}</title>
    <script>
        $(document).ready(function () {
            $('a[id^="i"]').on('click', function () {
                var id = this.id;
                $.get("editItemAjax", {itemId: ${item.id} , url: $("#input" + this.id).val()}, function (res) {
                    var r = res;
                    if (r == 0) {
                        $('#' + id).addClass('disabled');
                        $('#p' + id).html('Successfully deleted');
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <p><fmt:message key="existing.item"/></p>
    <table class="table table-bordered">
        <tr>
            <td>Id</td>
            <td>${item.id}</td>
        </tr>
        <tr>
            <td><fmt:message key="category"/></td>
            <td>${item.category}</td>
        </tr>
        <tr>
            <td><fmt:message key="name"/></td>
            <td>${item.name}</td>
        </tr>
        <tr>
            <td><fmt:message key="size"/></td>
            <td>${item.size}</td>
        </tr>
        <tr>
            <td><fmt:message key="weight"/></td>
            <td>${item.weight}</td>
        </tr>
        <tr>
            <td><fmt:message key="color"/></td>
            <td>${item.color}</td>
        </tr>
        <tr>
            <td><fmt:message key="price"/></td>
            <td>${item.price}</td>
        </tr>
        <tr>
            <td><fmt:message key="date"/></td>
            <td>${item.date}</td>
        </tr>
    </table>
    <table class="table" id="table">
    <c:set var="counter" value="0"/>
        <c:forEach var="i" items="${item.imageURLs}">
            <tr>
                <td><a class="btn btn-primary btn-lg btn-block" href="#" id="i${counter}">Delete</a></td>
                <td><input id="inputi${counter}" type="hidden" value="${i}"/><p id="pi${counter}">${i}</p></td>
            </tr>
            <c:set var="counter" value="${counter + 1}"/>
        </c:forEach>
    </table>

    <p><fmt:message key="will.become"/></p>
    <form action="saveItem" method="post">
        <table class="table table-bordered">
            <tr>
                <td><p class="font-weight-bold">Id</p></td>
                <td>${item.id}</td>
                <input id="itemId" name="itemId" type="hidden" value="${item.id}">
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="category"><fmt:message key="category"/></label></p></td>
                <td><input class="form-control" id="category" name="category"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="name"><fmt:message key="name"/></label></p></td>
                <td><input class="form-control" id="name" name="name"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><fmt:message key="size"/></p></td>
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
                <td><p class="font-weight-bold"><label for="weight"><fmt:message key="weight"/></label></p></td>
                <td><input class="form-control" id="weight" name="weight" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="color"><fmt:message key="color"/></label></p></td>
                <td><input class="form-control" id="color" name="color"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="price"><fmt:message key="price"/></label></p></td>
                <td><input class="form-control" id="price" name="price" type="number"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="date"><fmt:message key="date"/></label></p></td>
                <td><input class="form-control" id="date" type="date" name="date"></td>
            </tr>
            <tr>
                <td><p class="font-weight-bold"><label for="imgPaths"><fmt:message key="img"/></label></p></td>
                <td><input class="form-control" id="imgPaths" name="imgPaths"></td>
            </tr>
        </table>
        <input class="btn btn-primary btn-lg btn-block" type="submit" value="Save">
    </form>
</div>
</body>
</html>
