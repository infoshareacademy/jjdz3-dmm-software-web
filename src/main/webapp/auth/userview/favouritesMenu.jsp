<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Your Favourites</title>
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<p><b>Your Favourites</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Choose Your collection:</p>
<a href="../userview/favouriterevenue">Investment Revenues</a><br>
<a href="../userview/favouriteindicatorcomparator">Indicator Comparators</a><br>
<a href="../userview/favouriterevenue">Charts</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>