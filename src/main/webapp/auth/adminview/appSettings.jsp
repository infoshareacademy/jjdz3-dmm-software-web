<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Application settings</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Application settings</a>
    </div>
</nav>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<p>Application settings:</p>
<br>
<p>Data from CSV files:</p>
<p>Total Number of Funds: <b>${fundCount}</b></p>
<p>Total Number of Currencies: <b>${currencyCount}</b></p>
<br>
<form action="../adminview/appsettings" method="post">
    <button type="submit">Press to reload data model csv files...</button>
</form>
</br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>

