<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investments Info</title>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b>Investments Info</b></p>
<br>
<p>In our service you have available quotation data:</p>
<p>Currencies: <b>${currencyCount}</b> items</p>
<p>Funds: <b>${fundCount}</b> items</p>
<br>
List of investment names:
<c:forEach var="investment" items="${allInvestments}">
    ${investment.name},
</c:forEach>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>