<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Templates</title>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
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
        <h3>All Templates</h3>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Template message</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="template" items="${allTemplates}">
                <tr>
                    <td>${template.getId()}</td>
                    <td>${template.getTemplate()}</td>
                    <td>
                        <form action="/templates/${template.getId()}" method="get">
                            <button type="submit"><img src="/images/delete.png"></button>
                        </form>
                    </td>
                    <td>
                        <form action="/editTemplate/${template.getId()}" method="get">
                            <button type="submit"><img src="/images/edit.png"></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="get" action="/addTemplate">
            <button type="submit">Add Template</button>
        </form>
    </sec:authorize>
</div>
</body>
</html>
