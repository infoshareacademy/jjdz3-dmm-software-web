<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Investment Revenue Result</b></title>
</head>
<body>
<p><b> Analysis: Investment Revenue Result</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>

<tags:analysisResult revenueWrapper="${contentWrapper}"/>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>