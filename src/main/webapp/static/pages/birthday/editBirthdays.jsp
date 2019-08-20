<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Birthday table</title>
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
    <h3>Edit row</h3>
    <table>
        <tr>
            <td>User name</td>
            <td><input type="text" value="${userName}"></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="date" value="${dateOfBirth}" ></td>
        </tr>
    </table>
    <form method="get" action="/save">
        <button type="submit">Save changes</button>
    </form>
</div>
</body>
</html>
