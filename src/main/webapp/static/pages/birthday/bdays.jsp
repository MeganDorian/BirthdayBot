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
        <table class="table">
            <tr>
                <th>Id</th>
                <th>User Name</th>
                <th>Date of Birth</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="birthdays" items="${allBdays}">
                <tr>
                    <td>${birthdays.getId()}</td>
                    <td>${birthdays.getUserName()}</td>
                    <td>${birthdays.getDateOfBirth()}</td>
                    <td>
                        <form action="/bdays/${birthdays.getId()}" method="get">
                            <button type="submit"><img src="/images/delete.png"></button>
                        </form>
                    </td>
                    <td>
                        <form action="/editBirthday" method="post">
                            <button type="submit"><img src="/images/edit.png"></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="get">
            <p><button class="btn btn-default" type="submit" formaction="/add">Add Birthday</button>
        </form>

    </sec:authorize>
</div>
</body>
</html>
