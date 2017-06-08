<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<p><b>User Management</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<p>TODO</p>
<p>content: user table</p>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
