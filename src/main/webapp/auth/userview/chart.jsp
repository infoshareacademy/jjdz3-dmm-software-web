<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chart</title>
</head>
<body>
<p><b>Chart</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Chart display prototype</p>

<img src="../userview/chart" />

<form method="post" action="../userview/chart">
    <p><b>form:</b></p>
    <p>your custom chart name:</p>
    <p><input type="text" name="chartTitle" value="${chartTitle}"/></p>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>

<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
