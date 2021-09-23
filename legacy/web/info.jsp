<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="info"/></title>
    <%@ include file="WEB-INF/jspf/style.jspf" %>
</head>
<body>
<%@ include file="WEB-INF/jspf/header.jspf" %>
${param.message}
${message}
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
