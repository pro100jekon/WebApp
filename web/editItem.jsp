<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kalle
  Date: 08.11.2019
  Time: 02:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
    <title>Edit Item #${item.id}</title>
    <script>
        $(document).ready(function() {
            $('a[id^="i"]').on('click', function() {
                var id = this.id;
                $.get("editItemAjax", {ordinal: $("#input"+id).val()}, function (text) {
                    $("#alert").html(text);
                    $("#" + id).text("Removed");
                });
            });
        });
    </script>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
${error}
<p>Original item</p>
<table border="2">
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
            <td><a href="#" id="i${counter}">Delete</a></td>
            <td id="tdi${counter}">${i}</td>
            <input id="inputi${counter}" type="hidden" value="${counter}"/>
        </tr>
        <c:set var="counter" value="${counter + 1}"/>
    </c:forEach>
</table>
<div id="alert"></div>
<p>Changed item</p>
<form action="saveItem" method="post">
<table border="2">
    <tr>
        <td>Id</td>
        <td>${item.id}</td>
        <input name="itemIdentifier" type="hidden" value="${item.id}">
    </tr>
    <tr>
        <td>Category</td>
        <td><input name="category"></td>
    </tr>
    <tr>
        <td>Name</td>
        <td><input name="name"></td>
    </tr>
    <tr>
        <td>Size</td>
        <td><input name="size1" size="2">x<input name="size2" size="2">x<input name="size3" size="2"></td>
    </tr>
    <tr>
        <td>Weight</td>
        <td><input name="weight"></td>
    </tr>
    <tr>
        <td>Color</td>
        <td><input name="color"></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><input name="price"></td>
    </tr>
    <tr>
        <td>Date of addition</td>
        <td><input name="year" size="2">-<input name="month" size="2">-<input name="day" size="2"></td>
    </tr>
    <tr>
        <td>IMG (delimiter is ;)</td>
        <td><input name="imgPaths"></td>
    </tr>
</table>
    <input type="submit" value="Save">
</form>
</body>
</html>
