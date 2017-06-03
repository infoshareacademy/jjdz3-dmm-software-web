<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
<p><b>Favourite Analysis</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/formheader.jsp"/>
</br>
<p>Investment Revenue:</p>
<c:forEach var="contentWrapper" items="${contentWrappers}">
    <tags:analysisResult contentWrapper="${contentWrapper}"/>
</c:forEach>
</body>
</html>
