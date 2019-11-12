<%@include file="WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <title>Register your new account</title>
    <%@include file="WEB-INF/jspf/style.jspf" %>

</head>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container">
    <form class="px-4 py-3" action="registerAccount" method="post"
          oninput='confirmPassword.setCustomValidity(confirmPassword.value !== password.value ? "Passwords do not match." : "")'>
        <div class="form-group">
            <label for="email">Email address</label>
            <input name="email" type="email" class="form-control" id="email"
                   placeholder="email@example.com" required>
            <div class="invalid-feedback">Email is required</div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input name="password" type="password" class="form-control"
                   id="password" placeholder="Password" minlength="6" required>
            <div class="invalid-feedback">Password is required</div>

        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm password</label>
            <input name="confirmPassword" type="password" class="form-control"
                   id="confirmPassword" placeholder="Password" required>
            <div class="invalid-feedback">Invalid password</div>

        </div>
        <div class="form-group">
            <label for="firstName">First name</label>
            <input name="firstName" type="text" class="form-control" id="firstName"
                   placeholder="Your first name" required>
            <div class="invalid-feedback">Your first name is required</div>

        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <input name="lastName" type="text" class="form-control"
                   id="lastName" placeholder="Your last name" required>
            <div class="invalid-feedback">Your last name is required</div>
        </div>
        <button type="submit" class="btn btn-primary btn-lg btn-block">Sign up</button>
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
