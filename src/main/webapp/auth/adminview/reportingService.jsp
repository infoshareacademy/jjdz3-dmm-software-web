<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Reporting Service</title>
</head>
<body>
<p><b>Reporting Service</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<p>Task Schedule:</p>
<br>
<tags:tasks contentWrapper="${contentWrapper}"/>
<br>
<form action="../adminview/task" method="post">
    <p><b>Task form:</b></p>
    <p>1. name:
    <input type="text" name="taskName" id="inputForm" value="${taskName}"/></p>
    <p>2. Start Date:
    <input type="text" class="datePicker" name="startDate" value="${startDate}"/></p>
    <p>3. End Date:
    <input type="text" class="datePicker" name="endDate" value="${endDate}"/></p>
    <p>4. Start Delay:
    <input type="text" pattern="[0-9]*" name="startDelay" value="${startDelay}"/></p>
    <p>5. Time Span:
    <input type="text" pattern="[0-9]*" name="timeSpan" value="${timeSpan}"/></p>
    <p>6. Active:
    <input type="checkbox" name="isActive" value="${isActive}"/></p>
    <p>
    <button type="submit">Add Task</button>

</form>
</br>
<br>
<form action="../adminview/emailsender" method="post">
    <button type="submit">Press to send email</button>
</form>
</br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>

