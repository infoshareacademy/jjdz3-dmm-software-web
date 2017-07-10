<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Task Form</b></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datePicker").datepicker({
                dateFormat: "yy-mm-dd",
                changeMonth: true,
                changeYear: true,
            }).val()
        });
    </script>
</head>
<body>
<p><b>Task Form</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>


<form method="post" action="../adminview/task">
    <p><b>Task form:</b></p>
    <p>id:${contentWrapper.id}</p>
    <p><input name="id" value="${contentWrapper.id}" hidden/></p>
    <p>1. name:</p>
    <p><input type="text" required name="taskName" value="${contentWrapper.taskName}"/></p>
    <p>2. Start Date:</p>
    <p><input type="text" required class="datePicker" name="startDate" value="${contentWrapper.startDate}"/></p>
    <p>3. End Date:</p>
    <p><input type="text" required class="datePicker" name="endDate" value="${contentWrapper.endDate}"/></p>
    <p>4. Start Delay:</p>
    <p><input type="text" required pattern="[0-9]*" name="startDelay" value="${contentWrapper.startDelay}"/></p>
    <p>5. Time Span:</p>
    <p><input type="text" required pattern="[0-9]*" name="timeSpan" value="${contentWrapper.timeSpan}"/></p>
    <p>6. Active:</p>
    <p><input type="checkbox" clas name="isActive" value="${contentWrapper.active}"/></p>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>
<c:choose>
    <c:when test="${violations.size() >= 1}">
        <p style="color: red">Number of violations: ${violations.size()}</p>
        <ul>
            <c:forEach var="violation" items="${violations}">
                <li style="color: red">${violation.message}</li>
            </c:forEach>
        </ul>
    </c:when>
</c:choose>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
