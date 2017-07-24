<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><b>Reporting Service</b></title>

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
<p><b>Reporting Agent Service</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<img src="../webapp/resources/icons/taskAgentSmall.jpg">
<p>Agent tasks (sending e-mail report):</p>
<br>
<tags:tasks contentWrapper="${contentWrapper}"/>
<br>
<form action="../adminview/task" method="post">
    <table style="width:100%">
        <tr>
            <th>Task name</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Delay</th>
            <th>Time span</th>
            <th>Active</th>
            <th></th>
        </tr>
        <tr>
            <th><input type="text" name="taskName" required value="${taskName}"/></th>
            <th><input type="text" class="datePicker" required name="startDate" value="${startDate}"/></th>
            <th><input type="text" class="datePicker" required name="endDate" value="${endDate}"/></th>
            <th><input type="text" pattern="[0-9]*" required name="startDelay" value="${startDelay}"/></th>
            <th><input type="text" pattern="[0-9]*" required name="timeSpan" value="${timeSpan}"/></th>
            <th><input type="checkbox" name="isActive" value="${isActive}"/></th>
            <th><button type="submit">Add Task</button></th>
        </tr>
    </Table>

</form>
</br>
<br>
<form action="../adminview/emailsender" method="post">
    <button type="submit">
    <c:choose>
        <c:when test="${agentIsStarted}">
            STOP Agent job
        </c:when>
        <c:otherwise>
            START Agent job
        </c:otherwise>
    </c:choose>
    </button>
</form>
</br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>

