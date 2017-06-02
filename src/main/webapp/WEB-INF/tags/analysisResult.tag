<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="displayWrapper" type="com.dmmsoft.analyzer.analysis.InvestmentRevenue.DisplayWrapper" required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<p><b>User input data: </b></p>
<p>Investment name: <b>${displayWrapper.criteria.investmentName}</b></p>
<p>Invested capital: <b>${displayWrapper.criteria.investedCapital}</b></p>
<p>Buy date: <b>${displayWrapper.criteria.buyDate}</b></p>
<p>Sell date: <b>${displayWrapper.criteria.sellDate}</b></p>
<p>marked as favourite: <b>${displayWrapper.result.finallyEvaluatedInput.favourite}</b></p>

<tags:systemMessage systemMessage="${displayWrapper.message}"/>
<p><b>User input moderation report:</b></p>
<p>buy date: <b>${displayWrapper.result.finallyEvaluatedInput.buyDate}</b></p>
<p>sell date: <b>${displayWrapper.result.finallyEvaluatedInput.sellDate}</b></p>

</br>
<p><b>Result: </b></p>
<p>Capital Revenue: <b>${displayWrapper.result.capitalRevenueValue}</b> [PLN]</p>
<p>Capital Revenue Delta: <b>${displayWrapper.result.capitalRevenueDeltaPrecentValue}</b> [%]</p>


