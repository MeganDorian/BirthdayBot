<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Login page</title>
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div class="login-page">
    <div class="form">
<form action="/bdays" method="post" class="login-form">
    <h2>Please login</h2>
    <input type="text" name="userLogin" value="meh">
    <input type="password" name="password" value="megan"><br>
    <button type="submit">Login</button>
</form>
    </div>
</div>
</body>
</html>