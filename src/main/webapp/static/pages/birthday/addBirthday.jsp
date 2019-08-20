<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Birthday</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/styles.css"/>" rel="stylesheet">
</head>
<body>
<div>
    <form:form modelAttribute="newBirthday" method="post" action="/add">
        <h3>${inputError}</h3>
        <p>Input user name: <input type="text" name="userName" value=""></p>
        <p>Input birthday: <input type="date" name="dateOfBirth" value=""></p>
        <p><button type="submit">Add</button></p>
        <%--<a class="btn btn-default" href="/bdays" role="button">Back</a>--%>
    </form:form>
    <form>
        <a class="btn btn-default" href="<c:url value="/bdays"/>" role="button">Back</a>
    </form>
</div>
</body>
</html>
