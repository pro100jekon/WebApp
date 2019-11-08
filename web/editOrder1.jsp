<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ page import="com.epam.smyrnov.entity.order.Status" %>
<%@ page import="com.epam.smyrnov.entity.order.DeliveryType" %>
<%@ page import="com.epam.smyrnov.entity.order.PaymentType" %>
<html>
<head>
    <title>SO question 4112686</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#submitItem", function() {
            $.get("ajax", {param1: $("#input1").val(), param2: $("#input2").val()}, function (responseText) {
                $("#somediv").html(responseText);
            });
        });
    </script>
</head>
<body>
<button id="submitItem">press here</button>
<button>press here</button>
<form>
    <input id="input1" name="input1">
    <input id="input2" name="input2">
</form>
<div id="somediv"></div>
</body>
</html>
