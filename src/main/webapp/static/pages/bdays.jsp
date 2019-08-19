<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Birthdays</title>
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
    <sec:authorize access="isAuthenticated()">
        <h3>All Birthdays</h3>
        <table>
            <tr>
                <th>Id</th>
                <th>User Name</th>
                <th>Date of Birth</th>
            </tr>
            <c:forEach var="birthdays" items="${allBdays}">
                <tr>
                    <td>${birthdays.getId()}</td>
                    <td>${birthdays.getUserName()}</td>
                    <td>${birthdays.getDateOfBirth()}</td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="/add">
            <p><button type="submit">Add Birthday</button>
                <%--<button type="submit">Delete Row</button>--%>
            </p>
        </form>

    </sec:authorize>
</div>
</body>
</html>
