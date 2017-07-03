<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
</head>
<body>
<p><b>Investments indicator comparator result</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Comparison result</p>
<tags:indicatorResult contentWrapper="${contentWrapper}"/>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
