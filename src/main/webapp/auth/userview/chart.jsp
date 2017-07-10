<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Chart</title>
</head>
<body>
<p><b>Chart</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>TODO: chart display</p>

<img src="../userview/chart" />
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
