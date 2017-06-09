<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<p><b>Menu</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
</br>
<a href="../userview/favourite">Favourites</a>
<br>

<p>Choose your investment analysis:</p>
<a href="../userview/investments">Database info</a><br>
<a href="../userview/investmentrevenue">Investment revenue</a><br>
<a href="../userview/investments">Investment comparator</a><br>
<a href="../userview/investments">90 days Top10</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>
