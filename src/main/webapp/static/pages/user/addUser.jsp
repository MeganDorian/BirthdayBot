<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New User</title>
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
    <form:form modelAttribute="newUser" action="/addUser" method="post">
        <h3>Add new User</h3>
        <h3>${error}</h3>
        <table>
            <tr>
                <td>User name</td>
                <td><input type="text" maxlength="100" value="${newUser.getUserName()}" name="userName" pattern="^(?!\s*$).+" required></td>
            </tr>
            <tr>
                <td>Login</td>
                <td><input type="text" maxlength="50" value="${newUser.getLogin()}" name="login" pattern="^(?!\s*$).+" required></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" maxlength="100" name="password" value="${password}" pattern="^(?!\s*$).+" required></td>
            </tr>
            <tr>
                <td>Role</td>
                <td>
                    <select name="role" required>
                        <option>User</option>
                        <option>Admin</option>
                    </select>
                </td>
            </tr>
        </table>
        <p><button type="submit">Save changes</button></p>
    </form:form>
</div>
</body>
</html>
