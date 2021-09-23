<%@include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title><fmt:message key="register.new.acc"/></title>
    <%@include file="WEB-INF/jspf/style.jspf" %>

</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <form class="px-4 py-3" action="registerAccount" method="post"
          oninput='confirmPassword.setCustomValidity(confirmPassword.value !== password.value ? "Passwords do not match." : "")'>
        <div class="form-group">
            <label for="email"><fmt:message key="email"/></label>
            <input name="email" type="email" class="form-control" id="email"
                   placeholder="email@example.com" required>
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="password"/></label>
            <input name="password" type="password" class="form-control"
                   id="password" placeholder="Password" minlength="6" required>

        </div>
        <div class="form-group">
            <label for="confirmPassword"><fmt:message key="confirm.password"/></label>
            <input name="confirmPassword" type="password" class="form-control"
                   id="confirmPassword" placeholder="Password" required>
        </div>
        <div class="form-group">
            <label for="firstName"><fmt:message key="f.name"/></label>
            <input name="firstName" type="text" class="form-control" id="firstName"
                   placeholder="Your first name" required>
        </div>
        <div class="form-group">
            <label for="lastName"><fmt:message key="l.name"/>name</label>
            <input name="lastName" type="text" class="form-control"
                   id="lastName" placeholder="Your last name" required>
        </div>
        <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="sign.up"/></button>
    </form>
</div>
<%@include file="WEB-INF/jspf/footer.jspf" %>
<script>
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
