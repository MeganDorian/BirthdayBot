<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Add template</title>
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
    <script src="/js/inputCheck.js"></script>
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
    <form method="post" action="/addTemplate" onsubmit="isEmptyInput()">
        <h3>Add new template message</h3>
        <h3 id="errorText">${error}</h3>
        <p>Input birthday template:
            <textarea id="textArea" name="message" cols="30" rows="3" required></textarea>
        </p>
        <button type="submit">Add template</button>
    </form>
</div>

</body>
</html>
