<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
</head>
<body>
<p><b>Investments indicator comparator</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Enter names of 2 investments to compare</p>
<form action="../userview/comparator" method="post">
    <p>Investment A<input type="text" name="investmentNameA" value="${investmentNameA}" required/></p>
    <p>Investment B<input type="text" name="investmentNameB" value="${investmentNameB}" required/></p>
    <button type="submit">Submit</button>
</form>
<br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
