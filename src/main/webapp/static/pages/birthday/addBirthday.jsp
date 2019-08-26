<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Add Birthday</title>
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
    
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
    <form:form modelAttribute="newBirthday" method="post" action="/add">
        <h3>Input required values</h3>
        <h3>${inputError}</h3>
        <table>
            <tr>
                <td>Input user name: </td>
                <td><input type="text" name="userName" pattern="^(?!\s*$).+" required></td>
            </tr>
            <tr>
                <td>Input birthday: </td>
                <td><input type="date" name="dateOfBirth" required></td>
            </tr>
        </table>
            <button type="submit">Add</button>
    </form:form>
</div>
</body>
</html>
