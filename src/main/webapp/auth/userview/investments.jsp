<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investments Info</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Investments Info</a>
    </div>
</nav>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>In our service you have available quotation data:</p>
<p>Currencies: <b>${currencyCount}</b> items</p>
<p>Funds: <b>${fundCount}</b> items</p>
<br>
<p><h3><b>List of investment names:</b></h3></p>
<c:forEach var="investment" items="${allInvestments}">
    ${investment.name},
</c:forEach>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>