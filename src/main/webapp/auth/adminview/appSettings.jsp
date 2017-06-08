<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Application settings</title>
</head>
<body>
<p><b>Application settings</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<p>TODO</p>
<p>content: application settings </p>
<p>csv refresh button </p>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>

