<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        $(function () {
            $(document).ready(function () {
                $("#hideBtn").click(function () {
                    $(".Favourite").toggle();
                });
            });
            $(document).ready(function () {
                $("#editBtn").click(function () {
                    $(".editFavourite").toggle();
                });
            });
        });
    </script>
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Favourite Analysis</a>
    </div>
</nav>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
</br>
<p><b>Investment Revenue:</b>
    <button id="hideBtn">hide</button>
    <button id="editBtn">edit</button>
</p>
<div class="Favourite" style="background-color: #e0e0e0">
    <c:forEach var="contentWrapper" items="${contentWrappers}">
        <hr>
        <p><b>${contentWrapper.criteria.userCustomName}</b></p>
        <tags:analysisResult contentWrapper="${contentWrapper}"/>
        <div class="editFavourite" hidden>
            <tags:editFavourite contentWrapper="${contentWrapper}"/>
        </div>
    </c:forEach>
</div>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
