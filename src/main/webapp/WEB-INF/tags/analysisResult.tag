<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="contentWrapper" type="com.dmmsoft.analyzer.analysis.InvestmentRevenue.ContentWrapper"
              required="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<p><b>User input data: </b></p>
<p>Investment name: <b>${contentWrapper.criteria.investmentName}</b></p>
<p>Invested capital: <b>${contentWrapper.criteria.investedCapital}</b></p>
<p>Buy date: <b>${contentWrapper.criteria.buyDate}</b></p>
<p>Sell date: <b>${contentWrapper.criteria.sellDate}</b></p>
<p>marked as favourite: <b>${contentWrapper.result.finallyEvaluatedInput.favourite}</b></p>

<tags:systemMessage systemMessage="${contentWrapper.message}"/>

<p><b>User input moderation report:</b></p>

<p>buy date: <b>${contentWrapper.result.finallyEvaluatedInput.buyDate}</b></p>
<p>sell date: <b>${contentWrapper.result.finallyEvaluatedInput.sellDate}</b></p>


</br>
<p><b>Result: </b></p>
<p>Capital Revenue: <b>${contentWrapper.result.capitalRevenueValue}</b> [PLN]</p>
<p>Capital Revenue Delta: <b>${contentWrapper.result.capitalRevenueDeltaPrecentValue}</b> [%]</p>
</br>

