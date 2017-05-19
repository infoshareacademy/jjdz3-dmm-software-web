<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><b> JSP TestPage Revenue </b></title>
    <style>
        body {
            background-color: #ffffe6;
        }

        p {
            color: grey;
            font-family: cursive;
            font-size: 90%;
        }
    </style>

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
</html>

</head>
<body>
<p style={font-size:120%}><b>JSP TestPage</b></p>

<form method="post" action="revenue">
    <p><b> Analysis: Investment Revenue</b></p>
    <p>1. <input type="text" name="investmenName" style="width:170px" id="inputForm" placeholder="inv.name, e.g. USD"/></p>
    <p>2. <input type="text" pattern="[0-9]*"  name="capital" style="width:170px" placeholder="inv.capital, e.g. 10000"/></p>
    <p>3. <input type="text" class="datePicker" name="buyDate" readonly='true' style="width:85px" placeholder="buy date"/></p>
    <p>4. <input type="text" class="datePicker" name="sellDate" readonly='true' style="width:85px" placeholder="sell date"/></p>
    <p>   <input type="checkbox" name="isFavourite"/> add to favourites</p>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>

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

<jsp:include page="../partials/formheader.jsp"/>
<jsp:include page="../partials/footer.jsp" />
</body>
</html>