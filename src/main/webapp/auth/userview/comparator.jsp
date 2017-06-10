<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<nav class = "navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a href="#" class="navbar-brand">Investments indicator comparator</a>
    </div>
</nav>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>

<jsp:include page="../../partials/backToUserMenu.jsp"/>

<p><h4><b>Enter names of 2 investments to compare</b></h4></p>
<form action="../userview/comparator" method="post">
    <p>Investment A<input type="text" name="investmentNameA" value="${investmentNameA} " required/></p>
    <p>Investment B<input type="text" name="investmentNameB" value="${investmentNameB} " required/></p>
    <button type="submit">Submit</button>
</form>
<br>
<table style="width:100%">
    <tr align="left">
        <th></th>
        <th></th>
        <th>Investment A</th>
        <th>Investment B</th>
    </tr>
    <tr align="left">
        <th>No</th>
        <th>Indicator</th>
        <th>${investmentStatResultA.name}</th>
        <th>${investmentStatResultB.name}</th>
    </tr>
    <tr align="left">
        <th>1</th>
        <th>First Quotation: date, value</th>
        <th>${investmentStatResultA.firstQuotation.date}, ${investmentStatResultA.firstQuotation.close}</th>
        <th>${investmentStatResultB.firstQuotation.date}, ${investmentStatResultB.firstQuotation.close}</th>
    </tr>
    <tr align="left">
        <th>2</th>
        <th>Last Quotation: date, value</th>
        <th>${investmentStatResultA.lastQuotation.date}, ${investmentStatResultA.lastQuotation.close}</th>
        <th>${investmentStatResultB.lastQuotation.date}, ${investmentStatResultB.lastQuotation.close}</th>
    </tr>
    <tr align="left">
        <th>3</th>
        <th>Max Delta plus: date, nominal value , % value</th>
        <th>${investmentStatResultA.maxDeltaPlus.date}, ${investmentStatResultA.maxDeltaPlus.close}, ${investmentStatResultA.maxDeltaPlus.deltaClose}</th>
        <th>${investmentStatResultB.maxDeltaPlus.date}, ${investmentStatResultB.maxDeltaPlus.close}, ${investmentStatResultB.maxDeltaPlus.deltaClose}</th>
    </tr>
    <tr align="left">
        <th>4</th>
        <th>Max Delta minus: date, nominal value , % value</th>
        <th>${investmentStatResultA.maxDeltaMinus.date}, ${investmentStatResultA.maxDeltaMinus.close}, ${investmentStatResultA.maxDeltaMinus.deltaClose}</th>
        <th>${investmentStatResultB.maxDeltaMinus.date}, ${investmentStatResultB.maxDeltaMinus.close}, ${investmentStatResultB.maxDeltaMinus.deltaClose}</th>
    </tr>
    <tr align="left">
        <th>5</th>
        <th>Max Value Quotation: date, nominal value , % value</th>
        <th>${investmentStatResultA.maxValueQuotation.date}, ${investmentStatResultA.maxValueQuotation.close}, ${investmentStatResultA.maxValueQuotation.deltaClose}</th>
        <th>${investmentStatResultB.maxValueQuotation.date}, ${investmentStatResultB.maxValueQuotation.close}, ${investmentStatResultB.maxValueQuotation.deltaClose}</th>
    </tr>
    <tr align="left">
        <th>6</th>
        <th>Min Value Quotation: date, nominal value , % value</th>
        <th>${investmentStatResultA.minValueQuotation.date}, ${investmentStatResultA.minValueQuotation.close}, ${investmentStatResultA.minValueQuotation.deltaClose}</th>
        <th>${investmentStatResultB.minValueQuotation.date}, ${investmentStatResultB.minValueQuotation.close}, ${investmentStatResultB.minValueQuotation.deltaClose}</th>
    </tr>
</table>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
