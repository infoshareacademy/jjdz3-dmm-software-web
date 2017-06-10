<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Administrator Mode</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Administrator panel menu</a>
    </div>
</nav>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<p>Application:</p>
<a href="../adminview/appsettings">Settings</a><br>
<br>
<p>User:</p>
<a href="../adminview/usermanagement">Management</a><br>
<a href="../adminview/userstatistics">Statistics</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>