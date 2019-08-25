<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>400 Error</title>
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div>
    <ul class="navbar">
        <li><a href="<c:url value="/logout" />">Logout</a></li>
        <li><a href="<c:url value="/bdays"/>">Birthdays</a></li>
        <li><a href="<c:url value="/templates"/>">Templates</a></li>
    </ul>
</div>
<div>
    <form method="get" action="${page}">
        <h3>Bad request</h3>
        <p>Please return to the main page</p>
        <button type="submit">Back</button>
    </form>

</div>
</body>
</html>
