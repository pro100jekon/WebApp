<%@ include file="WEB-INF/jspf/taglib.jspf" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <%@include file="WEB-INF/jspf/style.jspf" %>
    <title><fmt:message key="users"/></title>
    <script>
        $(document).ready(function () {
            $('a[id^="user"]').on('click', function () {
                var id = this.id;
                $.get("editUserAjax", {
                    userId: $("#input" + id).val(),
                    block: $("#blocked" + id).val()
                }, function (user) {
                    $("#p" + id).html(user);
                    var block = $("#blocked" + id).val() == 'true';
                    $("#" + id).text(block ? "Block" : "Unblock");
                    $("#blocked" + id).val(!block);
                });
                return false;
            });
        });
    </script>
</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<main role="main">

    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading"><fmt:message key="all.users"/></h1>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <c:forEach var="i" items="${applicationScope.UserService.allUsers}">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <div class="card-body">
                                <p class="card-text">
                                    Id: ${i.id}<br>
                                    <fmt:message key="f.name"/>: ${i.firstName}<br>
                                    <fmt:message key="l.name"/>: ${i.lastName}<br>
                                    <fmt:message key="email"/>: ${i.email}<br>
                                    <fmt:message key="blocked"/>: <a id="puser${i.id}">${i.blocked}</a><br>
                                    <fmt:message key="verified"/>: ${i.verified}<br>
                                    <fmt:message key="role"/>: ${i.role.value()}<br>
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <input type="hidden" id="inputuser${i.id}" value="${i.id}">
                                    <input type="hidden" id="blockeduser${i.id}" value="${i.blocked}">
                                    <small class="text-capitalize"><a id="user${i.id}"
                                                                      href="#">${i.blocked ? "Unblock" : "Block"}</a></small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</main>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>