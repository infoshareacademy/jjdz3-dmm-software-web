<%--
  Created by IntelliJ IDEA.
  User: milo
  Date: 24.05.17
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Favourite Analysis</p>


<p><b>User input data: </b></p>
<p>Investment name: <b>${investmentRevenueCriteria.investmentName}</b></p>
<p>Invested capital: <b>${investmentRevenueCriteria.investedCapital}</b></p>
<p>Buy date: <b>${investmentRevenueCriteria.buyDate}</b></p>
<p>Sell date: <b>${investmentRevenueCriteria.sellDate}</b></p>
<p>marked as favourite: <b>${investmentRevenueResult.finallyEvaluatedInput.favourite}</b></p>
<p>${message}</p>
</br>
<p><b>Result: </b></p>
<p>Capital Revenue: <b>${investmentRevenueResult.capitalRevenueValue}</b> [PLN]</p>
<p>Capital Revenue Delta: <b>${investmentRevenueResult.capitalRevenueDeltaPrecentValue}</b> [%]</p>

<p><b>User input moderation report:</b></p>
<p>buy date: <b>${investmentRevenueResult.finallyEvaluatedInput.buyDate}</b></p>
<p>sell date: <b>${investmentRevenueResult.finallyEvaluatedInput.sellDate}</b></p>



</body>
</html>
