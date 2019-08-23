<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="button" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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
    <form:form modelAttribute="saveChanges" action="/editBirthday/${id}" method="post">
    <h3>Edit row</h3>
        <h3>${error}</h3>
    <table>
        <tr>
            <td>User name</td>
            <td><input type="text" value="${userName}" name="userName" pattern="^(?!\s*$).+" required></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="date" value="${dateOfBirth}" name="dateOfBirth" required></td>
        </tr>
    </table>
        <p><button type="submit">Save changes</button></p>
    </form:form>
</div>
</body>
</html>
