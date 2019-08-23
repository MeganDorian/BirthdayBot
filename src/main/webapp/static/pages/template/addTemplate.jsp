<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Add template</title>
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
    <form method="post" action="/addTemplate">
        <h3>Add new template message</h3>
        <h3>${error}</h3>
        <p>Input birthday template:
            <%--<textarea name="message" cols="30" rows="5" required></textarea>--%>
            <input type="text" class="input" required name="message">
        </p>
        <%--Input birthday template:--%>
        <%--<input type="text" name="message" value="">--%>
        <%--<textarea name="message" cols="20" rows="2" wrap="soft">${message}</textarea>--%>
        <button type="submit">Add template</button>
    </form>
</div>

</body>
</html>
