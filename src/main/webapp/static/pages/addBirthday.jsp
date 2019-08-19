<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Birthday</title>
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
</head>
<body>
<div>
    <form:form modelAttribute="newBirthday" method="post" action="/add">
        <h3>${inputError}</h3>
        <p>Input user name: <input type="text" name="userName" value=""></p>
        <p>Input birthday: <input type="date" class="form-control" name="dateOfBirth" value=""></p>
        <button type="submit">Add</button>
    </form:form>
</div>
</body>
</html>
