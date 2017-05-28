<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="../partials/meta.jsp" />
</head>
<body>
<p>Menu</p>
<a href="../analyzer/favourite">Favourites</a>
<br>
<p>Choose your investment analysis:</p>
<a href="../analyzer/investmentrevenue">Revenue</a><br>
<a href="../analyzer/investmentrevenue">Statistics</a><br>
<a href="../analyzer/investmentrevenue">Quotation Fluctuations</a><br>
<a href="../analyzer/investmentrevenue">Top10</a><br>
<br>
<jsp:include page="../partials/footer.jsp" />
</body>
</html>
