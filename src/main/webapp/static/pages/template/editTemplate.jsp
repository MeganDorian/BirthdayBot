<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Edit Template</title>
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
    <form:form modelAttribute="saveTemplate" action="/editTemplate/${id}" method="post" onsubmit="isEmptyInput()">
        <h3>Edit template</h3>
        <h3>${error}</h3>
        <p>Template message: <textarea id="textArea" name="template" cols="30" rows="5" wrap="soft" required>${template}</textarea></p>
        <p><button>Save changes</button></p>
    </form:form>
</div>
</body>
</html>
