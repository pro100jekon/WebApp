<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form>
    <c:set var="i" value="${i + 1}"/>
    <input name="item${i}" value="${i}">
</form>