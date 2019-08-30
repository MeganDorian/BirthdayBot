<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
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
    <sec:authentication property="principal.username" var="userName"/>
        <sec:authorize access="isAuthenticated()">
            <h3>Users</h3>
            <h4>${error}</h4>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>User Name</th>
                    <th>Role</th>
                    <th>Last activity</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="users" items="${users}">
                    <tr>
                        <td>${users.getId()}</td>
                        <td>${users.getUserName()}</td>
                        <td>${users.getRole()}</td>
                        <td>${users.getLastActivity()}</td>
                        <td>
                            <c:if test="${userName!=users.getUserName()}">
                            <form action="/users/${users.getId()}" method="get">
                                <button type="submit"><img src="/images/delete.png"></button>
                            </form>
                            </c:if>
                        </td>
                        <td>
                            <form action="/editUser/${users.getId()}" method="get">
                                <button type="submit"><img src="/images/edit.png"></button>
                            </form>
                        </td>
                        <td >
                            <c:if test="${userName!=users.getUserName()}">
                                <form action="/status/${users.getId()}" method="get">
                                    <button type="submit">${users.getStatusButton(users.getActive())}</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form method="get" action="/addUser">
                <button type="submit">Add User</button>
            </form>
        </sec:authorize>
</div>
</body>
</html>
