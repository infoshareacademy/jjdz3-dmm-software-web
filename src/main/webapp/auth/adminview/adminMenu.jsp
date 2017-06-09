<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Administrator Mode</title>
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<p><b>Administrator panel menu</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<p>Application:</p>
<a href="../adminview/appsettings">Settings</a><br>
<br>
<p>User:</p>
<a href="../adminview/usermanagement">Management</a><br>
<a href="../adminview/userstatistics">Statistics</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>