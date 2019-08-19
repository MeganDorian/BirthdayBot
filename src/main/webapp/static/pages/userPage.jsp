<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>User page</title>
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div class="form">
        <h3>User Page</h3>
        <sec:authorize access="isAuthenticated()">
            <p>Your login is: <sec:authentication property="principal.username" /></p>
            <p><a href="<c:url value="/logout" />" role="button">Log out</a></p>
        </sec:authorize>
</div>
</body>
</html>