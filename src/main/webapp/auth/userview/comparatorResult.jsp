<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
</head>
<body>
<p><b>Investments indicator comparator result</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p>Comparison result</p>

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
