<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
</head>
<body>
<p><b>Investments Comparator</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Enter names of 2 investments to compare</p>
<form action="../auth/userview/comparator" method="post">
    <p><input type="text" name="investmentNameA" value="${investmentNameA}"/></p>
    <p><input type="text" name="investmentNameB" value="${investmentNameB}"/></p>
    <button type="submit">Submit</button>
</form>

${investmentStatResultA.name}
${investmentStatResultA.firstQuotation.date}
${investmentStatResultA.firstQuotation.close}
${investmentStatResultA.lastQuotation.date}
${investmentStatResultA.lastQuotation.close}
${investmentStatResultA.maxDeltaPlus.date}
${investmentStatResultA.maxDeltaPlus.close}
${investmentStatResultA.maxDeltaPlus.deltaClose}
${investmentStatResultA.maxDeltaMinus.date}
${investmentStatResultA.maxDeltaMinus.close}
${investmentStatResultA.maxDeltaMinus.deltaClose}

${investmentStatResultA.maxValueQuotation.date}
${investmentStatResultA.maxValueQuotation.close}
${investmentStatResultA.maxValueQuotation.deltaClose}

${investmentStatResultA.minValueQuotation.date}
${investmentStatResultA.minValueQuotation.close}
${investmentStatResultA.minValueQuotation.deltaClose}


<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
