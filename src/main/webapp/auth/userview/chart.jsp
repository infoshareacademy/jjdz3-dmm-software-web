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
<p>Chart</p>
<p>Enter comparison data</p>

<form method="post" action="../userview/chart">
    <p><b>TODO form:</b></p>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>

<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
