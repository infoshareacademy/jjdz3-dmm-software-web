<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="../../partials/meta.jsp" />
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">DMM - Menu</a>
    </div>
</nav>
<p><h4 class="form-signin-heading"><tags:userLogin user="${sessionScope.authenticatedUser}"/></h4></p>

<p><a class="btn btn-success" type="button" href="../userview/favourite">Favourites</a></p>
    <div class="list-group">
        <a href="#" class="list-group-item active">Choose your investment analysis:</a>
        <a href="../userview/investments" class="list-group-item">Database info</a>
        <a href="../userview/investmentrevenue" class="list-group-item">Investment revenue</a>
        <a href="../userview/comparator" class="list-group-item">Investment comparator</a>
        <a href="../userview/investments" class="list-group-item">90 days Top10</a>
    </div>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>
