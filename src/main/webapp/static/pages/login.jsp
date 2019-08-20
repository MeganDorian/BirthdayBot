<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login page</title>
    <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>--%>
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div class="login-page">
    <div class="form">
<form action="/bdays" method="post" class="login-form">
    <h2>Please login</h2>
    <input type="text" name="userLogin" value="spring">
    <input type="password" name="password" value="12345"><br>
    <button type="submit">Login</button>
</form>
    </div>
</div>
</body>
</html>