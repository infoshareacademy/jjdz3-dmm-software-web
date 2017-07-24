<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Statistics</title>
</head>
<body>
<tags:appMode  appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>

<p><b>User Statistics</b></p>
<tags:invRevCritTable allInvRevCrit="${allInvRevCrit}"/>
<br>
<br>

<p><b>User activity report csv</b></p>
<a href="../adminview/userstatistics/csv">Download</a>
<br>
<br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
