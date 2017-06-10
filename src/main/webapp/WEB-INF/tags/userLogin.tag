<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user" type="com.dmmsoft.user.User" required="true" %>
<html>
<head>
    <jsp:include page="../../partials/meta.jsp" />
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<form method="post" action="../logout">
    <h4> <input type="submit" class="btn btn-danger" value="Logout">  ${user.login} </h4>
</form>
</body>
</html>