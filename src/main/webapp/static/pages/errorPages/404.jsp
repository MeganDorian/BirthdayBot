<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 Not Found</title>
    <link href="<c:url value="/css/styles.css" />" rel="stylesheet">
</head>
<body>
<div>
    <ul class="navbar">
        <li><a href="<c:url value="/logout" />">Logout</a></li>
        <li><a href="<c:url value="/bdays"/>">Birthdays</a></li>
        <sec:authorize access="hasAnyRole('ADMIN')">
            <li><a href="<c:url value="/templates"/>">Templates</a></li>
            <li><a href="<c:url value="/users"/>">Users</a></li>
        </sec:authorize>
    </ul>
</div>
<div>
    <form method="get" action="${page}">
        <h3>Not found</h3>
        <p>Entity with ${id} id not found, please check input values and retry</p>
        <button type="submit">Retry</button>
    </form>

</div>
</body>
</html>
